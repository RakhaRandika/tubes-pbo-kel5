module com.example.projekpbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.projekpbo to javafx.fxml;
    opens com.example.projekpbo.models to javafx.base;
    exports com.example.projekpbo;

    }