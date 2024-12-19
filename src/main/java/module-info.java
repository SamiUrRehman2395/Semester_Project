module com.example.chattingappelements {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    //requires org.python.jython2.standalone;


    opens com.example.chattingappelements to javafx.fxml;
    exports com.example.chattingappelements;
    exports com.example.chattingappelements.later;
    opens com.example.chattingappelements.later to javafx.fxml;
}