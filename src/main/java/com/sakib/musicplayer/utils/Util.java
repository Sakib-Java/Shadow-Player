package com.sakib.musicplayer.utils;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

public class Util {
    public static String removeExtension(String name) {
        int pos = name.lastIndexOf(".");
        if (pos > 0 && pos < (name.length() - 1)) { // If '.' is not the first or last character.
            name = name.substring(0, pos);
        }
        return name;
    }

    public static void setCurrentTimeText(double time, Label obj) {
        int min = (int) Math.floor(time/60);
        int sec = (int) Math.round(time);

        if (sec%60 < 10) {
            obj.setText(min+":0"+sec%60);
        } else {
            obj.setText(min+":"+sec%60);
        }
    }

    public static void applySliderStyle(Slider slider, int v) {
        StackPane trackPane = (StackPane) slider.lookup(".track");
        String style = String.format("-fx-background-color: linear-gradient(to right, #7C5CFC %d%%, #969696 %d%%);", v, 0);
        trackPane.setStyle(style);
    }
}