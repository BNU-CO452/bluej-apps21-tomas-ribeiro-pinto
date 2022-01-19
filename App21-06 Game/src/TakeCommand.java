
/**
 * This command allows the player to
 * take or pickup an item from a room
 * and carry it around to use somewhere
 * else
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 */
public class TakeCommand extends ZuulCommand
{
    String itemName;

    /**
     * Take an item from a location and add it
     * to the player's inventory.
     */
    public TakeCommand(Game zuul, String itemName)
    {
        super(zuul);
        this.itemName = itemName;
    }

    public void execute()
    {
        if (itemName == null)
        {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        Map map = zuul.MAP;
        // remove the item from the current room
        // and add it to the player's inventory
        map.getCurrentLocation().takeItem(itemName);
        // Print out a suitable message.
    }
}
