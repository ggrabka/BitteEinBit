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


public class Helper {

    int counter = 0;
    public static final String PRODUCTS_JSON = "products.json";

    List<Product> products = new ArrayList<>();
    Gson gson = new Gson();

    public void readFromJsonFile() {
        try {
            FileReader reader = new FileReader(PRODUCTS_JSON);
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
    }

    public void checkIfProductExistsInJsonFile(Product product) {
        Scanner scanner = new Scanner(System.in);
        for (Product currentProduct : products) {
            counter = currentProduct.getProductId();
            if (currentProduct.getName().equals(product.getName())) {
                System.out.println("Product with the same name already exist. Do you wish to overwrite to the product? y/n");
                String input = scanner.next();
                while(!input.equals("y") && !input.equals("n")) {
                    System.out.println("Wrong input, please enter y or n");
                    input = scanner.next();
                }
                if(input.equals("y")) {
                    product.setProductId(counter);
                    currentProduct.setName(product.getName());
                    currentProduct.setPrice(product.getPrice());
                    currentProduct.setProductGroup(product.getProductGroup());
                    break;
                } else {
                    return;
                }
            }
            counter+=1;
        }
    }

    public void addProductToJsonFile(Product product) {
        if(counter > products.size()) {
            product.setProductId(counter);
            products.add(product);
        }
        writeToJsonFile();
    }

    public void removeProductFromJsonFile(Product product) {
        int sizeBeforeRemoval = products.size();
        for (Product currentProduct : products) {
            if (currentProduct.getName().equals(product.getName())) {
                products.remove(product);
                System.out.println("Product removed successfully.");
                break;
            }
            counter++;
        }
        if((products.size() == sizeBeforeRemoval)) {
            System.out.println("No product found with the provided parameters!");
        }
        writeToJsonFile();
    }

    public void writeToJsonFile() {
        try {
            FileWriter writer = new FileWriter(PRODUCTS_JSON);
            gson.toJson(products,writer);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error in writing the file.");
        }
    }
}
