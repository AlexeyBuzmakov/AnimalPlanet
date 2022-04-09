package food;

public class Grass extends Food{
    private static int grassCount;

    public static int getGrassCount() {
        return grassCount;
    }

    public static void setGrassCount(int grassCount) {
        Grass.grassCount = grassCount;
    }

    @Override
    public String toString() {
        return " G  ";
    }
}
