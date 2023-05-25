import java.util.logging.Level;

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
        try
        {
            int rowsCount = Integer.parseInt(rowsField.getText());

            if (rowsCount <= 0)
            {
                throw new IllegalArgumentException("Rows number must be positive, got " + rowsCount);
            }

            int columnsCount = Integer.parseInt(columnsField.getText());

            if (columnsCount <= 0)
            {
                throw new IllegalArgumentException("Rows number must be positive, got " + columnsCount);
            }

            double propability = Double.parseDouble(propabilityField.getText());

            if (propability > 1 || propability < 0)
            {
                throw new IllegalArgumentException("Propability must be in range <0, 1>, got " + propability);
            }

            double speed = Double.parseDouble(speedField.getText());

            if (speed <= 0)
            {
                throw new IllegalArgumentException("Speed must be positive, got " + speed);
            }

            GridPane root = new GridPane();
            //Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            stage.getScene().setRoot(root);;

            new Board(root, columnsCount, rowsCount, propability, speed);
            stage.show();
        }
        catch (Exception exception)
        {
            MyLogger.logger.log(Level.INFO, exception.getMessage());
        }
    }
    
}
