import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Countdown here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Countdown extends Actor
{
    int count = 0;
    boolean finished = true;
    
    public void act() 
    {
        if (!finished) {
            count--;
            if (count <= 0) finished = true;
        }
    }

    public void countFrom(int num) {
        //do nothing if num is non-positive
        if(num <= 0) return;
        
        count = num;
        finished = false;
    }

    public boolean isFinished() { return finished; }
    
    public int getVal() { return count; }
}
