import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class ShopItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopItem extends Actor
{
    public ShopItem() {
        GreenfootImage img = new GreenfootImage(20, 20);
        img.setColor(Color.MAGENTA);
        img.fill();
        img.setColor(Color.WHITE);
        img.drawRect(0,0,19,19);
        setImage(img);
    }
}
