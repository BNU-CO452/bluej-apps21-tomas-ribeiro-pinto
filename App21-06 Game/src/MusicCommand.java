/**
 * This command allows the player to
 * turn on or off the background music current playing
 *
 * @author Derek Peacock & Nicholas Day
 * @modified Tomás Pinto
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

    public void execute()
    {
        if (Game.currentBackground.isActive())
        {
            Game.stopSound(Game.currentBackground);
            Game.backgroundMute = true;
            System.out.println(ConsoleColours.ANSI_RED + " Music off" + ConsoleColours.ANSI_RESET);
        }
        else
        {
            Game.playSound();
            System.out.println(ConsoleColours.ANSI_RED + " Music on" + ConsoleColours.ANSI_RESET);
        }
    }
}
