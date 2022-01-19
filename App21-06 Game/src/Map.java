/**
 * This class is reponsible for creating and
 * linking all the Locations in the game to
 * form a location network
 * <p>
 * [Computing lab]
 * |
 * [Graduation]<---->[Reception]<---->[Café]
 * |
 * [SU]<------->[Outside]<---->[Halls]
 * |
 * [Pub]
 *
 * @author Derek Peacock and Nicholas Day
 * @version 18th January 2022
 * @modified Tomás Pinto
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
     * su, halls and pub
     */
    private void createOutside()
    {
        outside = new Location("outside the main entrance of the university.");
        outside.setItem(new Item("Card", "Use your student card to get access to buildings"), "card");

    }

    /**
     * Create the su linked to the outside
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

        // Set item to pick up while at the pub
        pub.setItem(new Item("Beer", "Relax with some booze"), "beer");
    }

    /**
     * Create the halls linked to the outside
     */
    private void createHalls()
    {
        halls = new Location("in the student halls.");

        halls.setExit("west", outside);
        outside.setExit("east", halls);
    }

    /**
     * Create the university reception and link it to the outside,
     * café, graduation theatre and lab
     */
    private void createReception()
    {
        reception = new Location("in the university reception.");

        // Set the items present in this location
        reception.setItem(new Item("Sanitizer", "Use the sanitizer to keep you free from any virus"), "sanitizer");
        reception.setItem(new Item("Water", "Use the sanitizer to keep you free from any virus"), "water");
        reception.setItem(new Item("Snack", "Use the sanitizer to keep you free from any virus"), "snack");

        outside.setExit("north", reception);
        reception.setExit("south", outside);
    }

    /**
     * Create the café linked with the reception
     */
    private void createCafe()
    {
        cafe = new Location("in the university café.");

        cafe.setExit("west", reception);
        reception.setExit("east", cafe);
    }

    /**
     * Create the graduation theatre
     */
    private void createGraduation()
    {
        graduation = new Location("in graduation room.");

        graduation.setExit("east", reception);
        reception.setExit("west", graduation);

        // Easter Egg that makes the user win the game
        graduation.setItem(new Item("Diploma", "Win the game by taking this item"), "diploma");
    }

    /**
     * Create the lab and link it with the reception
     */
    private void createLab()
    {
        lab = new Location("in the computing lab.");

        lab.setExit("south", reception);
        reception.setExit("north", lab);
    }

    /**
     * This method returns the current location the user is in
     *
     * @return Location object
     */
    public static Location getCurrentLocation()
    {
        return currentLocation;
    }

    /**
     * Method that allows to enter another location
     *
     * @param nextLocation
     */
    public static void enterLocation(Location nextLocation)
    {
        currentLocation = nextLocation;
    }

}
