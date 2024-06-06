package udruzenje.view;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import udruzenje.model.Kupovina;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.SvemirskoTeloUtils;
import java.util.List;

public class SvemirskoTeloView extends Stage {

    private TableView<SvemirskoTelo> tableView;
    private Button btnPrikazObjekata;
    private Button btnPrikazMisija;
    private CheckBox cbPlanete;
    private CheckBox cbSateliti;
    private FilteredList<SvemirskoTelo> filteredList;
    private int idKorisnika;


    public SvemirskoTeloView(int idKorisnika) {
        init(idKorisnika);
    }

    private void init(int idKorisnika) {
        this.setTitle("Nastanjive planete");
        this.idKorisnika = idKorisnika;
        List<SvemirskoTelo> svemirskoTeloLista = SvemirskoTeloUtils.selectSvaTela();
        ObservableList<SvemirskoTelo> items = FXCollections.observableArrayList(svemirskoTeloLista);

        filteredList = new FilteredList<>(items, p -> true);

        tableView = new TableView<>(filteredList);

        TableColumn<SvemirskoTelo, Integer> idKolona = new TableColumn<>("ID");
        idKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<SvemirskoTelo, String> imeKolona = new TableColumn<>("Ime");
        imeKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIme()));

        TableColumn<SvemirskoTelo, String> tipKolona = new TableColumn<>("Tip");
        tipKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTip()));

        TableColumn<SvemirskoTelo, String> teloIdKolona = new TableColumn<>("Telo ID");
        teloIdKolona.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTeloID()));

        TableColumn<SvemirskoTelo, Boolean> istrazenKolona = new TableColumn<>("Nastanjiva");
        istrazenKolona.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().isNastanjiva()));

        tableView.getColumns().addAll(idKolona, imeKolona, tipKolona, teloIdKolona, istrazenKolona);

        btnPrikazObjekata = new Button("Prikaz objekata");
        btnPrikazObjekata.setOnAction(e -> prikaziObjekte());
        btnPrikazMisija = new Button("Prikaz misija");
        btnPrikazMisija.setOnAction(e -> prikaziMisije());

        cbPlanete = new CheckBox("Planete");
        cbPlanete.setSelected(true);
        cbSateliti = new CheckBox("Sateliti");
        cbSateliti.setSelected(true);

        cbPlanete.setOnAction(e -> filter());
        cbSateliti.setOnAction(e -> filter());

        HBox hbox = new HBox(btnPrikazObjekata,cbPlanete,cbSateliti,btnPrikazMisija);

        VBox vbox = new VBox(tableView,hbox);


        Scene scene = new Scene(vbox,600,400);
        this.setScene(scene);
    }

    private void prikaziMisije() {
        SvemirskoTelo selectedTelo = tableView.getSelectionModel().getSelectedItem();


        if (selectedTelo != null) {
            MisijeView objektiView = new MisijeView(selectedTelo, idKorisnika);
            objektiView.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Molimo odaberite telo.");
            alert.showAndWait();
        }
    }


    private void prikaziObjekte() {
        System.out.println(tableView.getSelectionModel().getSelectedItem());
        SvemirskoTelo selectedTelo = tableView.getSelectionModel().getSelectedItem();
        if (selectedTelo != null) {
            ObjektiView objektiView = new ObjektiView(selectedTelo, idKorisnika);
            objektiView.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Upozorenje");
            alert.setHeaderText(null);
            alert.setContentText("Molimo odaberite telo.");
            alert.showAndWait();
        }
    }

    private void filter(){
        filteredList.setPredicate(telo -> {
            if (cbPlanete.isSelected() && cbSateliti.isSelected()) {
                return true;
            } else if (cbPlanete.isSelected()) {
                return telo.getTip().equalsIgnoreCase("Planeta");
            } else if (cbSateliti.isSelected()) {
                return telo.getTip().equalsIgnoreCase("Satelit");
            } else {
                return false;
            }
        });
    }
}
