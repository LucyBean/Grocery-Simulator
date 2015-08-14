import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TillZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TillZone extends InteractZone
{
    /**
     * Creates a new TillZone for an associated PayingZone.
     * @param payZone Customers in this PayingZone will be able to pay for items.
     */    
    public TillZone() {
        super(20, 20);
    }
    
    public boolean manned() {
        return (isTouching(Player.class));
    }
}
