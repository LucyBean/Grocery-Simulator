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

    @Override
    public void interact(Customer c) {
        if(c.getState() == CustomerState.PAYING) {
            if(t.manned()) {
                c.startInteracting();
                getWorld().getPlayer().checkOut(c);
            }
            else c.startWaiting();
        }
    }
}
