package udruzenje.view;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import udruzenje.model.utility.PlanetaUtils;
import udruzenje.model.Planeta;

import java.util.List;

public class MainView extends Stage {
    private final BorderPane root = new BorderPane();

    public MainView() {
        super.setTitle("UdruzenjeZus");
        Scene scena = new Scene(this.root,600,400);
        init();
        super.setScene(scena);
    }

    private void init(){
        GridPane loginStrana = new GridPane();
        loginStrana.setHgap(10);
        loginStrana.setVgap(10);
        loginStrana.setAlignment(Pos.CENTER);

        Label lbUsername = new Label("Username");
        TextField tfUsername = new TextField("BOJo");
        Label lbPassword = new Label("Password");
        TextField tfPassword = new TextField("1234");
        Button btnLogin = new Button("Prijava");
        Button btnRegister = new Button("Registracija");

        loginStrana.add(lbUsername,0,0);
        loginStrana.add(tfUsername,1,0);
        loginStrana.add(lbPassword,0,1);
        loginStrana.add(tfPassword,1,1);
        loginStrana.add(btnLogin, 1, 2);
        loginStrana.add(btnRegister, 0, 2);

        root.setCenter(loginStrana);
        BorderPane.setAlignment(loginStrana, Pos.CENTER);

        btnLogin.setOnAction(e -> {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            if (PlanetaUtils.loginZahtev(username, password)) {
                prikazPlaneta();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Pogrešno korisničko ime ili lozinka.");
                alert.showAndWait();
            }
        });

        btnRegister.setOnAction(e -> {
            RegistracijaView registracijaView = new RegistracijaView();
            registracijaView.initOwner(this);
            registracijaView.initModality(Modality.WINDOW_MODAL);
            registracijaView.initStyle(StageStyle.UTILITY);
            registracijaView.showAndWait();
        });


    }
    private void prikazPlaneta() {
        List<Planeta> planetList = PlanetaUtils.selectSvePlanete();
        ObservableList<Planeta> items = FXCollections.observableArrayList(planetList);

        TableView<Planeta> tableView = new TableView<>(items);

        TableColumn<Planeta, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        TableColumn<Planeta, String> imeColumn = new TableColumn<>("Ime");
        imeColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIme()));

        TableColumn<Planeta, String> tipColumn = new TableColumn<>("tip");
        tipColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTip()));

        TableColumn<Planeta, Integer> planetaIdColumn = new TableColumn<>("planetaId");
        planetaIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPlanetaId()));

        TableColumn<Planeta, Boolean> istrazenColumn = new TableColumn<>("Nastanjiva");
        istrazenColumn.setCellValueFactory(cellData -> new ReadOnlyBooleanWrapper(cellData.getValue().isNastanjiva()));

        tableView.getColumns().addAll(idColumn, imeColumn, tipColumn, planetaIdColumn, istrazenColumn);

        root.setCenter(tableView);
    }
}

