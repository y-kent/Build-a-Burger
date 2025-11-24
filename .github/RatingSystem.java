import java.util.ArrayList;

public class RatingSystem {
    
    public static double calculateRating(CustomerTicket order, Burger playerBurger, boolean onTime, Player player) {
        double rating = 5.0; 
        ArrayList<String> requested = order.getRequestedIngredients();
        ArrayList<Ingredient> actual = playerBurger.getIngredients();
        
        if (!onTime) {
            rating -= 1.0;
        }
        
        for (String requestedName : requested) {
            boolean found = false;
            for (Ingredient actualItem : actual) {
                if (actualItem.getName().equals(requestedName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                rating -= 1.0;
            }
        }
        
        for (Ingredient actualItem : actual) {
            boolean wasOrdered = false;
            for (String req : requested) {
                if (req.equals(actualItem.getName())) {
                    wasOrdered = true;
                    break;
                }
            }
            
            if (!wasOrdered) {
                rating -= 1.0;
                continue; 
            }
            
            String quality = actualItem.getQuality();
            
            if (actualItem instanceof Patty) {
                if (quality.equals("Cooked")) {
                    player.perfectPatties++;
                } else {
                    rating -= 1.0;
                }
            } else { 
                if (quality.equals("Right Amount")) {
                    if (actualItem instanceof Topping) player.perfectToppings++;
                    if (actualItem instanceof Sauce) player.perfectSauces++;
                } else {
                    rating -= 0.5;
                }
            }
        }
        
        if (rating < 1.0) {
            rating = 1.0;
        }
        return rating;
    }

    private static int getQualityPoints(String quality) {
        switch (quality) {
            case "Cooked":
            case "Right Amount":
                return 3; 
            case "Insufficient":
                return 2; 
            case "Overcooked":
            case "Uncooked":
            case "Too Much":
                return 1; 
            default:
                return 0;
        }
    }

    public static void displayRatingBreakdown(CustomerTicket order, Burger playerBurger) {
        System.out.println("🔍 Burger Quality Breakdown:");
        System.out.println("╔═════════════════╦══════════════╦════════╗");
        System.out.println("║   Ingredient    ║    Quality   ║ Points ║");
        System.out.println("╠═════════════════╬══════════════╬════════╣");
        
        ArrayList<String> requested = order.getRequestedIngredients();
        ArrayList<Ingredient> actual = playerBurger.getIngredients();
        
        for (String requestedName : requested) {
            boolean found = false;
            for (Ingredient actualItem : actual) {
                if (actualItem.getName().equals(requestedName)) {
                    int points = getQualityPoints(actualItem.getQuality());
                    System.out.printf("║ %-15s ║ %-12s ║  %d/3   ║\n", 
                        actualItem.getName(), actualItem.getQuality(), points);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.printf("║ %-15s ║ %-12s ║  0/3   ║\n", 
                    requestedName, "MISSING", 0);
            }
        }
        
        for (Ingredient actualItem : actual) {
            boolean wasOrdered = false;
            for (String req : requested) {
                if (req.equals(actualItem.getName())) {
                    wasOrdered = true;
                    break;
                }
            }
            if (!wasOrdered) {
                System.out.printf("║ %-15s ║ %-12s ║  -1    ║\n", 
                    actualItem.getName(), "EXTRA");
            }
        }
        
        System.out.println("╚═════════════════╩══════════════╩════════╝");
    }
}