import java.util.ArrayList;

/**
 * Player Class Description
 */

public class Player {

    private String name;
    public int score;
    public int grades;
    public static boolean girlfriend = false;
    public static ArrayList<Player> players;
    public Game game;
    public static boolean quit = false;
    public static boolean win = false;
    public static int wallet;

    public Player(String name, int score, int grades) {
        this.name = name;
        this.score = score;
        this.grades = grades;
        players = new ArrayList<Player>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getGrades() {
        return grades;
    }

    public void changeScore(int amount) {
        score = amount + score;
        if (amount > 0) {
            System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "Score ⬆ "
                    + amount + "% " + ConsoleColours.ANSI_RESET);
        } else if (amount < 0) {
            int symmetric = -amount;
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "Score ⬇ "
                    + symmetric + "% " + ConsoleColours.ANSI_RESET);
        }
        if(score <= 20){
            quit = true;
        }
    }

    public void changeGrades(int amount) {
        grades = amount + grades;
        if (amount > 0) {
            System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "Grades ⬆ "
                    + amount + "% " + ConsoleColours.ANSI_RESET);
        } else if (amount < 0) {
            int symmetric = -amount;
            System.out.println(" " + ConsoleColours.ANSI_BG_RED + "Grades ⬇ "
                    + symmetric + "% " + ConsoleColours.ANSI_RESET);
        }
        if(grades <= 20){
            quit = true;
        }
    }

    public static void getCafeCoins(){
        wallet = wallet + Story.cafeCoins;
        Story.cafeCoins = 0;
        System.out.println(" " +ConsoleColours.ANSI_BG_BLUE + "You now have: £" + wallet + " in your wallet." + ConsoleColours.ANSI_RESET);
    }

    public static void getCoins(int amount){
        wallet = wallet + amount;
        System.out.println(" " +ConsoleColours.ANSI_BG_BLUE + "You found " + amount + " coins by completing this challenge!" + ConsoleColours.ANSI_RESET);
        System.out.println(" You have: £" + wallet + " in your wallet.\n");
    }
}