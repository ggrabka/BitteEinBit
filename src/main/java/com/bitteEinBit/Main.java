package com.bitteEinBit;

public class Main {
    public static void main(String[] args) {

        Display display = new Display();

        display.displayEntrySelection();
        display.selectProduct();

       Product test = new Product(3.0, "Gebäck", "Weißbrot");
        Product test2 = new Product(3.0, "Gebäck", "Vollkornbrot");
        test.addProduct();
     //   test.removeProduct();
        test2.addProduct();
    }
    }