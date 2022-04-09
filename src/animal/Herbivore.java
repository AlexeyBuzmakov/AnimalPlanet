package animal;

public class Herbivore extends Animal{
    private static int herbivoreCount;
    private static int herbivoreVisibility;

    public Herbivore() {
        herbivoreCount++;
    }

    public static int getHerbivoreCount() {
        return herbivoreCount;
    }

    public static void setHerbivoreCount(int herbivoreCount) {
        Herbivore.herbivoreCount = herbivoreCount;
    }

    public static int getHerbivoreVisibility() {
        return herbivoreVisibility;
    }

    public static void setHerbivoreVisibility(int herbivoreVisibility) {
        Herbivore.herbivoreVisibility = herbivoreVisibility;
    }



    @Override
    public void die() {
        herbivoreCount--;
    }

    @Override
    public String toString() {
        return " H  ";
    }
}
