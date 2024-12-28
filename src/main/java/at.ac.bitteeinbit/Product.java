package at.ac.bitteeinbit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private void setName(String name) {
        this.name = name;
    }

    private void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    private void setPrice(double price) {
        this.price = price;
    }


    public void addProduct() {
        Gson gson = new Gson();
        List<Product> products = new ArrayList<>();

        //1. get products from product.json and save them into products list
        try {
            FileReader reader = new FileReader("products.json");
            Type type = new TypeToken<ArrayList<Product>>() {}.getType();
            products = gson.fromJson(reader, type);
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error in creating a FileReader object.");
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
        } catch(Exception e) {
            e.printStackTrace();
        }


        //2. check if product is already in the json and overwrite product
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        for (Product product : products) {
            System.out.println(product.getName());
            counter = product.getProductId();
            if (product.getName().equals(name)) {
                System.out.println("Product with the same name already exist. Do you wish to overwrite to the product? y/n");
                String input = scanner.next();
                while(!input.equals("y") && !input.equals("n")) {
                    System.out.println("Wrong input, please enter y or n");
                    input = scanner.next();
                }
                if(input.equals("y")) {
                   product.setName(this.name);
                   product.setPrice(this.price);
                   product.setProductGroup(this.productGroup);
                    break;
                } else if(input.equals("n")) {
                    return;
                }
            }
            counter+=1;
        }

        //3. Add product to the products.json
        this.productId = counter;
        if(this.productId > products.size()) {
            products.add(this);
        }

        try {
            FileWriter writer = new FileWriter("products.json");
            String test = gson.toJson(products);
            gson.toJson(products,writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error in writing the file.");
        }

    }

    public void removeProduct() {
        //toDo
    }

    @Override
    public String toString() {
        return "{productId=" + productId + ", productGroup=" + productGroup + ", name=" + name + ", price=" + price + "}";
    }
}
