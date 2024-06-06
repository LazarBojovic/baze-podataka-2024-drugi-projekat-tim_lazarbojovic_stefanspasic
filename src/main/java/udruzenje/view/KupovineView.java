package udruzenje.view;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import udruzenje.model.Kupovina;
import udruzenje.model.utility.KupovinaUtils;

import java.sql.Date;
import java.util.List;

public class KupovineView extends Stage {

    public KupovineView(int idKorisnika) {
        setTitle("Prethodne Kupovine korisnika sa ID- " + idKorisnika);

        List<Kupovina> kupovineList = KupovinaUtils.selectKupovineByKorisnik(idKorisnika);
        ObservableList<Kupovina> items = FXCollections.observableArrayList(kupovineList);

        TableView<Kupovina> tableView = new TableView<>(items);


        TableColumn<Kupovina, Integer> idKolona = new TableColumn<>("ID");
        idKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<Kupovina, Integer> korisnikKolona = new TableColumn<>("korisnik_id");
        korisnikKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKorisnik_id()));

        TableColumn<Kupovina, String> objekatKolona = new TableColumn<>("Objekat Ime");
        objekatKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getOjekatIme()));

        TableColumn<Kupovina, Date> polazakKolona = new TableColumn<>("polazak");
        polazakKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPolazak()));

        TableColumn<Kupovina, Date> dolazakKolona = new TableColumn<>("dolazak");
        dolazakKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDolazak()));

        TableColumn<Kupovina, String> brVozilaKolona = new TableColumn<>("brVozila");
        brVozilaKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getBrVozila()));


        tableView.getColumns().addAll(idKolona,objekatKolona,polazakKolona,dolazakKolona,brVozilaKolona,korisnikKolona);

        Scene scene = new Scene(tableView,600,400);

        setScene(scene);




    }
}
