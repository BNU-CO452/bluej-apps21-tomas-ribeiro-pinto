package com.company;

import java.util.ArrayList;
/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author: Tomás Pinto
 * @version: 30th November 2021
 */
public class StockList
{
    // A list of the products.
    private ArrayList<Product> stock;
    // Terminal colors retrieved from: https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    /**
     * Initialise the stock manager.
     */
    public StockList()
    {
        stock = new ArrayList<Product>();
    }

    /**
     * Add a product to the list.
     * @param item The product item to be added.
     */
    public void add(Product item)
    {
        stock.add(item);
    }
    
    /**
     * A method to buy a single quantity of the product
     */
    public void buyProduct(int productID)
    {
        buyProduct(productID, 1);
    }
    
    
    /**
     * Buy a quantity of a particular product.
     * Increase the quantity of the product by the given amount.
     * @param productID The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    public void buyProduct(int productID, int amount)
    {
        Product product = findProduct(productID);
        
        if(product != null) 
        {
            if(amount < 0)
            {
                System.out.println(ANSI_RED_BACKGROUND + "Cannot buy " + amount + " of " + product.getName()
                + " because there can't be a negative number of stock" + ANSI_RESET);
            }
            else if(product.getQuantity() + amount < 1000)
            {
                product.increaseQuantity(amount);
                System.out.println(ANSI_YELLOW_BACKGROUND + "Bought " + amount + " of " + product.getName() + ANSI_RESET);
            }
            else
            {
                System.out.println(ANSI_RED_BACKGROUND + "Not enough storage to buy " + amount + " of " + product.getName() + ANSI_RESET);
            }
        }
        
        else
        {
            System.out.println(ANSI_RED_BACKGROUND +"The desired product does not exist! Please check the ID and try again." + ANSI_RESET);
        }
    }
    
    /**
     * Find a product to match the product id,
     * if not found return null
     */
    public Product findProduct(int productID)
    {
        for(Product product : stock)
        {
           if(product.getID() == productID)
            {
                return product;
            }
        }
        return null;
    }
    
    
    /**
     * Sell one of the given product.
     * Show the before and after status of the product.
     * @param productID The ID of the product being sold.
     * @param amount The amount of the product being sold.
     */
    public void sellProduct(int productID, int amount)
    {
        Product product = findProduct(productID);
        
        if(product != null) 
        {
            if(amount < 0)
            {
                System.out.println(ANSI_RED_BACKGROUND + "Cannot sell " + amount + " of " + product.getName()
                + " because there can't be a negative number of stock." + ANSI_RESET);
            }
            
            else if(product.getQuantity() > 0 && product.getQuantity() >= amount)
            {
                product.decreaseQuantity(amount);
                System.out.println(ANSI_YELLOW_BACKGROUND +"Sold " + amount + " of " + product.getName() + ANSI_RESET);
            }
            
            else if(product.getQuantity() < amount)
            {
                System.out.println(ANSI_RED_BACKGROUND + "Cannot sell " + amount + " of " + product.getName()
                + " because there's only " + product.getQuantity() + " in stock." + ANSI_RESET);
            }
    
            else
            {
                System.out.println(ANSI_RED_BACKGROUND + "The item: " + product.getName() + " is out of stock!" + ANSI_RESET);
            }
        }
        
        else
        {
            System.out.println(ANSI_RED_BACKGROUND + "The desired product does not exist! Please check the ID and try again." + ANSI_RESET);
        }
    }

    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param productID The ID of the product.
     */
    public void numberInStock(int productID)
    {
        Product product = findProduct(productID);
        if(product != null)
        {
            System.out.println(ANSI_YELLOW_BACKGROUND +"There are " + product.getQuantity() + " units of " + product.getName() + " in stock" + ANSI_RESET);
        }
        else
        {
            System.out.println(ANSI_RED_BACKGROUND +"Product not found!" + ANSI_RESET);
        }
    }

    /**
     * Print details of the given product. If found,
     * its name and stock quantity will be shown.
     * @param productID The ID of the product to look for.
     */
    public void printProduct(int productID)
    {
        Product product = findProduct(productID);
        
        if(product != null) 
        {
            System.out.println(product.toString());
        }
        else
        {
            System.out.println(ANSI_RED_BACKGROUND +"Product not found!" + ANSI_RESET);
        }
    }
    
    /**
     * Print out each product in the stock
     * in the order they are in the stock list
     */
    public void print()
    {
        printHeading();
        
        for(Product product : stock)
        {
            System.out.println(product);
        }

        System.out.println();
    }
    
    /**
     * Prints the header of the Stock List
     */
    public void printHeading()
    {
        System.out.println();
        System.out.println(" Tomas' Shop Stock List");
        System.out.println(" ====================");
        System.out.println();
    }
    
    /**
     * Removing a specific product of the stock list
     * @param id The ID of the product to look for and remove
     */
    public void remove(int id)
    {
        Product product = findProduct(id);
        if(product != null)
        {
            stock.remove(product);
            System.out.println(ANSI_YELLOW_BACKGROUND + "The product " + product.getName() + " has been removed from the product list" + ANSI_RESET);
        }
        else
        {
            System.out.println(ANSI_RED_BACKGROUND + "Cannot find product to remove it. Try again..." + ANSI_RESET);
        }
    }
    
    /**
     * Search tool to search all the products that start with a given word.
     * @param word The word to look for
     */
    public void searchProduct(String word)
    {
        System.out.println();
        System.out.println(" Search List: Products that contain " + word);
        System.out.println(" ========================================");
        System.out.println();
        
        for(Product product: stock)
        {     
            if(product.getName().toLowerCase().contains(word.toLowerCase()))
            {
                System.out.println(product);
            }
        }
    }
    
    /**
     * Lists all the products that have fallen below 30 units in stock
     */
    public void listLowStock()
    {
        System.out.println();
        System.out.println(" Search List: Products with low stock " );
        System.out.println(" ====================================");
        System.out.println();
        
        for(Product product: stock)
        {     
            if(product.getQuantity() < 30)
            {
                System.out.println(product);
            }
        } 
        System.out.println();
    }
    
    /**
     * Re-stocking all the products listed in the low stock level list
     */
    public void restock()
    {
        for(Product product: stock)
        {     
            if(product.getQuantity() < 30)
            {
                product.increaseQuantity(30-product.getQuantity());
            }
        } 
    }
    /**
     * Re-stocking all the products listed in the low stock level list by a specific quantity
     */
    public void restockby(int amount)
    {
        for(Product product: stock)
        {
            if (product.getQuantity() < 30)
            {
                product.increaseQuantity(amount);
            }

        }
    }
}