import java.util.Scanner;
import java.util.Random;

public abstract class Ingredient {
    protected String name;
    protected String quality;

    public Ingredient(String name) {
        this.name = name;
        this.quality = "None";
    }

    public abstract void performMinigame(Scanner scanner, Random random, Player player);

    public String getName() {
        return name;
    }
    public String getQuality() {
        return quality;
    }
}