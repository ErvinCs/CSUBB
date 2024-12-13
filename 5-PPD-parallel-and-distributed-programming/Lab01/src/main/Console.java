package main;

import main.products.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Console
{
    //System Model: ASUS GL 552JX
    //Processor: Intel(R) Core(TM) i7-4720HQ CPU @ 2.60GHz (8 CPUs)
    //Memory: 16Gb RAM
    //Operating System: Windows 10 64-bit
    public static void main(String[] args)
    {
        Semaphore salesSem = new Semaphore(1);
        Semaphore invSem = new Semaphore(1);
        Inventory inventory = new Inventory();
        inventory.initiateProducts();

        System.out.println(inventory.toString());

        List<Sale> sales = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 250; i++)
        {
            sales.add(new Sale(inventory, salesSem, invSem));
            sales.get(i).start();
        }

        for(Sale s : sales) {
            try {
                s.getThread().join();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted!" + ex.getMessage());
            }
        }
        long stopTime = System.currentTimeMillis();

        System.out.println(inventory.toString());

        long elapsedTime = stopTime - startTime;
        System.out.println("Time consumed: " + elapsedTime + "ms.");
    }
}
