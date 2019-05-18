package sagi.elements;

/* Version history:
 * 15.6.2017: Class started
 * 23.6.2017: Class finished to version 0.1.
 * 5.7.2017: Fixed typos in methods.
 */

/** 
 * Item class for Sakari's Adventure Game Interface.
 * @author Sakari Eskelinen
 * Class created: 15.6.2017
 * Last update: 5.7.2017
 * Version: 0.11
 */
public class Item extends Entity {
   /*
    * Attributes
    */
   
   /** Weight of an item. */
   private double mass;
   
   /** Whether you can stack multiple of the same type of items in one slot.
   Feature not yet implemented. */
   private boolean stackable;
   
   /** How much room does this item take. */
   private int space;
   
   /** Whether a character can use this item somehow. */
   private boolean usable;
   
   /*
    * Builders
    */ 
   
   public Item() {
      super();
      name("Unidentified Item");
      stackable = true;
      usable = false;
   }
   
   public Item(String name) {
      super();
      name(name);
      usable = true;
   }
   
   /*
    * Accessors
    */
   
   /** No checks on the weight, so it can be a negative value. Theoretically it would mean something that reduces burden. */
   public void mass(double weight) {
      mass = weight;
   }
   
   public double mass() {
      return mass;
   }
   
   /** No checkups, so negative values are possible. Less than 0 values should make no difference from plain 0 though in presumed application. */
   public void space(int size) {
      space = size;
   }
   
   public int space() {
      return space;
   }
   
   public boolean usable() {
      return usable;
   }
   
   public void usable(boolean canUse) {
      usable = canUse;
   }
}