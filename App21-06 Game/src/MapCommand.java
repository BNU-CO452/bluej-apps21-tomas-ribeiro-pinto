import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This command transfers the player from
 * one location to another location provided the
 * two locations are linked by a valid exit
 *
 * @author Derek Peacock & Nicholas Day
 * @version 2021-08-23
 */
public class MapCommand extends ZuulCommand {

    public MapCommand(Game zuul) {
        super(zuul);
    }

    public void execute() {
        System.out.println("\n|---------------------------------------------|\n" +
                           "|                  "+ ConsoleColours.ANSI_BLUE +"[Computing lab]"+ ConsoleColours.ANSI_RESET +"            |\n" +
                           "|                  "+ ConsoleColours.ANSI_RED +"       |      "+ ConsoleColours.ANSI_RESET +"             |\n" +
                           "| "+ ConsoleColours.ANSI_BLUE +"[Graduation]"+ConsoleColours.ANSI_RED+"<---->"+ ConsoleColours.ANSI_BLUE +"[Reception]"+ConsoleColours.ANSI_RED+"<---->"+ ConsoleColours.ANSI_BLUE +"[Café]"+ ConsoleColours.ANSI_RESET +"   |\n" +
                           "|        "+ ConsoleColours.ANSI_RED +"                 |     "+ ConsoleColours.ANSI_RESET +"              |\n" +
                           "|        "+ ConsoleColours.ANSI_BLUE +"[SU]"+ConsoleColours.ANSI_RED+"<------->"+ ConsoleColours.ANSI_BLUE +"[Outside]"+ConsoleColours.ANSI_RED+"<---->"+ ConsoleColours.ANSI_BLUE +"[Halls]"+ ConsoleColours.ANSI_RESET +"  |\n" +
                           "|        "+ ConsoleColours.ANSI_RED +"                 |      "+ ConsoleColours.ANSI_RESET +"             |\n" +
                           "|                       "+ ConsoleColours.ANSI_BLUE +"[Pub]"+ ConsoleColours.ANSI_RESET +"                 |\n" +
                           "|---------------------------------------------|");
    }
}
