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
    Status happiness;
    CustomerState currentState;
    CustomerBusyState currentBusyState = CustomerBusyState.NONE;
    
    ShopItemType nextItem;

    Target t;

    public Customer(Target initial) {
        GreenfootImage img = new GreenfootImage(30, 50);
        img.setColor(Color.RED);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0,0, 39, 99);
        setImage(img);
        
        stateImg = new AttachedImage(new Point(0, -10));
        setState(CustomerState.BUYING);
        attach(stateImg);
        
        happiness =  new Status(100);
        attach(happiness.getStatBar(), new Point(0, -40));

        t = initial;

        setCollider(new Collider(28, 18, this), new Point(0, 15));
    }

    /*
     * Ensures that the StateImage is also updated.
     */
    public void setState(CustomerState s) {
        currentState = s;
        currentBusyState = CustomerBusyState.NONE;
        
        stateImg.setImage(new GreenfootImage(currentState.getAbbrev(), 20,
                Color.WHITE, new Color(0,0,0,0)));
    }

    public CustomerState getState() {
        return currentState;
    }
    
    public ShopItemType getNextItem() {
        return nextItem;
    }
    
    public void startWaiting() {
        currentBusyState = CustomerBusyState.WAITING;
    }
    
    public void startInteracting() {
        currentBusyState = CustomerBusyState.INTERACTING;
    }
    
    public boolean isBusy() {
        return (currentBusyState != CustomerBusyState.NONE);
    }
    
    public boolean isWaiting() {
        return (currentBusyState == CustomerBusyState.WAITING);
    }

    public void act() 
    {
        if(t != null) {
            moveTowards(t, walkSpeed);
            if(!isBusy() && intersects(t)) t = t.getNext(this);
        }

        InteractZone iz = (InteractZone) getOneIntersectingObject(InteractZone.class);
        if(iz != null) iz.interact(this);
        
        if(isWaiting()) happiness.decrement();
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
                    if(c.isBusy()) startWaiting();
                }
        
        return isWall || isCustomer;
    }
}

