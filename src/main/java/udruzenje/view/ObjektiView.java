package udruzenje.view;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import udruzenje.model.Objekat;
import udruzenje.model.SvemirskoTelo;
import udruzenje.model.utility.ObjekatUtils;

import java.util.List;

public class ObjektiView extends Stage {

    public ObjektiView(SvemirskoTelo telo) {
        setTitle("Objekti na planeti: " + telo.getIme());

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);

        List<Objekat> objektiList = ObjekatUtils.selectObjektiBySvemirskoTelo(telo.getId());
        ObservableList<Objekat> items = FXCollections.observableArrayList(objektiList);

        TableView<Objekat> tableView = new TableView<>(items);

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

        root.setCenter(tableView);
        setScene(scene);
    }

}
