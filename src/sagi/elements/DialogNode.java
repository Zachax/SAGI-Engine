package sagi.elements;

/* Version history:
 * 4.5.2019: Class created. (0.1)
 */

/** 
 * This is a dialogue node class for SAGI engine.
 *
 * @author Sakari Eskelinen
 * Class created: 4.5.2019
 * Last update: 4.5.2019
 * Version: 0.1
 */
public class DialogNode extends Includes {
   /*
    * Attributes
    */
   
   
   /*
    * Builders
    */
   
   DialogNode() {
      super();
      name("A dialogue");
   }
   
   DialogNode(String named) {
      super();
      name(named);
   }
    
   /*
    * Accessors
    */
   
   
   
   @Override
   public String toString() {
      String info = name;
      return info;
   }
    
   /*
    * Other Methods
    */
}