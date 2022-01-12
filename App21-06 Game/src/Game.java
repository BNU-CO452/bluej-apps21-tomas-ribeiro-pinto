import javax.swing.*;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  locations, creates the CommandReader and starts the game.  
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * Modified and extended by Student Name
 */

public class Game 
{
    public final Map MAP;
    public CommandReader reader;
    public static boolean gameOver;
    private ConsoleColours consoleColours;
    private Player player;
    public static Player currentPlayer;
    public static Story story;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        MAP = new Map();
        reader = new CommandReader(this);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() throws InterruptedException {
        printWelcome();
        gameOver = false;
        // Enter the main command loop.  Here we repeatedly 
        // read commands and execute them until the game is over.
                
        while (!gameOver && !Player.quit)
        {
            gameOver = reader.getCommand();
        }
        if(Player.quit){
            JFrame frame = new JFrame("You Lost the Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300,300);
            JButton button = new JButton("OK");
            frame.getContentPane().add(button); // Adds Button to content pane of frame
            frame.setVisible(true);
            System.out.println("\n \n" + ConsoleColours.ANSI_BG_RED + " You have lost the game! Your score or grades have fallen below 20% and you were expelled from the University..." + ConsoleColours.ANSI_RESET);
        }
        System.out.println(ConsoleColours.ANSI_GREEN + "\n Thank you for playing 'Save the Student'! Goodbye..." + ConsoleColours.ANSI_RESET);
    }

    public Player createPlayer(Player player){
        this.player = player;
        return player;
    }

    public void createPlayers() throws InterruptedException {
        Player player1 = createPlayer(new Player("John", 55,45));
        Player player2 = createPlayer(new Player("Robert", 45,55));
        Player player3 = createPlayer(new Player("Connor", 50,50));
        player.players.add(player1);
        player.players.add(player2);
        player.players.add(player3);
        System.out.println("Select your player by entering their number: ");
        reader.choosePlayer();
        Thread.sleep(200);
        System.out.println(ConsoleColours.ANSI_BG_YELLOW + " You have chosen " + currentPlayer.getName() + " to be your player. Good Luck! " + ConsoleColours.ANSI_RESET);
        System.out.println();
    }

    public void setPlayer(String selection){

        if(selection.equals("1")){
            currentPlayer = player.players.get(0);
        }

        else if(selection.equals("2")){
            currentPlayer = player.players.get(1);
        }

        else if(selection.equals("3")){
            currentPlayer = player.players.get(2);
        }

        else{
            System.out.println("Please input a number between 1 and 3 to choose your player:");
            reader.choosePlayer();
        }
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() throws InterruptedException {
        System.out.println();
        System.out.println(ConsoleColours.ANSI_BG_GREEN + "Welcome to Save the Student!" + ConsoleColours.ANSI_RESET);
        System.out.println("Save the student is an exciting new game about the daily life of a university student.");
        System.out.println("Pick a student among the available characters and save them from disaster" + "\n" + "by leading them to their graduation ceremony successfully!");
        System.out.println("For this, you will have to navigate through the campus and complete the challenges of each stage.");
        System.out.println("But be careful! You might catch the freshers’ flu, fail an assignment for being too drunk" + "\n"
                            + "or even realise that your annoying girlfriend is trying to manipulate you to not study…");
        System.out.println("Are you up to this challenge or will you let your character fail to graduate?");
        System.out.println();
        System.out.println(" " + ConsoleColours.ANSI_RED + "If you need help, type 'help'." + ConsoleColours.ANSI_RESET);
        System.out.println();
        createPlayers();
        Thread.sleep(1500);
        System.out.println(MAP.getCurrentLocation().getLongDescription());

    }
}
