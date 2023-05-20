import java.util.Random;

public class RandomNumberGenerator extends Random
{
    private static Random random = new Random();

    public static int getRandomInt()
    {
        return random.nextInt(256);
    }

    public static double getRandomDouble()
    {
        return random.nextDouble();
    }
}
