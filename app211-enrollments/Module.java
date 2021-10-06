/**
 * This class provides a tool to create and print a module details.
 *
 * @author Tomás Pinto
 * @version 1st Oct 2021
 */
public class Module
{
    // Module's Code
    private String module_code;
    // Title of the Module
    private String module_title;
    // Credit Value of the Module
    private int credit_value;

    /**
     * Create a new Module with a code, title and credit value.
     */
    public Module(String module_code, String module_title, int credit_value)
    {
       
        this.module_code = module_code;
        this.module_title = module_title;
        this.credit_value = credit_value;
    }

    /**
    * Return the Module's Code
     */
    public String getCode()
    {
        // put your code here
        return module_code;
    }
    
    /**
    * Return the Module's Title
     */
    public String getTitle()
    {
        // put your code here
        return module_title;
    }

        /**
    * Return the Module's Credit Value
     */
    public int getCreditValue()
    {
        // put your code here
        return credit_value;
    }
    /**
     * Set a new credit value for the module.
     */
    public void setCreditValue(int credit_value)
    {
           this.credit_value = credit_value;
    }
    /**
     * Print out the details of the module to the terminal.
     */
    private void printHeading()
    {
        System.out.println(" --------------------------------");
        System.out.println("   App211: Course Details");
        System.out.println(" --------------------------------");
        System.out.println();
    }
    /**
     * Print all the module details
     */
    public void print()
    {
        printHeading();
        System.out.println("   Module Title: " + module_title);
        System.out.println("   Module Code:  " + module_code);
        System.out.println("   Credit Value: " + credit_value);
    }
    
    
    
}
