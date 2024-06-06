package udruzenje.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import udruzenje.model.Objekat;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.KupovinaUtils;
import udruzenje.model.utility.ObjekatUtils;
import java.sql.Date;
import java.util.List;

public class ObjektiView extends Stage {

    private TableView tableView;
    private DatePicker polazakDate;
    private DatePicker dolazakDate;
    private TextField brVozila;
    private Objekat selectedObjekat;

    public ObjektiView(SvemirskoTelo telo, int korisnik) {
        setTitle("Objekti na planeti: " + telo.getIme());

        polazakDate = new DatePicker();
        polazakDate.setPromptText("Polazak");
        dolazakDate = new DatePicker();
        polazakDate.setPromptText("Dolazak");

        brVozila = new TextField();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        Label label1 = new Label("Br Vozila");

        Button btnKupi = new Button("Kupi Objekat");

        List<Objekat> objektiList = ObjekatUtils.selectObjektiBySvemirskoTelo(telo.getId());
        ObservableList<Objekat> items = FXCollections.observableArrayList(objektiList);

            tableView = new TableView<>(items);
        VBox vBox = new VBox(polazakDate,dolazakDate,label1, brVozila,btnKupi);
        HBox hbox = new HBox(tableView, vBox);

        TableColumn<Objekat, Integer> idKolona = new TableColumn<>("ID");
        idKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));


        TableColumn<Objekat, String> imeKolona = new TableColumn<>("Ime");
        imeKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIme()));

        TableColumn<Objekat, String> tipKolona = new TableColumn<>("Tip");
        tipKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTip()));

        TableColumn<Objekat, Double> cenaKolona = new TableColumn<>("cena");
        cenaKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCena()));

        TableColumn<Objekat, Double> povrsinaKolona = new TableColumn<>("Povrsina");
        povrsinaKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPovrsina()));

        tableView.getColumns().addAll(idKolona, imeKolona, tipKolona, cenaKolona, povrsinaKolona);

        btnKupi.setOnAction( e -> {
            Objekat selektovaniObjekat = (Objekat) tableView.getSelectionModel().getSelectedItem();
             if (selektovaniObjekat != null && dolazakDate.getValue() !=null && polazakDate.getValue() !=null && !brVozila.getText().isEmpty()) {
                 if (KupovinaUtils.kupovinaObjekta(korisnik, selektovaniObjekat.getId(), Date.valueOf(polazakDate.getValue()), Date.valueOf(dolazakDate.getValue()), brVozila.getText())) {
                     if (ObjekatUtils.setObljekatKupljen(selektovaniObjekat.getId())) {
                         items.remove(selektovaniObjekat);
                         message("Uspesna Kupovina!", true);
                     } else
                         message("Došlo je do greške prilikom ažuriranja objekta.", false);
                 }
                 else
                     message("Došlo je do greške prilikom kupovine. Molimo pokušajte ponovo.",false);
             }
             else
                 message("Molimo unesite sve potrebne podatke", false);
        });

        root.setCenter(hbox);
        setScene(scene);
    }

    private void message(String message, boolean b) {
        Alert alert;

        if(b){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uspeh!");
        }
        else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");

        }
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
