import greenfoot.Greenfoot;
import java.util.Arrays;
/**
 * For all my miscellaneous functionallity required for the most excellent
 * game on the planet.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Misc  
{
    /**
     * Rolls a number of integers and guarantees them to all be different! Inclusive
     * of min and max. If number > (max - min + 1) then the max val will be repeated.
     * Numbers will be sorted.
     */
    public static int[] rollInts(int number, int min, int max) {
        //initialise to all hold the max value
        int[] rolls = new int[number];
        for(int i = 0; i < number; i++) {
            rolls[i] = max;
        }

        for(int i = 0; i < number; i++) {
            //Roll a number depending on available spaces
            int available = max - min + 1 - i;
            int roll;
            //Leave the loop early if we run out of slots
            if(available > 0) roll = Greenfoot.getRandomNumber(available);
            else break;

            roll += min;
            
            //Make sure roll is an untaken slot
            for(int j = 0; j < i; j++) {
                if(roll >= rolls[j]) roll++;
            }

            rolls[i] = roll;
            Arrays.sort(rolls);
        }
        
        return rolls;
    }
}
