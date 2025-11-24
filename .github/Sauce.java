import java.util.Scanner;
import java.util.Random;

public class Sauce extends Ingredient {
    public Sauce(String name) {
        super(name);
    }

    @Override
    public void performMinigame(Scanner scanner, Random random, Player player) {
        System.out.println("==================================");
        System.out.println("   ADDING " + this.name.toUpperCase());
        System.out.println("==================================");
        
        String[] typingPhrases = {
            "Squeeze the bottle!",
            "Perfect burger coming up",
            "Five star service",
            "This sauce is the secret",
            "Order up!"
        };
        String phraseToType = typingPhrases[random.nextInt(typingPhrases.length)];
        
        long timeLimit = 5000; 
        if (player.hasSmoothSauce) { 
            timeLimit = 7000; 
            System.out.println("(Your SmoothSauce upgrade gives you more time!)"); 
        }
        
        System.out.println("Type the following phrase *exactly*!");
        System.out.println("You have " + (timeLimit / 1000) + " seconds!");
        System.out.println();
        System.out.println("Type: " + phraseToType);
        System.out.print("> ");

        long startTime = System.currentTimeMillis();
        String typed = scanner.nextLine();
        long timeTaken = System.currentTimeMillis() - startTime;

        if (typed.equals(phraseToType) && timeTaken <= timeLimit) {
            this.quality = "Right Amount";
        } else {
            System.out.println();
            System.out.println("--- OOPS! ---");
            if (random.nextBoolean()) {
                this.quality = "Too Much";
            } else {
                this.quality = "Insufficient";
            }
        }
    }
}