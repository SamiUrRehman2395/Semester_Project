package com.example.chattingappelements.later;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ContactView {

    private String name;
    private String phone;
    private Image image;
    private String lastContent;
    private final Image defaultImage = new Image("file:serverB.png");

    public ContactView(String name, String phone, Image image, String lastContent) {
        this.name = name;
        this.phone = phone;
        this.image = image;
        this.lastContent = lastContent;
    }

    public ContactView(String name) {
        this.name = name;
        this.image = defaultImage;
        this.lastContent = "";
        this.phone = "";
    }

    public Node crateContact() {
        HBox contactView = new HBox(10); // Add spacing between elements
        ImageView profilePicture = imageRounder(image);
        Label nameLabel = new Label(name);

        // Optional: Display phone and last content if available
        Label phoneLabel = new Label(phone.isEmpty() ? "" : " (" + phone + ")");
        Label lastContact = new Label(lastContent.isEmpty() ? "" : " - " + lastContent);

        contactView.getChildren().addAll(profilePicture, nameLabel, phoneLabel, lastContact);
        return contactView;
    }

    private ImageView imageRounder(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        return imageView;
    }



}
