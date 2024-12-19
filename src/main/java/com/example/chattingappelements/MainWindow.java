package com.example.chattingappelements;

import java.io.*;
import java.net.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import org.python.antlr.ast.Str;

import java.util.ArrayList;

public class MainWindow extends Application {

    public static Scene scene;
    public static Stage stage;
    public static BorderPane root = new BorderPane();
    public static VBox contactsBox = new VBox();
    static ScrollPane scrollPane = new ScrollPane();
//    public static List<Contact> contacts = retrieveContactsFromFile();
    public static ArrayList<Contact> contacts = new ArrayList<>();
    HBox menu = new HBox();
    public static ArrayList<ContactBox> boxes = new ArrayList<>();



    private Boolean isVerified = false;




    //stuff that will be used for connecting with server
    public static String userPhoneNumber;
    private String userName;
    public static Client client;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        verify(primaryStage);
        stage = primaryStage;
        TopBar topBar = new TopBar();
        HBox menu = topBar.create();
        root.setTop(menu);

        //Ai Integration
        ClownAi ai = new ClownAi();
        contactsBox.getChildren().add(ai.createClown(primaryStage));


        contactsBox.setSpacing(5);
        contactsBox.setPadding(new Insets(10, 10, 10, 10));

        scrollPane.setContent(contactsBox);
        scrollPane.setFitToWidth(true);



        root.setCenter(scrollPane);
        scene = new Scene(root, 400, 600);
        scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main");





        root.requestFocus();
        client = new Client();



        //funtion calls
        contactsInitializtion();


//Action events here
        topBar.getAddContact().setOnAction(actionEvent -> addContact());

        //adding event that when backspace is pressed the border pane's middle change contacts box
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE){
                root.setCenter(scrollPane);
                root.setTop(menu);
                root.setBottom(null);
            }
        });


//Styles here
        contactsBox.setStyle("-fx-background-color: rgb(30,31,34);");
        contactsBox.setStyle("-fx-background-color: #005b52; -fx-border-color: #005b52; -fx-border-width: 2px; -fx-padding: 10;");
        scrollPane.setStyle("-fx-background-color: #005b52; -fx-border-color: #005b52; -fx-border-width: 2px; -fx-padding: 10;");

    }



    public void addContact() {
        Stage contactAddStage = new Stage();
        contactAddStage.setTitle("Add Contact");

        // Create UI elements
        Label nameLabel = new Label("Contact Name:");
        TextField nameField = new TextField();

        Label numberLabel = new Label("Contact Number:");
        TextField numberField = new TextField();

        Label pictureLabel = new Label("Profile Picture:");
        Button choosePictureButton = new Button("Choose Picture");
        Label picturePathLabel = new Label();

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(nameLabel, nameField, numberLabel, numberField, pictureLabel, choosePictureButton, picturePathLabel, saveButton, cancelButton);

        Scene scene = new Scene(layout, 300, 300);
        contactAddStage.setScene(scene);

        // File chooser logic
        final FileChooser fileChooser = new FileChooser();
        choosePictureButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(contactAddStage);
            if (file != null) {
                picturePathLabel.setText(file.getAbsolutePath());
            }
        });

        // Save button logic
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String number = numberField.getText();
            String picturePath = picturePathLabel.getText();
            ContactBox box = new ContactBox(new Contact(name,number));
        box.setProfilePicture(new Image(picturePathLabel.getText()));
        boxes.add(box);
        contactsBox.getChildren().add(box.create(stage));
            if (name.isEmpty() || number.isEmpty() || picturePath.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in all fields and select a picture.", ButtonType.OK);
                alert.showAndWait();
            } else {
                // Store the details
                System.out.println("Name: " + name);
                System.out.println("Number: " + number);
                System.out.println("Picture Path: " + picturePath);



                // Close the stage
                contactAddStage.close();
            }
        });

        // Cancel button logic
        cancelButton.setOnAction(e -> contactAddStage.close());




        // Show the stage
        contactAddStage.show();
    }

    public void contactsInitializtion(){
        contacts.add(new Contact("Eman", "03193544291"));
        contacts.get(0).setContactImage(new Image("file:eman.png"));
        contacts.add(new Contact("John Doe", "123123"));
        contacts.add(new Contact("gen Asim", "456456"));
        contacts.add(new Contact("Gen Bajwa", "1213113"));
        contacts.add(new Contact("Lumber 1", "23232333"));
        contacts.add(new Contact("Lumber 2", "23232333"));
        contacts.add(new Contact("Lumber 3", "23232333"));
        contacts.add(new Contact("Lumber 4", "23232333"));
        contacts.add(new Contact("Lumber 5", "23232333"));
        contacts.add(new Contact("Lumber 6", "23232333"));

        contacts.get(0).addMessage(new Message("hello","You"));
        contacts.get(0).addMessage(new Message("wassup","Eman"));
        contacts.get(0).addMessage(new Message("nigga","Eman"));
        contacts.get(0).addMessage(new Message("how are you","Eman"));
        contacts.get(0).addMessage(new Message("no","Eman"));
        contacts.get(0).addMessage(new Message("yes","Eman"));

        contacts.get(1).addMessage(new Message("Hello Qamar","You"));
        contacts.get(1).addMessage(new Message("Bolo Bhai","Qamar"));
        System.out.println(contacts.get(0).getChatHistory().size());
        setContactsBox(stage);
    }

    public void setContactsBox(Stage primaryStage){
        for (int i = 0; i < contacts.size(); i++) {
            ContactBox box = new ContactBox(contacts.get(i));
            boxes.add(box);
            contactsBox.getChildren().add(box.create(primaryStage));
        }
    }



    //checks whether the user already exists or not , users Phone number will identify the
    // user, user will give phone number to communicate , the return user will suggests whether
    //    the user exists or not
    public void verify(Stage stage) throws IOException {
        File file = new File("exists.txt");
        if (!file.exists()){
            file.createNewFile();
            System.out.println("new user detected");
            register();
            return;
        }else {

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                String data[] = line.split(",");
                System.out.println(data[0] + " |||" + data[1]);
                userPhoneNumber = data[1];
                userName = data[0];

            }catch (IOException e){
                e.printStackTrace();
            }

            stage.show();
        }

    }


    //    incase there is new user of application.
    public Boolean register() throws IOException {
//        stage.hide();
        Stage registerStage = new Stage();
        VBox vbox = new VBox();
        Scene registerScene = new Scene(vbox, 400, 400);
        registerStage.setScene(registerScene);

        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);


        Label name = new Label("Name : ");
        TextField nameField = new TextField();
        name.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");


        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(name,nameField);
        nameBox.setSpacing(5);
        nameBox.setAlignment(Pos.CENTER);
