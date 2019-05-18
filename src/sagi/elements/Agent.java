package sagi.elements;

import java.awt.image.*;

/* Version history:
 * 23.6.2017: Class created (0.09)
 * 5.7.2017: Fixed typos. (0.099)
 * 6.7.2017: Renamed class from Character to Agent in order to avoid conflicts. (0.0991)
 */

/** 
 * Character class for SAGI engine.
 * All entities that are in-game characters, whether PC or NPC, should be instances of this class.
 * There might become subclasses for this and otherwise this class is definitely unfinished at version 0.1.
 * In fact I decided to leave the class even more unfinished (0.09) for now.
 *
 * @author Sakari Eskelinen
 * Class created: 23.6.2017
 * Last update: 6.7.2017
 * Version: 0.0991
 */
public class Agent extends Entity {
   /*
    * Attributes
    */
    
   /** Whether or not this character can be a player character. True == PC, false == NPC */
   private boolean playable;
   
   /** Maximum health points of the character.
   Health might not be used in a typical SAGI game, but it's still made there for having something in. */
   private int HPmax;
   
   /** The current health points. */
   private int HPnow;
   
   /** Whether the character is currently alive or not. */
   private boolean alive;
   
   /** Image of the character when faced towards the player. */
   private BufferedImage imgFront;
   
   /** Items that the character is carrying. */
   private Bag pockets;
   
   /** Default max HP of a character */
   private int defaultHP = 50;
   
   /*
    * Builders
    */
    
   public Agent() {
      HPmax = defaultHP;
      HPnow = defaultHP;
      alive = true;
      pockets = new Bag();
   }
    
   /** @param namePerson == name of the character created.
   Automatically names pockets according to the character name, even while this might not get used. */
   public Agent(String namePerson) {
      HPmax = defaultHP;
      HPnow = HPmax;
      alive = true;
      String pocketInfo = "Pockets of " + namePerson;
      pockets = new Bag(pocketInfo);
      name(namePerson);
   }
    
   /*
    * Accessors
    */
   
   public boolean playable() {
      return playable;
   }
   
   public void playable(boolean playMe) {
      playable = playMe;
   }
   
   public int HPmax() {
      return HPmax;
   }
   
   /** Currently max HP can be less than zero. */
   public void HPmax(int maxHP) {
      HPmax = maxHP;
   }
   
   /** Can not add more health than is the current max health. If attempted, HPnow caps to max.
   This is potentially changed later on. */
   public void HPnow(int nowHP) {
      if (nowHP <= HPmax) {
         HPnow = nowHP;
      } else
         HPnow = HPmax;
   }
   
   public int HPnow() {
      return HPnow;
   }
   
   /* HP reduction/increase methods are currently not implemented, as those might not be necessary in the game time for start. */
   
   public void alive(boolean kill) {
      alive = kill;
   }
   
   public boolean alive() {
      return alive;
   }
   
   public BufferedImage imgFront() {
      return imgFront;
   }
   
   public boolean imgFront(BufferedImage pic) {
      if (pic != null) {
         imgFront = pic;
         return true;
      } else
         return false;
   }
   
   public Bag pockets() {
      return pockets;
   }
   
   public boolean pockets(Bag newBag) {
      if (newBag != null) {
         pockets = newBag;
         return true;
      } else
         return false;
   }
   
   @Override
   public String toString() {
      String info = super.toString() + " HP: " + HPnow + "/" + HPmax + " " + alive + " Has " + pockets.size() + " items";
      return info;
   }
    
   /*
    * Other Methods
    */
}