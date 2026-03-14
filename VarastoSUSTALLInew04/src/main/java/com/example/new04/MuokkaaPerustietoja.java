package com.example.new04;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MuokkaaPerustietoja extends Stage {

    private static final String TIEDOSTO = "Laitumientiedot.txt";

    private TextField nimi = new TextField();
    private TextField id = new TextField();
    private TextField pintaAla = new TextField();
    private TextField omistus = new TextField();
    private TextField tyyppi = new TextField();
    private TextField alalaji = new TextField();

    private String laitumenNimi;

    public MuokkaaPerustietoja(String laitumenNimi) {
        this.laitumenNimi = laitumenNimi;

        lataaTiedot();

        Button tallenna = new Button("TALLENNA");
        tallenna.setOnAction(e -> tallennaTiedot());

        VBox root = new VBox(10,
                new Label("NIMI:"), nimi,
                new Label("ID:"), id,
                new Label("Pinta-ala:"), pintaAla,
                new Label("Oma/Vuokra:"), omistus,
                new Label("Tyyppi:"), tyyppi,
                new Label("Ala-laji:"), alalaji,
                tallenna
        );
        root.setPadding(new Insets(20));

        setScene(new Scene(root, 350, 450));
        setTitle("Muokkaa perustietoja – " + laitumenNimi);
    }

    private void lataaTiedot() {
        try (BufferedReader br = new BufferedReader(new FileReader(TIEDOSTO))) {
            String rivi;
            while ((rivi = br.readLine()) != null) {
                if (rivi.startsWith(laitumenNimi + ":")) {
                    String[] osat = rivi.split(":")[1].split(";");
                    nimi.setText(osat[0]);
                    id.setText(osat[1]);
                    pintaAla.setText(osat[2]);
                    omistus.setText(osat[3]);
                    tyyppi.setText(osat[4]);
                    alalaji.setText(osat[5]);
                }
            }
        } catch (Exception ignored) {}
    }

    private void tallennaTiedot() {
        Map<String, String> kaikki = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(TIEDOSTO))) {
            String rivi;
            while ((rivi = br.readLine()) != null) {
                String[] osat = rivi.split(":");
                kaikki.put(osat[0], osat[1]);
            }
        } catch (Exception ignored) {}

        String uusi = String.join(";",
                nimi.getText(),
                id.getText(),
                pintaAla.getText(),
                omistus.getText(),
                tyyppi.getText(),
                alalaji.getText()
        );

        kaikki.put(laitumenNimi, uusi);

        try (PrintWriter pw = new PrintWriter(new FileWriter(TIEDOSTO))) {
            for (String key : kaikki.keySet()) {
                pw.println(key + ":" + kaikki.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Alert(Alert.AlertType.INFORMATION, "Tallennettu!").show();
    }
}
