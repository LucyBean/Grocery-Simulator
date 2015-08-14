import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopWorld extends WorldExtra
{
    Target[] targets = new Target[8];

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

        Shelf shelfP = new Shelf(5, ShopItemType.PINK);
        Shelf shelfB = new Shelf(5, ShopItemType.BLUE);
        Shelf shelfO = new Shelf(6, ShopItemType.ORANGE);
        Shelf shelfR = new Shelf(6, ShopItemType.RED);
        addObject(shelfP, 460, 240);
        addObject(shelfB, 140, 240);
        addObject(shelfO, 100, 90);
        addObject(shelfR, 500, 90);

        ItemPickUpZone itemZoneP = new ItemPickUpZone(shelfP);
        ItemPickUpZone itemZoneB = new ItemPickUpZone(shelfB);
        ItemPickUpZone itemZoneO = new ItemPickUpZone(shelfO);
        ItemPickUpZone itemZoneR = new ItemPickUpZone(shelfR);
        TillZone tillZone = new TillZone();
        PayingZone payingZone = new PayingZone(tillZone);
        LeavingZone leavingZone = new LeavingZone();

        addObject(itemZoneP, 460, 280);
        addObject(itemZoneB, 140, 280);
        addObject(itemZoneO, 100, 130);
        addObject(itemZoneR, 500, 130);
        addObject(payingZone, 160, 345);
        addObject(tillZone, 100, 375);
        addObject(leavingZone, 300, 30);

        prepTargets();

        for(int i = 0; i < 8; i++) {
            addObject(new Customer(targets[0]), 300, 15);
        }
        
        addPlayer();
    }

    private void prepTargets() {
        for(int i = 0; i < targets.length; i++) {
            targets[i] = new Target(i);
        }

        /*targets[0].setBuying(targets[1]);
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
        targets[4].setLeaving(targets[1]);*/

        addObject(targets[0], 300, 75);
        addObject(targets[1], 300, 295);
        addObject(targets[2], 460, 270);
        addObject(targets[3], 400, 350);
        addObject(targets[4], 150, 350);
    }
}
