package animal;

public class Herbivore extends Animal{
    private static int herbivoreCount;

    public Herbivore() {
        herbivoreCount++;
    }

    public static int getHerbivoreCount() {
        return herbivoreCount;
    }

    public static void setHerbivoreCount(int herbivoreCount) {
        Herbivore.herbivoreCount = herbivoreCount;
    }

    @Override
    public void die() {
        herbivoreCount--;
    }

    @Override
    public String toString() {
        return " H ";
    }
}
