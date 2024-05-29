module com.example.projekpbo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekpbo to javafx.fxml;
    exports com.example.projekpbo;
}