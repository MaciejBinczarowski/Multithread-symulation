import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI 
{
    public GUI(Stage stage) throws IOException
    {
        GridPane root = new GridPane();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        new Board(root, 20, 20);
        stage.show();
    }
    
}
