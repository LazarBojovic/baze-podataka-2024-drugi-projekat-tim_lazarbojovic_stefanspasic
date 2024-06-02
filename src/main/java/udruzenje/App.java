package udruzenje;

import udruzenje.model.utility.JDBCUtils;
import udruzenje.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        JDBCUtils.connect();
        stage = new MainView();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        JDBCUtils.closeConnection();
    }
}
