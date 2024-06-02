
import planeta.model.utility.JDBCUtils;
import planeta.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        JDBCUtils.connect();
        stage = new MainView();
        stage.show();
    }
}
