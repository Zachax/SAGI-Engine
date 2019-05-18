/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sagi.engine;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Sakari Eskelinen (se88018@student.uta.fi)
 * Created 7.7.2017
 */
public class MouseMastery extends MouseAdapter {
    private int mouseX;
    private int mouseY;
    private boolean button = false;
    private MainFrame mainClass;
    private int frameSide = 5; // presumed window frame size on side
    private int frameTop = 42; // presumed window frame & menu size on top
    
    public MouseMastery() {
     
    }
    
    public MouseMastery(MainFrame classy) {
        mainClass = classy;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = true;
        // mainClass.mousePressed();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = false;
        mainClass.mouseClicked(e.getClickCount());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        mouseX = MouseInfo.getPointerInfo().getLocation().x - frameSide;
        mouseY = MouseInfo.getPointerInfo().getLocation().y - frameTop;
        /*
        mouseX = e.getX();
        mouseY = e.getY();
        */
        mainClass.mouseMoving();
    }
    
    public int mouseX() {
        return mouseX;
    }
    
    public int mouseY() {
        return mouseY;
    }
    
    public boolean mouseButton() {
        return button;
    }
    
    public void mainClass(MainFrame classy) {
        if (classy != null)
            mainClass = classy;
    }
}
