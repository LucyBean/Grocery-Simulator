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
    final int walkSpeed = 4;

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
        selectShoppingList();

        t = initial;
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

    private void selectShoppingList() {
        shoppingList = new LinkedList<ShopItemType>();

        int listLen = Greenfoot.getRandomNumber(4);
        int[] items = Misc.rollInts(listLen, 0, 3);
        for(int i = 0; i < items.length; i++) {
            shoppingList.add(ShopItemType.numToType(items[i]));
        }

        /*
         * If a Customer (for some reason) has an initial shopping list of zero, they will
         * immediately be put in the PAYING state.
         */
        if(listLen == 0) {
            setState(CustomerState.PAYING);
        }
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

    /**
     * Returns the next item the Customer's shopping list.
     */
    public ShopItemType getNextItem() {
        if(!shoppingList.isEmpty()) return shoppingList.peek();
        else return null;
    }

    /**
     * Method to be called when a Customer receives an item.
     */
    public void receiveItem(ShopItem si) {
        shoppingList.remove();
        currentBusyState = CustomerBusyState.NONE;
        if(shoppingList.isEmpty()) {
            setState(CustomerState.PAYING);
        }
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
     * with the same state and trying to get the same item.
     * This allows for queueing behaviours.
     */
    @Override
    public boolean isObjectAtEdge(Direction d) {
        boolean isWall = isObjectAtEdge(d, NoWalkZone.class);
        Customer c = (Customer) getOneObjectAtEdge(d, Customer.class);
        boolean isCustomer = false;
        if(c != null
                && c.getState() == getState()
                && c.getNextItem() == getNextItem()
                && c.isBusy()) {
            isCustomer = true;
            startWaiting();
        }

        return isWall || isCustomer;
    }
}

