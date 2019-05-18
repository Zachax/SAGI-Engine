/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sagi.engine;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import sagi.elements.*;

/**
 * Testing framework for SAGI engine.
 * Created in 2017.
 * Last update 13.4.2019
 * @author Sakari
 */
public class TestingPanel extends javax.swing.JFrame {

    private Zone checkable;
    private LocationList listing;
    private BufferedImage previewImg;
    private Location thisPlace;
    private Entity thisItem;
    private MouseMastery mauser;
    private Thread colorThread;
    private GraphicsWindow graphics;
    private int nudgeX;
    private Situation state;
    private MainFrame main;
    
    /**
     * Creates new form TestingPanel
     */
    public TestingPanel() {
        initComponents();
        
        colorThread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Color c;
                        if (previewImg != null) {
                            Rectangle box = new Rectangle(previewImg.getWidth(), previewImg.getHeight());
                            if (box.contains(mauser.mouseX(), mauser.mouseY())) {
                                c = new Color(previewImg.getRGB(mauser.mouseX(), mauser.mouseY()));
                                labelColor.setText("R" + c.getRed() + " G" + c.getGreen() + " B" + c.getBlue());
                                labelColor.setForeground(c);
                                int pixel = previewImg.getRGB(mauser.mouseX(), mauser.mouseY());
                                if ((pixel >>> 24) == 0x00)
                                    labelColor.setText("Transu!");
                            }
                        }
                        
                        if (mauser != null) {
                            int mX = mauser.mouseX();
                            int mY = mauser.mouseY();
                            mouseXY.setText(mX + ":" + mY);
                            if (thisPlace != null)
                                labelFound.setText("" + thisPlace.contains(mX - state.nudgeX(), mY));
                        }
                        
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted:\n" + e);
                    }
                }
            }
        };
    }

    public void setZone(Zone area) {
        if (area != null) {
            checkable = area;
            listing = checkable.locations();
            sliderX.setMaximum(checkable.zoneWidth());
            sliderY.setMaximum(checkable.zoneHeight());
            System.out.println("Size: " + listing.size());
        }
    }
    
    public void setMouse(MouseMastery mouse) {
        if (mouse != null)
            mauser = mouse;
    }
    
    private void updateField() {
        sliderX.setValue(thisPlace.locaX());
        sliderY.setValue(thisPlace.locaY());
        
        JLabel image = new JLabel(new ImageIcon(previewImg));
        image.setVisible(true);
        previewField.setViewportView(image);
    }
    
    public void setGraphics(GraphicsWindow gfx) {
        if (gfx != null)
            graphics = gfx;
    }
    
    public void setMain(MainFrame main) {
        if (main != null) {
            this.main = main;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        previewField = new javax.swing.JScrollPane();
        visibilityToggle = new javax.swing.JToggleButton();
        indexPic = new javax.swing.JTextField();
        seekerBtn = new javax.swing.JButton();
        itemName = new javax.swing.JLabel();
        sliderX = new javax.swing.JSlider();
        sliderY = new javax.swing.JSlider();
        labelColor = new javax.swing.JLabel();
        buttonColor = new javax.swing.JToggleButton();
        mouseXY = new javax.swing.JLabel();
        labelFound = new javax.swing.JLabel();

        setTitle("Test Panel");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 0, 51));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        visibilityToggle.setSelected(true);
        visibilityToggle.setText("Visible");
        visibilityToggle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                visibilityToggleItemStateChanged(evt);
            }
        });
        visibilityToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visibilityToggleActionPerformed(evt);
            }
        });

        indexPic.setText("0");
        indexPic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indexPicActionPerformed(evt);
            }
        });

        seekerBtn.setText("Seek");
        seekerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seekerBtnActionPerformed(evt);
            }
        });

        itemName.setText("Name");

        sliderX.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderXStateChanged(evt);
            }
        });

        sliderY.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderY.setInverted(true);
        sliderY.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderYStateChanged(evt);
            }
        });

        labelColor.setText("Pixel color");

        buttonColor.setText("Color");
        buttonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColorActionPerformed(evt);
            }
        });

        mouseXY.setText("mouseXY");

        labelFound.setText("Finder");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(477, 477, 477)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(visibilityToggle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(indexPic))
                            .addComponent(itemName))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(seekerBtn)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sliderY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sliderX, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addComponent(previewField, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(labelColor))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mouseXY)
                            .addComponent(buttonColor)
                            .addComponent(labelFound))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(previewField)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(itemName)
                .addGap(18, 18, 18)
                .addComponent(visibilityToggle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indexPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seekerBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderY, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(labelFound)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mouseXY)
                .addGap(18, 18, 18)
                .addComponent(labelColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonColor)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void indexPicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indexPicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indexPicActionPerformed

    private void seekerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seekerBtnActionPerformed
        String indexValue = indexPic.getText();
        int index = Integer.valueOf(indexValue);
        
        if (index < listing.size() && index >= 0) {
        
            thisPlace = (Location) listing.get(index);
            System.out.println(thisPlace);
            thisItem = (Entity) thisPlace.get(0);
            previewImg = thisItem.icon();
            updateField();
            
        } else if (index == -1) {
            previewImg = checkable.landscape();
            updateField();
        } else
            System.out.println("Size: " + listing.size());
    }//GEN-LAST:event_seekerBtnActionPerformed

    private void visibilityToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visibilityToggleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_visibilityToggleActionPerformed

    private void visibilityToggleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_visibilityToggleItemStateChanged
        System.out.println(thisItem + " " + visibilityToggle.isSelected());
        thisItem.visible(visibilityToggle.isSelected());
    }//GEN-LAST:event_visibilityToggleItemStateChanged

    private void sliderXStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderXStateChanged
        if (thisPlace != null) {
            thisPlace.locaX(sliderX.getValue());
            System.out.println(sliderX.getValue());
        }
    }//GEN-LAST:event_sliderXStateChanged

    private void sliderYStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderYStateChanged
        if (thisPlace != null) {
            thisPlace.locaY(sliderY.getValue());
            System.out.println(sliderY.getValue());
        }
    }//GEN-LAST:event_sliderYStateChanged

    private void buttonColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColorActionPerformed
        if (colorThread != null) {
            if (buttonColor.isSelected()) {
                System.out.println(colorThread.getState());
                colorThread.start();
            } else
                colorThread.interrupt();
        } else
            System.out.println("Thread is null.");
    }//GEN-LAST:event_buttonColorActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        main.testpanel(false);
        System.out.println("Closing Test Panel");
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestingPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestingPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestingPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestingPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestingPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton buttonColor;
    private javax.swing.JTextField indexPic;
    private javax.swing.JLabel itemName;
    private javax.swing.JLabel labelColor;
    private javax.swing.JLabel labelFound;
    private javax.swing.JLabel mouseXY;
    private javax.swing.JScrollPane previewField;
    private javax.swing.JButton seekerBtn;
    private javax.swing.JSlider sliderX;
    private javax.swing.JSlider sliderY;
    private javax.swing.JToggleButton visibilityToggle;
    // End of variables declaration//GEN-END:variables
}