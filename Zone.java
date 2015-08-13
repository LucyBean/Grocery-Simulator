import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Zone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zone extends ActorExtra
{
    int width;
    int height;
    
    public Zone(int width, int height, Color c) {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(c);
        img.fill();
        setImage(img);
        
        this.width = width;
        this.height = height;
    }
}
