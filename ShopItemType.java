import java.awt.Color;
import greenfoot.GreenfootImage;

public enum ShopItemType {
    PINK (Color.MAGENTA), BLUE (Color.BLUE), RED (Color.RED), ORANGE (Color.ORANGE);
    
    GreenfootImage img;
    
    ShopItemType(Color c) {
        img = new GreenfootImage(20, 20);
        img.setColor(c);
        img.fill();
        img.setColor(Color.WHITE);
        img.drawRect(0,0,19,19);
    }
    
    public GreenfootImage getImage() {
        return img;
    }
}