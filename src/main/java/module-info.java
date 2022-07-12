module com.example.hungrima_store {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires MaterialFX;
    requires kernel;
    requires layout;
    opens com.example.hungrima_store to javafx.fxml;
    exports com.example.hungrima_store;
}