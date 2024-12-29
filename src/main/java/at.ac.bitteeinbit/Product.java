package at.ac.bitteeinbit;

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

    protected void setProductId(int productId) {this.productId = productId;}

    protected void setName(String name) {
        this.name = name;
    }

    protected void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    protected void setPrice(double price) {
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
