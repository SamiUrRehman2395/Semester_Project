package com.example.chattingappelements.later;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class Test extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ChattingAppElements");
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        Image image = new Image("file:serverB.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
//        imageView.setPreserveRatio(true);
//        hbox.setAlignment(Pos.BASELINE_CENTER);
        Button contactButton = new Button("");
//        contactButton.setPrefWidth(50);
//        contactButton.setPrefHeight(50);
        contactButton.setBackground(Background.EMPTY);
        Circle clip = new Circle(25,25,25);
        imageView.setClip(clip);


//        // Ensure the clip stays updated when the size changes
//        imageView.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
//            clip.setCenterX(newValue.getWidth() / 2);
//            clip.setCenterY(newValue.getHeight() / 2);
//            clip.setRadius(Math.min(newValue.getWidth(), newValue.getHeight()) / 2);
//        });


        contactButton.setGraphic(imageView);
        hbox.getChildren().add(contactButton);

        String type = "bold";

        Label label = new Label("Arthur");
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: White");

        hbox.getChildren().add(label);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setStyle("-fx-background-color: black;");


        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().add(hbox);

        Scene scene = new Scene(vbox,800,800);
        primaryStage.setScene(scene);


        primaryStage.show();

//        showImagePopup(image);
        contactButton.setOnAction(e -> showImagePopup(image,primaryStage));

        profileHoverEffect(contactButton);
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


    //take a button as an argument
    //add function that user can view picture in large scale
    public void openProfilePicture(Button button) {

    }


    //it takes image as argument and show popup
    private void showImagePopup(Image image,Stage primaryStage) {
        Popup popup = new Popup();

        // Create the ImageView for the popup
        ImageView fullScreenImage = new ImageView(image);
        fullScreenImage.setPreserveRatio(true);
        fullScreenImage.setFitWidth(400); // Adjust the size as needed
        fullScreenImage.setFitHeight(400);

        // Close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.hide());

        VBox popupLayout = new VBox(10);
        popupLayout.getChildren().addAll(fullScreenImage, closeButton);
        popupLayout.setStyle("-fx-padding: 10; -fx-background-color: white; -fx-alignment: center;");

        popup.getContent().add(popupLayout);

        popup.show(primaryStage);
    }
}
