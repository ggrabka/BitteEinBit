package com.bitteEinBit;

import java.util.Objects;

public class Product {
    private int productId;
    private double price;
    private String productGroup;
    private String name;

    public Product(double price, String productGroup, String name) {
        this.price = price;
        this.productGroup = productGroup;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getProductGroup() {
        return productGroup;
    }

    void setProductId(int productId) {this.productId = productId;}

    void setName(String name) {
        this.name = name;
    }

    void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    void setPrice(double price) {
        this.price = price;
    }

    public void addProduct() {
        Helper helper = new Helper();
        helper.readFromJsonFile();
        helper.checkIfProductExistsInJsonFile(this);
        helper.addProductToJsonFile(this);
    }

    public void removeProduct() {
        Helper helper = new Helper();
        helper.readFromJsonFile();
        helper.removeProductFromJsonFile(this);
    }

    @Override
    public String toString() {
        return "{productId=" + productId + ", productGroup=" + productGroup + ", name=" + name + ", price=" + price + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(productGroup, product.productGroup) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, productGroup, name);
    }
}
