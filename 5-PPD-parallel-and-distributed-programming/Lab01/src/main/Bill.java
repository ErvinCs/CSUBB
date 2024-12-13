package main;

import main.exceptions.QuantityException;
import main.products.Product;

import java.util.HashMap;
import java.util.Map;

public class Bill
{
    private Map<Product, Integer> map;
    private float totalPrice;
    private Inventory inventory;
    private int productsSold;


    public Bill(Inventory inventory) {
        this.totalPrice = 0;
        this.map = new HashMap<>();
        this.inventory = inventory;
        this.productsSold = 0;
    }

    public Bill(Map<Product, Integer> map, float totalPrice, Inventory inventory, int productsSold) {
        this.map = map;
        this.totalPrice = totalPrice;
        this.inventory = inventory;
        this.productsSold = productsSold;
    }

    public void addProduct(Product product, int quantity)
    {
        this.map.put(product, quantity);
        product.buyN(quantity);
        totalPrice += product.getPrice() * quantity;
        this.productsSold += quantity;
    }

    public int getProductsSold(){
        return this.productsSold;
    }

    public Map<Product, Integer> getMap() {
        return this.map;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString()
    {
        String output = "Bill:\n";
        for(Map.Entry<Product, Integer> entry : map.entrySet())
        {
            output += entry.getKey().getName() + " - UnitPrice: " + entry.getKey().getPrice() + ", Quantity: " + entry.getValue().toString();
            output += " Price: " + entry.getValue() * entry.getKey().getPrice() + "\n";
        }
        output += "Total - " + this.totalPrice + "\nNo. of sold products: " + this.productsSold + "\n";
        return output;
    }


}
