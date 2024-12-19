package com.example.chattingappelements;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.net.Socket;

public class ContactBox {

    private String contactName;
    private String contactNumber;
    private String messageContent;

    private Image profilePicture;
    private final Image defaultProfilePicture = new Image("file:defaultProfile.png");

    private ChatView chatView = new ChatView();
    private ScrollPane messageListView = new ScrollPane();

    private Boolean onlineStatus = false;


    private ObservableList<Message> chatHistory;

    public ContactBox(String contactName, String contactNumber, String messageContent, Image profilePicture) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.messageContent = messageContent;
        this.profilePicture = profilePicture;
    }

    public ContactBox(Contact contact) {
        this.contactName = contact.getName();
        this.contactNumber = contact.getPhoneNumber();
        if (contact.getContactImage() != null) {
            this.profilePicture = contact.getContactImage();
        }else
            this.profilePicture = defaultProfilePicture;
//        if (contact.getChatHistory().getLast().getContent() != null) {
//            this.messageContent = contact.getChatHistory().getLast().getContent();
//        }else
//            this.messageContent = "";
        this.chatHistory = contact.getChatHistory();


        messageListView = chatView.create(chatHistory);


    }
    public Node create(Stage stage) {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: grey; -fx-padding: 10; -fx-background-radius: 15");
        hbox.setAlignment(Pos.CENTER_LEFT);

        // Create a circular profile picture view
        ImageView contactImage = new ImageView(profilePicture);
        contactImage.setFitWidth(30);
        contactImage.setFitHeight(30);
        Circle circleClip = new Circle(15, 15, 15);
        contactImage.setClip(circleClip);

        Button profileButton = new Button();
        profileButton.setGraphic(contactImage);
        profileButton.setBackground(Background.EMPTY);

        // Handle hover effect
        profileHoverEffect(profileButton);

        VBox labelsBox = new VBox(5);
        Label nameLabel = new Label(contactName);
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        Label messageLabel = new Label(" ");

        if (chatHistory.size() > 0) {
            messageLabel.setText(chatHistory.getLast().onlyMessageContent());
        }

        labelsBox.getChildren().addAll(nameLabel, messageLabel);

        // Create the timeDateOfLastMessage VBox
        VBox timeDateOfLastMessage = new VBox(5);
        Label dateLabel = new Label();
        Label timeLabel = new Label();
        if (chatHistory.size() > 0) {
            dateLabel.setText(chatHistory.getLast().getMessageDate());
            timeLabel.setText(chatHistory.getLast().getMessageTime());
        }
        timeDateOfLastMessage.getChildren().addAll(dateLabel,timeLabel);
        timeDateOfLastMessage.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");



        // Add a listener to chatHistory to dynamically update UI on change
        chatHistory.addListener((javafx.collections.ListChangeListener<Message>) c -> {
            if (!chatHistory.isEmpty()) {
                Message latestMessage = chatHistory.getLast();
                dateLabel.setText(latestMessage.getMessageDate());
                timeLabel.setText(latestMessage.getMessageTime());
                messageLabel.setText(latestMessage.onlyMessageContent());
            } else {
                dateLabel.setText("");
                timeLabel.setText("");
                messageLabel.setText("");
            }
        });


        // Spacer to push the timeDateOfLastMessage VBox to the right
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Add elements to the hbox
        hbox.getChildren().addAll(profileButton, labelsBox, spacer, timeDateOfLastMessage);

        profileButton.setOnAction(e -> showImagePopup(profilePicture, stage));
        hbox.setOnMouseClicked(e -> openChat(stage, chatHistory));
        highlightBox(hbox);

        return hbox;
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


    public void openChat(Stage stage, ObservableList<Message> chatHistory) {
        MainWindow.root.setCenter(messageListView);
        MainWindow.root.setTop(contactInfoTile());
        MainWindow.root.setBottom(messageControl());
    }

    public HBox messageControl() {
        HBox hbox = new HBox();
        Button sendButton = new Button("Send");
        Button sendFileButton = new Button("Send File");
        TextField textInput = new TextField();

        // String to store the selected file path
        final String[] path = {""};


        hbox.getChildren().addAll(textInput, sendButton, sendFileButton);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setPadding(new Insets(10, 0, 10, 0));

        // Handle sending messages
        sendButton.setOnAction(e -> {
            String message = textInput.getText();
            if (!message.isEmpty()) {
                chatHistory.add(new Message(message, "You"));
                MainWindow.client.sendMessage(contactNumber+":"+message);
                textInput.clear();
            }
        });

        // Handle sending a file
        sendFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(MainWindow.stage);

            if (file != null) {
                path[0] = file.getAbsolutePath();
                System.out.println("File path selected: " + path[0]);
                // You can now send this file path or perform further logic as needed.
            }
        });

        // Handle key events for quick sending with the Enter key
        textInput.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                sendButton.fire();
            }
        });

        //hbox.setStyle("-fx-background-color: #02b07c; -fx-border-color: #128C7E; -fx-border-width: 2px; -fx-padding: 10;");
