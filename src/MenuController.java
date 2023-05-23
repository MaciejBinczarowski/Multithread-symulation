import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuController 
{
    @FXML
    private TextField rowsField;

    @FXML
    private TextField columnsField;

    @FXML
    private TextField propabilityField;

    @FXML
    private TextField speedField;

    @FXML
    private void setOnButtonPressed(ActionEvent event)
    {
        int rowsCount = Integer.parseInt(rowsField.getText());
        int columnsCount = Integer.parseInt(columnsField.getText());
        double propability = Double.parseDouble(propabilityField.getText());
        double speed = Double.parseDouble(speedField.getText());

        GridPane root = new GridPane();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        new Board(root, columnsCount, rowsCount);
        stage.show();

    }
    
}
