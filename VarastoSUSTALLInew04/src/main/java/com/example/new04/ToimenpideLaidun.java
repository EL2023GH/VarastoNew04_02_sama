package com.example.new04;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToimenpideLaidun extends Stage {

    private static final String TIEDOSTO = "laitumientoimenpiteet.txt";

    private ComboBox<String> laidunValinta;
    private TextArea toimenpideKentta;
    private TextArea viimeisetKentta;

    public ToimenpideLaidun() {

        // --- YLÄPALKIN OTSIKKO + SULJE ---
        Label otsikko = new Label("TOIMENPIDE VALITTU LAIDUN");
        otsikko.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button suljeBtn = new Button("X");
        suljeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16px;");
        suljeBtn.setOnAction(e -> close());

        HBox ylaPalkki = new HBox(otsikko, new Pane(), suljeBtn);
        HBox.setHgrow(ylaPalkki.getChildren().get(1), Priority.ALWAYS);
        ylaPalkki.setPadding(new Insets(10));
        ylaPalkki.setStyle("-fx-background-color: #3A6EA5;");
        ylaPalkki.setAlignment(Pos.CENTER_LEFT);

        // --- LAIDUNVALINTA ---
        Label valitseLaidunLbl = new Label("Valitse laidun");
        valitseLaidunLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        laidunValinta = new ComboBox<>();
        laidunValinta.getItems().addAll("Laidun 1", "Laidun 2", "Laidun 3", "Laidun 4");
        laidunValinta.setValue("Laidun 1");

        HBox laidunRivi = new HBox(10, valitseLaidunLbl, laidunValinta);
        laidunRivi.setAlignment(Pos.CENTER_LEFT);

        // --- TOIMENPIDEKENTTÄ ---
        Label toimenpideLbl = new Label("Toimenpide");
        toimenpideLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        toimenpideKentta = new TextArea();
        toimenpideKentta.setPromptText("Kirjoita tekstiä...");
        toimenpideKentta.setPrefRowCount(4);

        HBox toimenpideRivi = new HBox(10, toimenpideLbl, toimenpideKentta);
        toimenpideRivi.setAlignment(Pos.CENTER_LEFT);

        // --- TALLENNA ---
        Button tallennaBtn = new Button("Talleta");
        tallennaBtn.setStyle("-fx-background-color: #3A6EA5; -fx-text-fill: white; -fx-font-weight: bold;");
        tallennaBtn.setOnAction(e -> tallennaToimenpide());

        // --- VIIMEISET 3 TAPAHTUMAA ---
        Label viimeisetLbl = new Label("Viimeisimmät tapahtumat:");
        viimeisetLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        viimeisetKentta = new TextArea();
        viimeisetKentta.setEditable(false);
        viimeisetKentta.setPrefRowCount(6);

        lataaViimeisetTapahtumat();

        // --- TAKAISIN ETUSIVULLE ---
        Button takaisinBtn = new Button("Takaisin etusivulle");
        takaisinBtn.setStyle("-fx-background-color: #3A6EA5; -fx-text-fill: white; -fx-font-weight: bold;");
        takaisinBtn.setOnAction(e -> close());

        VBox root = new VBox(20,
                ylaPalkki,
                laidunRivi,
                toimenpideRivi,
                tallennaBtn,
                viimeisetLbl,
                viimeisetKentta,
                takaisinBtn
        );

        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #E8EEF5;");

        setScene(new Scene(root, 520, 520));
        setTitle("Toimenpide laitumella");
    }

    private void tallennaToimenpide() {
        String laidun = laidunValinta.getValue();
        String teksti = toimenpideKentta.getText().trim();
        LocalDate pvm = LocalDate.now();

        if (teksti.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Toimenpide ei voi olla tyhjä").show();
            return;
        }

        String rivi = pvm + ";" + laidun + ";" + teksti;

        try (PrintWriter pw = new PrintWriter(new FileWriter(TIEDOSTO, true))) {
            pw.println(rivi);
        } catch (IOException e) {
            e.printStackTrace();
        }

        toimenpideKentta.clear();
        lataaViimeisetTapahtumat();

        new Alert(Alert.AlertType.INFORMATION, "Tallennettu!").show();
    }

    private void lataaViimeisetTapahtumat() {
        viimeisetKentta.clear();

        List<String> rivit = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TIEDOSTO))) {
            String rivi;
            while ((rivi = br.readLine()) != null) {
                rivit.add(rivi);
            }
        } catch (Exception ignored) {}

        int alku = Math.max(0, rivit.size() - 3);

        for (int i = alku; i < rivit.size(); i++) {
            viimeisetKentta.appendText(rivit.get(i) + "\n");
        }
    }
}
