package udruzenje.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PocetnaStranaVIew extends BorderPane {

    int idKorisnika;
    public PocetnaStranaVIew(int idKorisnika) {
        this.idKorisnika = idKorisnika;
        init();
    }

    public void init() {
        Button btnProsleKupovine = new Button("Pregled prethodnih Kupovina");
        Button btnNovaKupovina = new Button("Nova Kupovina");

        HBox hbox = new HBox(btnNovaKupovina,btnProsleKupovine);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        this.setCenter(hbox);
        BorderPane.setAlignment(hbox, Pos.CENTER);

        btnNovaKupovina.setOnAction(e -> {
            prikazTela();
        });

        btnProsleKupovine.setOnAction(e -> {
            prikazKupovina();
        });

    }

    private void prikazKupovina() {
        KupovineView kupovineView = new KupovineView(idKorisnika);
        kupovineView.show();
    }

    private void prikazTela() {
        SvemirskoTeloView svemirskoTeloView = new SvemirskoTeloView(idKorisnika);
        svemirskoTeloView.show();
    }
}
