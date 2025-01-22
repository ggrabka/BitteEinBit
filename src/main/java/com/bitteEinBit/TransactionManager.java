package com.bitteEinBit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Die `TransactionManager`-Klasse verwaltet alle Transaktionen des Kassensystems.
 * Sie speichert neue Transaktionen, lädt gespeicherte Transaktionen aus einer JSON-Datei
 * und erstellt einen Tagesabschlussbericht mit allen Transaktionen.
 */
public class TransactionManager {
    // **Pfad zur JSON-Datei, in der die Transaktionen gespeichert werden.**
    private static final String TRANSACTIONS_JSON = "transactions.json";

    // **Liste zur Speicherung aller geladenen oder neu erstellten Transaktionen.**
    private final List<Transaction> transactions = new ArrayList<>();

    // **Gson-Instanz für die Verarbeitung von JSON-Daten.**
    // Diese Instanz registriert einen Adapter für `LocalDateTime`, damit Zeitstempel korrekt gespeichert werden.
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()) // Adapter für Zeitstempel
            .setPrettyPrinting() // Sorgt für eine lesbare JSON-Darstellung
            .create();

    /**
     * Konstruktor der `TransactionManager`-Klasse.
     * Beim Erstellen einer neuen Instanz werden alle vorherigen Transaktionen aus der JSON-Datei geladen.
     */
    public TransactionManager() {
        loadTransactions();
    }

    /**
     * **Lädt alle gespeicherten Transaktionen aus der JSON-Datei.**
     * Falls die Datei nicht existiert oder leer ist, wird eine leere Transaktionsliste erstellt.
     */
    private void loadTransactions() {
        File file = new File(TRANSACTIONS_JSON);

        // **Falls die Datei nicht existiert oder leer ist, wird eine neue Datei mit einer leeren Liste erstellt.**
        if (!file.exists() || file.length() == 0) {
            System.out.println("⚠️  Keine bestehenden Transaktionen gefunden. Eine neue Datei wird erstellt.");
            saveEmptyTransactionList(); // Erstellt eine leere JSON-Datei
            return;
        }

        // **Versucht, die Transaktionsdaten aus der Datei zu laden.**
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Transaction>>() {}.getType();
            List<Transaction> loadedTransactions = gson.fromJson(reader, type); // JSON-Parsing in eine Transaktionsliste

            if (loadedTransactions != null) {
                transactions.addAll(loadedTransactions); // Geladene Transaktionen zur Liste hinzufügen
            }
        } catch (Exception e) {
            // **Falls ein Fehler auftritt (z. B. beschädigte Datei), wird sie zurückgesetzt.**
            System.err.println("⚠️  Fehler beim Laden der Transaktionsdatei! Datei wird zurückgesetzt.");
            saveEmptyTransactionList();
        }
    }

    /**
     * **Speichert eine neue Transaktion in die JSON-Datei.**
     * Eine Transaktion wird nur gespeichert, wenn der Warenkorb nicht leer ist.
     * @param cart Der Warenkorb, aus dem eine neue Transaktion erstellt wird.
     */
    public void saveTransaction(Cart cart) {
        if (cart.getTotal() > 0) { // **Stellt sicher, dass nur Warenkörbe mit Inhalt gespeichert werden.**
            Transaction transaction = new Transaction(transactions.size() + 1, cart); // Erstellt eine neue Transaktion
            transactions.add(transaction); // Fügt die Transaktion zur Liste hinzu
            writeToJsonFile(); // Speichert die aktualisierte Transaktionsliste in die JSON-Datei
            System.out.println("✅ Transaktion gespeichert! ID: " + transaction.getTransactionId());
        } else {
            System.out.println("⚠️  Leerer Warenkorb kann nicht als Transaktion gespeichert werden.");
        }
    }

    /**
     * **Schreibt alle gespeicherten Transaktionen in die JSON-Datei.**
     * Falls ein Fehler auftritt, wird eine Fehlermeldung ausgegeben.
     */
    private void writeToJsonFile() {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_JSON)) {
            gson.toJson(transactions, writer); // Konvertiert die Liste in JSON und speichert sie
        } catch (IOException e) {
            System.err.println("⚠️  Fehler beim Schreiben der Transaktionsdatei.");
        }
    }

    /**
     * **Erstellt einen Tagesabschlussbericht mit allen Transaktionen.**
     * Der Bericht enthält alle getätigten Transaktionen sowie die Gesamteinnahmen des Tages.
     */
    public void endOfDayReport() {
        System.out.println("\n📊 KASSASTURZ - TAGESÜBERSICHT 📊");

        if (transactions.isEmpty()) { // **Falls es keine Transaktionen gibt, wird eine Nachricht ausgegeben.**
            System.out.println("ℹ️  Keine Transaktionen für heute.");
            return;
        }

        double totalRevenue = 0; // Variable zur Berechnung der Gesamteinnahmen

        // **Durchläuft alle gespeicherten Transaktionen und gibt sie aus.**
        for (Transaction transaction : transactions) {
            System.out.println(transaction); // Ruft `toString()` der Transaktion auf
            totalRevenue += transaction.getTotal(); // Addiert den Betrag zur Gesamtsumme
        }

        // **Gibt die Gesamteinnahmen des Tages aus.**
        System.out.printf("\n💰 Gesamtumsatz des Tages: %.2f €\n", totalRevenue);
    }

    /**
     * **Erstellt eine leere JSON-Datei, falls keine Transaktionen existieren oder die Datei fehlerhaft ist.**
     */
    private void saveEmptyTransactionList() {
        try (FileWriter writer = new FileWriter(TRANSACTIONS_JSON)) {
            gson.toJson(new ArrayList<>(), writer); // Speichert eine leere Liste in die JSON-Datei
            System.out.println("✅ Die Transaktionsdatei wurde zurückgesetzt.");
        } catch (IOException e) {
            System.err.println("⚠️  Fehler beim Zurücksetzen der JSON-Datei.");
        }
    }
}