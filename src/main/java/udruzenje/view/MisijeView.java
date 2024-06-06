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
import udruzenje.model.Misija;
import udruzenje.model.Objekat;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.KupovinaUtils;
import udruzenje.model.utility.ObjekatUtils;
import udruzenje.model.utility.SvemirskoTeloUtils;

import java.sql.Date;
import java.util.List;

public class MisijeView extends Stage {

    private TableView tableView;
    public MisijeView(SvemirskoTelo telo, int korisnik) {
        setTitle("Misije na planeti: " + telo.getIme());

        List<Misija> objektiList = SvemirskoTeloUtils.selectMisijeBySvemirskoTelo(telo.getId(), telo);
        ObservableList<Misija> items = FXCollections.observableArrayList(objektiList);

        tableView = new TableView<>(items);

        Scene scene = new Scene(tableView, 600, 400);


        TableColumn<Misija, Integer> idKolona = new TableColumn<>("ID");
        idKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<Misija, String> tipKolona = new TableColumn<>("Tip");
        tipKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTip()));

        TableColumn<Misija, String> teloKolona = new TableColumn<>("Telo Ime");
        teloKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getImeTela()));

        TableColumn<Misija, Date> pocetakKolona = new TableColumn<>("pocetak");
        pocetakKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPocetak()));

        TableColumn<Misija, Date> krajKolona = new TableColumn<>("Kraj");
        krajKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getKraj()));

        tableView.getColumns().addAll(idKolona, tipKolona, teloKolona, pocetakKolona, krajKolona);


        setScene(scene);
    }

}
