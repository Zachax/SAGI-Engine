package sagi.elements;

import java.awt.image.*;

/* Version history:
 * 15.6.2017: Class started
 * 23.6.2017: Revisions according to a new class plan: (0.1)
   - Removed name, description and picture attributes, since they're already in the entity superclass.
   - Removed also contents, as it should be included by interface.
   - Renamed methods for overloading instead of get/set.
   - Adjusted methods to call super class whenever possible.
 * 5.7.2017: Minor fixes & adjustment for parent class changes. (0.11)
 * 6.7.2017: Added layer variable. (0.12)
 * 10.7.2017: Added variables for mounted Entity's image's measures. 
    Also updated accessors, so that data related to that is updated when contents are changed. (0.13)
 * 19.4.2019: Minor updates, fixes.
 * 4.5.2019: Changed function find() to exists() due conflict with parent class.
 */

/** 
 * Location class for SAGI program.
 * This element is intended for Zones as sort of containers for accessable objects on the zone.
 * The intention is that there should be only few or even just one object per location, despite being a container type item.
 *
 * Not all locations need to have a name or description, but if an icon exists, it's meant to represent the location on the map.
 *
 * @author Sakari Eskelinen
 * Class created: 15.6.2017
 * Last edit: 4.5.2019
 * Version: 0.14.1
 */
public class Location extends Includes {
   /*
    * Attributes
    */
   
   // X coordinate
   private int locaX;
   
   // Y coordinate
   private int locaY;
   
   /** True == the location is actual concrete location.
   * While this is true, the coordinates might not be relevant.
   */
   private boolean concrete;
   
   /**
    * This variable indicates on how top level the location is. Eg. layer 1 goes below 2.
    */
   private int layer;
   
   /**
    * Width and height in pixels of the presumed only item (BufferedImage icon) located in the Location.
    * Note! This won't get updated automatically if the contents are changed from the mounted entity. 
    * Changes are automatically updated when there are changes in the object mounted on the Location.
    */
   private int width;
   private int height;
      
   /*
    * Builders
    */
    
   public Location(int x, int y) {
      super();
      locaX = x;
      locaY = y;
      concrete = true;
      layer = 1;
   }
    
   /*
    * Accessors
    */
    
   /** There is no checkup for the locaton.
   Negative values are possible - they might just not appear on the map. */
   public void locaX(int x) {
      locaX = x;
   }
   
   public int locaX() {
      return locaX;
   }
   
   public int locaY() {
      return locaY;
   }
   
   /** Negative values are possible - they might just not appear on the map. */
   public void locaY(int y) {
      locaY = y;
   }
   
   /**
    * Whether the Location is set concrete or not.
    * @return 
    */
   public boolean concrete() {
      return concrete;
   }
   
   public void concrete(boolean real) {
      concrete = real;
   }
   
   /**
    * Sets new layer (what is behind what).
    */
   public void layer(int l) {
       layer = l;
   }
   
   /**
    * Returns layer depth of a location. 10 goes as default main char for now.
    * @return Int value has no limitations as such.
    */
   public int layer() {
       return layer;
   }
   
   public int height() {
       return height;
   }
   
   public int width() {
       return width;
   }
   
   /** Actual adding is partially hidden within Includes interface. */
   @Override
   public boolean add(Object item) {
      if (item != null && item instanceof Entity) {
         boolean success = this.addItem(item);
         if (this.size() == 1)
             updateSize();
         return success;
      } else
         return false;
   }
   
   /** 
    * Calls the parent class for actual removal of an item, however it also updates Location's size variables,
    * in case removal is successfull. 
    */
   @Override
   public boolean remove(int index) {
       boolean returnValue = super.remove(index);
       if (returnValue)
           updateSize();
       return returnValue;
   }
    
   @Override
   public String toString() {
      String info = super.toString() + "\nX: " + locaX + " Y: " + locaY + " Concrete: " + concrete + " " + this.name();
      return info;
   }
    
   /*
    * Other Methods
    */
   
   /**
    * Updates height and width variables according to the first (and presumably only) item included in the contents of the Location.
    * If there are no contents to detect, or the 1st content item has no image in icon slot, sizes are reduced to zero.
     * @return True if update in effect; false when no icon found and values are reset.
    */
    public boolean updateSize() {
        // Flag for checking whether to reset size variables.
        boolean bummer = true;
        if (size() > 0) {
            Entity mounted = (Entity) this.get(0);
            BufferedImage pic = mounted.icon();
            if (pic != null) {
                height = pic.getHeight();
                width = pic.getWidth();
                bummer = false;
            } else
                bummer = true;
        } 
        if (bummer) {
            height = 0;
            width = 0;
        }
        return (!bummer);
    }
    
    /** 
     * Indicates whether the given spot is within limits of the image mounted on the Location's first item.
     * The method assumes that every item mounted on a location is standing on the middle and on top of the location.
     * (X/2 and Y grows negative compared to the Location X,Y.)
     * Due algorithm includes divided as integer, it's possible that the detection range goes off by a pixel.
     * @param x
     * @param y
     * @return 
     */
    public boolean contains(int x, int y) {
        if (width > 0 && height > 0) {
            int tempX = x - locaX + (width/2);
            int tempY = y - locaY + height;
            if (width >= tempX && tempX >= 0 && height >= tempY && tempY >= 0) {
                return true;
            } else
                return false;
        } else
            return false;
    }
    
    /**
     * Checks and returns an Entity if found by name on the Location.
     * @param name
     * @return 
     */
    public Entity find(String name) {
        Entity e = null;
        for (int i = 0; i < contents.size(); i++) {
            Entity item = (Entity) contents.get(i);
            if (item.name().equals(name)) {
                e = item;
                break;
            }
        }
        return e;
    }
    
    /**
     * Checks if an Entity exist on the Location.
     * @param item
     * @return 
     */
    public boolean exists(Entity item) {
        boolean found = false;
        for (int i = 0; i < contents.size(); i++) {
            if (item == (Entity) contents.get(i)) {
                found = true;
                break;
            }
        }
        return found;
    }
    
    /**
     * Returns the Gate of the Location - if there is any.
     * @return 
     */
    public Gate getGate() {
        Gate g = null;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i) instanceof Gate) {
                g = (Gate) contents.get(i);
            }
        }
        return g;
    }
    
    /**
     * Returns the Player of the Location - if there is any.
     * @return 
     */
    public Agent getPlayer() {
        Agent g = null;
        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i) instanceof Agent) {
                g = (Agent) contents.get(i);
                if (g.playable()) {
                    //System.out.println("I have " + g + " from Location contents.");
                    break;
                } else {
                    g = null;
                }
            }
        }
        return g;
    }
}