package com.sakib.musicplayer.controller;

import com.sakib.musicplayer.Main;
import com.sakib.musicplayer.service.AudioVisualizer;
import com.sakib.musicplayer.utils.Util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.util.Duration;
import java.io.File;

public class Controller {
    //UI Elements
    @FXML private Button forward_btn;
    @FXML private ImageView play_icon, music_note, circle, loop_icon;
    @FXML private Slider volume_slider, time_slider;
    @FXML private Label volume_label, song_name ,current_time ,end_time;

    //Images
    private final Image play_image = new Image(String.valueOf(Main.class.getResource("images/play.png")));
    private final Image pause_image = new Image(String.valueOf(Main.class.getResource("images/pause.png")));

    //Variables
    public File song;
    private MediaPlayer mediaPlayer;

    private boolean playing = false, pressing = false, looping = false;

    //Functions
    public void onPlayButtonClick() {
        //If music is playing, pause it and load the pause icon, otherwise do the vice versa
        if (playing) {
            play_icon.setLayoutX(408); play_icon.setLayoutY(28);
            play_icon.setImage(play_image);

            mediaPlayer.pause();
        } else {
            play_icon.setLayoutX(404); play_icon.setLayoutY(26);
            play_icon.setImage(pause_image);

            mediaPlayer.play();
            Util.setCurrentTimeText(mediaPlayer.getTotalDuration().toSeconds(), end_time);
        }

        playing = !playing;
    }

    public void onSeek(ActionEvent e) {
        double time = mediaPlayer.getCurrentTime().toSeconds();
        mediaPlayer.seek(Duration.seconds(time+(e.getSource().equals(forward_btn) ? 5 : -5)));
        time_slider.setValue((time/mediaPlayer.getTotalDuration().toSeconds())*100);
    }

    public void onLoop() {
        looping = !looping;
        loop_icon.setVisible(looping);
    }

    public void onSliderPressed() {
        pressing = true;
    }

    public void onSliderReleased() {
        double time = mediaPlayer.getTotalDuration().toSeconds()*time_slider.getValue()/100;
        mediaPlayer.seek(Duration.seconds(time));
        pressing = false;
    }

    public void playSong() {
        //0. Destroy the old media player to prevent memory leaks and overlapping audio
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.setAudioSpectrumListener(null); // Prevents background thread leak
            mediaPlayer.dispose();
        }

        //1. Song intialization
        Media media = new Media(song.toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //Wait for song to load before using it
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.setVolume(0.2);
            song_name.setText(Util.removeExtension(song.getName()));

            // 2. Delegate Audio Visualizer logic to the extracted class
            AudioVisualizer visualizer = new AudioVisualizer();
            visualizer.visualize(mediaPlayer, music_note, circle);

            // 3. Time tracking listener
            mediaPlayer.currentTimeProperty().addListener((observableValue, duration, t1) -> {
                double time = mediaPlayer.getCurrentTime().toSeconds();
                double end = mediaPlayer.getTotalDuration().toSeconds();

                if (!pressing) {
                    Util.setCurrentTimeText(time, current_time);
                    time_slider.setValue((time/end)*100);
                }
            });

            //Start the song on start
            onPlayButtonClick();
        });

        // Native End of Track Handler (Replaces manual tick checking)
        mediaPlayer.setOnEndOfMedia(() -> Platform.runLater(() -> {
            current_time.setText("0:00");
            time_slider.setValue(0);
            mediaPlayer.seek(Duration.ZERO);

            if (!looping) onPlayButtonClick(); // If looping let the song play again or
        }));
    }

    //Initialized UI listeners so that they run once even if new songs are played later
    @FXML public void initialize() {
        // Volume slider listener
        volume_slider.valueProperty().addListener((observableValue, number, t1) -> {
            int volume = (int) volume_slider.getValue();

            volume_label.setText("Volume: " + volume);
            mediaPlayer.setVolume((double) volume/100);

            Util.applySliderStyle(volume_slider, volume);
        });

        //Time slider listener
        time_slider.valueProperty().addListener((observable, number, t1) -> {
            int time = (int) time_slider.getValue();

            if (pressing) {
                Util.setCurrentTimeText(mediaPlayer.getTotalDuration().toSeconds()*time/100, current_time);
            }

            Util.applySliderStyle(time_slider, time);
        });
    }
}