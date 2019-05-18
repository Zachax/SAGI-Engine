/* Version history:
 * First created: 20.7.2017
 * 28.8.2017: Work continued significantly. Implemented object location recognition and many basic features.
 * 13.4.2019: Reviewing code. Adjusting nudge and else.
 * 19.4.2019: Some refactoring - cutting out unnecessary complications and else. (0.1.1)
 */

package sagi.engine;

import sagi.elements.*;
import java.awt.*;
import java.awt.image.*;

/**
 * This class for SAGI engine is for containing and sharing situational data (location, contents, where to draw etc.).
 * @author Sakari Eskelinen (se88018@student.uta.fi)
 * Created 20.7.2017
 * Last update: 19.4.2019
 * Version: 0.1.1
 */
public class Situation {
    /*
     * Attributes
    */
    private Zone onSpot; // The current location.
    private int nudgeX = 0; // X alteration for drawing.
    private int nudgeY = 0; // Y alteration for drawing.
    private boolean nudge = false; // Whether the nudge moves screen.
    private boolean running = false; // If the system is on.
    private GraphicsWindow gameGraphics; // Graphics drawing object shown in GUI.
    private Thread mainLoop; // Main updater loop.
    private String playerName;
    
    Situation(Zone spot, GraphicsWindow graf) {
        if (spot != null) {
            onSpot = spot;
            running = true;
        } else
            running = false;
        
        if (graf != null) {
            gameGraphics = graf;
            running = true;
        } else
            running = false;
            
        if (running)
            updaterCreate();
        else
            System.out.println("Note: Situation not running");
    }
    
    /*
    * Accessors
    */
    
    public boolean nudge() {
        return nudge;
    }
    
    public void nudge(boolean onoff) {
        nudge = onoff;
    }
    
    public int nudgeX() {
        return nudgeX;
    }
    
    /**
     * Adds value to nudgeX.
     * @param x 
     */
    public void nudgeX(int x) {
        nudgeX = nudgeX + x;
    }
    
    /**
     * Sets nudgeX to specific value.
     */
    public void setNudgeX(int x) {
        nudgeX = x;
    }
    
    public int nudgeY() {
        return nudgeY;
    }
    
    /**
     * Adds value to nudgeY.
     * @param y 
     */
    public void nudgeY(int y) {
        nudgeY = nudgeY + y;
    }
    
    /**
     * Sets nudgeY to specific value.
     */
    public void setNudgeY(int y) {
        nudgeY = y;
    }
    
    public Zone zone() {
        return onSpot;
    }
    
    public boolean zone(Zone spot) {
        if (spot != null) {
            onSpot = spot;
            return true;
        } else
            return false;
    }
    
    public boolean gameGraphics(GraphicsWindow graf) {
        if (graf != null) {
            gameGraphics = graf;
            return true;
        }
        else
            return false;
    }
    
    public void playerName(String nam) {
        if (nam != null) {
            playerName = nam;
            Agent ag = findPlayer();
            if (ag != null) {
                ag.name(playerName);
                System.out.println("Renamed player to " + ag.name());
            } else {
                System.out.println("Player not found (" + ag + ") - not named properly!");
            }
        }
    }
    
    public String playerName() {
        return playerName;
    }
    
    /*
    * Other methods
    */
    
    /**
    * Toggle for running on/off. If the system is on, turns it off and vice versa.
    */
    public void running() {
        if (running)
            running = false;
        else
            running = true;
    }
    
    /**
     * Returns true if system is running.
     * @return 
     */
    public boolean isRunning() {
        return running;
    }
    
    /**
     * Creates and starts an updater thread.
     */
    private void updaterCreate() {
        mainLoop = new Thread() {
            @Override
            public void run() {
                while(isRunning()) {
                    if (onSpot != null && gameGraphics != null)
                        /*
                        // Refactored code snip
                        && !gameGraphics.locationZone().equals(onSpot))
                        gameGraphics.locationZone(onSpot);
                        */
                    try {
                        Thread.sleep(gameGraphics.updateRate());
                    } catch (InterruptedException ex) { }
                    catch (NullPointerException ex) {
                        ex.printStackTrace();
                        System.out.println("Graphics object missing");
                    }
                }
            }
        };
        mainLoop.start();
    }
    
    /**
     * If old updater thread is running, it's interrupted. If no old thread exists, a new one is created.
     */
    public void updaterToggle() {
        if (isRunning() && mainLoop != null) {
            running();
        }
        else 
            updaterCreate();
    }

    /**
     * This might be useless.
     */
     private void paintCycle() {
         
    }
     
    public String itemSpotting(int x, int y) {
        String named = "";
        for (int i = 0; i < onSpot.size(); i++) {
            Entity target = onSpot.getItem(i);
            BufferedImage previewImg = target.icon();
            
            // Relative X and Y
            int rx = x - onSpot.getLocX(i);
            int ry = y - onSpot.getLocY(i);
            if (nudge) {
                rx -= nudgeX;
                ry -= nudgeY;
            }
            
            if (previewImg != null) {
                int width = previewImg.getWidth();
                int height = previewImg.getHeight();
                Rectangle box = new Rectangle(width, height);
                if (box.contains(rx + width/2, ry + height)) {
                    int pixel = previewImg.getRGB(rx + width/2, ry + height);
                    if ((pixel >>> 24) != 0x00) {
                        named = target.name();
                        //System.out.println(rx + " " + ry);
                    }
                }
            }
            
            if (onSpot.getLocation(i) != null) {
                //String found = "This came: " + onSpot.getLocation(i).contains(x, y);
                //System.out.println(found);
            }
        }
        return named;
    }
    
    public Entity findItem(String name) {
        Entity it = null;
        for (int i = 0; i < onSpot.size(); i++) {
            Entity target = onSpot.getItem(i);
            if (target.name().equals(name)) {
                it = target;
                break;
            }
        }
        return it;
    }
    
    public Agent findPlayer() {
        Agent agent = null;
        if (onSpot.locations() != null) {
            agent = onSpot.locations().findPlayer();
            //System.out.println(agent + " was found as player from Location List.");
        }
        return agent;
    }
    
    /**
     * If the target entity is within reach of subject Entity, returns true.
     */    
    public boolean withInRange(Entity target, Entity subject) {
        boolean isRange = false;
        double targetReach = target.reach();
        double subjectReach = target.reach();

        
        return isRange;
    }
}