import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class InteractZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class InteractZone extends Zone
{
    public InteractZone(int width, int height) {
        super(width, height, Color.ORANGE);
    }
    
    public void interact(Player p) {
        
    }
    
    public void interact(Customer c) {
        
    }
}
