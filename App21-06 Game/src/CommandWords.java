
/**
 * This contains all the possible command words
 * for the game of Zuul
 *
 * @author Derek Peacock & Nicholas Day
 * @version 18th January 2022
 * @modified Tomás Pinto
 */
public enum CommandWords
{
    GO("go", "Exit location in a direction"),
    TAKE("take", "Take an item from location"),
    WALLET("wallet", "Get coins and check wallet"),
    BUY("buy", "Buy score and grades to help you"),
    MAP("map", "Show campus map"),
    HELP("help", "List all available commands"),
    MUSIC("music", "Enable or disable background music"),
    QUIT("quit", "End the game");

    public final String word;
    public final String description;

    /**
     * Constructor for CommandWords
     *
     * @param word        : text command
     * @param description : description of the command to be included in help
     */
    CommandWords(String word, String description)
    {
        this.word = word;
        this.description = description;
    }
}
