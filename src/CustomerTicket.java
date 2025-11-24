import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class CustomerTicket {
    private ArrayList<String> requestedIngredients;
    private String customerName;
    private String customerDialogue;
    
    private static final String[] PATTY_DB = {"Beef Patty"};
    private static final String[] TOPPING_DB = {"Lettuce", "Cheese", "Tomatoes", "Onions", "Pickles", "Bacon", "Mushrooms"};
    private static final String[] SAUCE_DB = {"Ketchup", "Mustard", "Mayonnaise", "Hot Sauce", "BBQ", "Ranch"};
    
    private static final String[] NAMES_DB = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace", "Henry", "Ivy", "Jack"};
    private static final String[] DIALOGUE_DB = {
        "Just a simple burger, please.",
        "I'm starving! Load it up!",
        "Let's see what you've got, chef.",
        "Make it quick, I'm in a hurry!",
        "I hope this is worth the wait.",
        "My friend recommended this place!",
        "Something spicy, please!",
        "The usual for me.",
        "I'm feeling adventurous today.",
        "Make it perfect!"
    };

    public CustomerTicket(Random random, Player player) {
        this.requestedIngredients = new ArrayList<>();
        
        this.customerName = NAMES_DB[random.nextInt(NAMES_DB.length)];
        this.customerDialogue = DIALOGUE_DB[random.nextInt(DIALOGUE_DB.length)];
        
        int numPatties = random.nextInt(3) + 1; 
        for (int i = 0; i < numPatties; i++) {
             this.requestedIngredients.add(PATTY_DB[0]);
        }
        
        int numToppings = random.nextInt(4) + 2; 
        for (int i = 0; i < numToppings; i++) {
            String topping;
            if (player.hasUnlockedExtras) {
                topping = TOPPING_DB[random.nextInt(TOPPING_DB.length)];
            } else {
                topping = TOPPING_DB[random.nextInt(5)]; 
            }
            this.requestedIngredients.add(topping);
        }
        
        int numSauces = random.nextInt(3) + 1; 
        for (int i = 0; i < numSauces; i++) {
            String sauce;
            if (player.hasUnlockedExtras) {
                sauce = SAUCE_DB[random.nextInt(SAUCE_DB.length)];
            } else {
                sauce = SAUCE_DB[random.nextInt(4)];
            }
            this.requestedIngredients.add(sauce);
        }
        
        Collections.shuffle(this.requestedIngredients, random);
    }
    
    public void displayOrder() {
        System.out.println("  //============================\\\\");
        System.out.println(" //       CUSTOMER ORDER       \\\\");
        System.out.println("//==============================");
        System.out.println();
        System.out.println("   (Top Bun)");
        for (String item : this.requestedIngredients) {
            System.out.println("     - " + item);
        }
        System.out.println("   (Bottom Bun)");
        System.out.println();
        System.out.println("\\\\==============================//");
        System.out.println(" \\\\============================//");
        System.out.println();
    }
    
    public ArrayList<String> getRequestedIngredients() {
        return this.requestedIngredients;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public String getCustomerDialogue() {
        return this.customerDialogue;
    }
    
    public static boolean isPatty(String name) {
        return name.equals("Beef Patty");
    }
    
    public static boolean isSauce(String name) {
        for (String s : SAUCE_DB) {
            if (s.equals(name)) return true;
        }
        return false;
    }
    public static boolean isTopping(String name) {
        for (String s : TOPPING_DB) {
            if (s.equals(name)) return true;
        }
        return false;
    }
}
