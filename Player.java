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
    final String up = ",";
    final String down = "o";
    final String left = "a";
    final String right = "e";
    
    Countdown actTimer = new Countdown();
    Timeline actTimeline = new Timeline();
    int CHECKOUT_TIME = 100;
    int REFILL_TIME = 50;
    Status checkout = new Status(CHECKOUT_TIME, 0);
    AttachedImage checkoutBar = new AttachedImage(checkout.getStatBar(), new Point(0, -50));
    Status refill = new Status(REFILL_TIME, 0);
    AttachedImage refillBar = new AttachedImage(refill.getStatBar(), new Point(0, -50));
    
    
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
    
    @Override
    public void addedToWorld(World w) {
        super.addedToWorld(w);
        getWorld().addObject(actTimer);
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
        
        InteractZone iz = (InteractZone) collider.getOneIntersecting(InteractZone.class);
        if(iz != null) {
            iz.interact(this);
        }
        //If we leave an InteractZone reset the act timer
        if(iz == null) {
            actTimeline.jumpTo(0);
            detach(checkoutBar);
            detach(refillBar);
        }
    }    
    
    @Override
    public boolean isObjectAtEdge(Direction d) {
        return isObjectAtEdge(d, NoWalkZone.class);
    }
    
    /**
     * Starts the checkout for customer c
     */
    public void checkOut(Customer c) {
        switch(actTimeline.getCurrent()) {
            case 0:
                attach(checkoutBar);
                actTimer.countFrom(checkout.getMax());
                actTimeline.advance();
                break;
            case 1:
                if(actTimer.isFinished()) actTimeline.advance();
                checkout.setVal(CHECKOUT_TIME - actTimer.getVal());
                break;
            case 2:
                c.setState(CustomerState.LEAVING);
                detach(checkoutBar);
                actTimeline.jumpTo(0);
                break;                
        }
    }
    
    public void refillShelf(Shelf s) {
        switch(actTimeline.getCurrent()) {
            case 0:
                attach(refillBar);
                actTimer.countFrom(refill.getMax());
                actTimeline.advance();
                break;
            case 1:
                if(actTimer.isFinished()) actTimeline.advance();
                refill.setVal(REFILL_TIME - actTimer.getVal());
                break;
            case 2:
                s.fill();
                detach(refillBar);
                actTimeline.jumpTo(0);
                break;
        }
    }
}
