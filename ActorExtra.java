import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
/**
 * Write a description of class ActorExtra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ActorExtra extends Actor
{
    List<AttachedImage> images = new LinkedList<AttachedImage>();

    /**
     * Attaches a new AttachedImage object to this ActorExtra, allowing it to follow the
     * ActorExtra around the world.
     * 
     * @param i The AttachedImage to be attached.
     */
    public void attach(AttachedImage i) {
        images.add(i);

        if(getWorld() != null) {
            addImagesToWorld();
        }
    }
    
    public List<AttachedImage> getImages() {
        return images;
    }

    public WorldExtra getWorld() {
        return (WorldExtra) super.getWorld();
    }

    public void addedToWorld(World w) {
        addImagesToWorld();
    }

    /**
     * For every AttachedImage in the list, checks if it has been added, and adds it if
     * it has not.
     */
    private void addImagesToWorld() {
        Iterator<AttachedImage> i = images.iterator();
        while(i.hasNext()) {
            AttachedImage img = i.next();
            if(img.getWorld() == null) {
                Point p = img.getOffset();
                getWorld().addObject(img, getX() + p.x, getY() + p.y);
            }
        }
    }

    /**
     * Resizes the image according to the scale.
     * 1.0 represents no change, 2.0 represents double size, 0.5 represents half size.
     * 
     * @param scale The factor by which the image size is increased.
     */
    public void resizeImage(double scale) {
        GreenfootImage img = this.getImage();
        img.scale((int) (img.getWidth() * scale), (int) (img.getHeight() * scale));
        this.setImage(img);
    }

    /**
     * Checks whether the ActorExtra is at the specified edge.
     * 
     * @param d The direction in which to check.
     * @param tolerance The closeness allowed.
     */
    public boolean atEdge(Direction d, int tolerance) {
        switch (d) {
            case UP: return atTop(tolerance);
            case DOWN: return atBottom(tolerance);
            case LEFT: return atLeft(tolerance);
            case RIGHT: return atRight(tolerance);
            default: return false;
        }
    }

    public boolean atEdge(Direction d) { return atEdge(d, 5); }

    public boolean atLeft(int tolerance) { return getX() < tolerance; }

    public boolean atRight(int tolerance) { return getX() >= getWorld().getWidth() - tolerance; }

    public boolean atTop(int tolerance) { return getY() < tolerance; }

    public boolean atBottom(int tolerance) { return getY() >= getWorld().getHeight() - tolerance; }

    public boolean atLeft() { return atLeft(5); }

    public boolean atRight() { return atRight(5); }

    public boolean atTop() { return atTop(5); }

    public boolean atBottom() { return atBottom(5); }

    public boolean atEdge(int tolerance) { return atLeft(5) || atRight(5) || atTop(5) || atBottom(5); }

    public boolean atEdge() { return atEdge(5); }

    public void turnTowards(Actor a) {
        turnTowards(a.getX(), a.getY());
    }

    public void setLocation(Point p) {
        setLocation(p.x, p.y);
    }

    /*
     * Changes the location of the ActorExtra and ensures all attached images follow.
     */
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);

        Iterator<AttachedImage> i = images.iterator();
        while(i.hasNext()) {
            AttachedImage img = i.next();
            Point p = img.getOffset();
            img.setLocation(x + p.x, y + p.y);
        }
    }
}