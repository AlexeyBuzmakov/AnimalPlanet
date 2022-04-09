package game;


import gameobject.GameObject;
import animal.Herbivore;
import animal.Predator;
import food.Meat;
import food.Grass;
import java.util.Scanner;

public class Game {
    private static final int WIDTH = 25;
    private static final int HEIGTH = 25;
    private int coordinateX;
    private int coordinateY;
    private final GameObject[][] territory = new GameObject[WIDTH][HEIGTH];


    public void start() {
        inputParameters();
        createGameObject();
        searchFoodAnimal();
        doStepAnimal();
        showTerritory();
    }

    private void inputParameters() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество хищников");
        Predator.setPredatorCount(scanner.nextInt());

        System.out.println("Введите дальность видимости хищников");
        Predator.setPredatorVisibility(scanner.nextInt());

        System.out.println("Введите количество травоядных");
        Herbivore.setHerbivoreCount(scanner.nextInt());

        System.out.println("Введите дальность видимости травоядных");
        Herbivore.setHerbivoreVisibility(scanner.nextInt());

        System.out.println("Введите количество мяса");
        Meat.setMeatCount(scanner.nextInt());

        System.out.println("Введите количество травы");
        Grass.setGrassCount(scanner.nextInt());
    }

    private void createGameObject() {
        createPredator();
        createHerbivore();
        createMeat();
        createGrass();
    }

    private void createPredator() {
        int i = 0;
        while (i <= Predator.getPredatorCount()) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (territory[a][b] == null) {
                territory[a][b] = new Predator();
                i++;
            }
        }
    }

    private void createHerbivore() {
        int i = 0;
        while (i <= Herbivore.getHerbivoreCount()) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (territory[a][b] == null) {
                territory[a][b] = new Herbivore();
                i++;
            }
        }
    }

    private void createMeat() {
        int i = 0;
        while (i <= Meat.getMeatCount()) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (territory[a][b] == null) {
                territory[a][b] = new Meat();
                i++;
            }
        }
    }

    private void createGrass() {
        int i = 0;
        while (i <= Grass.getGrassCount()) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (territory[a][b] == null) {
                territory[a][b] = new Grass();
                i++;
            }
        }
    }

    private void searchFoodAnimal() {
        searchFoodPredator();
        searchFoodHerbivore();
    }

    private void searchFoodPredator() {
        int p = Predator.getPredatorVisibility();
        for (int i = 0; i < territory.length; i++) {
            for (int j = 0; j < territory[i].length; j++) {
                if (territory[i][j] instanceof Predator) {
                    coordinateX = i;
                    coordinateY = j;
                    int count = 0;
                    int x = 0;
                    if (p > i) {
                        i += p - i;
                    }
                    if (p > j) {
                        j += p - j;
                    }
                    for (int a = i - p; a <= 2 * p; a++) {
                        if (i + p > 24) {
                            x = i + p - 24;
                        }
                        if (j + p > 24) {
                            x = j + p - 24;
                        }

                        for (int b = j - p; b <= 2 * p - x; b++) {
                            if (count > 0)
                                break;
                            if (territory[a][b] instanceof Herbivore || territory[a][b] instanceof Meat) {
                                territory[a][b] = new Predator();
                                territory[i][j] = null;
                                count++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void searchFoodHerbivore() {
        int n = Herbivore.getHerbivoreVisibility();
        for (int i = 0; i < territory.length; i++) {
            for (int j = 0; j < territory[i].length; j++) {
                if (territory[i][j] instanceof Herbivore) {
                    coordinateX = i;
                    coordinateY = j;
                    int count = 0;
                    int x = 0;
                    if (n > i) {
                        i += n - i;
                    }
                    if (n > j) {
                        j += n - j;
                    }
                    for (int a = i - n; a <= 2 * n; a++) {
                        if (i + n > 24) {
                            x = i + n - 24;
                        }
                        if (j + n > 24) {
                            x = j + n - 24;
                        }
                        for (int b = j - n; b <= 2 * n - x; b++) {
                            if (count > 0)
                                break;
                            if (territory[a][b] instanceof Grass) {
                                territory[a][b] = new Herbivore();
                                territory[i][j] = null;
                                count++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void doStepAnimal() {
        doStepPredator(coordinateX, coordinateY);
        doStepHerbivore(coordinateX, coordinateY);
    }


    private void doStepPredator(int a, int b) {
        int n = 2;
        int m = 2;
        while (territory[a][b] != null) {
            for (int i = a - 1; i < n; i++) {
                for (int j = b - 1; j < m; j++) {
                    if (territory[i][j] == null) {
                        territory[i][j] = new Predator();
                        territory[a][b] = null;
                    }
                }
            }
            if (a > 0) {
                a--;
                n += 2;
            } else {
                n++;
            }
            if (b > 0) {
                b--;
                m += 2;
            } else {
                m++;
            }
        }
    }

    private void doStepHerbivore(int a, int b) {
        int n = 2;
        int m = 2;
        while (territory[a][b] != null) {
            for (int i = a - 1; i < n; i++) {
                for (int j = b - 1; j < m; j++) {
                    if (territory[i][j] == null) {
                        territory[i][j] = new Herbivore();
                        territory[a][b] = null;
                    }
                }
            }
            if (a > 0) {
                a--;
                n += 2;
            } else {
                n++;
            }
            if (b > 0) {
                b--;
                m += 2;
            } else {
                m++;
            }
        }
    }


    private void showTerritory() {
        for (int i = 0; i < territory.length; i++) {
            for (int j = 0; j < territory[i].length; j++) {
                System.out.print(territory[i][j] + " ");
            }
            System.out.println();
        }
        System.out.print("Хищников " + Predator.getPredatorCount() + " ");
        System.out.println("Травоядных " + Herbivore.getHerbivoreCount());
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
}



