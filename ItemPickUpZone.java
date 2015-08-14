import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ItemPickUpZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemPickUpZone extends InteractZone
{
    Shelf s;

    public ItemPickUpZone(Shelf associatedShelf) {
        super(40, 40);
        s = associatedShelf;
    }

    /*
     * If the associated shelf has an item available, the customer will pick
     * one up and transiting to a PAYING state.
     */
    @Override
    public void interact(Customer c) {
        if(c.getState() == CustomerState.BUYING) {
            if(s.hasItems()) {
                s.takeOne();
                c.setState(CustomerState.PAYING);
            } else {
                c.startWaiting();
            }
        }
    }

    /*
     * Shelf refilled when the player touches it.
     */
    @Override
    public void interact(Player p) {
        if(!s.isFull()) {
            p.refillShelf(s);
        }
    }
}
