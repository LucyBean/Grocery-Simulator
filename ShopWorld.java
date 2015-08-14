import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopWorld extends WorldExtra
{
    Target[] targets = new Target[5];

    /**
     * Constructor for objects of class ShopWorld.
     * 
     */
    public ShopWorld()
    {    
        //Top wall
        addObject(new NoWalkZone(200, 120), 100, 60);
        addObject(new NoWalkZone(200, 120), 500, 60);

        //Shelves
        addObject(new NoWalkZone(200, 100), 140, 220);
        addObject(new NoWalkZone(200, 100), 460, 220);

        //Checkout
        addObject(new NoWalkZone(100, 40), 100, 345);

        Shelf shelf = new Shelf(5, ShopItemType.PINK);
        addObject(shelf, 460, 240);
        
        Shelf shelf2 = new Shelf(5, ShopItemType.BLUE);
        addObject(shelf2, 140, 240);

        ItemPickUpZone itemZone = new ItemPickUpZone(shelf);
        ItemPickUpZone itemZone2 = new ItemPickUpZone(shelf2);
        TillZone tillZone = new TillZone();
        PayingZone payingZone = new PayingZone(tillZone);
        LeavingZone leavingZone = new LeavingZone();

        addObject(itemZone, 460, 280);
        addObject(itemZone2, 140, 280);
        addObject(payingZone, 160, 345);
        addObject(tillZone, 100, 375);
        addObject(leavingZone, 300, 30);

        prepTargets();

        for(int i = 0; i < 8; i++) {
            addObject(new Customer(targets[1]), 300, 15);
        }
        
        addPlayer();
    }

    private void prepTargets() {
        for(int i = 0; i < targets.length; i++) {
            targets[i] = new Target();
        }

        targets[0].setBuying(targets[1]);
        targets[1].setBuying(targets[2]);
        targets[3].setBuying(targets[2]);
        targets[4].setBuying(targets[2]);

        targets[0].setPaying(targets[1]);
        targets[1].setPaying(targets[4]);
        targets[2].setPaying(targets[3]);
        targets[3].setPaying(targets[4]);

        targets[1].setLeaving(targets[0]);
        targets[2].setLeaving(targets[1]);
        targets[3].setLeaving(targets[1]);
        targets[4].setLeaving(targets[1]);

        addObject(targets[0], 300, 15);
        addObject(targets[1], 300, 310);
        addObject(targets[2], 460, 280);
        addObject(targets[3], 400, 350);
        addObject(targets[4], 150, 350);
    }
}
