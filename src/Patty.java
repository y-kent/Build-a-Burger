import java.util.Scanner;
import java.util.Random;

public class Patty extends Ingredient {
    public Patty(String name) {
        super(name);
    }

    @Override
    public void performMinigame(Scanner scanner, Random random, Player player) {
        System.out.println("==================================");
        System.out.println("   COOKING " + this.name.toUpperCase());
        System.out.println("==================================");
        System.out.println("Get ready... press ENTER when it's 'Cooked'!");
        System.out.println("Don't press it early!");
        
        int cookWindow = 700; 
        if (player.hasGrillUpgrade) {
            cookWindow = 1000; 
            System.out.println("(Your GrillUpgrade gives you more time!)");
        }

        try {
            int cookTime = random.nextInt(3000) + 2000; 
            Thread.sleep(cookTime);

            if (System.in.available() > 0) {
                scanner.nextLine();
                System.out.println();
                System.out.println("--- UNCOOKED! ---");
                System.out.println("You pressed way too early!");
                this.quality = "Uncooked";
                return;
            }

            System.out.println();
            System.out.println("**********************************");
            System.out.println("!!! IT'S COOKED! (PRESS ENTER) !!!");
            System.out.println("**********************************");

            long startTime = System.currentTimeMillis();
            scanner.nextLine();
            long reactionTime = System.currentTimeMillis() - startTime;

            System.out.println();
            System.out.println("Your time: " + reactionTime + "ms");

            if (reactionTime < cookWindow) {
                this.quality = "Cooked";
            } else {
                System.out.println("--- OVERCOOKED! ---");
                this.quality = "Overcooked";
            }

        } catch (Exception e) {
            System.out.println("An error occurred!");
            this.quality = "Uncooked";
        }
    }
}

