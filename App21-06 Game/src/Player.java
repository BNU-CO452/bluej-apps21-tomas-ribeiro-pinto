import java.util.ArrayList;

/**
 * This the player class that generates players.
 * These hold the attributes: grades, girlfriend and score
 * necessary to win the game.
 */

public class Player
{

    private String name;
    public int score;
    public int grades;
    // The player starts the game as single
    public static boolean girlfriend = false;
    // ArrayList to store the created players
    public static ArrayList<Player> players;
    public Game game;
    // If the player has won the game
    public static boolean lose = false;
    // If the player has lost the game
    public static boolean win = false;
    // Place to store coins found in game
    public static int wallet;

    /**
     * Constructor for creating player objects
     *
     * @param name
     * @param score
     * @param grades
     */
    public Player(String name, int score, int grades)
    {
        this.name = name;
        this.score = score;
        this.grades = grades;
        players = new ArrayList<Player>();
    }

    /**
     * @return player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return player's score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * @return player's grade
     */
    public int getGrades()
    {
        return grades;
    }

    /**
     * Change score up or down and display it to the user
     * @param amount
     */
    public void changeScore(int amount)
    {
        score = amount + score;
        if (amount > 0)
        {
            System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "Score ⬆ "
                    + amount + "% " + ConsoleColours.ANSI_RESET);
        }
        // If we want to decrease score, we input a negative number
        else if (amount < 0)
        {
            int symmetric = -amount;
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "Score ⬇ "
                    + symmetric + "% " + ConsoleColours.ANSI_RESET);
        }
        // In case the player has fallen below than 21%, they lose the game
        if (score <= 20)
        {
            lose = true;
        }
    }

    /**
     * Change grades up or down and display it to the user
     * @param amount
     */
    public void changeGrades(int amount)
    {
        grades = amount + grades;
        if (amount > 0)
        {
            System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "Grades ⬆ "
                    + amount + "% " + ConsoleColours.ANSI_RESET);
        }
        // If we want to decrease grades, we input a negative number
        else if (amount < 0)
        {
            int symmetric = -amount;
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "Grades ⬇ "
                    + symmetric + "% " + ConsoleColours.ANSI_RESET);
        }
        // In case the player has fallen below than 21%, they lose the game
        if (grades <= 20)
        {
            lose = true;
        }
    }

    /**
     * Get the coins located in the café
     */
    public static void getCafeCoins()
    {
        wallet = wallet + Story.cafeCoins;
        Story.cafeCoins = 0;
        System.out.println(" " + ConsoleColours.ANSI_BG_BLUE + "You now have: £" + wallet + " in your wallet." + ConsoleColours.ANSI_RESET);
    }

    /**
     * Reward coins after challenges
     * @param amount
     */
    public static void getCoins(int amount)
    {
        wallet = wallet + amount;
        System.out.println(" " + ConsoleColours.ANSI_BG_BLUE + "You found " + amount + " coins by completing this challenge!" + ConsoleColours.ANSI_RESET);
        System.out.println(" You have: £" + wallet + " in your wallet.\n");
    }
}