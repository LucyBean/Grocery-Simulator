import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Iterator;
/**
 * Write a description of class WorldExtra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class WorldExtra extends World
{
    int numberOfCustomers;
    
    /**
     * Constructor for objects of class WorldExtra.
     * 
     */
    public WorldExtra()
    {            
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false);
    }
    
    public void addObject(Actor add, Actor at) {
        super.addObject(add, at.getX(), at.getY());
    }
    
    public void scroll(Direction d, int amount) {
        List<Actor> slidable = getObjects(Slidable.class);
        Iterator<Actor> i = slidable.iterator();
        
        while(i.hasNext()) {
            Actor a = i.next();
            a.setLocation(a.getX() + d.getX() * amount, a.getY() + d.getY() * amount);
        }
    }
    
    public void addObject(Customer c, int x, int y) {
        numberOfCustomers++;
        super.addObject(c,x,y);
    }
    
    public void removeObject(CollidingActor a) {
        if(a instanceof Customer) numberOfCustomers--;
        
        Iterator<AttachedImage> i = a.getImages().iterator();
        while(i.hasNext()) {
            removeObject(i.next());
        }
        
        Collider c = a.getCollider();
        if (c != null) super.removeObject(c);
        super.removeObject(a);
    }
    
    public void removeObject(ActorExtra a) {
        Iterator<AttachedImage> i = a.getImages().iterator();
        while(i.hasNext()) {
            removeObject(i.next());
        }
        super.removeObject(a);
    }
    
    public void addObject(Countdown c) {
        addObject(c, -10, 0);
    }
    
    public int getCustomerCount() {
        return numberOfCustomers;
    }
}
