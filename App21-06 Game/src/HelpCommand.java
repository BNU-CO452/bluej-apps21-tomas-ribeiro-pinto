
/**
 * HelpCommand is the class that prints
 * some helpful information to the user
 *
 * @author Tomás Pinto
 * @version 18th January 2022
 */
public class HelpCommand extends ZuulCommand
{
    public HelpCommand(Game zuul)
    {
        super(zuul);
    }

    /**
     * Print out some help information and
     * a list of the command words.
     */
    public void execute()
    {
        System.out.println(" You are lost. You are alone. You wander");
        System.out.println(" around at the university. You need help...");
        System.out.println();
        System.out.println(" Your command words are:");
        System.out.println();

        //For each loop that generates the help table with
        // command words and their descriptions.
        for(CommandWords command : CommandWords.values())
        {
            System.out.println(" " + command.word + 
                               "\t  : " + command.description);                        
        }   
        System.out.println();
        System.out.println(" e.g. go west, take beer");
        System.out.println();
        System.out.println(Map.getCurrentLocation().getLongDescription());
    }
}
