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
        if(s.hasItems() && c.getState() == CustomerState.BUYING) {
            s.takeOne();
            c.setState(CustomerState.PAYING);
        }
    }

    /*
     * TODO: If the associated shelf is not full, the player will refill it.
     */
    @Override
    public void interact(Player p) {

    }
}
