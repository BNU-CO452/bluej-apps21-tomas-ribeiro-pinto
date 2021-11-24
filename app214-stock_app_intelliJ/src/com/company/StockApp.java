package com.company;

/**
 * This app provides a user interface to the
 * stock manager so that users can add, edit,
 * print and remove stock products
 *
 * @author Student Name
 * @version 0.1
 */
public class StockApp
{
    private InputReader reader;
    private StockList stock;
    private StockList stockList;
    private StockDemo demo;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    
    /**
     * Constructor for objects of class StockApp
     */
    public StockApp()
    {
        reader = new InputReader();
        stock = new StockList();
        demo = new StockDemo(stock);
        //this.stockList = stockList;
    }

    /**
     *  Display a list of menu choices and execute
     *  the user's choice.
     */
    public void run()
    {
        boolean finished = false;
        
        while(!finished)
        {
            printHeading();
            printMenuChoices();
           
            String choice = reader.getString("Please enter your choice > ");
            
            finished = executeChoice(choice.toLowerCase());
        }
    }

    private boolean executeChoice(String choice)
    {
        if(choice.equals("quit"))
        {
            System.out.println(ANSI_GREEN_BACKGROUND + "Goodbye! Closing the application..." + ANSI_RESET);
            return true;
        }
        else if(choice.equals("add"))
        {
            int id = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the ID for the product desired" + ANSI_RESET);
            String name = reader.getString(ANSI_GREEN_BACKGROUND + "Please enter the name for the new product" + ANSI_RESET);
            Product product = new Product(id, name);
            if (stock.findProduct(id) != null)
            {
                System.out.println(ANSI_RED_BACKGROUND + "Operation denied! There is already a product with that same id..." + ANSI_RESET);
            }
            else
            {
                stock.add(product);
                System.out.println(ANSI_YELLOW_BACKGROUND + "The product " + product.getName() + " has been added to the product list" + ANSI_RESET);
            }
        }

        else if(choice.equals("remove"))
        {
            int id = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the ID of the product you want to remove" + ANSI_RESET);
            stock.remove(id);
        }
        else if(choice.equals("print"))
        {
            stock.print();
        }

        else if(choice.equals("buy"))
        {
            int id = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the ID of the product you want to buy" + ANSI_RESET);
            int amount = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the amount you want to buy" + ANSI_RESET);
            stock.buyProduct(id, amount);
        }
        else if(choice.equals("sell"))
        {
            int id = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the ID of the product you want to sell" + ANSI_RESET);
            int amount = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the amount you want to sell" + ANSI_RESET);
            stock.sellProduct(id, amount);
        }
        else if(choice.equals("low"))
        {
            stock.listLowStock();
        }
        else if(choice.equals("search"))
        {
            String word = reader.getString(ANSI_GREEN_BACKGROUND + "To search for specific products please enter their name here:" + ANSI_RESET);
            stock.searchProduct(word);
        }
        else if(choice.equals("stock"))
        {
            int id = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the ID of the product you want to check stock" + ANSI_RESET);
            stock.numberInStock(id);
        }
        else if(choice.equals("restock"))
        {
            int amount = reader.getInt(ANSI_GREEN_BACKGROUND + "Please enter the amount you want to re-stock all the products in low stock by" + ANSI_RESET);
            stock.restockby(amount);
        }
        else if(choice.equals("demo"))
        {
            demo.runDemo();
        }
        else
        {
            System.out.println(ANSI_RED_BACKGROUND + "The choice you selected is not available. Check the table and your spelling..."
                    + ANSI_RESET );
        }

        return false;
    }
   
    /**
     * Print out a menu of operation choices
     */
    private void printMenuChoices()
    {
        System.out.println();
        System.out.println(ANSI_RED + "    Add:" + ANSI_RESET+ "        Add a new product");
        System.out.println(ANSI_RED + "    Remove:" + ANSI_RESET+ "     Remove an old product");
        System.out.println(ANSI_RED + "    Print:" + ANSI_RESET+ "      Print all products");
        System.out.println(ANSI_RED + "    Buy:" + ANSI_RESET+ "        Increase product stock");
        System.out.println(ANSI_RED + "    Sell:" + ANSI_RESET+ "       Decrease product stock");
        System.out.println(ANSI_RED + "    Low:" + ANSI_RESET+ "        Show products with low stock");
        System.out.println(ANSI_RED + "    Search:" + ANSI_RESET+ "     Search products by name");
        System.out.println(ANSI_RED + "    Stock:" + ANSI_RESET+ "      Show stock of product by id");
        System.out.println(ANSI_RED + "    Restock:" + ANSI_RESET+ "    Re-stock all products in low stock by a given amount");
        System.out.println(ANSI_RED + "    Demo:" + ANSI_RESET+ "       Run StockDemo");
        System.out.println(ANSI_RED + "    Quit:" + ANSI_RESET+ "       Quit the program");
        System.out.println();        
    }
    
    /**
     * Print the title of the program and the authors name
     */
    private void printHeading()
    {
        System.out.println(ANSI_RESET + "********************************");
        System.out.println("  " + ANSI_GREEN_BACKGROUND + "App21-04:" + ANSI_RESET + " Stock Application ");
        System.out.println("      by Tomás Pinto");
        System.out.println("********************************");
    }
}