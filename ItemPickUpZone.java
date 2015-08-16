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
        super(20, 20);
        s = associatedShelf;
    }

    /*
     * If the associated shelf has an item available, the customer will pick
     * one up and transiting to a PAYING state.
     */
    @Override
    public void interact(Customer c) {
        if(c.getState() == CustomerState.BUYING && s.getType() == c.getNextItem()) {
                if(s.hasItems()) {
                    ShopItem i = s.takeOne();
                    c.receiveItem(i);
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
