import java.util.logging.Level;

public class MyThread extends Thread
{
    private Cell cell;
    private double propability = 0.1;
    private double speed = 100;

    public MyThread(Cell cell)
    {
        this.cell = cell;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            if (RandomNumberGenerator.getRandomDouble() <= propability)
            {
                MyLogger.logger.log(Level.INFO, "Column: " + cell.getColumn() + ", row: " + cell.getRow() + " changed color");
                cell.setRandomFill();
            }
            long delay = Double.valueOf((RandomNumberGenerator.getRandomDouble() + 0.5) * speed).longValue();
            try { Thread.sleep(delay); }catch (InterruptedException e) {}
        }
    }
}
