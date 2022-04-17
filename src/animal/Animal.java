package animal;


import gameobject.GameObject;

public abstract class Animal extends GameObject {
    private static int visibility;
    private  static int hungerDays;
    private boolean isTired;

    public Animal() {
       hungerDays = 5;
    }

    public static int getVisibility() {
        return visibility;
    }

    public static void setVisibility(int visibility) {
        Animal.visibility = visibility;
    }

    public boolean isTired() {
        return isTired;
    }

    public void setTired(boolean tired) {
        isTired = tired;
    }

    public abstract void die();

    public void satiety() {
        hungerDays = 5;
    }

    public boolean hungerDie() {
        hungerDays--;
        return hungerDays < 0;
    }
}
