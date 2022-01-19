/**
 * This class is the blueprint that creates
 * the items that a player can pick up during the game
 */

public class Item
{

    private String name;
    private String description;

    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}