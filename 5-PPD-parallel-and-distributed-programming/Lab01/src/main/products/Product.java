package main.products;

public class Product
{
    private String name;
    private float price;
    private int quantity;

    public Product() {
        this.name = this.getClass().getSimpleName();
        this.price = -1;
        this.quantity = -1;
    }

    public Product(float price, int quantity) {
        this.name = this.getClass().getSimpleName();
        this.price = price;
        this.quantity = quantity;
    }

    public boolean buyN(int quantity) {
        if (this.quantity <= 0 || quantity > this.quantity)
            return false;
        this.quantity -= quantity;
        return true;
    }

    public int getQuantity() {
        return quantity;
    }

    public void supplyN(int quantity)
    {
        if(this.quantity > 0)
            this.quantity += quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Product)) {
            return false;
        }

        Product p = (Product) other;
        return this.name.equals(p.getName());
    }

    @Override
    public String toString()
    {
        return "Name: " + this.name +
                ", Price: " + this.price +
                ", Quantity: " + this.quantity + "\n";
    }

}
