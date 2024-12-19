package com.example.chattingappelements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestWindow1 {

    public static void main(String[] args) throws IOException {
        File file = new File("data.ser");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("File created");
        }

        // Create contacts and save them
//        saveContactsToFile(file);

//        // Functionality to search for a contact by name
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the name to search for: ");
//        String nameToSearch = scanner.nextLine();
//        searchContact(nameToSearch, file);
//        scanner.close();
//
        List<Contact> ret = new ArrayList<>();
        ret = retrieveContactsFromFile(new File("data.ser"));
        if (ret.isEmpty()) {
            System.out.println("No contacts found");
        }
        System.out.println("Number of contacts: " + ret.size());
    }

    // Method to save multiple contacts into the file
    public static void saveContactsToFile(File file) {
        try {
            List<Contact> contacts = new ArrayList<>();

            // Creating multiple contacts
            Contact contact1 = new Contact("Eman", "0311245454");
            contact1.getChatHistory().add(new Message("Hello", "you"));
            contact1.getChatHistory().add(new Message("Hi", "you"));

            Contact contact2 = new Contact("Alice", "0323456789");
            contact2.getChatHistory().add(new Message("How are you?", "you"));
            contact2.getChatHistory().add(new Message("Good morning", "you"));

            Contact contact3 = new Contact("Bob", "0339876543");
            contact3.getChatHistory().add(new Message("Good evening", "you"));
            contact3.getChatHistory().add(new Message("See you soon", "you"));

            Contact contact4 = new Contact("Charlie", "0345678901");
            contact4.getChatHistory().add(new Message("What's up?", "you"));
            contact4.getChatHistory().add(new Message("😊", "you"));

            // Adding them to a list
            contacts.add(contact1);
            contacts.add(contact2);
            contacts.add(contact3);
            contacts.add(contact4);

            // Save all contacts to the file
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(contacts);

            oos.close();
            System.out.println("Contacts saved to file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Functionality to read contacts from the file
    public static List<Contact> retrieveContactsFromFile(File file) {
        List<Contact> contacts = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            contacts = (List<Contact>) ois.readObject();

            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Functionality to search for a contact by name
    public static void searchContact(String nameToSearch, File file) {
        List<Contact> contacts = retrieveContactsFromFile(file);
        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(nameToSearch)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Contact found: " + nameToSearch);
        } else {
            System.out.println("Contact not found: " + nameToSearch);
        }
    }
}
