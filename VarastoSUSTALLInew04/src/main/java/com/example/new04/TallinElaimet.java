package com.example.new04;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TallinElaimet extends Stage {

    public TallinElaimet() {
        VBox root = new VBox(20, new Label("Tallin eläinten määrän muokkaus (tuleva toiminto)"));
        setScene(new Scene(root, 300, 200));
        setTitle("Tallin eläimet");
    }
}

