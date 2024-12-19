package com.example.chattingappelements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClownAi {
//
//    public static void main(String[] args) {
//        try {
//            // Python script and the prompt
//            String pythonScript = "generate_response.py";
//            String prompt = "Explain how computer works";
//
//            // Build the process to execute the Python script
//            ProcessBuilder pb = new ProcessBuilder("python3", pythonScript, prompt);
//            Process process = pb.start();
//
//            // Capture the script output
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            StringBuilder output = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line);
//            }
//
//            // Wait for the process to complete
//            process.waitFor();
//
//            // Store the output in a Java string
//            String response = output.toString();
//            System.out.println("Python function returned: " + response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public HBox createClown(Stage clownStage) {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: grey; -fx-padding: 10; -fx-background-radius: 15");
        hbox.setAlignment(Pos.CENTER_LEFT);


        Image img = new Image("file:clownAi.png");
        // Create a circular profile picture view
        ImageView contactImage = new ImageView(img);
        contactImage.setFitWidth(50);
        contactImage.setFitHeight(50);
        Circle circleClip = new Circle(25, 25, 25);
        contactImage.setClip(circleClip);
//        contactImage.setPreserveRatio(true);

        Button profileButton = new Button();
        profileButton.setGraphic(contactImage);
        profileButton.setBackground(Background.EMPTY);

        // Handle hover effect
        profileHoverEffect(profileButton);

        // Label elements
        VBox labelsBox = new VBox(5);
        Label nameLabel = new Label("Clown AI");
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        Label messageLabel = new Label("I am clown");
        messageLabel.setStyle("-fx-text-fill: white; -fx-font-weight: normal; -fx-font-size: 12px;");

        labelsBox.getChildren().addAll(nameLabel, messageLabel);

        // Add everything to the hbox
        hbox.getChildren().addAll(profileButton, labelsBox);


//       hbox.setOnKeyPressed(event->{
//            if (event.getCode() == KeyCode.BACK_SPACE){
//                MainWindow.root.setCenter(MainWindow.scrollPane);
//            }
//        });

        profileButton.setOnAction(e -> showImagePopup(img, clownStage));
        hbox.setOnMouseClicked(event->{
            chatAI();
        });
        return hbox;
    }


    public void chatAI(){
        Button sendButton = new Button("Send");
        TextField prompt = new TextField();

        HBox control = new HBox();
        control.setSpacing(10);
        control.setPadding(new Insets(10, 10, 10, 10));
        control.setAlignment(Pos.CENTER);
        control.getChildren().addAll(prompt,sendButton);


        TextArea chat = new TextArea();
        chat.setEditable(false);
        chat.setWrapText(true);
        chat.appendText("Ai  : Hi there!!! How may I assist You?\n");

        chat.setOnKeyPressed(event->{
            if (event.getCode() == KeyCode.BACK_SPACE){
                MainWindow.root.setCenter(MainWindow.scrollPane);
            }
        });

        MainWindow.root.setCenter(chat);
        MainWindow.root.setBottom(control);

        sendButton.setOnAction(e->{
            chat.appendText("You : "+prompt.getText()+ "\n");
            System.out.println("prompt: "+prompt.getText());
            chat.appendText(req( "Ai : " +prompt.getText())+"\n");
            prompt.clear();


        });



        MainWindow.root.setOnKeyPressed(event->{
            if (event.getCode() == KeyCode.ENTER){
                sendButton.fire();
            }
        });
    }



    public String req(String prompt){

        System.out.println(" req prompt: "+prompt);
        try {
            System.out.println("in try");
            // Python script and the prompt
            String pythonScript = "generate_response.py";

            // Build the process to execute the Python script
            ProcessBuilder pb = new ProcessBuilder("python3", pythonScript, prompt);
            Process process = pb.start();

            // Capture the script output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            System.out.println("wait start");

            // Wait for the process to complete
            process.waitFor();

            System.out.println("wait over");

            // Store the output in a Java string

            String response = output.toString();
            System.out.println("Python function returned: " + response);
            return response;
//            System.out.println("Python function returned: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Ai : Something went wrong Indeed i am a clown" ;
    }


    private void profileHoverEffect(Button button) {
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

        // Setup full-screen image view in the popup
        ImageView fullScreenImage = new ImageView(image);
        fullScreenImage.setPreserveRatio(true);
        fullScreenImage.setFitWidth(400);
        fullScreenImage.setFitHeight(400);

        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> popup.hide());


        HBox popupButtons = new HBox();
        popupButtons.setAlignment(Pos.CENTER);

        popupButtons.getChildren().add(closeButton);

        VBox popupLayout = new VBox(10);
        popupLayout.getChildren().addAll(fullScreenImage, popupButtons);
        popupLayout.setStyle("-fx-padding: 10; -fx-background-color: white; -fx-alignment: center;");

        popup.getContent().add(popupLayout);
        popup.show(primaryStage);
    }



}
