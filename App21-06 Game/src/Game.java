import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * This class is the main class of the "Save the Student" game.
 * "Save the Student" is a simple, text-based adventure game.
 * The objective is to navigate through the campus, using the Map class
 * and complete challenges to graduate from this digital University
 * <p>
 * This main class creates and initialises all the others: it creates all
 * locations, creates the CommandReader and starts the game.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 18th January 2022
 * @modified Tomás Pinto
 */

public class Game
{
    public final Map MAP;
    public CommandReader reader;
    public static boolean gameOver;
    public static Player player;
    public static Player currentPlayer;
    public static Clip currentBackground;
    public static boolean backgroundMute;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        MAP = new Map();
        reader = new CommandReader(this);
    }

    /**
     * Main play routine.  Loops until end of play.
     *
     * @throws InterruptedException
     */
    public void play() throws InterruptedException
    {
        printWelcome();
        gameOver = false;
        // Enter the main command loop.  Here we repeatedly 
        // read commands and execute them until the game is over.

        while (!gameOver && !Player.lose && !Player.win)
        {
            gameOver = reader.getCommand();
        }
        //In case the player wins the game, it executes this method
        if (Player.win)
        {
            //Stop current background music and play a celebration music
            stopSound(currentBackground);
            playCelebration();

            //Delays the next lines for 1 second
            Thread.sleep(1000);

            //Creates a window using Swing as a GUI
            JFrame frame = new JFrame("You Lost the Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(500, 130);

            //Creating the panel and adding components
            JPanel panel = new JPanel();
            JLabel label = new JLabel("You have won the game! Let's Celebrate! ");
            JLabel label1 = new JLabel("Score: " + Game.currentPlayer.getScore() + "% | Grades: " + Game.currentPlayer.getGrades() + "%");
            JButton button = new JButton("Ok");
            button.addActionListener(e -> frame.dispose()); //Defines what happens when clicking the ok button
            panel.add(label);
            panel.add(label1);
            panel.add(button);

            //Adding Components to the frame.
            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            System.out.println("\n \n" + ConsoleColours.ANSI_BG_GREEN + " You have won the game! Congratulations..." + ConsoleColours.ANSI_RESET);
        }
        if (Player.lose)
        {
            Thread.sleep(1000);
            JFrame frame = new JFrame("You Lost the Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(500, 130);
            JPanel panel = new JPanel(); // the panel is not visible in output
            JLabel label = new JLabel("You lost the game! Thank you for playing it. ");
            JLabel label1 = new JLabel("Score: " + Game.currentPlayer.getScore() + "% | Grades: " + Game.currentPlayer.getGrades() + "%");
            JButton button = new JButton("Ok");
            button.addActionListener(e -> frame.dispose());
            panel.add(label); // Components Added using Flow Layout
            panel.add(label1);
            panel.add(button);
            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            System.out.println("\n \n" + ConsoleColours.ANSI_BG_RED + " You have lost the game! Your score or grades have fallen below 20% and you were expelled from the University..." + ConsoleColours.ANSI_RESET);
        }
        //When the game loop is over, it displays a goodbye message and some credits
        System.out.println(ConsoleColours.ANSI_GREEN + "\n Thank you for playing 'Save the Student'! Goodbye..." + ConsoleColours.ANSI_RESET);
        System.out.println(ConsoleColours.ANSI_GREEN + " Game created by Tomas Pinto - 18th January 2022" + ConsoleColours.ANSI_RESET);
    }

    public Player createPlayer(Player player)
    {
        this.player = player;
        return player;
    }

    /**
     * This method creates the players John, Robert and Connor and assigns
     * score and grades to them. Lists the players and executes the choosePlayer method
     * to pick the player.
     *
     * @throws InterruptedException
     */
    public void createPlayers() throws InterruptedException
    {
        Player player1 = createPlayer(new Player("John", 55, 45));
        Player player2 = createPlayer(new Player("Robert", 45, 55));
        Player player3 = createPlayer(new Player("Connor", 50, 50));
        Player.players.add(player1);
        Player.players.add(player2);
        Player.players.add(player3);
        System.out.println(" " + ConsoleColours.ANSI_BG_BLUE + "Choose one of the characters to start the game!" + ConsoleColours.ANSI_RESET + "\n\n" +
                "   ( ͡° ͜ʖ ͡°)       ( ͠° ͟ʖ ͠°)        ( ͡~ ͜ʖ ͡°)");
        System.out.println(" ----John----  ----Robert----  ----Connor----\n" +
                "  Score:  55%   Score:  45%     Score: 50%\n" +
                "  Grades: 45%   Grades: 55%     Grades: 50%\n\n");
        System.out.println("Select your player by entering their name: ");
        reader.choosePlayer();
        Thread.sleep(200);
        System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "You have chosen " + currentPlayer.getName() + " to be your player. Good Luck! " + ConsoleColours.ANSI_RESET);
        System.out.println();
    }

    /**
     * Sets the player with the user choice
     *
     * @param selection
     */
    public void setPlayer(String selection)
    {

        if (selection.equals("john") || selection.equals("1"))
        {
            currentPlayer = Player.players.get(0);
        }

        else if (selection.equals("robert") || selection.equals("2"))
        {
            currentPlayer = Player.players.get(1);
        }

        else if (selection.equals("connor") || selection.equals("3"))
        {
            currentPlayer = Player.players.get(2);
        }

        else
        {
            System.out.println("Please input John, Robert or Connor to choose your player:");
            reader.choosePlayer();
        }
    }

    /**
     * Print out the opening message for the player.
     * Prints the help and command options and starts the game
     */
    private void printWelcome() throws InterruptedException
    {
        System.out.println();
        System.out.println(ConsoleColours.ANSI_BG_GREEN + "Welcome to Save the Student!" + ConsoleColours.ANSI_RESET);
        System.out.println("Save the student is an exciting new game about the daily life of a university student.");
        System.out.println("Pick a student among the available characters, and save them from disaster" + "\n" + "by leading them to their graduation ceremony successfully!");
        System.out.println("For this, you will have to navigate through the campus and complete the challenges of each academic stage.");
        System.out.println("But be careful! You might catch the freshers’ flu, fail an assignment for being too drunk" + "\n"
                + "or even realise that your annoying girlfriend is trying to manipulate you not to study…");
        System.out.println("Are you up to this challenge or will you let your character fail to graduate?");
        System.out.println();
        miniHelp();
        System.out.println(" " + ConsoleColours.ANSI_RED + "If during the game need help with commands, type 'help'." + ConsoleColours.ANSI_RESET);
        System.out.println();
        createPlayers();
        Thread.sleep(1500);
        System.out.println(Map.getCurrentLocation().getLongDescription());
        playSound();
    }

    /**
     * Plays the background music using the Clip class
     */
    public static void playSound()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/tomaspinto/Documents/GitHub/bluej-apps21-tomas-ribeiro-pinto/App21-06 Game/background_music.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            currentBackground = clip;
        } catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Plays the Rock Music used in the SU location
     */
    public static void playNirvana()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/tomaspinto/Documents/GitHub/bluej-apps21-tomas-ribeiro-pinto/App21-06 Game/nirvana_sound.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            currentBackground = clip;
        } catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Music playing when the player wins
     */
    public static void playCelebration()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/tomaspinto/Documents/GitHub/bluej-apps21-tomas-ribeiro-pinto/App21-06 Game/celebration.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            currentBackground = clip;
        } catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Stops the current background music
     *
     * @param currentBackground
     */
    public static void stopSound(Clip currentBackground)
    {
        Game.currentBackground = currentBackground;
        currentBackground.stop();
    }

    /**
     * Prints the commands to the user
     */
    public void miniHelp()
    {
        System.out.println(" " + ConsoleColours.ANSI_BG_BLUE + "How to play the game?" + ConsoleColours.ANSI_RESET +
                "\n Your command words are:\n");
        for (CommandWords command : CommandWords.values())
        {
            System.out.println(" " + command.word +
                    "\t  : " + command.description);
        }
        System.out.println();
        System.out.println(" e.g. go west, take gold");
    }
}
