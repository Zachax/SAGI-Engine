/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sagi.resource;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;



/**
 *
 * @author Sakari Eskelinen (se88018@student.uta.fi)
 * Created 9.7.2017
 */
public class TransparencyTest extends JPanel {
    private BufferedImage patientImg;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Paint background
        Graphics2D g2 = (Graphics2D)g;
        for (int i = 0; i < 100; i++)
            drawPoint(g2, i,5);
        g2.drawImage(patientImg, 5, 5, this);
    }
    
    public void setImg(BufferedImage img) {
        patientImg = img;
    }
    
    public void drawPoint(Graphics2D g2, int x, int y) {
        g2.draw(new Line2D.Float(x, y, x, y));
    }
}
