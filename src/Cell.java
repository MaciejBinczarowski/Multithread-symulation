import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle
{
    private int column;
    private int row;
    private Cell leftCell;
    private Cell rightCell;
    private Cell upperCell;
    private Cell lowerCell;

    public Cell(double height, double width,int column, int row)
    {
        super(height, width);
        this.column = column;
        this.row = row;
        setRandomFill();
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
        double redAverage = (Color.web(leftCell.getFill().toString()).getRed() + Color.web(rightCell.getFill().toString()).getRed() + Color.web(upperCell.getFill().toString()).getRed() + Color.web(lowerCell.getFill().toString()).getRed()) / 4.0;
        double greenAverage = (Color.web(leftCell.getFill().toString()).getGreen() + Color.web(rightCell.getFill().toString()).getGreen() + Color.web(upperCell.getFill().toString()).getGreen() + Color.web(lowerCell.getFill().toString()).getGreen()) / 4.0;
        double blueAverage = (Color.web(leftCell.getFill().toString()).getBlue() + Color.web(rightCell.getFill().toString()).getBlue() + Color.web(upperCell.getFill().toString()).getBlue() + Color.web(lowerCell.getFill().toString()).getBlue()) / 4.0;

        setFill(Color.color(redAverage, greenAverage, blueAverage));

        // System.out.println("Color changed");
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
