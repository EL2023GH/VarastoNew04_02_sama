package com.example.new04;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MuokkaaLaidunta02vanha extends Stage {

    public MuokkaaLaidunta02vanha(String laitumenNimi) {

        TextField nimiKentta = new TextField(laitumenNimi);
        nimiKentta.setEditable(false);

        // 3 lorem ipsum kenttää
        TextField t1 = new TextField("12345");
        TextField t2 = new TextField("67890");
        TextField t3 = new TextField("54321");

        Button muokkaaPerustietoja = new Button("Muokkaa perustietoja");
        muokkaaPerustietoja.setOnAction(e -> new MuokkaaPerustietoja(laitumenNimi).show());

        VBox root = new VBox(15, new Label("Valittu laidun:"), nimiKentta,
                t1, t2, t3, muokkaaPerustietoja);
        root.setPadding(new Insets(20));

        setScene(new Scene(root, 350, 300));
        setTitle("Muokkaa laidunta – " + laitumenNimi);
    }
}
