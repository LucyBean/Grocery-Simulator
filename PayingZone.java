import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PayingZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PayingZone extends InteractZone
{
    public PayingZone() {
        super(40, 40);
    }

    @Override
    public void interact(Customer c) {
        if(c.getState() == CustomerState.PAYING) {
            c.setState(CustomerState.LEAVING);
        }
    }
}
