package at.ac.bitteeinbit;

public class Product {

    private String name;
    private double price;
    private int id;
    private int quantity;

    private Product[] products;

    public Product(String name, double price, int id) {

        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = 0;
    }

    public Product(Product[] products) {

        this.products = products;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public int getId() {

        return id;
    }

    public int getQuantity() {

        return quantity;
    }

    public void increaseQuantity() {

        this.quantity++;
    }

    public void decreaseQuantity() {

        if (this.quantity > 0) {

            this.quantity--;
        }
    }

    public void printProduct() {

        System.out.printf(" >> %s %.2f €\n (Im Warenkorb: %d)\n", this.name, this.price, this.quantity);
    }

    public String toString() {

        return String.format(" // %s %.2f € Nr. %d", this.name, this.price, this.id);
    }

    public void displayProducts() {

        for (Product product : products) {

            System.out.println(product);
        }
    }
}