import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ShopWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShopWorld extends WorldExtra
{
    Target[] targets = new Target[10];
    Countdown customerSpawnTimer = new Countdown();

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

        addObject(customerSpawnTimer);

        prepTargets();

        addPlayer();
    }

    private void prepTargets() {
        for(int i = 0; i < targets.length; i++) {
            targets[i] = new Target(i);
        }

        targets[0].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[5]);
        targets[1].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[2]);
        targets[2].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[2]);
        targets[3].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[2]);
        targets[4].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[2]);
        targets[5].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[1]);
        targets[6].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[1]);
        targets[7].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[5]);
        targets[8].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[5]);
        targets[9].setNext(CustomerState.BUYING, ShopItemType.PINK, targets[0]);

        targets[0].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[5]);
        targets[1].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[6]);
        targets[2].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[1]);
        targets[3].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[6]);
        targets[4].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[1]);
        targets[5].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[1]);
        targets[6].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[6]);
        targets[7].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[5]);
        targets[8].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[5]);
        targets[9].setNext(CustomerState.BUYING, ShopItemType.BLUE, targets[0]);

        targets[0].setNext(CustomerState.PAYING, targets[5]);
        targets[1].setNext(CustomerState.PAYING, targets[3]);
        targets[2].setNext(CustomerState.PAYING, targets[3]);
        targets[3].setNext(CustomerState.PAYING, targets[4]);
        targets[4].setNext(CustomerState.PAYING, targets[4]);
        targets[5].setNext(CustomerState.PAYING, targets[1]);
        targets[6].setNext(CustomerState.PAYING, targets[1]);
        targets[7].setNext(CustomerState.PAYING, targets[5]);
        targets[8].setNext(CustomerState.PAYING, targets[5]);
        targets[9].setNext(CustomerState.PAYING, targets[0]);

        targets[0].setNext(CustomerState.LEAVING, targets[9]);
        targets[1].setNext(CustomerState.LEAVING, targets[0]);
        targets[2].setNext(CustomerState.LEAVING, targets[1]);
        targets[3].setNext(CustomerState.LEAVING, targets[1]);
        targets[4].setNext(CustomerState.LEAVING, targets[1]);

        addObject(targets[0], 300, 75);
        addObject(targets[1], 300, 295);
        addObject(targets[2], 460, 270);
        addObject(targets[3], 400, 350);
        addObject(targets[4], 150, 350);
        addObject(targets[5], 300, 145);
        addObject(targets[6], 140, 270);
        addObject(targets[7], 100, 120);
        addObject(targets[8], 500, 120);
        addObject(targets[9], 300, 15);
    }

    public void act() {
        if(customerSpawnTimer.isFinished()) {
            int roll = Greenfoot.getRandomNumber(100);
            if(roll < (36 - numberOfCustomers * numberOfCustomers)) {
                addObject(new Customer(targets[0]), 300, 15);
                customerSpawnTimer.countFrom(100);
            }
        }
    }
}
