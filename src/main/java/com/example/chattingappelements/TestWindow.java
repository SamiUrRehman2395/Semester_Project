package com.example.chattingappelements;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TestWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ListView listView = new ListView();

        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        list.add("Python");
        list.add("C");
        list.add("C++");
        list.add("C#");
        list.add("C#");
        list.add("C");
        list.add("C++");

        for (String s : list) {
            Label label = new Label(s);
            listView.getItems().add(label);
            label.setOnMouseEntered(event -> {
                label.setScaleX(1.1);
                label.setScaleY(1.1);
            });

            label.setOnMouseExited(event -> {
                label.setScaleX(1.0);
                label.setScaleY(1.0);

            });
        }


        primaryStage.setScene(new Scene(listView));
        primaryStage.show();



    }



}
