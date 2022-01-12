import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

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
                
        while (!gameOver && !Player.quit && !Player.win)
        {
            gameOver = reader.getCommand();
        }
        if(Player.win){
            JFrame frame = new JFrame("You Won the Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300,300);
            JButton button = new JButton("OK");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("You clicked the button");
                }
            });
            frame.getContentPane().add(button); // Adds Button to content pane of frame
            frame.setVisible(true);
            System.out.println("\n \n" + ConsoleColours.ANSI_BG_RED + " You have won the game! Congratulations..." + ConsoleColours.ANSI_RESET);
        }
        if(Player.quit){
            JFrame frame = new JFrame("You Lost the Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setSize(400, 70);

            //Creating the panel at bottom and adding components
            JPanel panel = new JPanel(); // the panel is not visible in output
            JLabel label = new JLabel("You lost the game! Thank you for playing it. ");
            JButton button = new JButton("Ok");
            button.addActionListener(e -> frame.dispose());
            panel.add(label); // Components Added using Flow Layout
            panel.add(button);

            //Adding Components to the frame.
            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.setLocationRelativeTo(null);
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
        Player.players.add(player1);
        Player.players.add(player2);
        Player.players.add(player3);
        System.out.println(" Meet the characters!");
        System.out.println(" ----John----  ----Robert----  ----Connor----\n" +
                           "  Score:  55%   Score:  45%     Score: 50%\n" +
                           "  Grades: 45%   Grades: 55%     Grades: 50%\n\n");
        System.out.println("Select your player by entering their name: ");
        reader.choosePlayer();
        Thread.sleep(200);
        System.out.println(ConsoleColours.ANSI_BG_YELLOW + " You have chosen " + currentPlayer.getName() + " to be your player. Good Luck! " + ConsoleColours.ANSI_RESET);
        System.out.println();
    }

    public void setPlayer(String selection){

        if(selection.equals("john")){
            currentPlayer = Player.players.get(0);
        }

        else if(selection.equals("robert")){
            currentPlayer = Player.players.get(1);
        }

        else if(selection.equals("connor")){
            currentPlayer = Player.players.get(2);
        }

        else{
            System.out.println("Please input john, robert or connor to choose your player:");
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
        System.out.println(Map.getCurrentLocation().getLongDescription());
        playSound();
    }
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/tomaspinto/Downloads/nirvana.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
