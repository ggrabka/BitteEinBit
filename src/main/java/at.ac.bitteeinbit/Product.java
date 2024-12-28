package at.ac.bitteeinbit;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Product {
    private int productId,price;
    private String productGroup,name;

    public Product(int price, String productGroup, String name) {
        this.price = price;
        this.productGroup = productGroup;
        this.name = name;
    }

    public Product getProduct() {
        return this;
    }

    public void testJson() {
        Gson gson = new Gson();

        try {
            FileReader reader = new FileReader("products.json");
            Product test = gson.fromJson(reader,Product.class);
            System.out.println(test.name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void addProduct(int price, String productGroup, String name) throws FileNotFoundException {
        //check if product is already in product json



    }

    public void removeProduct() {
        //toDo
    }
}
