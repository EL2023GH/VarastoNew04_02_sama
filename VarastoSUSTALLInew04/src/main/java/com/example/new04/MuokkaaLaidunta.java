package com.example.new04;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MuokkaaLaidunta extends Stage {

    private static final String TIEDOSTO = "laitumientoimenpiteet.txt";

    private TextArea toimenpiteetKentta;

    public MuokkaaLaidunta(String laitumenNimi) {

        // --- YLÄPALKIN OTSIKKO ---
        Label otsikko = new Label("MUOKKAA LAIDUN – " + laitumenNimi);
        otsikko.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        HBox ylaPalkki = new HBox(otsikko);
        ylaPalkki.setAlignment(Pos.CENTER);
        ylaPalkki.setPadding(new Insets(10));
        ylaPalkki.setStyle("-fx-background-color: #7A3DB8;"); // Violetti

        // --- LAITUMEN NIMI ---
        Label valittuLbl = new Label("Valittu laidun:");
        valittuLbl.setStyle("-fx-font-weight: bold;");

        TextField nimiKentta = new TextField(laitumenNimi);
        nimiKentta.setEditable(false);

        // 3 lorem ipsum kenttää
        TextField t1 = new TextField("12345");
        TextField t2 = new TextField("67890");
        TextField t3 = new TextField("54321");

        // --- TOIMENPITEET TEKSTIKENTTÄ ---
        Label tpLabel = new Label("Toimenpiteet tälle laitumelle:");
        tpLabel.setStyle("-fx-font-weight: bold;");

        toimenpiteetKentta = new TextArea();
        toimenpiteetKentta.setEditable(false);
        toimenpiteetKentta.setPrefRowCount(8);

        // Lataa 3 viimeisintä toimenpidettä
        lataaToimenpiteet(laitumenNimi);

        // --- MUOKKAA PERUSTIETOJA ---
        Button muokkaaPerustietoja = new Button("Muokkaa perustietoja");
        muokkaaPerustietoja.setStyle("-fx-background-color: #7A3DB8; -fx-text-fill: white; -fx-font-weight: bold;");
        muokkaaPerustietoja.setOnAction(e -> new MuokkaaPerustietoja(laitumenNimi).show());

        VBox root = new VBox(15,
                ylaPalkki,
                valittuLbl,
                nimiKentta,
                t1, t2, t3,
                tpLabel,
                toimenpiteetKentta,
                muokkaaPerustietoja
        );

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #F2E6FF;"); // Vaalea violetti tausta

        setScene(new Scene(root, 450, 550));
        setTitle("Muokkaa laidunta – " + laitumenNimi);
    }

    // --- LADATAAN 3 VIIMEISINTÄ TOIMENPIDETTÄ ---
    private void lataaToimenpiteet(String laitumenNimi) {
        toimenpiteetKentta.clear();

        File f = new File(TIEDOSTO);
        if (!f.exists()) {
            return; // Ei tiedostoa → ei näytetä mitään
        }

        List<String> rivit = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TIEDOSTO))) {
            String rivi;
            while ((rivi = br.readLine()) != null) {

                // Muoto: PVM ; LAIDUN ; TEKSTI
                String[] osat = rivi.split(";");
                if (osat.length >= 3) {
                    String laidun = osat[1].trim();

                    // Näytetään vain valitun laitumen rivit
                    if (laidun.equalsIgnoreCase(laitumenNimi)) {
                        rivit.add(rivi);
                    }
                }
            }
        } catch (Exception ignored) {}

        // Näytetään vain 3 viimeisintä
        int alku = Math.max(0, rivit.size() - 3);

        for (int i = alku; i < rivit.size(); i++) {
            toimenpiteetKentta.appendText(rivit.get(i) + "\n");
        }
    }
}
