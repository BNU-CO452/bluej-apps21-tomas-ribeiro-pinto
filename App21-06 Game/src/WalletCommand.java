/**
 * This command allows the player to
 * take coins from the café
 *
 * @author Tomás Pinto
 * @version 21st January 2022
 */
public class WalletCommand extends ZuulCommand
{
    public WalletCommand(Game zuul)
    {
        super(zuul);
    }

    /**
     * Gets the coins in the café
     */
    public void execute()
    {
        if (Map.currentLocation == Map.cafe && Story.cafeCoins > 0)
        {
            Player.getCafeCoins();
        }
        else
        {
            System.out.println(" No coins to pick up in this location.");
            System.out.println(" " + ConsoleColours.ANSI_BG_BLUE + "You have: £" + Player.wallet + " in your wallet." + ConsoleColours.ANSI_RESET);
        }
    }
}
