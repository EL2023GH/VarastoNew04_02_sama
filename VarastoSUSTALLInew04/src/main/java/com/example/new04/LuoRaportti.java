package com.example.new04;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LuoRaportti extends Stage {

    public LuoRaportti() {
        VBox root = new VBox(20, new Label("Raportin luonti (tuleva toiminto)"));
        setScene(new Scene(root, 300, 200));
        setTitle("Luo raportti");
    }
}
