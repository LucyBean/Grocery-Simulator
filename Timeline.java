/**
 * Write a description of class Timeline here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timeline  
{
    int currentScene = 0;
    
    public void advance() { currentScene++; }
    
    public void jumpTo(int to) { currentScene = to; }
    
    public int getCurrent() { return currentScene; }
}
