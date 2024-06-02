package udruzenje.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import udruzenje.model.utility.KorisnikUtils;

public class RegistracijaView extends Stage {

    public RegistracijaView() {
        super.setTitle("Registracija");
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        Scene scena = new Scene(root, 300, 200);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label lbIme = new Label("Ime:");
        TextField tfIme = new TextField();

        Label lbPrezime = new Label("Prezime:");
        TextField tfPrezime = new TextField();

        Label lbKorisnickoIme = new Label("Korisničko ime:");
        TextField tfKorisnickoIme = new TextField();

        Label lbLozinka = new Label("Lozinka:");
        PasswordField pfLozinka = new PasswordField();

        Button btnRegistracija = new Button("Registruj se");

        gridPane.add(lbIme, 0, 0);
        gridPane.add(tfIme, 1, 0);
        gridPane.add(lbPrezime, 0, 1);
        gridPane.add(tfPrezime, 1, 1);
        gridPane.add(lbKorisnickoIme, 0, 2);
        gridPane.add(tfKorisnickoIme, 1, 2);
        gridPane.add(lbLozinka, 0, 3);
        gridPane.add(pfLozinka, 1, 3);
        gridPane.add(btnRegistracija, 1, 4);

        root.getChildren().add(gridPane);

        btnRegistracija.setOnAction(e -> {
            String ime = tfIme.getText();
            String prezime = tfPrezime.getText();
            String korisnickoIme = tfKorisnickoIme.getText();
            String lozinka = pfLozinka.getText();

            if (!ime.isEmpty() && !prezime.isEmpty() && !korisnickoIme.isEmpty() && !lozinka.isEmpty()) {
                if (KorisnikUtils.registracijaKorisnika(ime, prezime, korisnickoIme, lozinka)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registracija");
                    alert.setHeaderText(null);
                    alert.setContentText("Uspešno ste se registrovali.");
                    alert.showAndWait();
                    close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Greška");
                    alert.setHeaderText(null);
                    alert.setContentText("Došlo je do greške prilikom registracije. Molimo pokušajte ponovo.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setHeaderText(null);
                alert.setContentText("Molimo popunite sva polja.");
                alert.showAndWait();
            }
        });

        super.setScene(scena);
    }
}