hbox.setStyle("-fx-background-color: #02b07c; -fx-border-color: #005b52; -fx-border-width: 2px; -fx-padding: 10;");

        MainWindow.root.requestFocus();
        return hbox;
    }

//
//    public static void updateRecord(){
//
//    }


    private void showImagePopup(Image image, Stage primaryStage) {
        Popup popup = new Popup();

        // Setup full-screen image view in the popup
        ImageView fullScreenImage = new ImageView(image);
        fullScreenImage.setPreserveRatio(true);
        fullScreenImage.setFitWidth(400);
        fullScreenImage.setFitHeight(400);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> popup.hide());

        Button chatButton = new Button("Chat");
        chatButton.setOnAction(e -> {
            popup.hide();
            openChat(primaryStage,chatHistory);
        });

        Button conntactInfo = new Button("Contact Info");



        HBox popupButtons = new HBox();
        popupButtons.setAlignment(Pos.CENTER);

        popupButtons.setSpacing(10);
        popupButtons.getChildren().addAll( chatButton, conntactInfo,closeButton);

        VBox popupLayout = new VBox(10);
        popupLayout.getChildren().addAll(fullScreenImage, popupButtons);
        popupLayout.setStyle("-fx-background-color: #005b52; -fx-border-color: #005b52; -fx-border-width: 2px; -fx-padding: 10; -fx-border-radius: 10");
        closeButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #02b07c;");
        chatButton.setStyle("-fx-text-fill: white;-fx-font-weight: bold; -fx-background-color: #02b07c;");
        conntactInfo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;-fx-background-color: #02b07c;");
        popupButtons.setStyle("-fx-background-color: #005b52; -fx-border-color: #005b52; -fx-border-width: 2px; -fx-padding: 10;");

        popup.getContent().add(popupLayout);
        popup.show(primaryStage);

    }


    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Image getDefaultProfilePicture() {
        return defaultProfilePicture;
    }

    public ObservableList<Message> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ObservableList<Message> chatHistory) {
        this.chatHistory = chatHistory;
    }

    public void highlightBox(HBox hbox) {
        // Add a mouse enter effect
        hbox.setOnMouseEntered(event -> {
            hbox.setStyle("-fx-background-color: grey; -fx-padding: 10; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, #00c276, 10, 0.5, 0, 0);");
//            System.out.println("entered");
        });

        // Add a mouse exit effect
        hbox.setOnMouseExited(event -> {
            hbox.setStyle("-fx-background-color: grey; -fx-padding: 10; -fx-background-radius: 15; -fx-effect: None;");

        });
    }




    public HBox contactInfoTile(){
        HBox hbox = new HBox();
        ImageView contactImage = new ImageView(profilePicture);
        contactImage.setFitWidth(40);
        contactImage.setFitHeight(40);

        Circle clip = new Circle(20, 20, 20);
        contactImage.setClip(clip);


        VBox box = new VBox();

        Label contactName = new Label(getContactName());
        contactName.setStyle("-fx-font-style: bold; -fx-font-weight: bold;");
        Label status = new Label("");
        if (onlineStatus){
            status.setText("Online");
        }else
            status.setText("Online");


        box.getChildren().addAll(contactName, status);

        hbox.setSpacing(20);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.getChildren().addAll(contactImage,box);

        hbox.setStyle("-fx-background-color: #02b07c; -fx-border-color: #02b07c; -fx-border-width: 2px; -fx-padding: 10;");


        return hbox;
    }
}


