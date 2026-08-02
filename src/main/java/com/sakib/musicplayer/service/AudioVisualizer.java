package com.sakib.musicplayer.service;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

public class AudioVisualizer {
    private double smoothBass = 0.0;

    // Visualizer Configuration
    private static final int BAND_NUM = 2; // Number of bands to use for visualizer
    private static final double SMOOTHING = 0.2; // How much to react with bass
    private static final double STRENGTH = 1.2;
    private static final double ROTATION = 2.0;

    public void visualize(MediaPlayer mediaPlayer, ImageView note, ImageView circle) {
        mediaPlayer.setAudioSpectrumNumBands(128); // Frequency Range divided by Spectrum Bands
        mediaPlayer.setAudioSpectrumInterval(0.04); // Bass checked 25 times every 1 second, 1/25 = 0.04
        mediaPlayer.setAudioSpectrumThreshold(-60); // Loudness level in decibels

        mediaPlayer.setAudioSpectrumListener((timestamp, duration, magnitudes, phases) -> {
            double bass = 0;

            for (int i = 0; i < BAND_NUM; i++) {
                bass += magnitudes[i];
            }

            bass /= BAND_NUM;

            // Convert from dB (-60-0) to normalized value (0-1)
            double bassLevel = (bass + 60) / 60.0;
            smoothBass += (bassLevel - smoothBass) * SMOOTHING;
            double scale = 1 + smoothBass * STRENGTH;

            Platform.runLater(() -> {
                note.setScaleX(scale);
                note.setScaleY(scale);
                circle.setRotate((circle.getRotate() + ROTATION) % 360); //Modulus set to reset rotation to 0
            });
        });
    }
}