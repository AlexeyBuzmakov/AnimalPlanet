package food;

public class Meat extends Food{
    private static int meatCount;

    public static int getMeatCount() {
        return meatCount;
    }

    public static void setMeatCount(int meatCount) {
        Meat.meatCount = meatCount;
    }

    @Override
    public void destroyFood() {
        meatCount--;
    }

    @Override
    public String toString() {
        return " M ";
    }
}
