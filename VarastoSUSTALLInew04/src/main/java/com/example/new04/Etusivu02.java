package com.example.new04;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Etusivu02 extends Application {

    @Override
    public void start(Stage stage) {

        // OTSIKKO KESKELLE
        Label otsikko = new Label("ETUSIVU");
        otsikko.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        HBox otsikkoBox = new HBox(otsikko);
        otsikkoBox.setAlignment(Pos.CENTER);

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

        // Uudet painikkeet
        Button btnElaimet = new Button("Muokkaa eläinten määrää tallilla");
        Button btnSiirra = new Button("Siirrä eläimiä laidunten välillä");
        Button btnRaportti = new Button("Luo raportti");
        Button btnLisaaLaidun = new Button("Lisää uusi laidun");
        Button btnToimenpide = new Button("Kirjaa toimenpide laitumella");
        Button btnSade = new Button("Sade nappi");

        btnElaimet.setOnAction(e -> new TallinElaimet().show());
        btnSiirra.setOnAction(e -> new SiirraElaimia().show());
        btnRaportti.setOnAction(e -> new LuoRaportti().show());
        btnLisaaLaidun.setOnAction(e -> new LisaaLaidun().show());
        btnToimenpide.setOnAction(e -> new ToimenpideLaidun().show());
        btnSade.setOnAction(e -> new Sadepaivat().show());

        // Pääikkunaan siirtyminen
        Button avaaPaaIkkuna = new Button("Avaa Pääikkuna");
        avaaPaaIkkuna.setOnAction(e -> {
            try {
                new PaaIkkuna().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Laidunruudukko
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(l1, 0, 0); grid.add(tieto1, 0, 1); grid.add(b1, 0, 2);
        grid.add(l2, 1, 0); grid.add(tieto2, 1, 1); grid.add(b2, 1, 2);
        grid.add(l3, 2, 0); grid.add(tieto3, 2, 1); grid.add(b3, 2, 2);
        grid.add(l4, 3, 0); grid.add(tieto4, 3, 1); grid.add(b4, 3, 2);

        // Uudet painikkeet alle
        VBox uudetPainikkeet = new VBox(10,
                btnElaimet, btnSiirra, btnRaportti,
                btnLisaaLaidun, btnToimenpide, btnSade,
                avaaPaaIkkuna
        );

        VBox root = new VBox(20, otsikkoBox, grid, uudetPainikkeet);
        root.setPadding(new Insets(20));

        root.setStyle(
                "-fx-background-image: url('taustakuva01.jpg');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center center;"
        );



        stage.setScene(new Scene(root, 750, 600));
        stage.setTitle("SUSTALLI – Etusivu");
        stage.show();
    }
}
