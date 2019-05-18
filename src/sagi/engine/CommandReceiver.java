/*
 * This is stuff made by Sakari Eskelinen.
 * There are no set territories, and no rules of engagement.
 * tyhmamooses@hotmail.com
 *
 * Class created 19.4.2019
 */
package sagi.engine;

import sagi.elements.*;

/**
 * This class is to handle user interface given commands.
 * @author sakari
 */
public class CommandReceiver {
    private Situation sit;
    
    public CommandReceiver(Situation s) {
        situation(s);
    }
    
    public void situation(Situation s) {
        if (s != null) {
            sit = s;
        }
    }
    
    /**
     * Deliver mouseclick on x y coordinates into a command.
     * @param x
     * @param y
     * @param count
     * @param am If a character is meant to be moved to the location.
     */
    public void click(int x, int y, int count, AgentMover am) {
        if (count > 0 && am != null) {
            am.mouseComIn(x, y, count);
        }
        Entity target = callSpot(x, y);
        if (target != null) {
            figureAction(target);
        }
    }
    
    /**
     * Find Entity on the referred coordinates of the zone.
     * @param x
     * @param y 
     */
    public Entity callSpot(int x, int y) {
        String item = (sit.itemSpotting(x, y));
        if (item != null && item.length() > 0) {
            //System.out.println(item + sit.findItem(item));
            return sit.findItem(item);
        }
        return null;
    }
    
    public void figureAction(Entity e) {
        if (e instanceof Gate) {
            Gate g = (Gate) e;
            if (g.open()) {
                Location player = sit.zone().locations().findLocation(sit.playerName());
                //Location gateSpot = g.findLocation(sit.zone());
                Location target = g.target().findGateTo(sit.zone());
                if (player != null) {
                    player.locaX(target.locaX());
                    player.locaY(target.locaY());
                }
                sit.zone().remove(player);
                sit.zone(g.target());
                sit.zone().add(player);
            }
        }
    }
}
