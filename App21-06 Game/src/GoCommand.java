
/**
 * This command transfers the player from
 * one location to another location provided the
 * two locations are linked by a valid exit
 *
 * @author Derek Peacock & Nicholas Day
 * @modified Tomás Pinto
 * @version 18th January 2022
 */
public class GoCommand extends ZuulCommand
{
    String direction;
    
    public GoCommand(Game zuul, String direction)
    {
        super(zuul);
        this.direction = direction;
    }

    /**
     * Executes the Go command
     */
    public void execute()
    {
        if(direction == null) 
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        Map map = zuul.MAP;
        
        // Try to leave current room.
        Location currentLocation = Map.getCurrentLocation();
        Location nextLocation = currentLocation.getExit(direction);

        if (nextLocation == null) 
        {
            System.out.println("There is no exit in that direction!");
        }
        else
        {
            //If the player has the student card in their inventory
            if(Location.inventory.containsKey("card")){
                map.enterLocation(nextLocation);
                System.out.println(Map.getCurrentLocation().getLongDescription());
            }
            else{
                System.out.println(" You need your student card to enter in the building! ");
            }
        }
    }
}
