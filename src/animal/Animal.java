package animal;


import gameobject.GameObject;

public abstract class Animal extends GameObject {
    private static int visibility;
    private static int hungerDays;
    private boolean isTired;
    private int life;

    public Animal() {
       life = getHungerDays();
    }

    public static int getHungerDays() {
        return hungerDays;
    }

    public static void setHungerDays(int hungerDays) {
        Animal.hungerDays = hungerDays;
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
        life = getHungerDays();
    }

    public boolean hungerDie() {
        life--;
        return life < 0;
    }
}
