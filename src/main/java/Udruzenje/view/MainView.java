package Udruzenje.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;

public class MainView extends Stage {
    private final BorderPane root = new BorderPane();

    public MainView() {
        super.setTitle("UdruzenjeZus");

        super.setScene(new Scene(this.root));
    }
}
