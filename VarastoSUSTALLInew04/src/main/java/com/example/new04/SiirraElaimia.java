package com.example.new04;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SiirraElaimia extends Stage {

    public SiirraElaimia() {
        VBox root = new VBox(20, new Label("Eläinten siirto laidunten välillä (tuleva toiminto)"));
        setScene(new Scene(root, 300, 200));
        setTitle("Siirrä eläimiä");
    }
}
