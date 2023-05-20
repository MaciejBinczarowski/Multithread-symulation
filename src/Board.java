import java.util.logging.Level;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board 
{
    // private Rectangle square = new Rectangle(25, 25);
    private int columnsCount;
    private int rowsCount;
    private GridPane gridPane;

    public Board(GridPane pane, int n, int m)
    {
        this.columnsCount = n;
        this.rowsCount = m;
        this.gridPane = pane;
        setGrid();
    }

    private void setGrid()
    {
        for (int i = 0; i < columnsCount; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / columnsCount);
            gridPane.getColumnConstraints().add(colConst);
        }

        for (int i = 0; i < rowsCount; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rowsCount);
            gridPane.getRowConstraints().add(rowConst);         
        }

        for (int column = 0; column < columnsCount; column++)
        {
            for (int row = 0; row < rowsCount; row++)
            {
                MyLogger.logger.log(Level.INFO, "Filing column " + column);
                Cell cell = new Cell(gridPane.getPrefWidth() / columnsCount - 5, gridPane.getPrefHeight() / rowsCount - 5, column, row);
                
                System.out.println(gridPane.getCellBounds(row, column));
                System.out.println(gridPane.getColumnCount());
                
                MyThread thred = new MyThread(cell);
                thred.setDaemon(true);
                thred.start();
                MyLogger.logger.log(Level.INFO, "Thred X: " + column + ", Y: " + row + " started.");

                // GridPane.setFillHeight(square, true);
                // GridPane.setFillWidth(square, true);
                // GridPane.setColumnIndex(square, column);
                // GridPane.setRowIndex(square, row);
                gridPane.add(cell, column, row );

            }
        }
    }
}
