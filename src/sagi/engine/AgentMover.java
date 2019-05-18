/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sagi.engine;

import sagi.elements.*;

/**
 * This class is meant to move locations that are mounting agents,
 * and also manipulate the mounted agents when necessary.
 * @author Sakari Eskelinen (se88018@student.uta.fi)
 * Created 7.7.2017
 * Last edit 13.4.2019
 */
public class AgentMover {
    /**
     * Location that has the handled character.
     */
    private Location mount;
    
    private MouseMastery dataMus;
    private MainFrame master;
    
    /**
     * Vertical adjusting modifier multiplier to make movements not go too high.
     * If set to 1 it has no effect - value is divider.
     */
    private int adjustY = 10;
    
    /**
     * Thread for the walking process.
     */
    private Thread walkerThread;
    private boolean continueOK;
    private int sleepRate = 20;
    
    /**
     * Variable indicating speed multiplier.
     */
    private int moveMultiplier = 5;
    
    /**
     * Variable indicating base pixel amount moved at once.
     */
    private int moveUnit = 2;
    
    public AgentMover(MainFrame host, Location spot) {
        master = host;
        if (spot != null) {
            mount = spot;
        }
    }
    
    public void location(Location place) {
        if (place != null) {
            mount = place;
        }
    }
    
    public void mouse(MouseMastery mouse) {
        if (mouse != null) {
            dataMus = mouse;
        }
    }
    
    public void mouseComIn(int x, int y, int clicks) {
        if (walkerThread == null || !walkerThread.isAlive()) {
            // Y value is adjusted so that the object would not hit too high on the screen.
            y = y += (mount.height()/adjustY);
            walkAgent(x, y, clicks);
        } else {
            walkerThread.interrupt();
            continueOK = false;
            // walkAgent(x, y, clicks);
        }
    }
    
    public void moveAgent(int x, int y) {
        mount.locaX(x);
        mount.locaY(y);
    }
    
    public void moveAgentX(int x) {
        mount.locaX(x);
    }
    
    public void moveAgentY(int y) {
        mount.locaX(y);
    }
    
    /**
     * Creates a walker agent that will move the mounted Location towards desired coordinates.
     * @param x
     * @param y
     * @param speed 
     */
    public void walkAgent(int x, int y, int speed) {
        walkerThread = new Thread() {
            @Override
            public void run() {
                boolean enRoute = true;
                continueOK = true;
                int i = 0;
                while (enRoute && continueOK) {
                    boolean checking = true;
                    int locX = mount.locaX();
                    int locY = mount.locaY();
                    if (checking) {
                        if (locX == x && locY == y)
                            enRoute = false;

                        if (locX < x) {
                            int step = moveUnit + speed * moveMultiplier; 
                            mount.locaX(locX + step);
                            master.nudgeX(-step);
                            if (mount.locaX() > x)
                                mount.locaX(x);
                        } else if (locX > x) {
                            int step = moveUnit + speed * moveMultiplier;
                            mount.locaX(locX - step);
                            master.nudgeX(step);
                            if (mount.locaX() < x)
                                mount.locaX(x);
                        }

                        if (locY < y) {
                            mount.locaY(locY + moveUnit + speed * moveMultiplier);
                            // master.nudgeY(-1);
                            if (mount.locaY() > y)
                                mount.locaY(y);
                        } else if (locY > y) {
                            mount.locaY(locY - moveUnit - speed * moveMultiplier);
                            // master.nudgeY(1);
                            if (mount.locaY() < y)
                                mount.locaY(y);
                        }
                    }
                    checking = false;
                    i++;
                    if (i > 1000) {
                        System.out.println("Character stuck?");
                        continueOK = false;
                    }
                    try {
                        Thread.sleep(sleepRate);
                    } catch (InterruptedException e) {
                        
                    }
                }
                continueOK = false;
            }
        };
        walkerThread.start();
    }
    
    public void walkAgentGo(int x, int y) {
        walkerThread = new Thread() {
            @Override
            public void run() {
                boolean enRoute = true;
                while (dataMus.mouseButton() && enRoute) {
                    enRoute = walkingAgent(x, y, 3);
                }
            }
        };
        walkerThread.start();
    }
    
    private boolean walkingAgent(int x, int y, int speed) {
        int locX = mount.locaX();
        int locY = mount.locaY();
        boolean enRoute = true;

        if (locX == x && locY == y)
            enRoute = false;

        if (locX < x) {
            int step = moveUnit + speed * moveMultiplier; 
            mount.locaX(locX + step);
            master.nudgeX(-step);
            if (mount.locaX() > x)
                mount.locaX(x);
        } else if (locX > x) {
            int step = moveUnit + speed * moveMultiplier;
            mount.locaX(locX - step);
            master.nudgeX(step);
            if (mount.locaX() < x)
                mount.locaX(x);
        }

        if (locY < y) {
            mount.locaY(locY + moveUnit + speed * moveMultiplier);
            if (mount.locaY() > y)
                mount.locaY(y);
        } else if (locY > y) {
            mount.locaY(locY - moveUnit - speed * moveMultiplier);
            if (mount.locaY() < y)
                mount.locaY(y);
        }
        return enRoute;
    }
    
    public void continueOK(boolean ok) {
        // continueOK = ok;
    }
}