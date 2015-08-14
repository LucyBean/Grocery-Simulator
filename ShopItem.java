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
    ShopItemType t;
    public ShopItem(ShopItemType type) {
        t = type;
        setImage(t.getImage());
    }
}
