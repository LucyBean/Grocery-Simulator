import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

public class NoWalkZone extends Zone
{
    public NoWalkZone(int width, int height) {
        super(width, height, Color.GREEN);
        
        new Collider(width, height, this);
    }
    
    @Override
    public void addedToWorld(World w) {
        getWorld().addObject(new Collider(width, height, this), getX(), getY());
    }
}