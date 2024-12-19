package com.example.chattingappelements;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class SomethingNew extends Application {

    @Override
    public void start(Stage stage) {
        ListView<String> listView = new ListView<>();
        List<String> names = new ArrayList<>();

        names.add("Ali");
        names.add("Umar");
        names.add("Moeed");
        names.add("Wajahat");

        listView.getItems().addAll(names);

        // Disable ListView selection
//        listView.setMouseTransparent(true);
//        listView.setFocusTraversable(false);

        // Use a custom cell factory
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String name, boolean empty) {
                        super.updateItem(name, empty);

                        if (empty || name == null) {
                            setGraphic(null);
                        } else {
                            Button button = new Button(name);
                            button.setAlignment(Pos.CENTER_RIGHT);

                            // Handle mouse hover effect
                            button.setOnMouseEntered(mouseEvent -> button.setOpacity(0.5));
                            button.setOnMouseExited(mouseEvent -> button.setOpacity(1));

                            HBox hBox = new HBox();
                            hBox.setAlignment(Pos.CENTER_RIGHT);
                            hBox.getChildren().add(button);

                            setGraphic(hBox);
                        }
                    }
                };
            }
        });

        // Set the background color
        listView.setStyle("-fx-background-color: lightblue;");

        VBox vbox = new VBox(listView);
        Scene scene = new Scene(vbox, 300, 250);

        stage.setScene(scene);
        stage.setTitle("Unselectable ListView with Buttons");
        stage.show();
    }

    public static void main(String[] args) {
//        Abc abc = new Abc();

        launch(args);
    }


}


