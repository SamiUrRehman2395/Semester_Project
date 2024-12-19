package com.example.chattingappelements.later;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestContact extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ChattingAppElements");

        VBox vBox = new VBox(10); // Add spacing between contacts
        vBox.setStyle("-fx-padding: 10; -fx-background-color: #f4f4f4;"); // Optional styling

        // Create a contact and add it to the VBox
        ContactView contact1 = new ContactView("Contact 1");
        vBox.getChildren().add(contact1.crateContact());

        Scene scene = new Scene(vBox, 400, 300); // Set a window size
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
