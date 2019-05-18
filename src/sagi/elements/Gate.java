package sagi.elements;

/* Version history:
 * 23.6.2017: Class created (0.1)
 * 19.4.2019: Fixed builder.
 */

/** 
 * This class is a gate entity for SAGI, for entrances between Zones.
 * It is expected that each implementation of a gate will be placed on a Location.
 *
 * @author Sakari Eskelinen
 * Class created: 23.6.2017
 * Last update: 19.4.2019
 * Version: 0.1.1
 */
public class Gate extends Entity {
   /*
    * Attributes
    */
   
   /** This is the Zone where this Gate is supposed to take the character if entered. */
   Zone target;
   
   /** Main state of the Gate: whether it's open (enterable) or not. */
   boolean open;
   
   /*
    * Builders
    */
   
    public Gate() {
       super();
      name("A Gate");
      open = false;
   }
   
   /** This is expected to be the default builder. Automatically creates "Gate to X" type of a name. */
   public Gate(Zone zone) {
       super();
      target(zone);
      open = true;
      
      String named = "Gate to " + zone.name();
      name(named);
   }
    
   /*
    * Accessors
    */
   
   public Zone target() {
      return target;
   }
   
   public boolean target(Zone there) {
      if (there != null) {
         target = there;
         return true;
      } else
         return false;
   }
   
   public String targetName() {
      if (target != null) {
         String targetName = target.name();
         return targetName;
      } else
         return null;
   }
   
   public boolean open() {
      return open;
   }
   
   public void open(boolean setOpen) {
      open = setOpen;
   }
    
   @Override
   public String toString() {
      String info = super.toString() + " Target: " + target;
      return info;
   }
    
   /*
    * Other Methods
    */
}