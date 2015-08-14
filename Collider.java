import greenfoot.*;
import java.awt.Color;

/**
 * Collider class. Used by move(Direction, int amount) to determine
 * where collision checking should be performed.
 */

public class Collider extends ActorExtra
{   
    int w, h;
    Actor object;
    
    /**
     * Creates a new Collider with the associated Actor.
     */    
    public Collider(int width, int height, Actor object) {
        w = width;
        h = height;
        this.object = object;
        
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(new Color(200, 255, 255, 150));
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, width - 1, height - 1);
        setImage(img);
    }
    
    public Actor getOneIntersecting(java.lang.Class cls) {
        return getOneIntersectingObject(cls);
    }
    
    public Actor getAssociatedActor() {
        return object;
    }
    
    public Point topLeft() {
        return new Point(getX() - w/2, getY() - h/2);
    }
    
    public Point topRight() {        
        return new Point(getX() + w/2, getY() - h/2);
    }
    
    public Point bottomLeft() {        
        return new Point(getX() - w/2, getY() + h/2);
    }
    
    public Point bottomRight() {
        return new Point(getX() + w/2, getY() + h/2);
    }
}
