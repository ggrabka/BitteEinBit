package at.ac.bitteeinbit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productId;
    private double price;
    private String productGroup;
    private String name;
    List<Product> products;

    public Product(double price, String productGroup, String name) {
        this.price = price;
        this.productGroup = productGroup;
        this.name = name;
    }

    public Product getProduct() {
        return this;
    }

    public double getPrice() {
        return price;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct(int price, String productGroup, String name) throws FileNotFoundException {
        //check if product is already in product json
    }

    public void removeProduct() {
        //toDo
    }

    @Override
    public String toString() {
        return "{productId=" + productId + "productGroup=" + productGroup + ", name=" + name + ", price= " + price + "}";
    }
}
