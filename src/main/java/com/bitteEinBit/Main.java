package com.bitteEinBit;

/**
 * Die `Main`-Klasse startet das Kassensystem und ermöglicht die Interaktion mit der Kasse.
 */
public class Main {
    public static void main(String[] args) {
        CashRegisterScreen cashRegisterScreen = new CashRegisterScreen();
        cashRegisterScreen.startForCustomer(); // Startet die Kassenoberfläche
    }
}