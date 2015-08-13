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
    
    AttachedImage stateImg;
    Mood happiness;
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
        
        stateImg = new AttachedImage(new Point(0,-10));
        setState(CustomerState.BUYING);
        attach(stateImg);
        
        StatBar happinessBar = new StatBar(new Point(0, -40), 100);
        happiness =  new Mood(100, happinessBar);
        attach(happinessBar);

        t = initial;

        setCollider(new Collider(28, 18, this), new Point(0, 15));
    }

    /*
     * Ensures that the StateImage is also updated.
     */
    public void setState(CustomerState s) {
        currentState = s;
        waiting = false;
        
        stateImg.setImage(new GreenfootImage(currentState.getAbbrev(), 20,
                Color.WHITE, new Color(0,0,0,0)));
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
            if(intersects(t)) t = t.getNext(currentState);
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

class Mood {
    int moodVal;
    final int max;
    StatBar bar;
    
    public Mood(int max, StatBar bar) {
        this(max, max, bar);
    }
    
    public Mood(int max, int init, StatBar bar) {
        this.max = max;
        this.bar = bar;
        setVal(init);
    }
    
    public int getVal() {
        return moodVal;
    }
    
    public void setVal(int val) {
        if(val < 0) val = 0;
        if(val > max) val = max;
        moodVal = val;
        
        bar.update(moodVal);
    }
    
    public void decrement() {
        if(moodVal > 0) moodVal--;
        bar.update(moodVal);
    }
    
    public void increment() {
        if(moodVal < max) moodVal++;
        bar.update(moodVal);
    }
}