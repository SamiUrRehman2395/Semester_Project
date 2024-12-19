package com.example.chattingappelements.later;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

public class Test1 extends Application {
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        primaryStage.setTitle("ChattingAppElements");

        // Create a VBox that will hold multiple HBoxes
        VBox mainContainer = new VBox(10); // Spacing of 10 between elements

        // Load the image
        Image image = new Image("file:serverB.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        // Create dummy HBoxes with name and message for scrolling
        for (int i = 0; i < 50; i++) { // Generate multiple elements
            HBox hbox = createContactBox(image, "Arthur " + (i + 1), "Hey, how are you?");


            // Add the HBox to the main container
            mainContainer.getChildren().add(hbox);
        }

        // Wrap the VBox with ScrollPane for scrolling
        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true); // Ensure the ScrollPane takes up the full width
        scrollPane.setPannable(true);

        Button searchButton = new Button("Search");
        root.setTop(searchButton);

        Button addContactButton = new Button("Add Contact");
        root.setBottom(addContactButton);

        searchButton.setOnAction(e -> {
            VBox vbox = new VBox(10);
            Label label = new Label("test Here");
            Label label1 = new Label("Note");
            vbox.getChildren().addAll(label, label1);
            if (root.getLeft() == null) {
                root.setLeft(vbox);
            }else {
                root.setLeft(null);
            }

        });

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a single contact box for testing purposes.
     */
    private HBox createContactBox(Image image, String name, String message) {

        HBox hbox = new HBox();
        hbox.setSpacing(10);

        // Clip the image to a circular shape
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Circle clip = new Circle(25, 25, 25);
        imageView.setClip(clip);

        Button contactButton = new Button("");
        contactButton.setBackground(Background.EMPTY);
        contactButton.setGraphic(imageView);

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        Label messageLabel = new Label(message);
        messageLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12; -fx-font-family: Candara");

        VBox textContainer = new VBox(5);
        textContainer.getChildren().addAll(nameLabel, messageLabel);
        textContainer.setAlignment(Pos.CENTER_LEFT);

        hbox.getChildren().addAll(contactButton, textContainer);

        hbox.setPadding(new Insets(10));
        hbox.setStyle(
                "-fx-background-color: black; " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-color: white; " +
                        "-fx-border-width: 1;"
        );

    addMessageContactHoverEffect(messageLabel);
    profileHoverEffect(contactButton);

//    showImagePopup(image,p);

        return hbox;
    }




public void profileHoverEffect(Button button) {
        button.setOnMouseEntered(event -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
        });
        button.setOnMouseExited(event -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });
    }

    private void showImagePopup(Image image, Stage primaryStage) {
        Popup popup = new Popup();

        ImageView fullScreenImage = new ImageView(image);
        fullScreenImage.setPreserveRatio(true);
        fullScreenImage.setFitWidth(400);
        fullScreenImage.setFitHeight(400);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.hide());

        VBox popupLayout = new VBox(10);
        popupLayout.getChildren().addAll(fullScreenImage, closeButton);
        popupLayout.setStyle("-fx-padding: 10; -fx-background-color: white; -fx-alignment: center;");

        popup.getContent().add(popupLayout);

        popup.show(primaryStage);
    }



    public void addMessageContactHoverEffect(Label label) {
        label.setOnMouseEntered(event -> {
            label.setScaleX(1.1);
            label.setScaleY(1.1);
            label.setStyle("-fx-font-style: italic; -fx-text-fill: white;");
        });
        label.setOnMouseExited(event -> {
            label.setScaleX(1.0);
            label.setScaleY(1.0);
            label.setStyle("-fx-text-fill: white; -fx-font-size: 12; -fx-font-family: Candara");


        });


    }
}
