package com.example.chattingappelements;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChatView {

    private ObservableList<Message> chat = FXCollections.observableArrayList();





    public ScrollPane create(ObservableList<Message> messages) {
        VBox vbox = new VBox(10); // VBox with spacing between messages
        vbox.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true); // Make the ScrollPane fit to the VBox's width
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Hide horizontal scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Show vertical scrollbar as needed

        // Add existing messages to the VBox
        for (Message m : messages) {
            vbox.getChildren().add(createMessageLabel(m));
        }

        // Add listener to the ObservableList
        messages.addListener((ListChangeListener<Message>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Message m : change.getAddedSubList()) {
                        vbox.getChildren().add(createMessageLabel(m));
                    }
                    // Scroll to the bottom to show the latest message
                    vbox.layout(); // Ensure the layout is updated before scrolling
                    scrollPane.setVvalue(1.0); // Scroll to the bottom
                }
            }
        });

//        vbox.setStyle("-fx-background-color: #02b07c");
        return scrollPane;
    }

    private HBox createMessageLabel(Message message) {
        Label label = new Label(message.getContent());
        label.setStyle("-fx-background-color: lightgray; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");

        HBox messageContainer = new HBox();
        messageContainer.setPadding(new Insets(5));

        if (!message.getSender().equals("You")) {
            messageContainer.setAlignment(Pos.CENTER_LEFT); // Align to left
        } else {
            messageContainer.setAlignment(Pos.CENTER_RIGHT); // Align to right
            label.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");
        }

        messageContainer.getChildren().add(label);
        return messageContainer;
    }
//    public ListView<Message> create(ObservableList<Message> messages) {
//        this.chat = messages;
//
//
//

//
//
//        ListView<Message> view = new ListView<>();
//        view.setItems(messages);
//
//        view.setStyle("-fx-font-family: Candara; -fx-text-fill: white; -fx-background-color: #005b52");
//
////        Background background = new Background(new BackgroundFill(Color.RED, null, null));
////        view.setBackground(background);
//
//        // Attach listener to scroll when the observable list changes
//        messages.addListener((javafx.collections.ListChangeListener<Message>) c -> {
//            if (!messages.isEmpty()) {
//                view.scrollTo(messages.size() - 1);
//            }
//        });
//
////        vbox.getChildren().addAll(view, messageControl(view, messages));
//        return view;
//    }

























//
//    public HBox messageControl(ListView<Message> view, ObservableList<Message> messages) {
//        HBox hbox = new HBox();
//        Button sendButton = new Button("Send");
//        TextField textInput = new TextField();
//        hbox.getChildren().addAll(textInput, sendButton);
//        hbox.setSpacing(10);
//        hbox.setAlignment(Pos.BASELINE_CENTER);
//
//        sendButton.setOnAction(e -> {
//            String message = textInput.getText();
//            if (!message.isEmpty()) {
//                messages.add(new Message(message, "Nigga"));
//                textInput.clear();
//            }
//        });
//
//        textInput.setOnKeyPressed(keyEvent -> {
//            if (keyEvent.getCode() == KeyCode.ENTER) {
//                sendButton.fire();
//            }
//        });
//
//        return hbox;
//    }

}