package animal;

public class Predator extends Animal{
    private static int predatorCount;
    private static int predatorVisibility;

    public Predator() {
        predatorCount++;
    }

    public static int getPredatorCount() {
        return predatorCount;
    }

    public static void setPredatorCount(int predatorCount) {
        Predator.predatorCount = predatorCount;
    }

    public static int getPredatorVisibility() {
        return predatorVisibility;
    }

    public static void setPredatorVisibility(int predatorVisibility) {
        Predator.predatorVisibility = predatorVisibility;
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
