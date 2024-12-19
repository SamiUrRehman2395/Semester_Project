package com.example.chattingappelements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class TopBar {
    private Button searchButton;
    private Button tilesButton;

    public TopBar() {
    }

//    public HBox create() {
//        // Create the HBox (top bar)
//        HBox topBar = new HBox();
//
//        // Initialize buttons
//        searchButton = new Button("Search");
//        tilesButton = new Button("Tile");
//
//        // Create a flexible spacer that will expand to push the tile button to the right corner
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
//
//
//        /*
//        * The Region spacer = new Region(); acts as a flexible, resizable space. HBox.setHgrow
//        * (spacer, javafx.scene.layout.Priority.ALWAYS); ensures that this region takes up all available
//        *
//        * */
//
//        // Add elements to the HBox: searchButton, spacer, tilesButton
//        topBar.getChildren().addAll(searchButton, spacer, tilesButton);
//
//        // Set HBox properties
//        topBar.setPrefWidth(MainWindow.stage.getWidth());
//        topBar.setAlignment(Pos.CENTER_LEFT);
//        topBar.setPadding(new Insets(5, 10, 5, 10)); //top //right //bottom //left
//
//        return topBar;
//    }


    // Menu Bar
    MenuBar menuBar = new MenuBar();
    Menu optionsMenu = new Menu("Contacts");
    MenuItem addContact = new MenuItem("Add Contact");
    MenuItem deleteContact = new MenuItem("Delete Contact");
    MenuItem modifyContact = new MenuItem("Modify Contact");
    MenuItem userManual = new MenuItem("User Manual");


    TextField search = new TextField();



    public HBox create(){

        search.setPromptText("Search");


        optionsMenu.getItems().addAll(addContact, deleteContact, modifyContact, userManual);
        menuBar.getMenus().addAll(optionsMenu);


        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
        box.setPadding(new Insets(10, 10, 10, 10));
        box.getChildren().addAll(menuBar,search);

box.setStyle("-fx-background-color: #005b52; -fx-border-color: #004112; -fx-border-width: 2px; -fx-padding: 10;");



        return box;
    }


    public Button getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(Button searchButton) {
        this.searchButton = searchButton;
    }

    public Button getTilesButton() {
        return tilesButton;
    }

    public void setTilesButton(Button tilesButton) {
        this.tilesButton = tilesButton;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(MenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public Menu getOptionsMenu() {
        return optionsMenu;
    }

    public void setOptionsMenu(Menu optionsMenu) {
        this.optionsMenu = optionsMenu;
    }

    public MenuItem getAddContact() {
        return addContact;
    }

    public void setAddContact(MenuItem addContact) {
        this.addContact = addContact;
    }

    public MenuItem getDeleteContact() {
        return deleteContact;
    }

    public void setDeleteContact(MenuItem deleteContact) {
        this.deleteContact = deleteContact;
    }

    public MenuItem getModifyContact() {
        return modifyContact;
    }

    public void setModifyContact(MenuItem modifyContact) {
        this.modifyContact = modifyContact;
    }

    public MenuItem getUserManual() {
        return userManual;
    }

    public void setUserManual(MenuItem userManual) {
        this.userManual = userManual;
    }

    public TextField getSearch() {
        return search;
    }

    public void setSearch(TextField search) {
        this.search = search;
    }
}

