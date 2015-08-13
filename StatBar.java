import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class StatBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatBar extends AttachedImage
{
    final int max;
    int current;

    public StatBar(Point p, int max) {
        super(p);
        this.max = max;
        current = max;

        draw();
    }

    public void update(int current) {
        this.current = current;
        if(current < 0) current = 0;
        if(current > max) current = max;
        draw();
    }

    private void draw() {
        int width = 30;
        int height = 10;
        double c = current;
        double m = max;
        double fillFrac = c / m;

        GreenfootImage img = new GreenfootImage(width,height);
        img.setColor(Color.BLACK);
        img.fill();

        /*
         * Causes the fillColor to transition linearly from Green -> Yellow -> Red, with
         * a 0.1 tolerance at either end
         */
        Color fillColor;
        if(fillFrac >= 0.9)
            fillColor = Color.GREEN;
        else if(fillFrac >= 0.5) {
            int r = (int) (255 * (0.9-fillFrac) / 0.4);
            r = Math.max(r, 0);
            r = Math.min(r, 255);
            fillColor = new Color(r, 255, 0);
        }
        else if (fillFrac >= 0.1) {
            int g = (int) (255 * fillFrac / 0.4);
            g = Math.max(g, 0);
            g = Math.min(g, 255);
            fillColor = new Color(255, g, 0);
        }
        else
            fillColor = Color.RED;

        int barWidth = (int) (fillFrac * width);

        img.setColor(fillColor);
        img.fillRect(0, 0, barWidth, height);

        img.setColor(Color.WHITE);
        img.drawRect(0, 0, width - 1, height - 1);

        setImage(img);
    }
}
