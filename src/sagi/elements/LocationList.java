package sagi.elements;

/* Version history:
 * 23.6.2017: Class started. (0.1)
 * 5.7.2017: Adjustment for parent class changes. (0.11)
   - Copy builder was missing.
 * 28.8.2017: Added getX/Y methods. (0.12)
 * 19.4.2019: Added find methods. 
 * 5.4.2019: Refactored for changed find() to exists() function in parent class. (0.13.1)
 */

/** 
 * This class is a list of Location classes for SAGI engine.
 *
 * @author Sakari Eskelinen
 * Class created: 23.6.2017
 * Last update: 19.4.2019
 * Version: 0.13.1
 */
public class LocationList extends Includes {
   /*
    * Attributes
    */
    
   /** Check Includes interface. */
   
   /*
    * Builders
    */
   
   /** By default location lists are expected to be unique, even while this might not have much practical significance. */
   public LocationList() {
       super();
      name("Unnamed list of locations");
      unique(true);
   }
   
   /** A recommended manner of naming the list would be to include the intended Zone where the list belongs, but it is not necessary to name a list at all. */
   public LocationList(String naming) {
       super();
      name(naming);
   }
   
   /**
    * Copy builder
    * @param locations 
    */
   public LocationList(LocationList locations) {
       name(locations.name());
       unique(locations.unique());
       contents(locations.contents);
   }
    
   /*
    * Accessors
    */
   
   /** Only Locations may be added to this container class. */
   @Override
   public boolean add(Object newItem) {
      if (newItem != null && newItem instanceof Location) {
         boolean success = this.addItem(newItem);
         return success;
      } else
         return false;
   }
    
   @Override
   public String toString() {
      String info = super.toString() + " Size: " + size();
      return info;
   }
    
   /*
    * Other Methods
    */

    /*
   * Returns X coordinates of the item by index value.
   */
    public int getLocaX(int indx) {
        if (indx >= 0 && indx < contents.size()) {
            Location spot = (Location) contents.get(indx);
            return (spot.locaX());
        } else 
            return 0;
    }
    
    /*
   * Returns Y coordinates of the item by index value.
   */
    public int getLocaY(int indx) {
        if (indx >= 0 && indx < contents.size()) {
            Location spot = (Location) contents.get(indx);
            return (spot.locaY());
        } else 
            return 0;
    }
    
    /**
     * Finds the location by name of an Entity on the location.
     * @return null if not found
     */
    public Location findLocation(String name) {
        Location found = null;
        if (name != null) {
            for (int i = 0; i < contents.size(); i++) {
                Location l = (Location) contents.get(i);
                Entity item = l.find(name);
                if (item != null) {
                    found = l;
                    break;
                }
            }
        }
        return found;
    }
    
    /**
     * Finds the location by an Entity on the location.
     * @return null if not found
     */
    public Location findLocation(Entity item) {
        Location found = null;
        if (item != null) {
            for (int i = 0; i < contents.size(); i++) {
                Location l = (Location) contents.get(i);
                if (l.exists(item)) {
                    found = l;
                    break;
                }
            }
        }
        return found;
    }
    
    public Location findGateTo(Zone z) {
        Location l = null;
        if (z != null) {
            for (int i = 0; i < contents.size(); i++) {
                Location spot = (Location) contents.get(i);
                Gate g = spot.getGate();
                if (g != null && g.target() == z) {
                    l = spot;
                    break;
                }
            }
        }
        return l;
    }
    
    public Agent findPlayer() {
        Agent a = null;
        if (contents != null) {
            for (int i = 0; i < contents.size(); i++) {
                Location spot = (Location) contents.get(i);
                Agent g = spot.getPlayer();
                if (g != null) {
                    //System.out.println(g + " was suggested as player from Location.");
                    a = g;
                    break;
                }
            }
        }
        return a;
    }
    
    /**
     * 
     * @param l
     * @return 
     */
    public Location remove(Location l) {
        if (l != null) {
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i) == l) {
                    //System.out.println("I should remove " + contents.get(i));
                    l = (Location) contents.get(i);
                    contents.remove(l);
                }
            }
        }
        return l;
    }
}