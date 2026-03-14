package com.example.new04;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Map;

public class KaavioIkkuna extends Stage {

    private String valittuTyyppi;

    public KaavioIkkuna(String tyyppi) {
        this.valittuTyyppi = tyyppi;
        piirraKaavio();
    }

    private void piirraKaavio() {

        CategoryAxis xAkseli = new CategoryAxis();
        NumberAxis yAkseli = new NumberAxis();
        BarChart<String, Number> kaavio = new BarChart<>(xAkseli, yAkseli);

        XYChart.Series<String, Number> sarja = new XYChart.Series<>();
        sarja.setName(valittuTyyppi);

        for (Map.Entry<LocalDate, PaivanTiedot> entry : Tietovarasto.haeKaikkiTiedot().entrySet()) {
            LocalDate pvm = entry.getKey();
            PaivanTiedot t = entry.getValue();

            double arvo = 0;

            if (valittuTyyppi.equals("Jäte")) arvo = t.jate;
            if (valittuTyyppi.equals("Energia")) arvo = t.energia;
            if (valittuTyyppi.equals("Vesi")) arvo = t.vesi;
            if (valittuTyyppi.equals("Lanta")) arvo = t.lanta;
            if (valittuTyyppi.equals("Kuivike")) arvo = t.kuivike;
            if (valittuTyyppi.equals("Auto")) arvo = t.auto;

            sarja.getData().add(new XYChart.Data<>(pvm.toString(), arvo));
        }

        kaavio.getData().add(sarja);

        Scene scene = new Scene(kaavio, 600, 400);
        setScene(scene);
        setTitle("SUSTALLI – Kaavio: " + valittuTyyppi);
    }

    public static class Tietovarasto {

        private static Map<LocalDate, PaivanTiedot> tiedot = new java.util.HashMap<>();

        public static void tallennaPaivanTiedot(LocalDate pvm,
                                                double jate, double energia, double vesi,
                                                double lanta, double kuivike, double auto) {
            tiedot.put(pvm, new PaivanTiedot(jate, energia, vesi, lanta, kuivike, auto));
        }

        public static Map<LocalDate, PaivanTiedot> haeKaikkiTiedot() {
            return tiedot;
        }
    }
}
