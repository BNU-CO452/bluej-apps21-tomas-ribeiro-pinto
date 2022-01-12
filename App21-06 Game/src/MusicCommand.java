
/**
 * This command allows the player to
 * take or pickup an item from a room
 * and carry it around to use somewhere
 * else
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 */
public class MusicCommand extends ZuulCommand
{
    /**
     * Enables background music to be on or off
     */
    public MusicCommand(Game zuul)
    {
        super(zuul);
    }

    public void execute() {
        if (Game.currentBackground.isActive()) {
            Game.stopSound(Game.currentBackground);
            Game.backgroundMute = true;
            System.out.println(ConsoleColours.ANSI_RED + " Music off" +ConsoleColours.ANSI_RESET);
        }
        else {
            Game.playSound();
            System.out.println(ConsoleColours.ANSI_RED + " Music on" +ConsoleColours.ANSI_RESET);
        }
    }
}
