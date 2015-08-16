import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * CollidingActor class for objects that need to move using precise collision checking.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class CollidingActor extends ActorExtra
{
    Collider collider;
    Point colliderPoint;

    double errX = 0.0;
    double errY = 0.0;

    /**
     * Position for collider relative to actor's centre.
     * @param c Collider object to be attached.
     * @param p Point of the Collider's centre relative to the CollidingActor's centre.
     */
    public void setCollider(Collider c, Point p) {
        collider = c;
        colliderPoint = p;
    }

    public Collider getCollider() {
        return collider;
    }

    /**
     * Also causes the attached Collider (if any) to be removed as well as the CollidingActor.
     */
    @Override
    public void addedToWorld(World w) {
        super.addedToWorld(w);

        if (collider != null) {
            w.addObject(collider, getX() + colliderPoint.x, getY() + colliderPoint.y);
        }
    }

    /**
     * Checks the row/column of pixels immediately adjacent to the CollidingActor in the
     * specified direction for a Collider object, then returns the associated Actor. Pixels
     * are checked top-to-bottom, left-to-right, with the first Actor's Collider found in
     * this order returned.
     * 
     * @param d Direction to check for a Collider
     * @param filter Causes only objects of this class to be returned
     * @return Returns an Actor object with an associated Collider in the immediately adjacent row/column
     */
    protected Actor getOneObjectAtEdge(Direction d, java.lang.Class filter) {
        if(collider == null) return null;

        Point start = null;
        Point end = null;

        switch(d) {
            case DOWN:
            //for each cell in the strip of cells immediately below
            //the player
            //get the bottom left and right co-ords of the collider
            start = collider.bottomLeft();
            end = collider.bottomRight();

            //look at the strip below
            start.y++;
            end.y++;
            break;

            case UP:
            start = collider.topLeft();
            end = collider.topRight();
            start.y--;
            end.y--;
            break;

            case LEFT:
            start = collider.topLeft();
            end = collider.bottomLeft();
            start.x--;
            end.x--;
            break;

            case RIGHT:
            start = collider.topRight();
            end = collider.bottomRight();
            start.x++;
            end.x++;
            break;
        }

        if(start == null || end == null) return null;

        /*
         * This code uses precise collision checking to check every point along the edge of
         * the CollidingActor.
         */
        for(int x = start.x; x <= end.x; x++) {
            for(int y = start.y; y <= end.y; y++) {
                Collider c = (Collider) getOneObjectAtOffset(x - getX(), y - getY(), Collider.class);
                if(c != null) {
                    Actor a = c.getAssociatedActor();
                    if(filter == null) return a;
                    if(filter.isInstance(a)) return a;
                }
            }
        }

        return null;
    }

    /**
     * Detects whether there is a Collider object with an associated Actor of the
     * specified class in the immediately adjacent row or column of pixels in the
     * specified direction.
     * 
     * @param d The direction in which to check.
     * @param cls The class of the Collider's associated Actor to check for.
     */
    protected boolean isObjectAtEdge(Direction d, java.lang.Class cls) {
        return (getOneObjectAtEdge(d, cls) != null);
    }

    /**
     * Detects whether there is a Collider object in the immediately adjacent row or column
     * of pixels in the specified direction.
     */
    protected boolean isObjectAtEdge(Direction d) {
        return isObjectAtEdge(d, null);
    }

    public void moveUp(int amount) { move(Direction.UP, amount); }

    public void moveDown(int amount) { move(Direction.DOWN, amount); }

    public void moveLeft(int amount) { move(Direction.LEFT, amount); }

    public void moveRight(int amount) { move(Direction.RIGHT, amount); }

    /**
     * Uses precise collision checking if a Collider object is attached. The CollidingActor
     * will be moved as far as possible in the given direction (up to amount) without
     * overlapping with any other Collider objects.
     * 
     * @param d The direction in which to move.
     * @param amount The maximum amount that the CollidingActor will move.
     */
    public void move(Direction d, int amount) {
        //flip the direction and amount if negative amount passed
        if(amount < 0) {
            d = d.opposite();
            amount *= -1;
        }

        if(collider == null) {
            setLocation(getX() + d.getX() * amount, getY() + d.getY() * amount);
        } else {

            for(int i = 0; i < amount; i++) {
                //check for an object in the strip in the given direction
                boolean canMove = !isObjectAtEdge(d);

                //if there is no object at the edge, move into it, else exit
                if(canMove) {
                    setLocation(getX() + d.getX(), getY() + d.getY());
                } else return;
            }
        }
    }

    /*
     * Also causes the associated Collider to be moved.
     */
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x,y);
        if (collider != null) collider.setLocation(x + colliderPoint.x, y + colliderPoint.y);
    }

    /**
     * Moves towards the specified Actor (as the crow flies) using precise collision checking.
     * The CollidingActor may move too far or not far enough, due to storing the current
     * position using integers.
     * 
     * @param target The Actor to move towards.
     * @param amount The maximum distance to move.
     */
    public void moveTowards(Actor target, int amount) {

        int dY = target.getY() - getY();
        int dX = target.getX() - getX();

        double distance = Math.sqrt(dY * dY + dX * dX);

        //ignore small distances
        if(distance <= 0.1) return;

        double dx = dX * amount / distance;
        double dy = dY * amount / distance;
        
        //Calculate the accumulated error and adjust for it if necessary
        int dxi = (int) dx;
        int dyi = (int) dy;

        errX += dx - dxi;
        errY += dy - dy;
        
        dxi += (int) errX;
        dyi += (int) errY;
        
        errX -= (int) errX;
        errY -= (int) errY;

        move(Direction.RIGHT, dxi);
        move(Direction.DOWN, dyi);
    }
}
