import java.util.Scanner;
import java.util.Random;

public class Topping extends Ingredient {
    public Topping(String name) {
        super(name);
    }

    @Override
    public void performMinigame(Scanner scanner, Random random, Player player) {
        System.out.println("==================================");
        System.out.println("   PREPARING " + this.name.toUpperCase());
        System.out.println("==================================");
        System.out.println("Get ready... press ENTER for the 'Right Amount'!");
        System.out.println("Don't press it early!");

        int bestWindow = 300; 
        int goodWindow = 700; 
        if (player.hasCleanCut) { 
            bestWindow = 450; 
            goodWindow = 900; 
            System.out.println("(Your CleanCut upgrade gives you more time!)"); 
        }

        try {
            int waitTime = random.nextInt(3000) + 2000; 
            Thread.sleep(waitTime);

            if (System.in.available() > 0) {
                scanner.nextLine();
                System.out.println();
                System.out.println("--- INSUFFICIENT! ---");
                System.out.println("You pressed way too early!");
                this.quality = "Insufficient";
                return;
            }

            System.out.println();
            System.out.println("*************************");
            System.out.println("!!! NOW! (PRESS ENTER) !!!");
            System.out.println("*************************");

            long startTime = System.currentTimeMillis();
            scanner.nextLine();
            long reactionTime = System.currentTimeMillis() - startTime;

            System.out.println();
            System.out.println("Your time: " + reactionTime + "ms");

            if (reactionTime < bestWindow) {
                this.quality = "Right Amount";
            } else if (reactionTime < goodWindow) {
                this.quality = "Insufficient";
            } else {
                System.out.println("--- TOO MUCH! ---");
                this.quality = "Too Much";
            }

        } catch (Exception e) {
            System.out.println("An error occurred!");
            this.quality = "Insufficient";
        }
    }
}
