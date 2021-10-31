import java.util.ArrayList;
/**
 * This class stores information about a course
 * that enrolled students may want to complete
 *
 * @author Derek Peacock and Nicholas Day
 * Modified by Tomás Pinto
 * @version 31st October 2021
 */
public class Course
{
    public final static int MAXN_MODULES = 4;

    public ArrayList<Module> modules;
    
    private String code;
    private String title;
    
    private Grades finalGrade;
     
    public Course()
    {
        this("BT1CWD1", "BSc Computing and Web Development");
    }
    
    /**
     * Constructor for objects of class Course
     */
    public Course(String code, String title)
    {
        // initialise instance variables
        this.code = code;
        this.title = title;
        
        modules  = new ArrayList<Module>();
        
        createModules();
    }

    /**
     * Create four modules and add them to the
     * modules list for testing purposes.
     */
    public void createModules()
    {
    Module co450 = new Module("CO450", "Computer Architectures");
    Module co452 = new Module("CO452", "Programming Concepts");
    Module co454 = new Module("CO454", "Digi-Tech");
    Module co456 = new Module("CO456", "Web Development");
    modules.add(co450);
    modules.add(co452);
    modules.add(co454);
    modules.add(co456);
    }
    
    /**
     * Add a module to the course
     */
    public void addModule(Module module)
    {
        if(modules.size() < MAXN_MODULES)
        {
            modules.add(module);
        }
    }
    
    /**
     * Convert module's mark to a grade (A,B,C,D, F or NS)
     */
    public Grades convertToGrade(int mark)
    {   
        if (mark > 0 && mark <= 39)
            return Grades.F;
        else if (mark > 39 && mark <=49)
            return Grades.D;
        else if (mark > 49 && mark <=59)
            return Grades.C;
        else if (mark > 59 && mark <=69)
            return Grades.B;
        else if (mark > 69 && mark <=100)
            return Grades.A;
        else
            return Grades.NS;
    }
    
    /**
     * Calculate the average mark from the four module marks
     * and convert that into a final grade.
     */
    public Grades calculateGrade(ArrayList<ModuleMark> marks)
    {
        int total = 0;
        int averageMark = 0;
        for (ModuleMark mark : marks)
        {
            total = total + mark.getValue();    
        }
        averageMark = total / modules.size();
        finalGrade = convertToGrade(averageMark);
        return finalGrade;
    }
    
    /**
     * Prints out the details of a course and the
     * four modules
     */
    public void print()
    {
        System.out.println();
        System.out.println(" Course " + code + ": " + title);
        //System.out.println();
        
        printModules();
    }
    
    /**
     * Print the course's four modules
     */
    public void printModules()
    {
    for(Module module : modules)
        {
    module.print();
    module.printCredit();
    }
    }

}
