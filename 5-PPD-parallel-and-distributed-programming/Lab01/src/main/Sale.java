package main;

import main.exceptions.QuantityException;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Sale implements Runnable
{
    private static int idGen = 0;
    private Thread thread;
    private String saleId;
    private Bill bill;
    private Inventory inventory;
    private Semaphore salesSemaphore;
    private Semaphore inventorySemaphore;

    public Sale(Inventory inventory, Semaphore salesSemaphore, Semaphore inventorySemaphore)
    {
        idGen += 1;
        this.saleId = Integer.toString(idGen);
        this.inventory = inventory;
        this.bill = new Bill(this.inventory);
        this.salesSemaphore = salesSemaphore;
        this.inventorySemaphore = inventorySemaphore;
    }

    @Override
    public void run() {
        System.out.println("Sale #" + this.saleId + " running.");
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 4); i++)
        {
            String randomProduct = this.generateRandomProduct();
            int randomQuantity = ThreadLocalRandom.current().nextInt(1, 29);
            try{
                this.salesSemaphore.acquire();
                inventory.saleProduct(randomProduct, randomQuantity);
                bill.addProduct(inventory.getProductByName(this.generateRandomProduct()), randomQuantity);
            }catch (InterruptedException | QuantityException ex) {
                System.out.println("Sale #" + this.saleId + " interrupted while selling.");
            }
            finally {
                salesSemaphore.release();
            }
        }
        try {
            this.inventorySemaphore.acquire();
            this.inventory.addBill(bill);
            this.inventorySemaphore.release();
            Thread.sleep(1000);
        }catch (InterruptedException ex) {
            System.out.println("Sale #" + this.saleId + " interrupted while recording bill.");
        } finally {
            System.out.println("Sale #" +  this.saleId + " exiting.");
        }
    }

    public void start() {
        System.out.println("Starting Sale #" + this.saleId + ".");
        if(thread == null) {
            thread = new Thread(this, saleId);
            thread.start();
        }
    }

    public Thread getThread() {
        return thread;
    }

    public Bill getBill() {
        return bill;
    }

    public String getSaleId()
    {
        return this.saleId;
    }

    private String generateRandomProduct()
    {
        int bound = 3;
        int randomNum = ThreadLocalRandom.current().nextInt(0, bound + 1);
        switch (randomNum)
        {
            case 0:
                return "Apple";
            case 1:
                return "Cucumber";
            case 2:
                return "Pear";
            case 3:
                return "ToiletPaper";
        }
        return "Apple";
    }
}