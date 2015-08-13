import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends CollidingActor
{
    String up = ",";
    String down = "o";
    String left = "a";
    String right = "e";
    
    final int walkSpeed = 4;
    
    final Collider collider = new Collider(40, 20, this);
    
    public Player() {
        GreenfootImage img = new GreenfootImage(40, 60);
        img.setColor(Color.BLUE);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0,0, 39, 99);
        setImage(img);
        
        setCollider(collider, new Point(0, 20));
    }
    
    public void act() 
    {
        if(Greenfoot.isKeyDown(up)) {
            move(Direction.UP, walkSpeed);
        }
        if(Greenfoot.isKeyDown(down)) {
            move(Direction.DOWN, walkSpeed);
        }
        if(Greenfoot.isKeyDown(left)) {
            move(Direction.LEFT, walkSpeed);
        }
        if(Greenfoot.isKeyDown(right)) {
            move(Direction.RIGHT, walkSpeed);
        }
        
        InteractZone iz = (InteractZone) getOneIntersectingObject(InteractZone.class);
        if(iz != null) iz.interact(this);
    }    
    
    @Override
    public boolean isObjectAtEdge(Direction d) {
        return isObjectAtEdge(d, NoWalkZone.class);
    }
}
