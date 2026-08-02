package com.sakib.musicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.sakib.musicplayer.controller.Controller;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static Controller controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 852, 480);

        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/Logo.png"))));
        stage.setTitle("Shadow Player");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        controller = fxmlLoader.getController();
        controller.song = new File(getParameters().getRaw().getFirst()); // To test locally, new File(C:/Directory/Audio.mp3);
        controller.playSong();
    }
}
