package animal;

public class Predator extends Animal{
    private static int predatorCount;

    public Predator() {
        predatorCount++;
    }

    public static int getPredatorCount() {
        return predatorCount;
    }

    public static void setPredatorCount(int predatorCount) {
        Predator.predatorCount = predatorCount;
    }

    @Override
    public void die() {
        predatorCount--;
    }

    @Override
    public String toString() {
        return " P  ";
    }
}
