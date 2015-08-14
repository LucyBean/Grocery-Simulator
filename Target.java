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
    CustomerState[] custStates = CustomerState.values();
    ShopItemType[] itemTypes = ShopItemType.values();
    int maxNum = custStates.length - 1 + itemTypes.length;
    Target[] targets = new Target[maxNum];

    public Target(int num) {
        GreenfootImage img = new GreenfootImage(" " + num + " ", 14, Color.WHITE, Color.BLACK);
        setImage(img);
    }

    public Target() {
        setImage(new GreenfootImage(10, 10));
    }

    /**
     * Sets the next target that routes a Customer based on their current CustomerState and
     * current ShopItemType. When CustomerState != BUYING, ShopItemType can be left
     * as null.
     */
    public void setNext(CustomerState c, ShopItemType item, Target t) {
        int index = stateToNum(c, item);
    }

    public Target getNext(Customer c) {
        CustomerState cs = c.getState();
        ShopItemType nextItem = c.getNextItem();
        
        return null;
    }

    /*
     * BUYING must be the first entry in CustomerState for this to work.
     */
    private int stateToNum(CustomerState c, ShopItemType s) {
        int currentIndex = 0;
        if(c == CustomerState.BUYING) {
            for(int i = 0; i < itemTypes.length; i++) {
                if (s == itemTypes[i]) {
                    return i;
                }
            }
        }
        for(int i = 1; i < custStates.length; i++) {
            if (c == custStates[i]) {
                return i + itemTypes.length - 1;
            }
        }
        
        return -1;
    }
}
