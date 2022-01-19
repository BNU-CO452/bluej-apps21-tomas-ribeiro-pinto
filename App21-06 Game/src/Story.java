import java.util.Scanner;

/**
 * This class holds the storyline and challenges
 * that take place in each location
 */
public class Story
{
    // Selection inputted by user in location
    private static int hallsSelection;
    private static int cafeSelection;
    private static int suSelection;
    private static int labSelection;

    // Variable for a challenge in lab
    public static int correct;
    // Counts the challenges completed by the player
    public static int challengeCount;
    // Coins located in the café
    public static int cafeCoins = 3;

    /**
     * Outside plot
     */
    public static void outsideStory()
    {
        // If the user does not have a card, that means he is new to the game
        if (!Location.inventory.containsKey("card"))
        {
            System.out.println(" Welcome " + Game.currentPlayer.getName() + " and congratulations for securing a place at the University of Computing!"
                    + "\n" + " At the moment, the freshers' fair is taking place. \n" +
                    " Outside the university, helpers are giving freshers their student cards. \n" +
                    " Music is playing and everyone is having fun with the activities provided by the Students' Union. \n" +
                    " Where should you go now??? \n");
        }
    }

    /**
     * Halls plot
     */
    public static void hallsStory()
    {
        // When between challenges, there is no challenge happening at this location
        if ((hallsSelection > 0 && challengeCount < 6))
        {
            System.out.println(" At the moment there is no challenge in this location... Come back later \n");
        }
        // Second challenge in halls
        else if (hallsSelection > 0 && hallsSelection < 3 && challengeCount > 5)
        {
            // This story splits in two timelines:
            // if the user has a girlfriend or not
            if (!Player.girlfriend)
            {
                System.out.println(" Welcome back to the flat! After a hard day of work at university, you are feeling a bit hungry. \n" +
                        " Do you want to pay for a delivery or cook food by yourself? Type one of the options available:\n" +
                        " Delivery - I will order a pizza or a burger...\n" +
                        " Cook - I will cook my own food, make a quick pasta maybe... ");
                chooseStory();

                // If the user selects delivery
                if (hallsSelection == 3)
                {
                    Game.currentPlayer.changeScore(-5);
                    System.out.println();
                }
                // If the user selects cook
                else if (hallsSelection == 4)
                {
                    Game.currentPlayer.changeScore(5);
                    System.out.println();
                }
                //increase challenge count
                challengeCount = challengeCount + 1;
                //because
                checkGraduation();
            }
            if (Player.girlfriend)
            {
                System.out.println(" As soon as you entered the flat, you receive a phone call from your girlfriend and she seems very upset with you. \n" +
                        " In the last days, she's being very rude with you and you can't stop arguing... Do you want answer the phone? \n\n" +
                        " Yes - I will answer the phone and try to calm her down.\n" +
                        " No - She is surely having a breakdown and you think she needs to cool off before talking to you.");
                chooseStory();
                if (hallsSelection == 3)
                {
                    System.out.println(" You answer the phone and she asks you why aren't you at her place giving her attention.\n" +
                            " She is so manipulative to get your attention and keep you from studying. Because of this, you decide to break up with her. Farewell...");
                    Game.currentPlayer.changeScore(5);
                    Game.currentPlayer.changeGrades(15);
                    Player.girlfriend = false;
                    System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "You are now single! " + ConsoleColours.ANSI_RESET + "\n");
                    Player.getCoins(2);
                }
                else if (hallsSelection == 4)
                {
                    System.out.println(" You decided to turn down the call and turn off the phone.\n" +
                            " Next day, you see her and you both have a 'little talk'... She didn't like what you done to her and because of this you broke up.\n" +
                            " She was so mad that you are now enemies... Better luck next time!");
                    Game.currentPlayer.changeScore(-10);
                    Game.currentPlayer.changeGrades(15);
                    Player.girlfriend = false;
                    System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "You are now single! " + ConsoleColours.ANSI_RESET + "\n");
                }
                challengeCount = challengeCount + 1;
                checkGraduation();
            }
        }
        if (hallsSelection == 0)
        {
            System.out.println(" Welcome to the student halls! Your room number is B34, and the reception working hours are from 9am to 9pm."
                    + "\n" + " NB: Your student card is always needed to enter in the building, so do not lose it! \n \n" +
                    " While moving into the flat, you noticed some noise in the shared kitchen, it seems your new flatmates are socialising in the kitchen. \n" +
                    " Do you want to join and meet them? Choose one of the options available: \n \n" +
                    " Yes - I want to join them and make new friends... \n" +
                    " No - I am very busy with the moving and meeting them will distract me!");

            chooseStory();

            if (hallsSelection == 1)
            {
                Game.currentPlayer.changeScore(10);
                Game.currentPlayer.changeGrades(5);
                System.out.println();
            }
            else if (hallsSelection == 2)
            {
                Game.currentPlayer.changeScore(-10);
                System.out.println();
            }
            challengeCount = challengeCount + 1;
        }
    }

    /**
     * Reception plot
     */
    public static void receptionStory()
    {
        System.out.println(" The university reception is here to help you with everything you need! From paying tuition to receiving help.");
        if (!Location.inventory.containsKey("sanitizer"))
            System.out.println(" At the entrance there is sanitizer available to use, feel free to take some...");
        if (!Location.inventory.containsKey("water") || !Location.inventory.containsKey("snack"))
            System.out.println(" To your left, there is also a vending machine with a snack and a water. If you feel hungry, you can always go to the café as well. \n");
    }

    /**
     * Student's Union plot
     */
    public static void suStory()
    {
        if (challengeCount < 2 || challengeCount == 4)
        {
            System.out.println(" Welcome to the Student's Union! We have plenty of fun stuff to do during the term time. \n" +
                    " At the moment there is no event taking place, please come back later...\n");
        }
        else if (suSelection > 0)
        {
            if (Player.girlfriend)
            {
                System.out.println(" Tonight you are going on a date with your girlfriend to the Student's Union Events. She is has been excited all week to go out with you...\n" +
                        " At the entrance, the security asks for both your student cards and identification. \n" +
                        " You find your student card however, you forgot your identification at home and you need an excuse to enter in the building. " +
                        " Which one of the following do you use? \n\n" +
                        " 1 - My ID was eaten by the dog, he destroyed the card.\n" +
                        " 2 - I was about to come to University, when I was mugged by 2 green aliens and they took my wallet.\n" +
                        " 3 - My girlfriend is a nice person but sometimes she likes to hide my stuff, so I couldn't find the ID.");
                chooseStory();
                if (suSelection == 9)
                {
                    System.out.println(" The security team expelled you and your girlfriend from the event. \n" +
                            " She is really mad and went home to avoid doing something she would regret... Your relationship has been suffering due to the constant arguments you have. \n" +
                            " Next time you see her, try to apologise if you want to keep her as your girlfriend. \n");
                    Game.currentPlayer.changeScore(-5);
                }
                else if (suSelection == 10)
                {
                    System.out.println(" The security guy laughed but as a matter of security she couldn't let you both enter the event. \n" +
                            " Your girlfriend is really upset because she wanted to have a good evening with you and you started arguing... She went home to avoid doing something she would regret... \n" +
                            " Your relationship has been suffering due to the constant arguments you have. \n" +
                            " Next time you see her, try to apologise if you want to keep her as your girlfriend. \n");
                    Game.currentPlayer.changeScore(-5);
                }
                else if (suSelection == 11)
                {
                    System.out.println(" Your girlfriend called you a liar and you started arguing about the annoying things you have been doing in the past weeks. \n" +
                            " Your relationship has been suffering due to the constant arguments you have. \n" +
                            " Because of this, she broke up with you! \n");
                    Game.currentPlayer.changeScore(-10);
                    Game.currentPlayer.changeGrades(15);
                    System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "You are now single! " + ConsoleColours.ANSI_RESET + "\n");
                    Player.girlfriend = false;
                }
                challengeCount = challengeCount + 1;
                System.out.println(" " + ConsoleColours.ANSI_BLUE + "There is one challenge left in the student halls in order to graduate or fail it. Do you think you're ready for the last challenge?\n" + ConsoleColours.ANSI_RESET);
            }
            if (!Player.girlfriend && suSelection < 9)
            {
                System.out.println(" Tonight there is a 90s theme party happening at the Student's Union and you were invited by Jessie, \n" +
                        " a course friend to have fun and dance a bit after a dinner with other colleagues. \n" +
                        " However, after the dinner you realise you have a test for Computer Architectures the day after and you still need to revise some stuff. \n" +
                        " Do you still want to join them at the late party? Choose one of the options available: \n \n" +
                        " Yes - I will join them as I can always go to Referrals if I fail this module \n" +
                        " No - I apologize and go directly home to study and have a good night of sleep \n" +
                        " Stay - I will stay for a drink and go back home before 10:30pm");
                chooseStory();
                if (suSelection == 6)
                {
                    System.out.println(" Crazy or not here I come...\n\n" +
                            " Random Fun Fact: Did you know that over 76 million Tamagotchis were sold in the 90s? \n" +
                            " If you don't know what Tamagotchis were, I'm definitely getting too old... \n" +
                            " Source: https://bestlifeonline.com/90s-facts/ \n");
                    Game.currentPlayer.changeScore(10);
                    Game.currentPlayer.changeGrades(-10);
                }
                else if (suSelection == 7)
                {
                    System.out.println(" You apologise you friends and you go home to have a good night of sleep... \n\n" +
                            " Random Fun Fact: Did you know that we are the only mammals that willingly delay sleep? \n" +
                            " Source: https://health.clevelandclinic.org/22-facts-about-sleep-that-will-surprise-you/ \n");
                    Game.currentPlayer.changeScore(-5);
                    Game.currentPlayer.changeGrades(+10);
                }
                else if (suSelection == 8)
                {
                    System.out.println(" " + Game.currentPlayer.getName() + " being responsible, look at you! Your friends understand your situation and wish you luck for the test. \n\n" +
                            " Random Fun Fact: Did you know that the Slavic word ‘beer’ came from the verb ‘to drink’? Initially, beer was any kind of drink. \n" +
                            " Source: https://beerconnoisseur.com/blogs/20-interesting-facts-about-beer \n");
                    Game.currentPlayer.changeScore(5);
                    Game.currentPlayer.changeGrades(-5);
                    Player.getCoins(2);
                }
                challengeCount = challengeCount + 1;
                System.out.println(" " + ConsoleColours.ANSI_BLUE + "There is one challenge left in the student halls in order to graduate or fail it. Do you think you're ready for the last challenge?\n" + ConsoleColours.ANSI_RESET);
            }
        }
        else if (suSelection == 0)
        {
            if (Game.currentBackground.isActive())
            {
                Game.stopSound(Game.currentBackground);
            }
            Game.playNirvana();
            System.out.println(" Welcome to the Student's Union! \n" +
                    " There is a Rock and Roll theme party taking place and you were invited by Josh, a good friend of you from primary school times. \n" +
                    " You enter in the venue at around 10pm and the first music playing is from Nirvana - Smells Like Teen Spirit. You immediately start to rock and dance... \n \n" +
                    " Josh, asks you if you want a beer or some shots, what do you prefer? Choose one of the options available:\n \n" +
                    " Beer - I will take beer as it is the most affordable drink in the menu \n" +
                    " Shot - 5 pounds for 3? Let's get crazy and enjoy the night!!! \n" +
                    " None - I don't need alcohol to have fun, and also I don't have much money to spend...");

            chooseStory();

            if (suSelection == 1)
            {
                System.out.println(" You thank Josh for the drink and you think a pint of beer should not be a problem as it is friday... \n");
            }
            else if (suSelection == 2)
            {
                Game.currentPlayer.changeScore(-10);
                System.out.println(" Josh gets you some of the most powerful shots and you get drunk instantly. Tomorrow morning won't be pleasant! \n");
            }
            else if (suSelection == 3)
            {
                Game.currentPlayer.changeScore(5);
                System.out.println(" That's a good attitude from you " + Game.currentPlayer.getName() + "! Your grades benefit from being sober, so you can focus on Computing. \n");
                Player.getCoins(2);
            }

            System.out.println(" After a while of having some good solo dance, it's time for upgrade... \n" +
                    " You notice a pale white girl with rosy cheeks approaching you, she introduces herself as Mary and invites you to a dance... \n" +
                    " What do you say? Choose one of the options available:\n \n" +
                    " Yes - I want to dance with the pretty girl in front of me. \n" +
                    " No - I just broke up with my ex-girlfriend Lilly last week, I should not be dancing and flirting with other girls just yet... \n" +
                    "      -> PS: I know as a storyteller I should not intervene, but bro shes looks amazing! I would say yes, but it's up to you. ");

            chooseStory();

            if (suSelection == 4)
            {
                Game.currentPlayer.changeScore(10);
                Game.currentPlayer.changeGrades(-5);
                System.out.println(" You accepted her request and you keep dancing all night. \n" +
                        " Later, you start drinking with her and both get too drunk and eventually kiss several times. \n" +
                        " During the following days you reflect on that night and your developed feelings for Mary, that's when you decide to date her. \n" +
                        " I told you that was a good choice... ( ͡° ͜ʖ ͡°)");
                Player.girlfriend = true;
                System.out.println(" " + ConsoleColours.ANSI_BG_YELLOW + "You now have a girlfriend, don't let that affect your studies!" + ConsoleColours.ANSI_RESET + "\n");
                Player.getCoins(2);
            }
            else if (suSelection == 5)
            {
                Game.currentPlayer.changeScore(-5);
                System.out.println(" You turned down her request but she does not give up. \n" +
                        " At some point, you start to think she is the crazy stalker you have been receiving messages on Instagram all week. \n" +
                        " You and your friend Josh decide to call security and she is expelled from the event. \n" +
                        " Well, I guess you're right. Maybe she was not meant to be the next one... \n");
            }
            challengeCount = challengeCount + 1;
            if (Game.currentBackground.isActive())
            {
                Game.stopSound(Game.currentBackground);
            }
            Game.playSound();
        }
    }

    /**
     * Café plot
     */
    public static void cafeStory()
    {
        if (cafeSelection == 0)
        {
            System.out.println(" Welcome to the Café! This is probably the best place to be in the university...\n" +
                    " You can see rounded tables, chairs, a vending machine currently out of stock, the front counter with a big queue. \n" +
                    " The dark and intense smell of coffee is unforgettable, but the noise is the main attraction as it's something unique... \n \n" +
                    " You see some of your friends and decide to join them at the queue.\n" +
                    " Some minutes later, it is your turn to order some food and drinks. The waitress asks you if you want a coffee, what do you say? Choose one of the options available: \n \n" +
                    " Yes - I want some coffee... I'll take a Cappuccino! \n" +
                    " No - I'll keep around just for chat with friends, I'm not paying 3 pounds for a small coffee! What a scam...");

            chooseStory();

            if (cafeSelection == 1)
            {
                Game.currentPlayer.changeScore(-5);
                System.out.println(" Caffeine is not good for you while studying. It affects your sleep... \n");
            }
            else if (cafeSelection == 2)
            {
                Game.currentPlayer.changeScore(5);
                Game.currentPlayer.changeGrades(5);
                System.out.println();
            }
            challengeCount = challengeCount + 1;
        }
        else
        {
            System.out.println(" At the moment the café is close. There is no challenge taking place in this location...");
            if (cafeCoins > 0)
            {
                System.out.println(" You have found 3 coins. To put them in your wallet, type 'wallet'");
            }
            System.out.println();
        }
    }

    /**
     * Graduation plot
     */
    public static void graduationStory()
    {
        if (challengeCount < 7)
        {
            System.out.println(" Welcome to the graduation theatre! It's not your time yet...\n" +
                    " You must go and finish university first before you can graduate. \n" +
                    " Please come back later... \n\n" +
                    " PS: Can ye beat them?\n" +
                    " Where Caesar shifted,\n" +
                    " Three is correct\n" +
                    " If they find thy success,\n" +
                    " they shall be very upset\n\n" +
                    " " + ConsoleColours.ANSI_BRIGHT_CYAN + "wdnh glsorpd" + ConsoleColours.ANSI_RESET + "\n");
        }
        else if (Game.currentPlayer.getGrades() >= 50 && Game.currentPlayer.getScore() >= 50)
        {
            System.out.println(" Congratulations and welcome to the graduation ceremony, you just finished university with success!\n" +
                    " Score: " + Game.currentPlayer.getScore() + "% | Grades: " + Game.currentPlayer.getGrades() + "%");
            Player.win = true;
        }
        else
        {
            System.out.println(" Unfortunately you were not able to pass all the modules and failed university. You are now in great debt... \n" +
                    " Score: " + Game.currentPlayer.getScore() + "% | Grades: " + Game.currentPlayer.getGrades() + "%" +
                    " You just lost the game! \n");
            Player.quit = true;
        }
    }

    public static void labStory()
    {
        if ((labSelection > 0 && challengeCount < 4) || (labSelection > 2))
        {
            System.out.println(" At the moment there is no challenge in this location... Come back later.\n");
        }
        else if (labSelection > 0 && labSelection < 3 && challengeCount >= 4)
        {
            System.out.println(" You enter in the lab and realize that today you have a test about ArrayLists in Java.");
            if (labSelection == 1)
            {
                System.out.println(" You forgot to study after missing the class to go skate. Do you think you can help "
                        + Game.currentPlayer.getName() + " pass the test? \n");
            }
            else
            {
                System.out.println(" Are you ready for the ArrayList test? Try to help "
                        + Game.currentPlayer.getName() + " pass the test and the module. \n");
            }
            System.out.println(" There are 2 multiple answer questions in this test, to answer them just input the letter of the answer. Good Luck! \n \n" +
                    " What of the following answers is NOT correct about ArrayLists? \n " +
                    " A- ArrayList in Java uses an index-based structure.        B- The size of ArrayList can increase or decrease at runtime. \n " +
                    " C- Duplicate elements are not allowed in the array list.   D- It allows to delete elements.");
            chooseStory();
            if (labSelection == 5)
            {
                correct = correct + 1;
                labSelection = 2;
            }
            System.out.println("\n How can we create a generic ArrayList object in Java? \n " +
                    " A- ArrayList<E> list = new Array();     B- ArrayList<E> list = new ArrayList<E>(); \n " +
                    " C- list = new Array(E);                 D- ArrayList<E> list = new ArrayList(<E>); ");
            chooseStory();
            if (labSelection == 4)
            {
                correct = correct + 1;
            }
            System.out.println(" You answered " + correct + "/2 questions correctly " + ConsoleColours.ANSI_RESET);
            Game.currentPlayer.changeGrades(correct * 5);
            if (correct == 0)
                Game.currentPlayer.changeGrades(-5);
            challengeCount = challengeCount + 1;
            if (correct == 2)
                Player.getCoins(2);
        }

        if (labSelection == 0)
        {
            System.out.println(" Welcome to the computing lab. Your class with Nick is about to start! \n" +
                    " The first programming language you will be learning is Java using BlueJ application. \n" +
                    " Professor Nicholas is explaining the basics of how an ArrayList works but obviously is not as appealing as the sun outside. \n" +
                    " You receive a text message from a friend to go to a skate park nearby. You are bored and not enjoying the class. \n" +
                    " You decide to leave the class and study later, however you are going to have a test next week about ArrayLists in Java. \n \n" +
                    " Do you still want to leave the class? Choose one of the available options: \n" +
                    " Yes - I'm going to say I am feeling sick as I have time to study later and can cover how ArrayLists work. \n" +
                    " No - It's better for me to stay here and take any doubts I may have with Nick.");
            chooseStory();

            if (labSelection == 1)
            {
                Game.currentPlayer.changeScore(10);
                Game.currentPlayer.changeGrades(-15);
            }
            else if (labSelection == 2)
            {
                Game.currentPlayer.changeScore(-5);
                Game.currentPlayer.changeGrades(10);
                System.out.println();
            }
            challengeCount = challengeCount + 1;
        }
    }

    /**
     * Pub plot
     */
    public static void pubStory()
    {
        System.out.println(" Welcome! Do you want some booze? \n" +
                " At the table on your left you can see this price list: \n");
        if (!Location.inventory.containsKey("beer"))
            System.out.println(" Beer --------- FREE - Take it using the take command");
        if (Location.inventory.containsKey("beer"))
            System.out.println(" Beer --------- OUT OF STOCK");
        System.out.println(" " + ConsoleColours.ANSI_RED + "Products to buy using the buy command:" + ConsoleColours.ANSI_RESET);
        System.out.println(" Score (+5%) ---- £5\n" +
                " Grades (+5%) --- £5\n\n" +
                " You have: £" + Player.wallet + " in your wallet.");
        if (Player.wallet == 0)
        {
            System.out.println(" Do you need money? Look for coins in the game and by completing challenges...");
        }
        System.out.println();
    }

    public static void chooseStory()
    {
        String selectionLine;
        Scanner choice = new Scanner(System.in);
        System.out.print(" > ");
        selectionLine = choice.nextLine().toLowerCase();

        storySelection(selectionLine);
    }

    public static void storySelection(String selection)
    {
        if (Map.currentLocation == Map.halls)
        {
            if (hallsSelection == 0)
            {
                switch (selection)
                {
                    case "yes" -> hallsSelection = 1;
                    case "no" -> hallsSelection = 2;
                    default -> {
                        System.out.println("Please input yes or no to submit your choice:");
                        chooseStory();
                    }
                }
            }
            else if (Player.girlfriend)
            {
                switch (selection)
                {
                    case "yes" -> hallsSelection = 3;
                    case "no" -> hallsSelection = 4;
                    default -> {
                        System.out.println("Please input yes or no to submit your choice:");
                        chooseStory();
                    }
                }
            }
            else
            {
                switch (selection)
                {
                    case "delivery" -> hallsSelection = 3;
                    case "cook" -> hallsSelection = 4;
                    default -> {
                        System.out.println("Please input one of the options available to submit your choice:");
                        chooseStory();
                    }
                }
            }
        }

        if (Map.currentLocation == Map.cafe)
        {
            switch (selection)
            {
                case "yes" -> cafeSelection = 1;
                case "no" -> cafeSelection = 2;
                default -> {
                    System.out.println("Please input yes or no to submit your choice");
                    chooseStory();
                }
            }
        }
        if (Map.currentLocation == Map.su)
        {
            if (suSelection == 0)
            {
                switch (selection)
                {
                    case "beer" -> suSelection = 1;
                    case "shot" -> suSelection = 2;
                    case "none" -> suSelection = 3;
                    default -> {
                        System.out.println("Please input one of the available options to submit your choice");
                        chooseStory();
                    }
                }
            }
            else if (suSelection > 0 && suSelection <= 3)
            {
                switch (selection)
                {
                    case "yes" -> suSelection = 4;
                    case "no" -> suSelection = 5;
                    default -> {
                        System.out.println("Please input one of the available options to submit your choice:");
                        chooseStory();
                    }
                }
            }
            else if (suSelection > 3)
            {
                if (Player.girlfriend)
                {
                    switch (selection)
                    {
                        case "1" -> suSelection = 9;
                        case "2" -> suSelection = 10;
                        case "3" -> suSelection = 11;
                        default -> {
                            System.out.println("Please input one of the available options to submit your choice:");
                            chooseStory();
                        }
                    }
                }
                if (!Player.girlfriend)
                {
                    switch (selection)
                    {
                        case "yes" -> suSelection = 6;
                        case "no" -> suSelection = 7;
                        case "stay" -> suSelection = 8;
                        default -> {
                            System.out.println("Please input one of the available options to submit your choice:");
                            chooseStory();
                        }
                    }
                }
            }
        }

        if (Map.currentLocation == Map.lab)
        {
            if (labSelection == 0)
            {
                switch (selection)
                {
                    case "yes" -> labSelection = 1;
                    case "no" -> labSelection = 2;
                    default -> {
                        System.out.println("Please input yes or no to submit your choice:");
                        chooseStory();
                    }
                }
            }
            else
            {
                switch (selection)
                {
                    case "a" -> labSelection = 3;
                    case "b" -> labSelection = 4;
                    case "c" -> labSelection = 5;
                    case "d" -> labSelection = 6;
                    default -> {
                        System.out.println("Please input one of the available options to submit your choice:");
                        chooseStory();
                    }
                }
            }
        }
    }

    public static void checkGraduation()
    {
        if (challengeCount == 7)
        {
            Map.enterLocation(Map.graduation);
            System.out.println(ConsoleColours.ANSI_RED + " You have been teleported to the graduation theatre because you have finished all the challenges! \n" + ConsoleColours.ANSI_RESET);
        }
    }
}
