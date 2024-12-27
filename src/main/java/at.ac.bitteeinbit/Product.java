package at.ac.bitteeinbit;

import com.google.gson.Gson;

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

    public void addProduct(int price, String productGroup, String name) {
        //check if product is already in product json
        Gson gson = new Gson();
    }

    public void removeProduct() {
        //toDo
    }
}
