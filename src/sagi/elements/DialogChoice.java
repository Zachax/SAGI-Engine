/*
 * This is stuff made by Sakari Eskelinen.
 * There are no set territories, and no rules of engagement.
 * tyhmamooses@hotmail.com
 * Version history:
 * 4.5.2019: Class created.
 */
package sagi.elements;

/**
 *
 * @author sakari
 */
public class DialogChoice extends Entity {
    /*
    * Attributes
    */
    
    /* If this dialogue choice has been picked, the following dialogue branch
    will follow.*/
    private DialogNode result;
   
    /*
    * Builders
    */
    public DialogChoice() {
        super();
        name = "Conversation selection.";
    }
    
    /*
    * Accessors
    */
    public DialogNode result() {
        return result;
    }
    
    /* Nulls are allowed, as null result is expected to end the conversation. */
    public void result(DialogNode dn) {
        result = dn;
    }
   
    
    /*
    * Other Methods
    */
    
    @Override
    public String toString() {
        String info = name;
        return info;
    }
}
