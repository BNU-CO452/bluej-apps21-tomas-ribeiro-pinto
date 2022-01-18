
/**
 * This command allows the player to
 * take or pickup an item from a room
 * and carry it around to use somewhere
 * else
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 */
public class WalletCommand extends ZuulCommand
{
    /**
     * Enables background music to be on or off
     */
    public WalletCommand(Game zuul)
    {
        super(zuul);
    }

    public void execute() {
        if (Map.currentLocation == Map.cafe && Story.cafeCoins > 0){
            Player.getCafeCoins();
        }
        else{
            System.out.println(" Invalid command, there aren't coins to pick up in this location.");
            System.out.println(" " +ConsoleColours.ANSI_BG_BLUE + "You have: £" + Player.wallet + " in your wallet." + ConsoleColours.ANSI_RESET);
        }
    }
}
