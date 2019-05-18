/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sagi.resource;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sagi.elements.*;

/**
 *
 * @author Sakari Eskelinen (se88018@student.uta.fi)
 * Created 6.7.2017
 * Last edit 13.4.2019
 */
public class TestingAreas {
    private Zone testZone;
    private int height = 650;
    private int width = 900;
    private Location mainCharSpot;
    
    public TestingAreas() {
        testZone = new Zone();
        testZone.name("Test Zone");
        testZone.zoneWidth(width);
        testZone.zoneHeight(height);
        BufferedImage backgroundImg = loadPic("scan0007.jpg");
        testZone.landscape(backgroundImg);
        
        LocationList zoneLocations = new LocationList("Testing Locations");
        LocationList zoneLocations2 = new LocationList("Testing Locations2");
        
        // Creating secondary test zone
        Zone bZone = new Zone();
        bZone.name("Kupland");
        bZone.zoneWidth(width);
        bZone.zoneHeight(height);
        BufferedImage bZoneBg = loadPic("kupa.jpg");
        System.out.println("Setting background for k: " + bZone.landscape(bZoneBg));
        
        int locaCount = (int) (Math.random() * 1) + 1;
        for (int i = 0; i < locaCount; i++) {
            int a = (int) (Math.random() * width);
            int b = (int) (Math.random() * height);
            Location newSpot = new Location(a, b);
            Item randomItem = new Item();
            randomItem.icon(rndPic());
            
            String namingL = "Location " + i;
            newSpot.name(namingL);
            String namingI = "Item " + i;
            randomItem.name(namingI);
            
            newSpot.add(randomItem);
            zoneLocations.add(newSpot);
            System.out.println(i + " " + locaCount + " X: " + a + " Y: " + b);
        }
        
        BufferedImage basicDoor = loadPic("crudewoodendoor.png");
        Gate geit = new Gate(bZone);
        geit.icon(basicDoor);
        Location gateSpot = new Location(300, 500);
        gateSpot.add(geit);
        zoneLocations.add(gateSpot);
        
        Gate gateBack = new Gate(testZone);
        gateBack.icon(basicDoor);
        Location gateBackS = new Location(1300, 450);
        gateBackS.add(gateBack);
        zoneLocations2.add(gateBackS);
        
        // Character creation with location
        mainCharSpot = new Location(500, 500);
        mainCharSpot.name("MainChar");
        zoneLocations.add(mainCharSpot);
        BufferedImage mainCharImg = loadPic("taustaton.png");
        Agent sakari = new Agent(sagi.elements.References.PLAYER_NAME);
        sakari.icon(mainCharImg);
        sakari.playable(true);
        mainCharSpot.add(sakari);
        mainCharSpot.layer(10);
        
        testZone.locations(zoneLocations);
        bZone.locations(zoneLocations2);
    }
    
    private BufferedImage rndPic() {
        int count = (int) (Math.random() * 6);
        String fileName = "scan000" + count + ".jpg";
        BufferedImage pic = loadPic(fileName);
        return pic;
    }
    
    private BufferedImage loadPic(String fileName) {
        try {
            File imgFile = new File (System.getProperty("user.dir") + "/res/" + fileName);
            System.out.println(imgFile);
            BufferedImage picture = ImageIO.read(imgFile);
            return picture;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public Zone testArea() {
        return testZone;
    }
    
    public Location mainCharLoc() {
        return mainCharSpot;
    }
}
