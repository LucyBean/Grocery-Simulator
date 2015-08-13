public enum Direction {
    UP (new Point(0, -1)), DOWN (new Point(0,1)),
    LEFT (new Point(-1,0)), RIGHT (new Point(1,0));
    
    Point p;
    Direction opposite;
    
    Direction(Point dir) {
        p = dir;
    }
    
    public Point asCoOrd() { return p; }
    
    public int getX() { return p.x; }
    public int getY() { return p.y; }
    
    public Direction opposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
        }
        return null;
    }
}