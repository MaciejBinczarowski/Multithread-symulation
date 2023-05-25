import java.util.logging.Level;

import javafx.application.Platform;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Cell extends Rectangle
{
    private int column;
    private int row;
    private Cell leftCell;
    private Cell rightCell;
    private Cell upperCell;
    private Cell lowerCell;
    private MyThread myThread;
    private Object myMutex = new Object();

    public Object getMyMutex() {
        return myMutex;
    }

    public MyThread getMyThread() 
    {
        return myThread;
    }

    public void setMyThread(MyThread myThread) 
    {
        this.myThread = myThread;
    }

    public Cell(double height, double width,int column, int row)
    {
        super(height, width);
        this.column = column;
        this.row = row;
        setRandomFill();
        setOnClickAction();
    }

    public void setRandomFill()
    {
        synchronized(myMutex)
        {
            double red = RandomNumberGenerator.getRandomDouble();
            double green = RandomNumberGenerator.getRandomDouble();
            double blue = RandomNumberGenerator.getRandomDouble();

            Platform.runLater(() -> setFill(Color.color(red, green, blue)));
        }
    }

    public void setNeighborsAverageFill()
    {
        int activeNeighborsCount = 0;
        double red = 0;
        double green = 0;
        double blue = 0;

        Cell[] neighbors = {leftCell, rightCell, upperCell, lowerCell};

        synchronized(myMutex)
        {
            for (Cell cell : neighbors) 
            {
                synchronized(cell.getMyMutex())
                {
                    if (cell.getMyThread() == null)
                    {
                        return;
                    }
                    
                    if ((cell.getMyThread().getState()) == Thread.State.WAITING)
                    {
                        continue;
                    }

                    red += (Color.web(cell.getFill().toString())).getRed();
                    green += (Color.web(cell.getFill().toString())).getGreen();
                    blue += (Color.web(cell.getFill().toString())).getBlue();

                    activeNeighborsCount++;
                }
            }
        }
        
        if (activeNeighborsCount == 0)
        {
            return;
        }

        double redAverage = red / activeNeighborsCount;
        double greenAverage = green / activeNeighborsCount;
        double blueAverage = blue / activeNeighborsCount;

        Platform.runLater(() -> setFill(Color.color(redAverage, greenAverage, blueAverage)));

        // System.out.println("Color changed");
    }

    private void setOnClickAction()
    {
        setOnMouseClicked(event -> 
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                MyLogger.logger.log(Level.INFO, "Cell X = " + column + ", Y = " + row + " pressed");
                myThread.setBlocked();
                setStroke((getStroke() == null) ? Color.BLACK : null);
                setStrokeType(StrokeType.INSIDE);
            }
        });
    }

    public void setLeftCell(Cell leftCell) {
        this.leftCell = leftCell;
    }

    public void setRightCell(Cell rightCell) {
        this.rightCell = rightCell;
    }

    public void setUpperCell(Cell upperCell) {
        this.upperCell = upperCell;
    }

    public void setLowerCell(Cell lowerCell) {
        this.lowerCell = lowerCell;
    }

    public int getColumn() 
    {
        return column;
    }

    public int getRow() 
    {
        return row;
    }

}
