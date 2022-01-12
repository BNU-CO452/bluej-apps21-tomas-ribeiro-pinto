import java.io.Console;
import java.util.Objects;
import java.util.Set;
import java.util.HashMap;

/**
 * Class Location - a location on the map of an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Location" represents one place in the scenery of the game.  It is 
 * connected to other locations via exits.  For each existing exit, the 
 * location stores a reference to the neighboring locations.
 * 
 * @author  Michael Kölling and David J. Barnes
 * Modified by Derek Peacock & Nicholas Day
 * @version 2016.02.29
 */

public class Location 
{
    private String description;
    // stores exits of this room.
    private Item item;
    private Game game;
    private Map map;
    private Story story;
    public HashMap<String, Location> exits;
    public static HashMap<String, Item> inventory;
    public HashMap<String, Item> itemlist;
    public String status;

    /**
     * Create a location described by "description".
     * Initially, a location has no exits.
     * "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Location(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        inventory = new HashMap<>();
        itemlist = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Location neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        String returnString = " You are " + description + "\n \n" + getExitString();

        if(inventory.size() == 0) {
            return returnString + getStatus();
        }

        return returnString + ("\n" + getInventoryString() + getStatus());
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        String returnString = chooseStory() + getExitString();
            if(inventory.size() == 0) {
                return returnString + getStatus();
            }

        return returnString + ("\n" + getInventoryString() + getStatus());
    }

    public String getStatus(){
        status = "\n Score: " + Game.currentPlayer.getScore() + "%" + "  Grades: " + Game.currentPlayer.getGrades() + "%" ;
        return status;
    }

    /**
     * Return a string listing the locations's exits,
     * for example "Exits: north west".
     */
    private String getExitString()
    {
        String exitNames = " Exits: ";
        Set<String> keys = exits.keySet();
        
        for(String exit : keys) 
        {
            if(exitNames.length() > 8)
                exitNames += ", " + exit;
            else
                exitNames += exit;
        }
        return exitNames;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Location getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void setItem(Item item, String name){
        this.item = item;
        itemlist.put(name, item);
    }

    public void takeItem(String itemName) {
        if(itemlist.containsKey(itemName)) {
            Item itemIndex = itemlist.get(itemName);
            inventory.put(itemName, itemIndex);
            itemlist.remove(itemName);
            System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "The item '" + itemName + "' has been taken. " + ConsoleColours.ANSI_RESET);
            pointsForTaking(itemName);
            System.out.println("\n" + getShortDescription());
        }
        else if(inventory.containsKey(itemName)){
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "The item '" + itemName + "' is already in your inventory " + ConsoleColours.ANSI_RESET);
        }
        else{
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "There is no such item as '" + itemName + "' in your current location! " + ConsoleColours.ANSI_RESET);
        }
    }

    public String getInventoryString()
    {
        String itemNames = " Inventory: ";
        Set<String> keys = inventory.keySet();

        for(String item : keys) {

            if (itemNames.length() > 12) {
                itemNames += ", " + item;
            } else {
                itemNames += item;
            }
        }
        return itemNames;
    }

    public String chooseStory(){
        System.out.println(" You are " + description + "\n");
        if (Map.getCurrentLocation() == Map.outside){
            Story.outsideStory();
        }
        else if(Map.getCurrentLocation() == Map.halls){
            Story.hallsStory();
        }
        else if(Map.getCurrentLocation() == Map.reception){
            Story.receptionStory();
        }
        else if(Map.getCurrentLocation() == Map.su){
            Story.suStory();
        }
        else if(Map.getCurrentLocation() == Map.graduation){
            Story.graduationStory();
        }
        else if(Map.getCurrentLocation() == Map.cafe){
            Story.cafeStory();
        }
        else if(Map.getCurrentLocation() == Map.lab){
            Story.labStory();
        }
        else if(Map.getCurrentLocation() == Map.pub){
            Story.pubStory();
        }
        return "";
    }

    public void pointsForTaking(String itemName){
        if(Objects.equals(itemName, "sanitizer")){
            Game.currentPlayer.changeScore(5);
            System.out.println(ConsoleColours.ANSI_GREEN + " Thank you for keeping our campus Covid-19 secure!" + ConsoleColours.ANSI_RESET);
        }
        if(Objects.equals(itemName, "water")){
            Game.currentPlayer.changeScore(5);
        }
        if(Objects.equals(itemName, "snack")){
            Game.currentPlayer.changeScore(-10);
        }
        if(Objects.equals(itemName, "beer")){
            Game.currentPlayer.changeScore(5);
            System.out.println(ConsoleColours.ANSI_GREEN + " Enjoy the beer :)" + ConsoleColours.ANSI_RESET);
        }
    }
}

