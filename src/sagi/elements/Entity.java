package sagi.elements;

import java.awt.image.BufferedImage;

/* Version history:
 * 13.6.2017: Class started
 * 23.6.2017: (0.1)
   * Revisions according to a new class plan:
   - Size attribute removed (moved to another class).
   - Added description and icon attributes instead (with according accessors).
   - Methods renamed according to my overall programming practice of overloading methods instead of hitting get/set in the name.
   - Note that details between class plan visual scheme (if you're ever accessable to that) might not be 100% synched with the actual classes with every detail, since I'm intending to save effort by not keeping the scheme updated all the time. Eg. the scheme might suggest some method to be of void type when the real program would be boolean type. The class plan is meant for my own sketch only, not as an actual documentation, not at least for now.
   * Class reverted from abstract class for certain practical needs to call & create vague Entity classes.
 * 5.-6.7.2017: Fixed minor typos etc. compiler errors. Added visible variable. (0.11)
 * 28.8.2017: Added some comments.
 * 13.4.2019: Added reach. (0.12)
 * 19.4.2019: Added Location finder.
 */

/** 
 * Basic element class for Sakari's Adventure Game Engine.
 *
 * @author Sakari Eskelinen
 * Last edit: 19.4.2019
 * Version: 0.13
 */
public class Entity {
   /*
    * Attributes
    */
    
   /** Whether there is supposedly only one type of this entity.*/
   protected boolean unique;
   
   /** Name of the specific entity. */ 
   protected String name = "Unnamed";
   
   /** Description of the specific entity. */
   protected String description = "This entity has no description.";
   
   /** Image that represents icon of the entity. 
   Icon is not necessarily used, and it also might be the only image out of an entity. */
   protected BufferedImage icon;
   
   /**
    * Whether the entity is visible, ie. drawn.
    */
   protected boolean visible;
   
   /**
    * Distance in pixels till where the entity can affect another.
    */
   protected double reach = 10;
   
   /* 
    * Builders
    */
   
   public Entity() {
      unique = false;
      visible = true;
   }
   
   public Entity(boolean single, String named, String describe, BufferedImage pic) {
      unique = single;
      name(named);
      description(describe);
      icon(pic);
      visible = true;
   }
   
   /*
    * Accessors
    */
   
   /**
    * Gives name for Entity object. Returns true if naming was successful.
    * @param naming
    * @return 
    */
   public boolean name(String naming) {
      if (naming != null) {
         name = naming;
         return true;
      } else
         return false;
   }
   
   /**
    * Returns used name of the Entity, if any.
    * @return 
    */
   public String name() {
      return name;
   }
   
   public boolean description(String describe) {
      if (describe != null) {
         description = describe;
         return true;
      } else
         return false;
   }
   
   public String description() {
      return description;
   }
   
   public void unique(boolean single) {
      unique = single;
   }
   
   public boolean unique() {
      return unique;
   }
   
   public boolean icon(BufferedImage pic) {
      if (pic != null) {
         icon = pic;
         return true;
      } else
         return false;
   }
   
   public BufferedImage icon() {
      return icon;
   }
   
   /**
    * Returns whether the Entity is visible or not.
    * @return 
    */
   public boolean visible() {
       return visible;
   }
   
   /**
    * Sets visibility of the Entity.
    * @param seen
    * @return 
    */
   public void visible(boolean seen) {
       visible = seen;
   }
   
   public double reach() {
       return reach;
   }
   
   public void reach(double a) {
       reach = Math.abs(a);
   }
   
   /*
    * Other Methods
    */
   
   @Override
   public String toString() {
      String info = getClass().getName() + ": " + name + " Unique: " + unique;
      return info;
   }
   
    /**
     * Attempts to find the Location on which the Entity is located on given Zone.
     * @return 
     */
    public Location findLocation(Zone z) {
        Location l = z.locations().findLocation(this);
        return l;
    }
}