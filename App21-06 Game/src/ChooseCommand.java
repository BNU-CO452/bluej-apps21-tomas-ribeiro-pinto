/**
 * Write a description of class ChooseCommand here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChooseCommand extends ZuulCommand
{
    String selection;
    private Player player;
    public Game game;

    public ChooseCommand(Game zuul, String selection)
    {
        super(zuul);
        this.selection = selection;
    }
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and
     * a list of the command words.
     */
    public void execute()
    {
        if(selection == null)
        {
            // if there is no second word, we don't know where to go...
            System.out.println("Please input a number between 1 and 3 >");
            return;
        }
        else if(selection.equals("1")){
            game.currentPlayer = player.players.get(0);
        }

        else if(selection.equals("2")){
            game.currentPlayer = player.players.get(1);
        }

        else if(selection.equals("3")){
            game.currentPlayer = player.players.get(2);
        }
    }
}
