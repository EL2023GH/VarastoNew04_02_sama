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

public class LisaaLaidun extends Stage {

    private static final String TIEDOSTO = "laitumet.txt";

    private VBox sisältöAlue;
    private ComboBox<String> poistoValikko;

    public LisaaLaidun() {

        // --- YLÄPALKIN OTSIKKO + SULJE ---
        Label otsikko = new Label("LAITUMIEN HALLINTA");
        otsikko.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button suljeBtn = new Button("X");
        suljeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16px;");
        suljeBtn.setOnAction(e -> close());

        HBox ylaPalkki = new HBox(otsikko, new Pane(), suljeBtn);
        HBox.setHgrow(ylaPalkki.getChildren().get(1), Priority.ALWAYS);

        ylaPalkki.setPadding(new Insets(10));
        ylaPalkki.setStyle("-fx-background-color: #3A6EA5;");
        ylaPalkki.setAlignment(Pos.CENTER_LEFT);

        // --- VALITSE LISÄÄ VAI POISTA ---
        Button btnLisaa = new Button("Lisää uusi laidun");
        Button btnPoista = new Button("Poista laidun");

        String btnStyle = "-fx-background-color: #3A6EA5; -fx-text-fill: white; -fx-font-weight: bold;";
        btnLisaa.setStyle(btnStyle);
        btnPoista.setStyle(btnStyle);

        btnLisaa.setOnAction(e -> näytäLisimisLomake());
        btnPoista.setOnAction(e -> näytäPoistoNäkymä());

        HBox valintaRivi = new HBox(20, btnLisaa, btnPoista);
        valintaRivi.setAlignment(Pos.CENTER);

        sisältöAlue = new VBox(20);
        sisältöAlue.setPadding(new Insets(20));

        VBox root = new VBox(20, ylaPalkki, valintaRivi, sisältöAlue);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #E8EEF5;");

        setScene(new Scene(root, 520, 500));
        setTitle("Lisää tai poista laidun");
    }

    // ----------------------------------------------
    //  L A I T U M E N   L I S Ä Y S
    // ----------------------------------------------

    private void näytäLisimisLomake() {
        sisältöAlue.getChildren().clear();

        int seuraavaNro = laskeSeuraavaNumero();

        Label nroLbl = new Label("Laitumen numero: " + seuraavaNro);
        nroLbl.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        // 5 kenttää
        TextField k1 = new TextField();
        TextField k2 = new TextField();
        TextField k3 = new TextField();
        TextField k4 = new TextField();
        TextField k5 = new TextField();

        k1.setPromptText("Tieto 1");
        k2.setPromptText("Tieto 2");
        k3.setPromptText("Tieto 3");
        k4.setPromptText("Tieto 4");
        k5.setPromptText("Tieto 5");

        Button tallenna = new Button("Tallenna laitun");
        tallenna.setStyle("-fx-background-color: #3A6EA5; -fx-text-fill: white; -fx-font-weight: bold;");
        tallenna.setOnAction(e -> {
            tallennaUusiLaitum(seuraavaNro,
                    k1.getText(), k2.getText(), k3.getText(), k4.getText(), k5.getText());
        });

        sisältöAlue.getChildren().addAll(
                nroLbl, k1, k2, k3, k4, k5, tallenna
        );
    }

    private int laskeSeuraavaNumero() {
        if (!new File(TIEDOSTO).exists()) return 1;

        int suurin = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(TIEDOSTO))) {
            String r;
            while ((r = br.readLine()) != null) {
                String[] osat = r.split(";");
                int nro = Integer.parseInt(osat[0]);
                if (nro > suurin) suurin = nro;
            }
        } catch (IOException ignored) {}

        return suurin + 1;
    }

    private void tallennaUusiLaitum(int nro, String... tiedot) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TIEDOSTO, true))) {
            pw.print(nro);
            for (String tieto : tiedot) {
                pw.print(";" + (tieto.trim().isEmpty() ? "" : tieto.trim()));
            }
            pw.println();
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, "Tallennus epäonnistui!").show();
            return;
        }

        new Alert(Alert.AlertType.INFORMATION, "Uusi laidun tallennettu!").show();
    }

    // ----------------------------------------------
    //  L A I T U M E N   P O I S T O
    // ----------------------------------------------

    private void näytäPoistoNäkymä() {
        sisältöAlue.getChildren().clear();

        Label valitseLbl = new Label("Valitse poistettava laidun:");
        valitseLbl.setStyle("-fx-font-weight: bold;");

        poistoValikko = new ComboBox<>();
        lataaLaitumetPoistovalikkoon();

        Button poistaBtn = new Button("Poista");
        poistaBtn.setStyle("-fx-background-color: #AA0000; -fx-text-fill: white; -fx-font-weight: bold;");
        poistaBtn.setOnAction(e -> vahvistaPoisto());

        sisältöAlue.getChildren().addAll(valitseLbl, poistoValikko, poistaBtn);
    }

    private void lataaLaitumetPoistovalikkoon() {
        poistoValikko.getItems().clear();

        File tied = new File(TIEDOSTO);

        if (!tied.exists()) {
            poistoValikko.setPromptText("EI LAITUMIA");
            poistoValikko.setDisable(true);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(tied))) {
            String r;
            while ((r = br.readLine()) != null) {
                poistoValikko.getItems().add(r);
            }
        } catch (IOException ignored) {}

        if (poistoValikko.getItems().isEmpty()) {
            poistoValikko.setPromptText("EI LAITUMIA");
            poistoValikko.setDisable(true);
        }
    }

    private void vahvistaPoisto() {
        String rivi = poistoValikko.getValue();
        if (rivi == null) return;

        Alert kysymys = new Alert(Alert.AlertType.CONFIRMATION,
                "Poistetaanko laidun: \n" + rivi,
                ButtonType.YES, ButtonType.NO);

        kysymys.showAndWait().ifPresent(v -> {
            if (v == ButtonType.YES) {
                poistaLaitum(rivi);
            }
        });
    }

    private void poistaLaitum(String poistettava) {
        List<String> kaikki = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TIEDOSTO))) {
            String r;
            while ((r = br.readLine()) != null) {
                if (!r.equals(poistettava))
                    kaikki.add(r);
            }
        } catch (IOException ignored) {}

        try (PrintWriter pw = new PrintWriter(new FileWriter(TIEDOSTO))) {
            for (String r : kaikki) pw.println(r);
        } catch (IOException ignored) {}

        new Alert(Alert.AlertType.INFORMATION, "Laidun poistettu!").show();

        näytäPoistoNäkymä();
    }
}

