package com.example.new04;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToimenpideLaidun02vanha extends Stage {

    public ToimenpideLaidun02vanha() {
        VBox root = new VBox(20, new Label("Toimenpiteen kirjaus laitumella (tuleva toiminto)"));
        setScene(new Scene(root, 300, 200));
        setTitle("Toimenpide laitumella");
    }
}
