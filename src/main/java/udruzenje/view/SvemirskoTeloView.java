package udruzenje.view;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.SvemirskoTeloUtils;
import java.util.List;

public class SvemirskoTeloView extends BorderPane {

    private TableView<SvemirskoTelo> tableView;
    private Button btnPrikazObjekata;

    private CheckBox cbPlanete;
    private CheckBox cbSateliti;
    private FilteredList<SvemirskoTelo> filteredList;

    public SvemirskoTeloView() {
        init();
    }

    private void init() {
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

        TableColumn<SvemirskoTelo, Integer> teloIdKolona = new TableColumn<>("Telo ID");
        teloIdKolona.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTeloID()));

        TableColumn<SvemirskoTelo, Boolean> istrazenKolona = new TableColumn<>("Nastanjiva");
        istrazenKolona.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().isNastanjiva()));

        tableView.getColumns().addAll(idKolona, imeKolona, tipKolona, teloIdKolona, istrazenKolona);

        btnPrikazObjekata = new Button("Prikaz objekata");
        btnPrikazObjekata.setOnAction(e -> prikaziObjekte());

        cbPlanete = new CheckBox("Planete");
        cbPlanete.setSelected(true);
        cbSateliti = new CheckBox("Sateliti");
        cbSateliti.setSelected(true);

        cbPlanete.setOnAction(e -> filter());
        cbSateliti.setOnAction(e -> filter());

        HBox hbox = new HBox(btnPrikazObjekata,cbPlanete,cbSateliti);

        VBox vbox = new VBox(tableView,hbox);



        this.setCenter(vbox);
    }


    private void prikaziObjekte() {
        System.out.println(tableView.getSelectionModel().getSelectedItem());
        SvemirskoTelo selectedTelo = tableView.getSelectionModel().getSelectedItem();
        if (selectedTelo != null) {
            ObjektiView objektiView = new ObjektiView(selectedTelo);
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
