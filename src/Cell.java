import java.util.logging.Level;
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
        double red = RandomNumberGenerator.getRandomDouble();
        double green = RandomNumberGenerator.getRandomDouble();
        double blue = RandomNumberGenerator.getRandomDouble();

        setFill(Color.color(red, green, blue));
        // System.out.println("Color randomly changed");
    }

    public void setNeighborsAverageFill()
    {
        int activeNeighborsCount = 0;
        double red = 0;
        double green = 0;
        double blue = 0;

        Cell[] neighbors = {leftCell, rightCell, upperCell, lowerCell};

        
        for (Cell cell : neighbors) 
        {
            if (cell.getMyThread().getState() == Thread.State.WAITING)
            {
                continue;
            }

            red += (Color.web(cell.getFill().toString())).getRed();
            green += (Color.web(cell.getFill().toString())).getGreen();
            blue += (Color.web(cell.getFill().toString())).getBlue();

            activeNeighborsCount++;
        }
        
        if (activeNeighborsCount == 0)
        {
            return;
        }

        double redAverage = red / activeNeighborsCount;
        double greenAverage = green / activeNeighborsCount;
        double blueAverage = blue / activeNeighborsCount;

        setFill(Color.color(redAverage, greenAverage, blueAverage));

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
