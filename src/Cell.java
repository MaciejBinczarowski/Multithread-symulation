import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle
{
    private int column;
    private int row;
    
    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

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
    }

}
