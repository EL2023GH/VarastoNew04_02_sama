package com.example.new04;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sadepaivat extends Stage {

    public Sadepaivat() {
        VBox root = new VBox(20, new Label("Sadepäivien kirjaus (tuleva toiminto)"));
        setScene(new Scene(root, 300, 200));
        setTitle("Sadepäivät");
    }
}
