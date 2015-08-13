import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer extends CollidingActor
{    
    final int walkSpeed = 2;
    
    GreenfootImage stateImg;
    Status happiness;
    CustomerState currentState;
    
    boolean waiting;

    Target t;

    public Customer(Target initial) {
        GreenfootImage img = new GreenfootImage(30, 50);
        img.setColor(Color.RED);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0,0, 39, 99);
        setImage(img);
        
        setState(CustomerState.BUYING);
        attach(stateImg, new Point(0,-10));
        
        StatBar happinessBar = new StatBar(100);
        happiness =  new Status(100, happinessBar);
        attach(happinessBar, new Point(0, -40));

        t = initial;

        setCollider(new Collider(28, 18, this), new Point(0, 15));
    }

    /*
     * Ensures that the StateImage is also updated.
     */
    public void setState(CustomerState s) {
        currentState = s;
        waiting = false;
        
        stateImg = new GreenfootImage(currentState.getAbbrev(), 20,
                Color.WHITE, new Color(0,0,0,0));
    }

    public CustomerState getState() {
        return currentState;
    }
    
    public void startWaiting() {
        waiting = true;
    }
    
    public boolean isWaiting() {
        return waiting;
    }

    public void act() 
    {
        if(t != null) {
            moveTowards(t, walkSpeed);
            if(!isWaiting() && intersects(t)) t = t.getNext(currentState);
        }

        InteractZone iz = (InteractZone) getOneIntersectingObject(InteractZone.class);
        if(iz != null) iz.interact(this);
        
        if(waiting) happiness.decrement();
    }
    
    /*
     * Overriden ensure that Customers collide only with NoWalkZones, and other Customers
     * with the same state (unless the Customer is leaving).
     * This allows for queueing behaviours.
     */
    @Override
    public boolean isObjectAtEdge(Direction d) {
        boolean isWall = isObjectAtEdge(d, NoWalkZone.class);
        Customer c = (Customer) getOneObjectAtEdge(d, Customer.class);
        boolean isCustomer = false;
        if(c != null
                && currentState != CustomerState.LEAVING
                && c.getState() == currentState) {
                    isCustomer = true;
                    if(c.isWaiting()) startWaiting();
                }
        
        return isWall || isCustomer;
    }
}

