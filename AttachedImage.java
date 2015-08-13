import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttachedImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttachedImage extends Actor
{
    Point p;
    
    public AttachedImage(Point offset) {
        p = offset;
    }
    
    public AttachedImage(GreenfootImage img, Point offset) {
        setImage(img);
        p = offset;
    }
    
    public void setImage(GreenfootImage img) {
        super.setImage(img);
    }
    
    public Point getOffset() {
        return p;
    }
}
