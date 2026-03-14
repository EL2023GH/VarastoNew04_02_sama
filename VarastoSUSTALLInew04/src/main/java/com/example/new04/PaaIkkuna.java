package com.example.new04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PaaIkkuna extends Application {

    @Override
    public void start(Stage stage) {

        DatePicker paivamaaraValinta = new DatePicker(LocalDate.now());

        TextField jateKentta = new TextField();
        jateKentta.setPromptText("Jäte (kg)");
        TextField co2Jate = teeLukittu();

        TextField energiaKentta = new TextField();
        energiaKentta.setPromptText("Energia (kWh)");
        TextField co2Energia = teeLukittu();

        TextField vesiKentta = new TextField();
        vesiKentta.setPromptText("Vesi (l)");
        TextField co2Vesi = teeLukittu();

        TextField lantaKentta = new TextField();
        lantaKentta.setPromptText("Lanta (kg)");
        TextField co2Lanta = teeLukittu();

        TextField kuivikeKentta = new TextField();
        kuivikeKentta.setPromptText("Kuivike (kg)");
        TextField co2Kuivike = teeLukittu();

        TextField autoKentta = new TextField();
        autoKentta.setPromptText("Auton käyttö (km)");
        TextField co2Auto = teeLukittu();

        ComboBox<String> nayttoValinta = new ComboBox<>();
        nayttoValinta.getItems().addAll("Jäte", "Energia", "Vesi", "Lanta", "Kuivike", "Auto");
        nayttoValinta.setValue("Jäte");

        Button tallennaBtn = new Button("Tallenna tiedot");
        Button avaaKaavioBtn = new Button("Avaa kaavio");

        tallennaBtn.setOnAction(e -> {
            try {
                LocalDate pvm = paivamaaraValinta.getValue();

                double jate = Double.parseDouble(jateKentta.getText());
                double energia = Double.parseDouble(energiaKentta.getText());
                double vesi = Double.parseDouble(vesiKentta.getText());
                double lanta = Double.parseDouble(lantaKentta.getText());
                double kuivike = Double.parseDouble(kuivikeKentta.getText());
                double auto = Double.parseDouble(autoKentta.getText());

                co2Jate.setText(String.format("%.2f", HiilijalanjalkiLaskin.laskeJate(jate)));
                co2Energia.setText(String.format("%.2f", HiilijalanjalkiLaskin.laskeEnergia(energia)));
                co2Vesi.setText(String.format("%.2f", HiilijalanjalkiLaskin.laskeVesi(vesi)));
                co2Lanta.setText(String.format("%.2f", HiilijalanjalkiLaskin.laskeLanta(lanta)));
                co2Kuivike.setText(String.format("%.2f", HiilijalanjalkiLaskin.laskeKuivike(kuivike)));
                co2Auto.setText(String.format("%.2f", HiilijalanjalkiLaskin.laskeAuto(auto)));

                KaavioIkkuna.Tietovarasto.tallennaPaivanTiedot(
                        pvm, jate, energia, vesi, lanta, kuivike, auto
                );

                jateKentta.clear();
                energiaKentta.clear();
                vesiKentta.clear();
                lantaKentta.clear();
                kuivikeKentta.clear();
                autoKentta.clear();

            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.ERROR, "Syötä vain numeroita").show();
            }
        });

        avaaKaavioBtn.setOnAction(e -> {
            KaavioIkkuna ikkuna = new KaavioIkkuna(nayttoValinta.getValue());
            ikkuna.show();
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(
                new Label("SUSTALLI – Päivittäinen ympäristötietojen syöttö"),
                paivamaaraValinta,

                new HBox(10, jateKentta, new Label("CO₂e:"), co2Jate),
                new HBox(10, energiaKentta, new Label("CO₂e:"), co2Energia),
                new HBox(10, vesiKentta, new Label("CO₂e:"), co2Vesi),
                new HBox(10, lantaKentta, new Label("CO₂e:"), co2Lanta),
                new HBox(10, kuivikeKentta, new Label("CO₂e:"), co2Kuivike),
                new HBox(10, autoKentta, new Label("CO₂e:"), co2Auto),

                nayttoValinta,
                tallennaBtn,
                avaaKaavioBtn
        );

        stage.setScene(new Scene(root, 420, 550));
        stage.setTitle("SUSTALLI – Pääikkuna");
        stage.show();
    }

    private TextField teeLukittu() {
        TextField tf = new TextField();
        tf.setEditable(false);
        tf.setStyle("-fx-background-color: #e0e0e0;");
        return tf;
    }
}
