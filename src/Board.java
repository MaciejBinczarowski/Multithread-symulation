import java.util.logging.Level;
import javafx.scene.layout.GridPane;

public class Board 
{
    // private Rectangle square = new Rectangle(25, 25);
    private int columnsCount;
    private int rowsCount;
    private GridPane gridPane;
    private double propability;
    private double speed;
    private Cell[][] gridCells;

    public Board(GridPane pane, int n, int m, double propability, double speed)
    {
        this.columnsCount = n;
        this.rowsCount = m;
        this.gridPane = pane;
        this.propability = propability;
        this.speed = speed;

        setGrid();
        setNeighbors(this.gridCells);
        startThreads(this.gridCells);
    }

    // creates grid m x n grid an and fills it with Cell objects and assigns relations with neighbors
    // and starts thread on each of them
    private void setGrid()
    {
        // for (int i = 0; i < columnsCount; i++) {
        //     ColumnConstraints colConst = new ColumnConstraints();
        //     colConst.setPercentWidth(100.0 / columnsCount);
        //     gridPane.getColumnConstraints().add(colConst);
        // }

        // for (int i = 0; i < rowsCount; i++) {
        //     RowConstraints rowConst = new RowConstraints();
        //     rowConst.setPercentHeight(100.0 / rowsCount);
        //     gridPane.getRowConstraints().add(rowConst);         
        // }

        Cell[][] gridCells = new Cell[rowsCount][columnsCount];
        for (int column = 0; column < columnsCount; column++)
        {
            for (int row = 0; row < rowsCount; row++)
            {
                MyLogger.logger.log(Level.INFO, "Filing column " + column);
                Cell cell = new Cell(50, 50, column, row);
                gridCells[row][column] = cell;
                gridPane.add(cell, column, row );
            }
        }

        this.gridCells = gridCells;
    }

    private void setNeighbors(Cell[][] gridCells)
    {
        System.out.println(gridCells[0][1]);
        for (int rowIndex = 0; rowIndex < rowsCount; rowIndex++)
        {
            // System.out.println(gridCells[0][0]);
            for (int columnIndex = 0; columnIndex < columnsCount; columnIndex++)
            {
                Cell currentCell = gridCells[rowIndex][columnIndex];

                // in left and upper neighbors is added columns/rowsCount in case columns/rowsCount - 1 
                // is negative (in java modulo from negative returns this number)
                currentCell.setLeftCell(gridCells[rowIndex][(columnIndex - 1 + columnsCount) % columnsCount]);
                currentCell.setRightCell(gridCells[rowIndex][(columnIndex + 1) % columnsCount]);
                currentCell.setUpperCell(gridCells[(rowIndex - 1 + rowsCount) % rowsCount][columnIndex]);
                currentCell.setLowerCell(gridCells[(rowIndex + 1) % rowsCount][columnIndex]);

                MyLogger.logger.log(Level.INFO, "Neighbors setted");
            }
        }
    }

    private void startThreads(Cell[][] gridCells)
    {
        Object mutex = new Object();

        for (Cell[] row : gridCells) 
        {
            for (Cell cell : row) 
            {
                MyThread thred = new MyThread(cell, mutex, propability, speed);
                thred.setDaemon(true);
                //Platform.runLater(thred);
                cell.setMyThread(thred);
                thred.start();
                MyLogger.logger.log(Level.INFO, "Thred X: " + cell.getColumn() + ", Y: " + cell.getRow() + " started.");
            }
        }
    }
}
