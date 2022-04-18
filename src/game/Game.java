package game;

import animal.Animal;
import gameobject.GameObject;
import animal.Herbivore;
import animal.Predator;
import food.Meat;
import food.Grass;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int WIDTH = 25;
    private static final int HEIGTH = 25;
    private static final GameObject[][] TERRITORY = new GameObject[WIDTH][HEIGTH];

    public void start() {
        inputParameters();
        createGameObject();
        showTerritory();
        while(checkEndGame()) {
            doStep();
            createMeat();
            createGrass();
            recuperation();
            showTerritory();
        }
    }

    private void inputParameters() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите стартовое количество хищников");
        Predator.setPredatorCount(scanner.nextInt());

        System.out.println("Введите стартовое количество травоядных");
        Herbivore.setHerbivoreCount(scanner.nextInt());

        System.out.println("Введите количество дней без еды до смерти животного");
        Animal.setHungerDays(scanner.nextInt());

        System.out.println("Введите дальность видимости животных");
        Animal.setVisibility(scanner.nextInt());

        System.out.println("Введите стартовое количество мяса");
        Meat.setMeatCount(scanner.nextInt());

        System.out.println("Введите стартовое количество травы");
        Grass.setGrassCount(scanner.nextInt());
    }

    private void createGameObject() {
        createPredator();
        createHerbivore();
        createMeat();
        createGrass();
    }

    private void createPredator() {
        int i = Predator.getPredatorCount();
        Predator.setPredatorCount(0);
        while (i > 0 ) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (TERRITORY[a][b] == null) {
                TERRITORY[a][b] = new Predator();
                i--;
            }
        }
    }

    private void createHerbivore() {
        int i = Herbivore.getHerbivoreCount();
        Herbivore.setHerbivoreCount(0);
        while (i > 0 ) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (TERRITORY[a][b] == null) {
                TERRITORY[a][b] = new Herbivore();
                i--;
            }
        }
    }

    private void createMeat() {
        int i = 0;
        while (i < Meat.getMeatCount()) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (TERRITORY[a][b] == null) {
                TERRITORY[a][b] = new Meat();
                i++;
            }
        }
    }

    private void createGrass() {
        int i = 0;
        while (i < Grass.getGrassCount()) {
            int a = (int) (Math.random() * 25);
            int b = (int) (Math.random() * 25);
            if (TERRITORY[a][b] == null) {
                TERRITORY[a][b] = new Grass();
                i++;
            }
        }
    }

    private void doStep() {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGTH; j++) {
                if(TERRITORY[i][j] instanceof Animal) {
                    if (!((Animal) TERRITORY[i][j]).isTired()) {
                        ((Animal) TERRITORY[i][j]).setTired(true);
                        if(!checkEat(i, j)) {
                            if(((Animal) TERRITORY[i][j]).hungerDie()) {
                                ((Animal) TERRITORY[i][j]).die();
                                TERRITORY[i][j] = null;
                            }
                            else {
                                moveAnimal(i, j);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean checkEat(int i, int j) {
        if(TERRITORY[i][j] instanceof Predator) {
            return checkEatPredator(i, j);
        }
        else {
            return checkEatHerbivore(i, j);
        }
    }

    private boolean checkEatPredator(int i, int j) {
        int visibility = Animal.getVisibility();
        for (int a = i - visibility; a <= i + visibility; a++) {
            for (int b = j - visibility; b <= j + visibility; b++) {
                if(a < 0 || b < 0 || a >= WIDTH || b >= HEIGTH) {
                    continue;
                }
                if (TERRITORY[a][b] instanceof Herbivore || TERRITORY[a][b] instanceof Meat) {
                    if(TERRITORY[a][b] instanceof Herbivore) {
                        ((Herbivore) TERRITORY[a][b]).die();
                    }
                    TERRITORY[a][b] = TERRITORY[i][j];
                    ((Predator) TERRITORY[a][b]).satiety();
                    TERRITORY[i][j] = null;
                    reproductionAnimal(a, b);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEatHerbivore(int i, int j) {
        int visibility = Animal.getVisibility();
        for (int a = i - visibility; a <= i + visibility; a++) {
            for (int b = j - visibility; b <= j + visibility; b++) {
                if(a < 0 || b < 0 || a >= WIDTH || b >= HEIGTH) {
                    continue;
                }
                if (TERRITORY[a][b] instanceof Grass) {
                    TERRITORY[a][b] = TERRITORY[i][j];
                    ((Herbivore) TERRITORY[a][b]).satiety();
                    TERRITORY[i][j] = null;
                    reproductionAnimal(a, b);
                    return true;
                }
            }
        }
        return false;
    }

    private void reproductionAnimal(int a, int b) {
        Random random = new Random();
        boolean reproduction = random.nextBoolean();
        if (reproduction) {
            int n = 3;
            int m = 3;
            while (true) {
                int i = (int) ((a - 1) + Math.random() * n);
                int j = (int) ((b - 1) + Math.random() * m);
                if (i >= 0 && i < WIDTH && j >= 0 && j < HEIGTH) {
                    if (TERRITORY[i][j] == null) {
                        if (TERRITORY[a][b] instanceof Predator) {
                            TERRITORY[i][j] = new Predator();

                        } else {
                            TERRITORY[i][j] = new Herbivore();
                        }
                        break;
                    }
                }
            }
        }
    }

    private void moveAnimal(int a, int b) {
        int lengthMove = 3;
        while(true) {
            int i = (int) ((a - 1) + Math.random() * lengthMove);
            int j = (int) ((b - 1) + Math.random() * lengthMove);
            if(i >= 0 && i < WIDTH && j >= 0 && j < HEIGTH) {
                if (TERRITORY[i][j] == null) {
                        TERRITORY[i][j] = TERRITORY[a][b];
                        TERRITORY[a][b] = null;
                        break;
                    }
                }
            }
        }

    private void recuperation() {
        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGTH; j++) {
                if(TERRITORY[i][j] instanceof Animal) {
                    ((Animal) TERRITORY[i][j]).setTired(false);
                }
            }
        }
    }

    private boolean checkEndGame() {
        if(Predator.getPredatorCount() == 0) {
            System.out.println("Травоядные победили");
            return false;
        }
        else if(Herbivore.getHerbivoreCount() == 0) {
            System.out.println("Хищники победили");
            return false;
        }
        return true;
    }

    private void showTerritory() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGTH; j++) {
            if(TERRITORY[i][j] == null) {
                System.out.print("   ");
                }
            else {
                System.out.print(TERRITORY[i][j]);
            }
            }
            System.out.println();
        }
        System.out.print("Хищников " + Predator.getPredatorCount() + " ");
        System.out.println("Травоядных " + Herbivore.getHerbivoreCount());
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}



