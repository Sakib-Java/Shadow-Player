module com.sakib.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.sakib.musicplayer to javafx.fxml;
    opens com.sakib.musicplayer.controller to javafx.fxml;
    opens com.sakib.musicplayer.images to javafx.graphics, javafx.fxml;

    exports com.sakib.musicplayer;
    exports com.sakib.musicplayer.controller;
}