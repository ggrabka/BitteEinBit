package at.ac.bitteeinbit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public void readFromJsonFile(List<Product> products, Gson gson) {
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
    }
}
