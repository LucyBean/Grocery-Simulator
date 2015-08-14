import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeavingZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeavingZone extends InteractZone
{
    public LeavingZone() {
        super(200, 60);
    }
    
    @Override
            public void interact(Customer c) {
                if(c.getState() == CustomerState.LEAVING) {
                    getWorld().removeObject(c);
                }
            }
}
