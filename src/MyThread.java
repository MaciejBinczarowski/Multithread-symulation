import java.util.logging.Level;

import javafx.application.Platform;

public class MyThread extends Thread
{
    private Object mutex;
    private Cell cell;
    private double propability;
    private double speed;
    volatile private boolean isBlocked = false;

    synchronized public void setBlocked() 
    {
        this.isBlocked = !this.isBlocked;
        notify();
    }

    public MyThread(Cell cell, Object mutex, double propability, double speed)
    {
        this.cell = cell;
        this.mutex = mutex;
        this.propability = propability;
        this.speed = speed;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            long delay = Double.valueOf((RandomNumberGenerator.getRandomDouble() + 0.5) * speed).longValue();
            try { Thread.sleep(delay); }catch (InterruptedException e) {System.out.println("Fred ma problem");}

            try
            {
                if (isBlocked)
                {
                    synchronized(this)
                    {
                        while (isBlocked)
                        {
                            wait();
                        }
                    }
                }
            }
            catch (InterruptedException e)
            {

            }

            synchronized(mutex)
            {
                System.out.println("Start X: " + cell.getColumn() + " Y: " + cell.getRow());

                if (RandomNumberGenerator.getRandomDouble() <= propability)
                {
                    // MyLogger.logger.log(Level.INFO, "Column: " + cell.getColumn() + ", row: " + cell.getRow() + " changed color");
                    Platform.runLater(() -> cell.setRandomFill());
                }
                else
                {
                    Platform.runLater(() -> cell.setNeighborsAverageFill());
                }

                System.out.println("End X: " + cell.getColumn() + " Y: " + cell.getRow());
            }
        }
    }
}
