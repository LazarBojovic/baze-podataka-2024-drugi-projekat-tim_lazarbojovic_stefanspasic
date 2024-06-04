package udruzenje.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import udruzenje.model.Objekat;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.ObjekatUtils;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ObjektiView extends Stage {

    private TableView tableView;
    private DatePicker polazakDate;
    private DatePicker dolazakDate;
    private TextField brVozila;
    private Objekat selectedObjekat;

    public ObjektiView(SvemirskoTelo telo) {
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

        TableColumn<Objekat, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<Objekat, String> imeColumn = new TableColumn<>("Ime");
        imeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIme()));

        TableColumn<Objekat, String> tipColumn = new TableColumn<>("Tip");
        tipColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTip()));

        TableColumn<Objekat, Double> cenaColumn = new TableColumn<>("cena");
        cenaColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getCena()));

        TableColumn<Objekat, Double> povrsinaColumn = new TableColumn<>("Povrsina");
        povrsinaColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPovrsina()));

        tableView.getColumns().addAll(idColumn, imeColumn, tipColumn, cenaColumn, povrsinaColumn);

        btnKupi.setOnAction( e -> {
            if(tableView.getSelectionModel().getSelectedItem() != null && dolazakDate.getValue() !=null && polazakDate.getValue() !=null && !brVozila.getText().isEmpty()){
                System.out.println("Uspesna Kupovina!");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Molimo unesite sve potrebne podatke");
                alert.showAndWait();
            }
        });

        root.setCenter(hbox);
        setScene(scene);
    }

}
