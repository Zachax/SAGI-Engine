/* Version history:
 * First created: ?.7.2017
 * 28.8.2017: Implementing Situation class (instead of test/temporary classes).
 * 13.4.2019: Reviewing the code. Added screen size maximums and other adjustments.
 */
package sagi.engine;

import sagi.elements.References;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import sagi.resource.*;
import sagi.elements.*;

/**
 * This class is the main GUI for SAGI engine.
 * @author Sakari
 */
public class MainFrame extends javax.swing.JFrame {
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        TestingAreas testi = new TestingAreas();
        System.out.println("Area used: " + testi);
        initComponents();
        zoneControl = new Situation(testi.testArea(), graphically);
        
        // Inits special features to graphical window
        //graphically.locationZone(testi.testArea());
        graphically.startRunning();
        graphically.setSituation(zoneControl);
        
        
        // Sets main frame size as X% of the screen size.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double sWidth = setScreenSize(screenSize, true);
        double sHeight = setScreenSize(screenSize, false);
        int sWide = (int) (sWidth * 1.0);
        int sHeig = (int) (sHeight * 0.7);
        screenSize = new Dimension(sWide, sHeig);
        setSize(screenSize);
        setLocationRelativeTo(null);
        
        
        mauser = new MouseMastery(this);
        addMouseListener(mauser);
        addMouseMotionListener(mauser);
        
        // Gives movement access to the main character
        mainCharMover(testi.mainCharLoc());
        
        // Creates command (mouseclick etc.) handler
        cr = new CommandReceiver(zoneControl);
        
        // Creates testing panel
        monitor = new TestingPanel();
        monitor.setVisible(testpanel);
        Dimension windowSize = monitor.getBounds().getSize();
        int wWidth = (int) windowSize.getWidth();
        int wHeight = (int) windowSize.getHeight();
        int mLocaX = (int) sWidth - wWidth;
        int mLocaY = (int) sHeight - wHeight;
        monitor.setLocation(mLocaX, mLocaY);
        monitor.setZone(testi.testArea());
        monitor.setMouse(mauser);
        monitor.setGraphics(graphically);
        monitor.setMain(this);
        
