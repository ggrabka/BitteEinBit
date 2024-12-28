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

public class Main {
    public static void main(String[] args) throws IOException {

        Product test = new Product(10, "Gebäck", "Pizza");

        Gson gson = new Gson();
        List<Product> products = new ArrayList<>();

        //1. get products from product.json and save them into products list

        try {
            FileReader reader = new FileReader("products.json");

            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            products = gson.fromJson(reader, type);
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. check if product is already in the json and overwrite product
        int counter = 0;
        for (Product product : products) {
            System.out.println(product.getName());
            counter = product.getProductId();
            if (product.getName().equals(test.getName())) {
                product.setName(test.getName());
                product.setPrice(test.getPrice());
                product.setProductGroup(test.getProductGroup());
            }
            counter+=1;
        }

        //3. Add product to the products.json
        test.setProductId(counter);
        products.add(test);

        try {
            FileWriter writer = new FileWriter("products.json");
            gson.toJson(products,writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }