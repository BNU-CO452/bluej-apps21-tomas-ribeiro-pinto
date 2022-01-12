
/**
 * This class is reponsible for creating and
 * linking all the Locations in the game to
 * form a 2D or 3D network
 *
 *  [Pub]<---->[Outside]<---->[Theatre]
 *                 |
 *          [Computer Lab]<---->[Office]
 *             
 * @author Derek Peacock and Nicholas Day
 * @version 2021-08-22
 */
public class Map
{
    // Need to add a list of exits
    public static Location outside, su, pub, halls, reception, cafe, graduation, lab;

    public static Location currentLocation;

    /**
     * Constructor for objects of class Map
     */
    public Map()
    {
        createLocations();
    }

    /**
     * Create all the Locations and link their exits together.
     * Set the current location to the outside.
     * Both locations need to have been created before
     * their exists are linked.
     */
    private void createLocations()
    {
        createOutside();
        createSu();
        createPub();
        createHalls();
        createReception();
        createCafe();
        createGraduation();
        createLab();

        currentLocation = outside;  // start game outside
    }
    
    /**
     * Create the outside and link it to the
     * theatre, lab and pub
     */
    private void createOutside()
    {
        outside = new Location("outside the main entrance of the university.");
        outside.setItem(new Item("Card", "Use your student card to get access to buildings"), "card");

    }

    /**
     * Create the theatre linked to the outside
     */
    private void createSu()
    {
        su = new Location("in the Students' Union.");

        su.setExit("east", outside);
        outside.setExit("west", su);

    }

    /**
     * Create the pub and link it to the outside
     */
    private void createPub()
    {
        pub = new Location("in the local pub.");

        outside.setExit("south", pub);
        pub.setExit("north", outside);

        pub.setItem(new Item("Beer", "Relax with some booze"), "beer");
    }

    /**
     * Create the office linked to the lab
     */
    private void createHalls()
    {
        halls = new Location("in the student halls.");

        halls.setExit("west", outside);
        outside.setExit("east", halls);
    }
    
    /**
     * Create the lab and link it to the outside and office
     */
    private void createReception()
    {
        reception = new Location("in the university reception.");
        reception.setItem(new Item("Sanitizer", "Use the sanitizer to keep you free from any virus"), "sanitizer");
        reception.setItem(new Item("Water", "Use the sanitizer to keep you free from any virus"), "water");
        reception.setItem(new Item("Snack", "Use the sanitizer to keep you free from any virus"), "snack");

        outside.setExit("north", reception);
        reception.setExit("south", outside);
    }

    private void createCafe()
    {
        cafe = new Location("in the university café.");

        cafe.setExit("west", reception);
        reception.setExit("east", cafe);
    }

    private void createGraduation()
    {
        graduation = new Location("in graduation room.");

        graduation.setExit("east", reception);
        reception.setExit("west", graduation);
    }

    private void createLab()
    {
        lab = new Location("in the computing lab.");

        lab.setExit("south", reception);
        reception.setExit("north", lab);
    }


    public static Location getCurrentLocation()
    {
        return currentLocation;
    }
    
    public void enterLocation(Location nextLocation)
    {
        currentLocation = nextLocation;
    }

}
