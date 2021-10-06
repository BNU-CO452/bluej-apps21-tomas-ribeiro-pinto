/**
 * This class contains information about an undergraduate course
 * at BNU together with a list of student who are enrolled
 *
 * @author Derek Peacock
 * @modified by Tomás Pinto
 * @version 1st Oct 2021
 */
public class Course
{
    // A unique identifier e.g. BT1CTG1
    private String course_code;
    // The full title including qualification and subject
    private String course_title;
    // Module details
    private Module module;
    /**
     * Create a Course with a maximum number of enrolments. 
     * All other details are set to unkown values.
     */
    public Course(String course_code, String course_title)
    {
        this.course_code = course_code;
        this.course_title = course_title;
    }

    
    /**
     * Print the details of the course, the list
     * of students enrolled and the module
     */
    public void print()
    {
        printHeading();
        
        System.out.println(" Course Code: " + course_code + ": " + course_title);
        System.out.println();
    }
    
    /**
     * Print out the details of the course to the terminal.
     */
    private void printHeading()
    {
        System.out.println(" --------------------------------");
        System.out.println("   App211: Course Details");
        System.out.println(" --------------------------------");
        System.out.println();
    }
    
    public void addModule(Module module)
    {
        this.module = module;
    }
    public void printModule()
    {
        module.print();
        System.out.println("   Course Code: " + course_code + ": " + course_title);
    }
}
