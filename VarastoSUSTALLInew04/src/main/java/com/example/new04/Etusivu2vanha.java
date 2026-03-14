package com.example.new04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Etusivu2vanha extends Application {

    @Override
    public void start(Stage stage) {

        Label otsikko = new Label("SUSTALLI – Etusivu");

        // Laidun-otsikot
        TextField l1 = new TextField("LAIDUN 1");
        TextField l2 = new TextField("LAIDUN 2");
        TextField l3 = new TextField("LAIDUN 3");
        TextField l4 = new TextField("LAIDUN 4");

        l1.setEditable(false);
        l2.setEditable(false);
        l3.setEditable(false);
        l4.setEditable(false);

        Label tieto1 = new Label("Tietoa laitumesta");
        Label tieto2 = new Label("Tietoa laitumesta");
        Label tieto3 = new Label("Tietoa laitumesta");
        Label tieto4 = new Label("Tietoa laitumesta");

        // Painikkeet laidunmuokkaukseen
        Button b1 = new Button("Muokkaa Laidun 1");
        Button b2 = new Button("Muokkaa Laidun 2");
        Button b3 = new Button("Muokkaa Laidun 3");
        Button b4 = new Button("Muokkaa Laidun 4");

        b1.setOnAction(e -> new MuokkaaLaidunta("Laidun 1").show());
        b2.setOnAction(e -> new MuokkaaLaidunta("Laidun 2").show());
        b3.setOnAction(e -> new MuokkaaLaidunta("Laidun 3").show());
        b4.setOnAction(e -> new MuokkaaLaidunta("Laidun 4").show());

        // Pääikkunaan siirtyminen
        Button avaaPaaIkkuna = new Button("Avaa Pääikkuna");
        avaaPaaIkkuna.setOnAction(e -> {
            try {
                new PaaIkkuna().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(l1, 0, 0); grid.add(tieto1, 0, 1); grid.add(b1, 0, 2);
        grid.add(l2, 1, 0); grid.add(tieto2, 1, 1); grid.add(b2, 1, 2);
        grid.add(l3, 2, 0); grid.add(tieto3, 2, 1); grid.add(b3, 2, 2);
        grid.add(l4, 3, 0); grid.add(tieto4, 3, 1); grid.add(b4, 3, 2);

        VBox root = new VBox(20, otsikko, grid, avaaPaaIkkuna);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 700, 350));
        stage.setTitle("SUSTALLI – Etusivu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
