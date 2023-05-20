import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI 
{
    public GUI(Stage stage) throws IOException
    {
        GridPane root = FXMLLoader.load(getClass().getResource("Board.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        new Board(root, 10, 10);
        stage.show();
    }
    
}