//        nameBox.setStyle("-fx-text-fill: #ffff");

        Label phoneNumber = new Label("Phone :");
        TextField phoneNumberField = new TextField();
        phoneNumber.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");


        HBox phoneBox = new HBox();
        phoneBox.getChildren().addAll(phoneNumber,phoneNumberField);
        phoneBox.setSpacing(5);
        phoneBox.setAlignment(Pos.CENTER);


        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-text-fill: blue");


        HBox cancelregister = new HBox();
        cancelregister.setSpacing(5);
        cancelregister.setPadding(new Insets(10, 10, 10, 10));
        cancelregister.setAlignment(Pos.CENTER);
        cancelregister.getChildren().addAll(cancelButton,registerButton);


        vbox.setStyle("-fx-background-color: black; -fx-font-family: Candara; -fx-text-fill: white;");
        vbox.getChildren().addAll(nameBox,phoneBox,cancelregister);
        registerStage.show();
        userPhoneNumber = phoneNumberField.getText();



        registerButton.setOnAction(e -> {

            File file = new File("exists.txt");

            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file,false))){
                writer.write(nameField.getText()+","+phoneNumberField.getText());
            }catch (IOException p){
                p.printStackTrace();
            }

            nameField.clear();
            phoneNumberField.clear();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("User Registered Successfully");
            registerStage.close();
            stage.show();
            return;
        });



        cancelButton.setOnAction(e -> {
            nameField.clear();
            phoneNumberField.clear();
        });


        return false;

    }

    public static void main(String[] args) {
        launch(args);
    }

}


class Client implements Runnable{

    private Socket socket; // Changed to Socket for client-side connection
    public static PrintWriter out;
    public static BufferedReader in;

    private String serverAddress;
    private int port = 12345;

    private Boolean userNamePush = false;


    public Client() {
//        System.out.println("Client created");
//        System.out.println("Phone"+ MainWindow.userPhoneNumber);
//        this.port = port;
        chatOnline();
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }

    }


    public void chatOnline() {
        serverAddress = "192.168.197.26";
        try {
            socket = new Socket(serverAddress, port); // Establish connection to server
            out = new PrintWriter(socket.getOutputStream(), true); // Initialize PrintWriter here
            out.println(MainWindow.userPhoneNumber);

            new Thread(this).start(); // Start the client listener thread
//            System.out.println("Successfully connected to Server" );
        } catch (IOException e) {
//            JOptionPane.showMessageDialog(this, "Could not connect to " + contact.getName() + " at " + serverAddress + ":" + port,
//                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
//
//            if ((in.toString().contains(":"))) {
//                iLoveFilter(in.toString());
//                System.out.println("gya");
//            }else {
//                System.out.println("mhi gya");
//            }
//
            String inputLine;
//            if (userNamePush){
//                inputLine = MainWindow.userPhoneNumber;
//                userNamePush = false;
//            }

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
//                test();
                if (inputLine.contains(":")){
                    iLoveFilter(inputLine);
//                    System.out.println("gya");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void test(){
        for (ContactBox b: MainWindow.boxes){
            System.out.println(b.getContactNumber());
        }
    }


    public void iLoveFilter(String content){


        if (!content.isEmpty()) {

            System.out.println(content);
            int indexOfColon = content.indexOf(':');
            if (indexOfColon == -1) {
                throw new IllegalArgumentException("Invalid input format. Expected ':' delimiter.");
            }

            String numberPart = content.substring(0, indexOfColon).trim();
            String messagePart = content.substring(indexOfColon + 1).trim();

            System.out.println(numberPart);
            System.out.println(messagePart);

            ContactBox box = findContactHbox(numberPart.trim(),messagePart);


        }


    }








    public ContactBox findContactHbox(String contactNumber, String message) {
        for (ContactBox box : MainWindow.boxes) {
            if (box.getContactNumber().equals(contactNumber)) {
                System.out.println(box.getContactNumber());
//                System.out.println("true hogya");

                // Use Platform.runLater to safely update UI components or observable lists
                Platform.runLater(() -> {
                    box.getChatHistory().add(new Message(message, contactNameFinder(contactNumber)));
                });

                return box;
            }
        }

        return null;
    }




    public String contactNameFinder(String number){
        String name;
        for (Contact c: MainWindow.contacts){
            if (c.getPhoneNumber().equals(number)){
                name = c.getName();
                return name;
            }
        }
        return "Error";
    }

}
















