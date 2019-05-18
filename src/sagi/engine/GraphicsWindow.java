/* Version history:
 * First created: 5.7.2017
 * 28.8.2017: Added getters for updateRate. (0.1)
 * 13.4.2019: Made nudge optional. (0.1.1)
 */

package sagi.engine;

import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import sagi.elements.*;

/**
 *
 * @author Sakari Eskelinen (tyhmamooses@hotmail.com)
 * Created 5.7.2017
 * Last update: 13.4.2019
 * Version: 0.1.1
 */
public class GraphicsWindow extends JPanel {
    private int updateRate = 20; // milliseconds delay
    //private Zone location; // Contains all drawable items.
    private Situation state;
    
    Thread graphicsThread = new Thread() {
        @Override
        public void run() {
            while(true) {
                repaint();

                try {
                    Thread.sleep(updateRate);
                } catch (InterruptedException ex) { }
            }
        }
    };
    
    public void startRunning() {
        graphicsThread.start();
    }
    
    public void stopRunning() {
        graphicsThread.interrupt();
    }

    /*
     * Graphic methods
     */
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background
        Graphics2D g2 = (Graphics2D)g;
        setDoubleBuffered(true);

        // Draw background
        drawBackground(g2);
        
        // Browses through locations containing items and drawing them if there are.
        browseLocations(g2);
    }
    
    /**
     * Draws background from attributed Zone.
     * @param g2 
     */
    private void drawBackground(Graphics2D g2) {
        if (state != null && state.zone() != null) {
            BufferedImage bgImage = state.zone().landscape();
            drawElement(bgImage, g2, 0, 0);
            //System.out.println("Drawing background on " + state.zone() + ": " + state.zone().landscape());
        }
    }
    
    /**
     * Draws any element picture. Has canvas nudge included.
     * @param pic
     * @param g2
     * @param x
     * @param y
     * @return 
     */
    private boolean drawElement(BufferedImage pic, Graphics2D g2, int x, int y) {
        if (pic != null)
            if (state.nudge()) {
                return g2.drawImage(pic, x + state.nudgeX(), y + state.nudgeY(), this);
            } else {
                return g2.drawImage(pic, x, y, this);
            }
        else
            return false;
    }
    
    /**
     * Goes through all locations listed in the Zone, and prints out items detected in those locations.
     * Draws all images centered by X and standing on location by Y (rises above Y).
     * @param g2 
     */
    private void browseLocations(Graphics2D g2) {
        if (state != null && state.zone() != null) {
            LocationList locas = state.zone().locations();
            for (int i = 0; i < locas.size(); i++) {
                Location thisPlace = (Location) locas.get(i);
                if (thisPlace.concrete()) {
                    LinkedList locationStuff = thisPlace.contents();
                    BufferedImage locationImage = browseList(locationStuff);
                    if (locationImage != null) {
                        int iHeight = locationImage.getHeight();
                        int iWidth = locationImage.getWidth();
                        int iX = thisPlace.locaX() - (iWidth/2);
                        int iY = thisPlace.locaY() - iHeight;
                        drawElement(locationImage, g2, iX, iY);
                        //g2.drawOval(thisPlace.locaX(), thisPlace.locaY(), 100, 100);
                        //g2.drawLine(iX, iY, iX, iY-100);
                    }
                }
            }
        }
    }
    
    /**
     * Goes through a list of items and returns the last one's icon to be drawn.
     */
    private BufferedImage browseList(LinkedList listing) {
        if (listing != null) {
            int listSize = listing.size();
            if (listSize > 0) {
                int i = listSize - 1;
                boolean notFound = true;
                while (notFound && i >= 0) {
                    Object item = listing.get(i);
                    if (item instanceof Entity) {
                        Entity element = (Entity) item;
                        if (element.visible()) {
                            return element.icon();
                        }
                    }
                    i--;
                }
                return null;
            } else 
                return null;
        } else
            return null;
    }
    
    /*
    * Variable accessors
    */
    
    /*
    // Local location system deprecated
    public Zone locationZone(){
        return location;
    }
    
    public boolean locationZone(Zone newPlace) {
        if (newPlace != null) {
            location = newPlace;
            return true;
        } else
            return false;
    }
    */
    
    public void setSituation(Situation status) {
        if (status != null) {
            state = status;
        }
    }
    
    public int updateRate(){
        return updateRate;
    }
    
    public void updateRate(int rate) {
        if (rate > 0)
            updateRate = rate;
    }
}