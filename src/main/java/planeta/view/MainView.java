package planeta.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import planeta.model.utility.JDBCUtils;

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

        Label lbUsername = new Label("Username");
        TextField tfUsername = new TextField();
        Label lbPassword = new Label("Password");
        TextField tfPassword = new TextField("");
        Button btnLogin = new Button("Prijava");

        loginStrana.add(lbUsername,0,0);
        loginStrana.add(tfUsername,1,0);
        loginStrana.add(lbPassword,0,1);
        loginStrana.add(tfPassword,1,1);
        loginStrana.add(btnLogin, 1, 2);


        btnLogin.setOnAction(e -> {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            if (JDBCUtils.loginZahtev(username, password)) {
                prikazPlaneta();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Greška");
                alert.setHeaderText(null);
                alert.setContentText("Pogrešno korisničko ime ili lozinka.");
                alert.showAndWait();
            }
        });

        root.setCenter(loginStrana);
    }
    private void prikazPlaneta() {
        List<String> planetList = JDBCUtils.selectSvePlanete();
        ObservableList<String> items = FXCollections.observableArrayList(planetList);

        ListView<String> listView = new ListView<>(items);
        root.setCenter(listView);
    }
}

