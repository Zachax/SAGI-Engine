package sagi.elements;

/* Version history:
 * 15.6.2017: Class started
 * 23.6.2017: Revisions according to the new class plan: (0.1)
   - Includes removing some unnecessary attributes & methods due the class being an extension of the Entiry class now.
   - Using own class instead of LinkedList.
   - Overloading in method names instead of get/set.
 * 6.7.2017: Minor adjustments and fixes. (0.11)
 * 28.8.2017: Added location getting methods. (0.12)
 * 13.4.2019: Added a real location fetching method.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
 
/** 
 * Zone class for Sakari's Adventure Game Interface.
 * Represents a zone where characters can move etc.
 *
 * @author Sakari Eskelinen
 * Class created: 15.6.2017
 * Last update: 13.4.2019
 * Version: 0.13
 */
public class Zone extends Entity {
   /*
    * Attributes
    */
   
   /** Locations that might contain something accessable.
   * Such as items or gates to other zones.
   */
   private LocationList locations;
   
   /** General elements that affect the whole zone. */
   private LinkedList features; // Not yet implemented for use
   
   /** Background image for the zone */
   private BufferedImage landscape;
   
   /** Width of the zone in presumed pixels */
   private int zoneWidth;
   
   /** Height of the zone in presumed pixels */
   private int zoneHeight;
   
   /*
    * Builders
    */
   
   public Zone() {
       super();
      locations = new LocationList();
      name("Zone");
      zoneWidth = 800;
      zoneHeight = 600;
   }
   
   public Zone(String named, int w, int h, BufferedImage scenery) {
       super();
      locations = new LocationList();
      landscape(scenery);
      name(named);
      zoneWidth(w);
      zoneHeight(h);
   }
    
   /*
    * Accessors
    */
    
   @Override
   public String toString() {
      String info = super.toString() + " Size (X,Y): " + zoneWidth + " " + zoneHeight;
      return info;
   }
   
   /* Getters & setters for width and height for the zone. */
   
   public int zoneWidth() {
      return zoneWidth;
   }
   
   /** Zone width (or height) can not be a negative value. */
   public boolean zoneWidth(int w) {
      if (w >= 0) {
         zoneWidth = w;
         return true;
      } else
         return false;
   }
   
   public int zoneHeight() {
      return zoneHeight;
   }
   
   public boolean zoneHeight(int h) {
      if (h >= 0) {
         zoneHeight = h;
         return true;
      } else
         return false;
   }
   
   // Background image setting/getting
   public boolean landscape(BufferedImage picture) {
      if (picture != null) {
         landscape = picture;
         return true;
      } else
         return false;
   }
   
   public BufferedImage landscape() {
      return landscape;
   }
   
   /** The system assumes that the contents of the location lists are handled elsewhere. */
   
   // Sets a new list of locations.
   public boolean locations(LocationList locas) {
      if (locas != null) {
         locations = new LocationList(locas);
         return true;
      } else
         return false;
   }
   
   // Returns the list of locations.
   // Warning, editing the returning list will edit the zone's list as well.
   // Since this is a potential source of bugs, might be considered to be changed.
   public LocationList locations() {
      return locations;
   }
    
   /*
    * Other Methods
    */
   
   /**
   * Returns X coordinates of the item on locations list by index value.
   */
    public int getLocX(int indx) {
        if (indx >= 0 && indx < locations.size()) {
            return (locations.getLocaX(indx));
        } else 
            return 0;
    }
    
    /**
   * Returns Y coordinates of the item on locations list by index value.
   */
    public int getLocY(int indx) {
        if (indx >= 0 && indx < locations.size()) {
            return (locations.getLocaY(indx));
        } else 
            return 0;
    }
    
    /**
     * Returns size of location list.
     * @return 
     */
    public int size() {
        return locations.size();
    }
    
    /**
     * Returns the item on location by index. It is presumed that every location has only one item.
     */
    public Entity getItem(int idx) {
        Entity item = null;
        if (locations != null && locations.size() > idx && idx >= 0) {
            Location place = (Location) locations.get(idx);
            item = (Entity) place.get(0);
        }
        return item;
    }
    
    /**
     * Returns a location by index.
     * @param idx
     * @return 
     */
    public Location getLocation(int idx) {
        Location loca = null;
        if (locations != null && locations.size() > idx && idx >= 0) {
            loca = (Location) locations.get(idx);
        }
        return loca;
    }
    
    public void add(Location l) {
        if (l != null && locations != null) {
            locations.add(l);
        }
    }
    
    public void remove(Location l) {
        if (l != null && locations != null) {
            locations.remove(l);
        }
    }
    
    public Location findGateTo(Zone z) {
        Location l = null;
        if (z != null && locations != null) {
            l = locations.findGateTo(z);
        }
        return l;
    }
}