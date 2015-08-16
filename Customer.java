import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.Queue;
import java.util.LinkedList;
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

    Queue<ShopItemType> shoppingList;

    Target t;

    /**
     * Creates a new Customer with an initial target.
     */
    public Customer(Target initial) {
        setImg();
        attachImages();
        
        t = initial;
        shoppingList = selectShoppingList();
        setCollider(new Collider(28, 18, this), new Point(0, 15));
    }
    
    private void setImg() {
        GreenfootImage img = new GreenfootImage(30, 50);
        img.setColor(Color.RED);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawRect(0,0, 39, 99);
        setImage(img);
        
    }
    
    private void attachImages() {
        stateImg = new AttachedImage(new Point(0, -10));
        setState(CustomerState.BUYING);
        attach(stateImg);

        happiness =  new Status(100);
        attach(happiness.getStatBar(), new Point(0, -40));
    }
    
    private Queue<ShopItemType> selectShoppingList() {
        LinkedList<ShopItemType> list = new LinkedList<ShopItemType>();
        
        int roll = Greenfoot.getRandomNumber(4);
        switch(roll) {
            case 0:
            list.add(ShopItemType.PINK);
            break;
            case 1:
            list.add(ShopItemType.BLUE);
            break;
            case 2:
            list.add(ShopItemType.RED);
            break;
            case 3:
            list.add(ShopItemType.ORANGE);
            break;
        }
        
        return list;
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
        if(shoppingList != null) return shoppingList.peek();
        else return null;
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
        if(c != null && c.getState() == currentState && c.isBusy()) {
            isCustomer = true;
            startWaiting();
        }

        return isWall || isCustomer;
    }
}