        settings = new ArrayList<Entity>();
        loadSettings(settingsFile);
    }
    
    public void mainCharMover(Location mount) {
        primeMover = new AgentMover(this, mount);
        primeMover.mouse(mauser);
    }
    
    public void nudgeX(int a) {
        zoneControl.nudgeX(a);
    }
    
    public void nudgeY(int a) {
        zoneControl.nudgeY(a);
    }
    
    // Seeks for info of the item above which mouse cursor is floating
    public void mouseMoving() {
        Point mark = new Point(mauser.mouseX(), mauser.mouseY());
        SwingUtilities.convertPointFromScreen(mark, this);
        int x = (int) mark.getX();
        int y = (int) mark.getY();
        String itemInfo = zoneControl.itemSpotting(x, y);
        // Provides name of the item the mouse cursor is hovering over
        if (itemInfo != null && itemInfo.length() > 0) {
            infoLabel.setText(itemInfo);
            setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        } else {
            infoLabel.setText(" ");
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
        // Sets mouse coordinate info
        mouseInfo.setText(x + ":" + y);
    }
    
    public void mouseClicked(int c) {
        // Normalizes multiple mouseclick amount
        if (c > 2 || mauser.mouseButton()) {
            c = 3;
        }
        Point mark = new Point(mauser.mouseX(), mauser.mouseY());
        SwingUtilities.convertPointFromScreen(mark, this);
        int x = (int) mark.getX();
        int y = (int) mark.getY();
        if (zoneControl.nudge()) {
            //primeMover.mouseComIn(x - zoneControl.nudgeX(), y, c);
            cr.click(x - zoneControl.nudgeX(), y, c, primeMover);
        } else {
            //primeMover.mouseComIn(x, y, c);
            cr.click(x, y, c, primeMover);
        }
        
    }
    
    // This doesn't work properly atm, so it should be deactivated from MouseMastery.
    public void mousePressed() {
        // primeMover.mouseComIn(mauser.mouseX() - graphically.nudgeX(), mauser.mouseY(), 1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        graphically = new sagi.engine.GraphicsWindow();
        controlPanel = new javax.swing.JPanel();
        infoLabel = new javax.swing.JLabel();
        mouseInfo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        gameMenu = new javax.swing.JMenu();
        quitMenu = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        testWindowCheck = new javax.swing.JCheckBoxMenuItem();
        nudgeCheck = new javax.swing.JCheckBoxMenuItem();
        loadSettings = new javax.swing.JMenuItem();
        saveSettings = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SAGI");
        setResizable(false);

        graphically.setName("SAGI"); // NOI18N

        controlPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), null, java.awt.Color.magenta));

        infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoLabel.setText("Information");
        infoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mouseInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mouseInfo.setText("Information");
        mouseInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(infoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
            .addComponent(mouseInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(infoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mouseInfo)
                .addGap(0, 52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout graphicallyLayout = new javax.swing.GroupLayout(graphically);
        graphically.setLayout(graphicallyLayout);
        graphicallyLayout.setHorizontalGroup(
            graphicallyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        graphicallyLayout.setVerticalGroup(
            graphicallyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, graphicallyLayout.createSequentialGroup()
                .addGap(0, 264, Short.MAX_VALUE)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameMenu.setText("Game");

        quitMenu.setText("Quit Game");
        quitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuActionPerformed(evt);
            }
        });
        gameMenu.add(quitMenu);

        jMenuBar1.add(gameMenu);

        settingsMenu.setText("Setup");
        settingsMenu.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                settingsMenuMenuSelected(evt);
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
        });

        testWindowCheck.setText("Test Window");
        testWindowCheck.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                testWindowCheckStateChanged(evt);
            }
        });
        settingsMenu.add(testWindowCheck);

        nudgeCheck.setSelected(true);
        nudgeCheck.setText("Nudge Frame");
        nudgeCheck.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                nudgeCheckStateChanged(evt);
            }
        });
        settingsMenu.add(nudgeCheck);

        loadSettings.setText("Load Settings");
        loadSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loadSettingsMouseReleased(evt);
            }
        });
        settingsMenu.add(loadSettings);

        saveSettings.setText("Save Settings");
        saveSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                saveSettingsMouseReleased(evt);
            }
        });
        settingsMenu.add(saveSettings);

        jMenuBar1.add(settingsMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphically, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(graphically, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void settingsMenuMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_settingsMenuMenuSelected
        testWindowCheck.setSelected(testpanel);
        if (zoneControl != null) {
            nudgeCheck.setSelected(zoneControl.nudge());
        }
    }//GEN-LAST:event_settingsMenuMenuSelected

    private void testWindowCheckStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_testWindowCheckStateChanged
        testpanel = testWindowCheck.isSelected();
        toggleTestpanel(testpanel);
    }//GEN-LAST:event_testWindowCheckStateChanged

    private void saveSettingsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveSettingsMouseReleased
        System.out.println("Saving settings...");
        saveSettings(settingsFile);
    }//GEN-LAST:event_saveSettingsMouseReleased

    private void loadSettingsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadSettingsMouseReleased
        System.out.println("Loading settings...");
        loadSettings(settingsFile);
    }//GEN-LAST:event_loadSettingsMouseReleased

    private void nudgeCheckStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_nudgeCheckStateChanged
        if (zoneControl != null) {
            zoneControl.nudge(nudgeCheck.isSelected());
        }
    }//GEN-LAST:event_nudgeCheckStateChanged

    private void quitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitMenuActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
        
    /**
     * Sets screen size according to monitor size.
     * However, limits the size according to maximum set size attribute.
     * @param screenSize
     * @param width
     * @return 
     */
    private double setScreenSize(Dimension screenSize, boolean width) {
        double size;
        if (width) {
            size = screenSize.getWidth();
            if (size > maxScreenWidth) {
                size = maxScreenWidth;
            }
            System.out.println("Screen width set as: " + size);
        } else {
            size = screenSize.getHeight();
            if (size > maxScreenHeight) {
                size = maxScreenHeight;
            }
            System.out.println("Screen height set as: " + size);
        }
        return size;
    }
    
    private void loadSettings(String fileName) {
        try {
            File file = new File (System.getProperty("user.dir") + "/gamedata/" + fileName);
            System.out.println(file);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String setting = reader.nextLine();
                Entity setted = new Entity();
                setted.name(setting.replaceAll("\\:.*","").trim());
                setted.description(setting.replaceAll(".*\\:","").trim());
                System.out.println("Loaded setting " + setted.name() + ":" + 
                        setted.description());
                settings.add(setted);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            applySettings();
        }
    }
    
    private void saveSettings(String fileName) {
        updateCurrentSettings();
        File file = new File(System.getProperty("user.dir") + "/gamedata/" + fileName);
        FileWriter fr = null;
        BufferedWriter br = null;
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            int i = 0;
            while (settings.size() > i){
                br.write(settings.get(i).name() + ": " + settings.get(i).description() +
                        System.getProperty("line.separator"));
                i++;
            }
        } catch (IOException e) {
            System.out.println("Failed!");
            //e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
                System.out.println("Saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Will apply settings that are currently set.
     */
    private void applySettings() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < settings.size(); i++) {
            Entity setting = settings.get(i);
            if (setting.name().startsWith(References.SCREEN_X_DATA)) {
                x = Integer.parseInt(setting.description().replaceAll("\\D+",""));
            } else if (setting.name().startsWith(References.SCREEN_Y_DATA)) {
                y = Integer.parseInt(setting.description().replaceAll("\\D+",""));
            } else if(setting.name().startsWith(References.TESTPANEL)) {
                testpanel = Boolean.parseBoolean(setting.description());
                toggleTestpanel(testpanel);
            } else if(setting.name().startsWith(References.NUDGE)) {
                if (zoneControl != null) {
                    zoneControl.nudge(Boolean.parseBoolean(setting.description()));
                }
            } else if(setting.name().startsWith(References.PLAYER_NAME)) {
                if (zoneControl != null) {
                    zoneControl.playerName(setting.description());
                    //System.out.println("Siis: " + zoneControl.playerName());
                }
            }
        }
        setScreen(x, y);
    }
    
    /**
     * Defines current settings as active ones. These settings can be saved.
     */
    private void updateCurrentSettings() {
        if (settings != null) {
            for (int i = 0; i < settings.size(); i++) {
                Entity setting = settings.get(i);
                if (setting.name().equals(References.SCREEN_X_DATA)) {
                    setting.description("" + getX());
                } else if (setting.name().equals(References.SCREEN_Y_DATA)) {
                    setting.description("" + getY());
                }
            }
        }
    }

    private void setScreen(int x, int y) {
        setLocation(x, y);
    }
    
    private void toggleTestpanel(boolean truth) {
        if (monitor != null) {
            monitor.setVisible(testpanel);
        }
    }
    
    public void testpanel(boolean set) {
        testpanel = set;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel controlPanel;
    private javax.swing.JMenu gameMenu;
    private sagi.engine.GraphicsWindow graphically;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem loadSettings;
    private javax.swing.JLabel mouseInfo;
    private javax.swing.JCheckBoxMenuItem nudgeCheck;
    private javax.swing.JMenuItem quitMenu;
    private javax.swing.JMenuItem saveSettings;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JCheckBoxMenuItem testWindowCheck;
    // End of variables declaration//GEN-END:variables
    // User set variables
    private MouseMastery mauser;
    private AgentMover primeMover;
    private Situation zoneControl;
    private double maxScreenWidth = 1800;
    private double maxScreenHeight = 1000;
    private String settingsFile = "settings.txt";
    private ArrayList<Entity> settings;
    private boolean testpanel = false;
    private TestingPanel monitor;
    private CommandReceiver cr;
}