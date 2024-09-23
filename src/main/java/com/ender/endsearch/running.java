package com.ender.endsearch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.io.IOException;

public class running extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(running.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setMinHeight(500);
        stage.setMinWidth(600);
        stage.setTitle("EndSearch");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}