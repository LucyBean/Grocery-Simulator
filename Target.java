import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Target here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Target extends Actor
{
    Target nextBuying;
    Target nextPaying;
    Target nextLeaving;
    
    public Target() {
        GreenfootImage img = new GreenfootImage(10, 10);
        img.setColor(Color.BLACK);
        img.fill();
        setImage(img);
    }
    
    public void setBuying(Target t) {
        nextBuying = t;
    }
    
    public void setPaying(Target t) {
        nextPaying = t;
    }
    
    public void setLeaving(Target t) {
        nextLeaving = t;
    }
    
    public Target getNext(CustomerState p) {
        switch(p) {
            case BUYING:
                return nextBuying;
            case PAYING:
                return nextPaying;
            case LEAVING:
                return nextLeaving;
        }
        
        return null;
    }
}
