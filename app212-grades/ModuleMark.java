/**
 * This class is created to store marks and 
 * associate a specific student to a module and the mark achieved.
 * 
 * @author Derek Peacock and Nicholas Day
 * Modified by Tomás Pinto
 * @version 31st October 2021
 */
public class ModuleMark
{
    private int mark;
    private Module module;

    /**
     * Constructor for objects of class ModuleMark
     */
    public ModuleMark(Module module)
    {
        mark = 0;
        this.module = module;
    }
    
    public int getValue()
    {
        return mark;
    }
    
    /**
     * If the mark is 40% or more then the 
     * student is awarded the module's credit.
     */
    public int getCredit()
    {
        if(mark <= Grades.F.getValue())
        {
            return 0;
        }
        else
        {
            return Module.CREDIT;
        }
    }
    
    /**
     * Set the mark for the associated module 
     * and link it to the student.
     */
    public void setMark(int mark)
    {
        this.mark = mark;
    }
    
    public Module getModule()
    {
        return module;
    }
    
    /**
     * Print the module details and any credit
     * that has been awarded.
     */
    public void print()
    {
        module.print();
        System.out.print("\t  " + getCredit() + "\t" + mark);
    }
}
