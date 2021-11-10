
/**
 * Demonstrate the StockManager and Product classes.
 * The demonstration becomes properly functional as
 * the StockManager class is completed.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class StockDemo
{
    // The stock manager.
    private StockList stock;
    private StockList stockList;

    /**
     * Create a StockManager and populate it with at least
     * 10 sample products.
     */
    public StockDemo(StockList stock)
    {
        this.stock = stock;
        
        // Add at least 10 products, they must be unique to you
        // Make sure the ids are sequential numbers
        
        stock.add(new Product(101, "Samsung Galaxy S20"));
        stock.add(new Product(102, "Apple iPhone 13"));
        stock.add(new Product(103, "Google Pixel 4A"));
        stock.add(new Product(104, "Xiaomi Mi 11"));
        stock.add(new Product(105, "Samsung Galaxy Fold"));
        stock.add(new Product(106, "Huawei P30 Pro"));
        stock.add(new Product(107, "Apple iPhone 8"));
        stock.add(new Product(108, "Xiaomi Redmi 4 Pro "));
        stock.add(new Product(109, "Realme 8 5G"));
        stock.add(new Product(110, "OnePlus 9 5G"));
    }
    
    /**
     * Provide a demonstration of how the ProductList meets all
     * the user requirements by making a delivery of each product 
     * buying it in various amounts and then selling each
     * product by various amounts. Make sure all the requirements
     * have been demonstrated.
     */
    public void runDemo()
    {
        // Show details of all of the products before delivery.
        
        stock.print();

        buyProducts();
        stock.print();        

        sellProducts();
        stock.print();        
    }
    
    private void buyProducts()
    {
         stock.buyProduct(101, 40);
         stock.buyProduct(102, 4000);
         stock.buyProduct(102, 853);
         stock.buyProduct(103, 30);
         stock.buyProduct(104, 90);
         stock.buyProduct(105, 50);
         stock.buyProduct(106, -38);
         stock.buyProduct(107, 40);
         stock.buyProduct(108, 99);
         stock.buyProduct(109, 78);
         stock.buyProduct(110, 430);
         stock.buyProduct(113, 107);
    }

    private void sellProducts()
    {
        stock.sellProduct(101, 32);
        stock.sellProduct(102, -1);
        stock.sellProduct(102, 10);
        stock.sellProduct(103, 3200);
        stock.sellProduct(104, 0);
        stock.sellProduct(105, 42);
        stock.sellProduct(106, 42);
        stock.sellProduct(107, 42);
        stock.sellProduct(107, 30);
        stock.sellProduct(108, 59);
        stock.sellProduct(109, 8);
        stock.sellProduct(110, 3);
        stock.sellProduct(113, 1);
    }   
}