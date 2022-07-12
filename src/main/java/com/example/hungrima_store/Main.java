package com.example.hungrima_store;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,400);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.getIcons().add(new Image("C:\\Users\\Arbitroy\\IdeaProjects\\HUNGRIMA STORE\\src\\main\\resources\\com\\example\\hungrima_store\\Images\\Lg.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}