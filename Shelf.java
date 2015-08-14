import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Shelf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shelf extends Actor
{
    ShopItem[] items;
    int spacing = 10;
    int remaining = 0;

    public Shelf(int size) {
        items = new ShopItem[size];

        GreenfootImage img = new GreenfootImage(size*30 + spacing, 10);
        img.setColor(Color.BLACK);
        img.fill();
        img.setColor(Color.WHITE);
        img.drawRect(0,0, img.getWidth()-1, img.getHeight()-1);
        setImage(img);
    }

    @Override
    public void addedToWorld(World w) {
        fill();
    }

    public void fill() {
        for(int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = new ShopItem();
            }
            int bw = items[i].getImage().getWidth();
            int bh = items[i].getImage().getHeight();

            //finds x co-ord of the centre of the box
            int x = getX() - getImage().getWidth()/2 + spacing + bw/2 + (bw + spacing) * i;
            int y = getY() - getImage().getHeight()/2 - bh/2;
            getWorld().addObject(items[i], x,y);
        }

        remaining = items.length;
    }
    
    public boolean hasItems() {
        return remaining > 0;
    }
    
    public boolean isFull() {
        return remaining == items.length;
    }

    public ShopItem takeOne() {
        if(remaining == 0) return null;
        else {
            ShopItem taken = items[remaining - 1];
            items[remaining - 1] = null;
            remaining--;
            
            getWorld().removeObject(taken);

            return taken;
        }
    }
}
