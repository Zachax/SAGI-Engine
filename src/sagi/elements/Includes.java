package sagi.elements;

/* Version history:
 * 23.6.2017: Class created originally as an interface. (0.1)
 * 5.7.2017: The interface was turned into a class. (0.12)
    - Getters for full container was missing.
 * 6.7.2017: Changed push() to add(). It was not intentional to add stuff to the beginning of a list. (0.121)
 * 19.4.2019: Added basic constructor.
 * 4.5.2019: Added index finder function. (0.123)
 */
 
import java.util.*;
 
/** 
 * A class for SAGI engine.
 * This class is to provide a container basis.
 *
 * @author Sakari Eskelinen
 * File created: 23.6.2017
 * Last update: 4.5.2019
 * Version: 0.123
 */
public class Includes extends Item {
   /*
    * Attributes
    */
    
   /** The amount of slots for separate entities this class can contain.
   In this early phase of programming this it is expected that most itesm will not have any practical max. */
   protected int maxSlots = 999999999; // 1B-1
   
   /** The maximum item mass the container can contain. By default, that is supposedly practically unlimited. */
   protected double maxMass = 999999999;
   
   /** The maximum amount of room the container can provide for total space taken by entities.
   Again, it's set by default to presumably higher values than can ever be hit to in normal operationing. */
   protected int maxArea = 999999999;
   
   /** Limited attribute series go for extra confirmations to the previous values. When they're false by default,
   the quota is not expected to be even checked. But this depends on the handling system not yet existing. */
   protected boolean limitedSlots = false;
   
   protected boolean limitedMass = false;
   
   protected boolean limitedArea = false;
   
   protected LinkedList contents = new LinkedList();
   
   public Includes() {
       super();
   }
    
   /*
    * Accessors
    */
    
   public void maxSlots(int value) {
      maxSlots = value;
   }
   
   public int maxSlots() {
      return maxSlots;
   }
   
   public void maxMass(double value) {
      maxMass = value;
   }
   
   public double maxMass() {
      return maxMass;
   }
   
   public void maxArea(int value) {
      maxArea = value;
   }
   
   public int maxArea() {
      return maxArea;
   }
    
   public boolean limitedSlots() {
      return limitedSlots;
   }
   
   public void limitedSlots(boolean truthness) {
      limitedSlots = truthness;
   }
   
   public boolean limitedMass() {
      return limitedMass;
   }
   
   public void limitedMass(boolean truthness) {
      limitedMass = truthness;
   }
   
   public boolean limitedArea() {
      return limitedArea;
   }
   
   public void limitedArea(boolean truthness) {
      limitedArea = truthness;
   }
   
   /** @return If the value is negative (-1), the container has not been yet initialized. */
   public int size() {
      if (contents != null) {
         return contents.size();
      } else
         return -1;
   }
   
   /** @return The object stored according to the given index value. Value is null if the given index is wrong or the container is null. */
   public Object get(int index) {
      if (contents != null && index >= 0) {
         if (index <= size()) {
            Object entity = contents.get(index);
            return entity;
         } else
            return null;
      } else
         return null;
   }
   
   /** @return Name of the object found with the given index value. Returns null, if nothing is found. 
   Can only reach for Entity class items.*/
   public String getName(int index) {
      Object entity = get(index);
      if (entity instanceof Entity) {
         Entity identified = (Entity) entity;
         String thisName = identified.name();
         return thisName;
      } else
         return null;
   }
   
   /** @return Gives a String[] list of names for the items included in this container item. */
   public String[] getListed() {
      if (contents != null) {
         int size = size();
         String[] nameList = new String[size];
         for (int i = 0; i < size; i++) {
            String oneName = getName(i);
            nameList[i] = oneName;
         }
         return nameList;
      } else
         return null;
   }
   
   /**
    * Finds index of a known Entity included. Returns -1 if not found.
    */
    public int find(Entity o) {
        Entity e = null;
        if (o != null) {
            for (int i = 0; i < contents.size(); i++) {
                if (contents.get(i).equals(o)) {
                    return i;
                }
            }
        } 
        return -1;
    }
   
   /** This method is expected to be overridden in the implementing method, in order to confirm the more specific class type of the added item. */
   public boolean add(Object newItem) {
      return addItem(newItem);
   }
   
   /** Adds an item to the container. 
   Accepts only Entity and subclasses types of items. !! This might be maybe moved to the add method. !!
   Set as protected, so that the actual adding of items should be overwritten within the implementing class for checking type etc.
   @return Returns true if adding successful, failed goes false. */
   protected boolean addItem(Object newItem) {
      if (newItem != null && contents != null) {
         if (newItem instanceof Entity) {
            contents.add(newItem);
            return true;
         } else
            return false;
      } else
         return false;
   }
   
   /** Removes an item from the container according to given index value.
   @return Returns true when successful, failed gives false. */
   public boolean remove(int index) {
      if (contents != null && index >= 0) {
         if (size() > index) {
            Object checking = contents.remove(index);
            if (checking != null)
               return true;
            else 
               return false;
         } else
            return false;
      } else
         return false;
   }
   
   /**
    * Returns the list of contents.
    * @return 
    */
   public LinkedList contents() {
       return contents;
   }
   
   /**
    * Sets a new list of contents.
    * @return Old contents.
    */
   public LinkedList contents(LinkedList newContents) {
       if (newContents != null) {
        LinkedList oldies = new LinkedList(contents);
        contents = new LinkedList(newContents);
        return oldies;
       } else
           return null;
   }
    
   @Override
   public String toString() {
      String info = getClass().getName();
      return info;
   }
    
   /*
    * Other Methods
    */
}