package com.example.chattingappelements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Comparator;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String phoneNumber;
    private transient ObservableList<Message> chatHistory = FXCollections.observableArrayList(); // Fixed list initialization
    private Image contactImage;

    public static int contactIdGenerator = 1;

    public Contact() {}

    public Contact(String name, String phoneNumber, Image contactImage) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.contactImage = contactImage;
        this.id = String.format("%d", contactIdGenerator++);
    }

    public Contact(String name, String phoneNumber) {
        this(name, phoneNumber, null);
    }

    public Image getContactImage() {
        return contactImage;
    }

    public void setContactImage(Image contactImage) {
        this.contactImage = contactImage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ObservableList<Message> getChatHistory() {
        return chatHistory; // Return ObservableList directly
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Add a message to chat history
    public void addMessage(Message message) {
        chatHistory.add(message);
    }

    @Override
    public String toString() {
        return String.format("%s.   %-25s\n      %-25s", id, name, phoneNumber);
    }

    public void smsSorter() {
        chatHistory.sort(Comparator.comparing(Message::getTimestamp).reversed());
    }

    public String getContactsInfo() {
        return String.format("\tID: %-03d | %-12s | %-22s", getId(), getName(), getPhoneNumber());
    }
}
