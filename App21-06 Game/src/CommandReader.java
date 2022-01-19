import java.util.Random;
import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 * <p>
 * The reader has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 18th January 2022
 * @modified Tomás Pinto
 */
public class CommandReader
{
    private Game game;
    private Scanner reader; // source of command input

    private String commandWord = null;
    private String word2 = null;

    /**
     * Create a parser to read from the terminal window.
     */
    public CommandReader(Game game)
    {
        this.game = game;
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public boolean getCommand()
    {
        String inputLine;

        System.out.print(" > ");
        inputLine = reader.nextLine().toLowerCase();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);

        if (tokenizer.hasNext())
        {
            commandWord = tokenizer.next();      // get first word

            if (tokenizer.hasNext())
            {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
            else word2 = null;
        }
        return executeCommand();
    }

    /**
     * Executes the command the user inputs
     *
     * @return
     */
    private boolean executeCommand()
    {
        if (commandWord.equals(CommandWords.GO.word))
        {
            //e.g. go south
            GoCommand go = new GoCommand(game, word2);
            go.execute();
        }
        else if (commandWord.equals(CommandWords.TAKE.word))
        {
            //e.g. take beer
            TakeCommand take = new TakeCommand(game, word2);
            take.execute();
        }
        else if (commandWord.equals(CommandWords.WALLET.word))
        {
            WalletCommand wallet = new WalletCommand(game);
            wallet.execute();
        }
        else if (commandWord.equals(CommandWords.BUY.word))
        {
            //e.g. buy score
            BuyCommand buy = new BuyCommand(game, word2);
            buy.execute();
        }
        else if (commandWord.equals(CommandWords.HELP.word))
        {
            HelpCommand help = new HelpCommand(game);
            help.execute();
        }
        else if (commandWord.equals(CommandWords.MUSIC.word))
        {
            MusicCommand music = new MusicCommand(game);
            music.execute();
        }
        else if (commandWord.equals(CommandWords.MAP.word))
        {
            MapCommand map = new MapCommand(game);
            map.execute();
        }
        else if (commandWord.equals(CommandWords.QUIT.word))
        {
            return true;  // game over
        }
        else if (Game.currentPlayer.getScore() <= 20 || Game.currentPlayer.getGrades() <= 20)
        {
            return true;  // game over if the player has less than 21% on grades or score
        }
        //In case the user inputs a word which is not a command
        else
        {
            //Uses Random class to generate a number
            Random random = new Random();
            //sets the bound from 1 to 6
            int result = random.nextInt(6 - 1) + 1;
            //Uses that number to choose a random sentence of the list
            switch (result)
            {
                case 1 -> System.out.println(" Huh? Check your spelling... ");
                case 2 -> System.out.println(" Are you having a stroke? Check your spelling... ");
                case 3 -> System.out.println(" What is that supposed to mean???");
                case 4 -> System.out.println(" I don't speak that language... Check your spelling!");
                case 5 -> System.out.println(" Do you really know how to write? What does that mean?");
                case 6 -> System.out.println(" What??? Check your spelling...");
            }
        }

        // Return false means the game is not over
        return false;
    }

    /**
     * This method reads the input to choose the character that
     * will play the game: John, Robert or Connor
     */
    public void choosePlayer()
    {
        String playerLine;

        System.out.print(" > ");
        playerLine = reader.nextLine().toLowerCase();

        game.setPlayer(playerLine);
    }
}
