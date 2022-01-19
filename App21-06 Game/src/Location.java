import java.util.Objects;
import java.util.Set;
import java.util.HashMap;

/**
 * Class Location - a location on the map of an adventure game.
 * <p>
 * A "Location" represents one place in the scenery of the game.  It is
 * connected to other locations via exits.  For each existing exit, the
 * location stores a reference to the neighboring locations.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 18th January 2022
 * @modified Tomás Pinto
 */

public class Location
{
    private String description;
    // Stores the exits of this room.
    public HashMap<String, Location> exits;
    // Creates the player inventory
    public static HashMap<String, Item> inventory;
    // Stores the location of the items in the game
    public HashMap<String, Item> itemlist;
    // Score and grades of the player
    public Item item;
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
     *
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

        if (inventory.size() == 0)
        {
            return returnString + getStatus();
        }

        return returnString + ("\n" + getInventoryString() + getStatus());
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * <p>
     * Here is the story and challenge of each location
     * <p>
     * Exits: north, west
     * Inventory: beer, card
     * Score: 50% | Grades 60%
     */
    public String getLongDescription()
    {
        System.out.println(" You are " + description + "\n");
        String returnString = chooseStory() + getExitString();
        if (inventory.size() == 0)
        {
            returnString = returnString + getStatus();
        }
        else
        {
            returnString = returnString + "\n" + getInventoryString() + getStatus();
        }

        // In the last challenge of halls and graduation,
        // we don't need the exits and other information anymore.
        if (Map.graduation.equals(Map.getCurrentLocation()))
        {
            if (Story.challengeCount == 7)
            {
                returnString = chooseStory();
            }
        }
        else if (Map.halls.equals(Map.getCurrentLocation()))
        {
            if (Story.challengeCount == 6)
            {
                returnString = chooseStory();
            }
        }

        return returnString;
    }

    /**
     * @return the score and grades of the current player
     */
    public String getStatus()
    {
        status = "\n Score: " + Game.currentPlayer.getScore() + "%" + "  Grades: " + Game.currentPlayer.getGrades() + "%";
        return status;
    }

    /**
     * Return a string listing the locations's exits,
     * for example "Exits: north, west".
     */
    private String getExitString()
    {
        String exitNames = " Exits: ";
        Set<String> keys = exits.keySet();

        for (String exit : keys)
        {
            if (exitNames.length() > 8)
                exitNames += ", " + exit;
            else
                exitNames += exit;
        }
        return exitNames;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Location getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     * Set the item in a HashMap
     *
     * @param item the item object
     * @param name The name of the item
     */
    public void setItem(Item item, String name)
    {
        this.item = item;
        itemlist.put(name, item);
    }

    /**
     * Take item from a location and add it to player's inventory
     *
     * @param itemName The item name
     */
    public void takeItem(String itemName)
    {
        if (itemlist.containsKey(itemName))
        {
            Item itemIndex = itemlist.get(itemName);
            inventory.put(itemName, itemIndex);
            // Remove the item from the current location
            itemlist.remove(itemName);
            System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "The item '" + itemName + "' has been taken. " + ConsoleColours.ANSI_RESET);
            pointsForTaking(itemName);
            System.out.println("\n" + getShortDescription());
        }
        else if (inventory.containsKey(itemName))
        {
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "The item '" + itemName + "' is already in your inventory " + ConsoleColours.ANSI_RESET);
        }
        else
        {
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "There is no such item as '" + itemName + "' in your current location! " + ConsoleColours.ANSI_RESET);
        }
    }

    /**
     * Returns the Player's Inventory
     *
     * @return
     */
    public String getInventoryString()
    {
        String itemNames = " Inventory: ";
        Set<String> keys = inventory.keySet();

        for (String item : keys)
        {

            if (itemNames.length() > 12)
            {
                itemNames += ", " + item;
            }
            else
            {
                itemNames += item;
            }
        }
        return itemNames;
    }

    /**
     * Depending on the current location of the player,
     * it displays the story and challenge of each place.
     *
     * @return
     */
    public String chooseStory()
    {

        if (Map.getCurrentLocation() == Map.outside)
        {
            Story.outsideStory();
        }
        else if (Map.getCurrentLocation() == Map.halls)
        {
            Story.hallsStory();
        }
        else if (Map.getCurrentLocation() == Map.reception)
        {
            Story.receptionStory();
        }
        else if (Map.getCurrentLocation() == Map.su)
        {
            Story.suStory();
        }
        else if (Map.getCurrentLocation() == Map.graduation)
        {
            Story.graduationStory();
        }
        else if (Map.getCurrentLocation() == Map.cafe)
        {
            Story.cafeStory();
        }
        else if (Map.getCurrentLocation() == Map.lab)
        {
            Story.labStory();
        }
        else if (Map.getCurrentLocation() == Map.pub)
        {
            Story.pubStory();
        }
        return "";
    }

    /**
     * Some items get points for being taken from a location,
     * and they increase or decrease the status attributes
     * of the player.
     *
     * @param itemName
     */
    public void pointsForTaking(String itemName)
    {
        if (Objects.equals(itemName, "sanitizer"))
        {
            Game.currentPlayer.changeScore(5);
            System.out.println(ConsoleColours.ANSI_GREEN + " Thank you for keeping our campus Covid-19 secure!" + ConsoleColours.ANSI_RESET);
        }
        if (Objects.equals(itemName, "water"))
        {
            Game.currentPlayer.changeScore(5);
        }
        if (Objects.equals(itemName, "snack"))
        {
            Game.currentPlayer.changeScore(-10);
        }
        if (Objects.equals(itemName, "beer"))
        {
            Game.currentPlayer.changeScore(5);
            System.out.println(ConsoleColours.ANSI_GREEN + " Enjoy the beer :)" + ConsoleColours.ANSI_RESET);
            Player.getCoins(1);
        }
        if (Objects.equals(itemName, "diploma"))
        {
            Player.win = true;
            System.out.println(ConsoleColours.ANSI_GREEN + " Congrats, you have won the game by cheating! That's life sometimes :)" + ConsoleColours.ANSI_RESET);
        }
    }
}

