package sagi.elements;

/* Version history:
 * 23.6.2017: Class created. (0.1)
 * 5.7.2017: Fixed minor typos. (0.11)
 */

/** 
 * This is a basic item container class for SAGI engine.
 *
 * @author Sakari Eskelinen
 * Class created: 23.6.2017
 * Last update: 5.7.2017
 * Version: 0.11
 */
public class Bag extends Includes {
   /*
    * Attributes
    */
   
   /** This defines whether it's ok for the bag to contain other bags; otherwise only Item class instances are accepted. */
   private boolean canContainBags;
   
   /*
    * Builders
    */
   
   Bag() {
      super();
      name("A container");
   }
   
   Bag(String named) {
      super();
      name(named);
   }
    
   /*
    * Accessors
    */
   
   public boolean canContainBags() {
      return canContainBags;
   }
   
   public void canContainBags(boolean bagsIn) {
      canContainBags = bagsIn;
   }
   
   /** Can only add Item class entities, plus Bag entities in case it is set allowed. */
   @Override
   public boolean add(Object newItem) {
      if (newItem != null) {
         if (newItem instanceof Item) {
            this.add(newItem);
            return true;
         } else if (newItem instanceof Bag) {
            if (canContainBags) {
               this.add(newItem);
               return true;
            } else
                return false;
         } else
            return false;
      } else
         return false;
   }
   
   @Override
   public String toString() {
      String info = super.toString() + " Contains " + size() + " objects.";
      return info;
   }
    
   /*
    * Other Methods
    */
}