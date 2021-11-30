package com.company;

/**
 * The main class, which allows use the class
 * without creating a Main object
 *
 * @author Tomás Pinto
 * @version 30th November 2021
 */
public class Main
{
    private static StockApp app;

    /**
     * Creates a new StockApp object and runs the program
     * @param args
     */
    public static void main(String[] args)
    {
        app = new StockApp();
        app.run();
    }
}
