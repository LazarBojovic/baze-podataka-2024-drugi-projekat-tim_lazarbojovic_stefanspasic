package udruzenje.view;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.SvemirskoTeloUtils;
import java.util.List;

public class SvemirskoTeloView extends BorderPane {

    private TableView<SvemirskoTelo> tableView;
    private Button btnPrikazObjekata;

    public SvemirskoTeloView() {
        init();
    }

    private void init() {
        List<SvemirskoTelo> svemirskoTeloLista = SvemirskoTeloUtils.selectSvaTela();
        ObservableList<SvemirskoTelo> items = FXCollections.observableArrayList(svemirskoTeloLista);

        tableView = new TableView<>(items);

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

        VBox vbox = new VBox(tableView,btnPrikazObjekata);



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
}
