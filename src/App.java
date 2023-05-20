import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main(String[] args) throws Exception 
    {
        MyLogger.loggerConfig();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        new GUI(stage);
    }
}
