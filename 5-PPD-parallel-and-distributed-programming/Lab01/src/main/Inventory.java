package main;

import main.exceptions.CheckException;
import main.exceptions.InvalidProduct;
import main.exceptions.QuantityException;
import main.products.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Inventory
{
    private float money;
    private List<Bill> listOfBills;
    private List<Product> products;
    private int checkCounter;
    private int totalProductsSold;

    public Inventory(){
        this.money = 0;
        this.listOfBills = new ArrayList<>();
        this.products = new ArrayList<>();
        this.checkCounter = 0;
        this.totalProductsSold = 0;
    }

    public float getMoney() {
        return money;
    }

    public List<Bill> getListOfBills() {
        return listOfBills;
    }

    public List<Product> getListOfProducts() {
        return products;
    }

    public boolean check() {
        int checkSum = 0;
        for(Bill bill : listOfBills)
            checkSum += bill.getTotalPrice();
        return (checkSum == this.money);
    }

    public void addBill(Bill bill)
    {
        if (this.checkCounter >= 3) {
            this.checkCounter = 0;
            if (!this.check())
                throw new CheckException("Inventory stock does not match with the registered bills!");
            System.out.println("Check: " + this.toString());
        }

        this.listOfBills.add(bill);
        this.money += bill.getTotalPrice();
        System.out.println(bill.toString());
        this.totalProductsSold += bill.getProductsSold();
        this.checkCounter++;
    }

    public void saleProduct(String productName, int quantity)
    {
        boolean success = false;
        for(Product p : this.products)
        {
            if (p.getName().equals(productName) && p.getQuantity() >= quantity) {
                p.buyN(quantity);
                success = true;
            }
        }
        if (!success) {
            throw new QuantityException("You're trying to buy " + quantity + " " + productName + "s. Operation failed!");
        }
    }

    public void supplyProduct(Product product)
    {
        this.products.add(product);
    }

    public void initiateProducts()
    {
        this.supplyProduct(new Apple(3, 5000));
        this.supplyProduct(new Pear(5, 5000));
        this.supplyProduct(new ToiletPaper(2, 5000));
        this.supplyProduct(new Cucumber(6, 5000));
    }

    public boolean productExists(String productName)
    {
        for(Product product : this.products)
        {
            if (product.getName().equals(productName))
                return true;
        }
        return false;
    }

    public Product getProductByName(String name)
    {
        for(Product product : this.products)
        {
            if (product.getName().equals(name))
                return product;
        }
        throw new InvalidProduct("Product does not exist!");
    }

    @Override
    public String toString()
    {
        String productString = products.stream().map(p -> p.toString()).reduce("", String::concat);
        return "\nInventory:\n" + productString + "Profit: " + this.money + "\nTotal no. of sold products: " + this.totalProductsSold + "\n";
    }
}
