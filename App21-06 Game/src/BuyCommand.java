
/**
 * This command allows the user to buy
 * some extras while at the location of the pub.
 * This can be made with coins earned in the game
 * to increase score or grades.
 *
 * @author Tomás Pinto
 * @version 18th January 2022
 */
public class BuyCommand extends ZuulCommand
{
    //Type of product: grades or score
    String product;


    public BuyCommand(Game zuul, String product)
    {
        super(zuul);
        this.product = product;
    }

    /**
     * Executes the command from the reader
     */
    public void execute() {
        if (Map.getCurrentLocation() == Map.pub) {
            if (product == null) {
                // if there is no second word, we don't know what to buy...
                System.out.println("Buy what?");
            }
            else if (product.equals("score")) {
                //Each extra is 5 coins
                if (Player.wallet >= 5) {
                    Game.currentPlayer.changeScore(5);
                    Player.wallet = Player.wallet - 5;
                }
                else {
                    System.out.println(" " + ConsoleColours.ANSI_RED + "You do not have enough money to buy the selected product." + ConsoleColours.ANSI_RESET);
                }
            }
            else if (product.equals("grades")) {
                if (Player.wallet >= 5) {
                    Game.currentPlayer.changeGrades(5);
                    Player.wallet = Player.wallet - 5;
                    System.out.println(" You have: £" + Player.wallet + " in your wallet.");
                }
                else {
                    System.out.println(" " + ConsoleColours.ANSI_RED + "You do not have enough money to buy the selected product." + ConsoleColours.ANSI_RESET);
                    System.out.println(" You have: £" + Player.wallet + " in your wallet.");
                }
            }
            else{
                System.out.println(" " + ConsoleColours.ANSI_RED + "You can only buy score or grades" + ConsoleColours.ANSI_RESET);
            }
        }

        //This command is only available in the pub
        else{
            System.out.println(" " + ConsoleColours.ANSI_RED + "To buy something, you need to go to the pub!" + ConsoleColours.ANSI_RESET);
        }
    }


}
