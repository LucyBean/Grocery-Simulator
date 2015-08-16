import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PayingZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PayingZone extends InteractZone
{
    TillZone t;
    Customer current;

    /**
     * Creates a new PayingZone with an associated TillZone. Customers can only
     * pay at this PayingZone if the associated TillZone is manned.
     * @param tillZone Associated TillZone
     */
    public PayingZone(TillZone tillZone) {
        super(20, 40);
        t = tillZone;
    }

    /**
     * The Customer interacts with the PayingZone. This will cause the Player (if any)
     * at the attached TillZone to start the checkout process for this Customer.
     * When a Customer arrives at an empty PayingZone, they will take the PayingZone and
     * other Customers will wait. When they leave, they will free the PayingZone.
     */
    @Override
    public void interact(Customer c) {
        if(c.getState() == CustomerState.PAYING) {
            if(t.manned() && current == null) {
                current = c;
                c.startInteracting();
            } if (t.manned() && current == c) {
                getWorld().getPlayer().checkOut(c);
            }
            else c.startWaiting();
        } else if (c.getState() == CustomerState.LEAVING && current == c) {
            current = null;
        }
    }
}
