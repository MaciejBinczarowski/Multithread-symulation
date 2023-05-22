import java.util.logging.Level;

public class MyThread extends Thread
{
    private Object mutex;
    private Cell cell;
    private double propability = 0.1;
    private double speed = 1000;

    public MyThread(Cell cell, Object mutex)
    {
        this.cell = cell;
        this.mutex = mutex;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            long delay = Double.valueOf((RandomNumberGenerator.getRandomDouble() + 0.5) * speed).longValue();
            try { Thread.sleep(delay); }catch (InterruptedException e) {System.out.println("Fred ma problem");}
            
            synchronized(mutex)
            {
                // System.out.println("Start X: " + cell.getColumn() + " Y: " + cell.getRow());

                if (RandomNumberGenerator.getRandomDouble() <= propability)
                {
                    // MyLogger.logger.log(Level.INFO, "Column: " + cell.getColumn() + ", row: " + cell.getRow() + " changed color");
                    cell.setRandomFill();
                }
                else
                {
                    cell.setNeighborsAverageFill();
                }

                // System.out.println("End X: " + cell.getColumn() + " Y: " + cell.getRow());
            }
        }
    }
}
