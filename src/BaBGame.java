import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class BaBGame {

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressEnterToContinue(Scanner scanner) {
        System.out.println();
        System.out.println("[Press Enter to continue...]");
        scanner.nextLine();
    }
    
    public static void displayHeader(String title) {
        int width = 50;
        String border = "╔" + "═".repeat(width) + "╗";
        String middle = "║" + centerText(title, width) + "║";
        String bottom = "╚" + "═".repeat(width) + "╝";
        System.out.println(border);
        System.out.println(middle);
        System.out.println(bottom);
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }

    public static void displayBurgerChefArt() {
        System.out.println("        ,---.         ,---. ");
        System.out.println("       / /\"`.\\.--\"\"--./,'\"\\ \\");
        System.out.println("       \\ \\    _       _    / /");
        System.out.println("        '-\"\"\"-( 0 ) ( 0 )-\"\"\"-'");
        System.out.println("             / .-----. \\");
        System.out.println("            / /       \\ \\");
        System.out.println("           / /         \\ \\");
        System.out.println("           \\ \\         / /");
        System.out.println("            \\ '-------' /");
        System.out.println("             '--.....--'");
    }
     
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        clearConsole();
        displayBurgerChefArt();
        System.out.println();
        displayHeader("WELCOME TO BaB (Build-a-Burger)");
        System.out.println();
        System.out.println("You've just been hired! Your job is to");
        System.out.println("build burgers for customers for 7 days.");
        System.out.println("Pay attention to their orders and your");
        System.out.println("quality! Good luck!");
        System.out.println();
        System.out.print("Please enter your name, chef: ");
        String playerName = scanner.nextLine();
        if (playerName.isEmpty()) playerName = "Chef";
        
        Player player = new Player(playerName);
        
        System.out.println("Welcome, Chef " + player.name + "!");
        pressEnterToContinue(scanner);
        
        int[] customerCounts = {2, 4, 5, 6, 7, 9, 10};
        
        for (int day = 0; day < 7; day++) {
            clearConsole();
            displayHeader("DAY " + (day + 1) + " of 7");
            System.out.println("You have " + customerCounts[day] + " customers today.");
            pressEnterToContinue(scanner);
            
            ArrayList<Double> dayRatings = new ArrayList<>();
            
            for (int cust = 0; cust < customerCounts[day]; cust++) {
                clearConsole();
                displayHeader("Day " + (day + 1) + ", Customer " + (cust + 1));
                
                CustomerTicket order = new CustomerTicket(random, player);
                System.out.println("💬 " + order.getCustomerName() + ": \"" + order.getCustomerDialogue() + "\"");
                System.out.println();
                order.displayOrder();
                
                System.out.println("You have 60 seconds to build this burger!");
                pressEnterToContinue(scanner);
                
                long burgerStartTime = System.currentTimeMillis();
                Burger playerBurger = runBuildStation(scanner, random, order, player);
                long burgerTimeTaken = System.currentTimeMillis() - burgerStartTime;
                
                boolean onTime = (burgerTimeTaken / 1000) <= 60;
                player.totalBurgers++;
                
                clearConsole();
                displayHeader("Customer " + (cust + 1) + " Rating");
                System.out.println("Your build time: " + (burgerTimeTaken / 1000) + " seconds.");
                System.out.println();
                
                double rating = RatingSystem.calculateRating(order, playerBurger, onTime, player);
                
                player.addRating(rating);
                dayRatings.add(rating);
                
                RatingSystem.displayRatingBreakdown(order, playerBurger);
                
                System.out.println("----------------------------------");
                System.out.println("Customer Rating: " + String.format("%.1f", rating) + " / 5.0 Stars");
                System.out.println("----------------------------------");
                pressEnterToContinue(scanner);
            }
            
            clearConsole();
            displayHeader("END OF DAY " + (day + 1));
            System.out.println("Today's Customer Ratings:");
            
            double dayTotal = 0.0;
            for (int i = 0; i < dayRatings.size(); i++) {
                System.out.println("  Customer " + (i+1) + ": " + String.format("%.1f", dayRatings.get(i)) + " Stars");
                dayTotal += dayRatings.get(i);
            }
            
            double dayAverage = dayTotal / dayRatings.size();
            player.setDailyAverage(day, dayAverage);
            
            System.out.println("----------------------------------");
            System.out.println("Daily Average: " + String.format("%.1f", dayAverage) + " / 5.0 Stars");
            
            if (day == 2) {
                runUpgradeChoice(scanner, player);
            }
            
            if (day == 3) {
                if (!player.hasUnlockedExtras) {
                    player.hasUnlockedExtras = true;
                    System.out.println();
                    System.out.println("******************************************");
                    System.out.println("  ITEM UNLOCK! ");
                    System.out.println("  You can now use Bacon, Mushrooms,");
                    System.out.println("  BBQ Sauce, and Ranch in your burgers!");
                    System.out.println("******************************************");
                }
            }
            
            if (day == 4) {
                runUpgradeChoice(scanner, player);
            }
            
            if (day < 6) {
                pressEnterToContinue(scanner);
            }
        }
        
        clearConsole();
        displayHeader("FINAL RESULTS");
        System.out.println("Congratulations, Chef " + player.name + "!");
        System.out.println("You survived 7 days at BaB!");
        System.out.println();
        System.out.println("--- Daily Averages ---");
        
        double totalSum = 0.0;
        for (int i = 0; i < 7; i++) {
            System.out.println("  Day " + (i+1) + ": " + String.format("%.1f", player.dailyAverageRatings[i]) + " Stars");
            totalSum += player.dailyAverageRatings[i];
        }
        
        double finalAverage = totalSum / 7.0;
        System.out.println("----------------------------------");
        System.out.println("FINAL AVERAGE RATING: " + String.format("%.1f", finalAverage) + " / 5.0 Stars");
        System.out.println();
        
        System.out.println("--- Achievements ---");
        if (finalAverage >= 4.5) {
            System.out.println("  [Best Cook] - Earned a final average of 4.5+ Stars!");
        } else if (finalAverage >= 3.5) {
            System.out.println("  [Grill Master] - Earned a final average of 3.5+ Stars!");
        } else {
            System.out.println("  [New Recruit] - You completed the 7 days!");
        }
        
        if (player.perfectPatties >= 10) {
            System.out.println("  [The Perfect Patty] - Cooked 10+ perfect patties!");
        }
        if (player.perfectToppings + player.perfectSauces > 20) {
            System.out.println("  [Topping Titan] - Got 20+ perfect toppings and sauces!");
        }
        
        System.out.println();
        System.out.println("Thanks for playing BaB - Build-a-Burger!");
        scanner.close();
    }
    
    public static void runUpgradeChoice(Scanner scanner, Player player) {
        System.out.println();
        displayHeader("CHOOSE AN UPGRADE");
        System.out.println("You've earned a new upgrade! Choose one:");
        System.out.println("----------------------------------");
        
        while(true) {
            if (player.hasGrillUpgrade) {
                System.out.println(" 1. [ALREADY CHOSEN] GrillUpgrade");
            } else {
                System.out.println(" 1. GrillUpgrade");
                System.out.println("    (Makes patty reaction game easier)");
            }
            
            if (player.hasCleanCut) { 
                System.out.println(" 2. [ALREADY CHOSEN] CleanCut"); 
            } else {
                System.out.println(" 2. CleanCut"); 
                System.out.println("    (Makes topping reaction game easier)");
            }

            if (player.hasSmoothSauce) { 
                System.out.println(" 3. [ALREADY CHOSEN] SmoothSauce"); 
            } else {
                System.out.println(" 3. SmoothSauce"); 
                System.out.println("    (Makes sauce typing game easier)");
            }
            
            System.out.println("----------------------------------");
            System.out.print("Choose 1, 2, or 3: ");
            
            String choice = scanner.nextLine();
            if (choice.equals("1") && !player.hasGrillUpgrade) {
                player.hasGrillUpgrade = true;
                System.out.println(">>> GrillUpgrade acquired! Your patty cooking will be easier.");
                break;
            } else if (choice.equals("2") && !player.hasCleanCut) { 
                player.hasCleanCut = true; 
                System.out.println(">>> CleanCut acquired! Your topping prep will be easier."); 
                break;
            } else if (choice.equals("3") && !player.hasSmoothSauce) { 
                player.hasSmoothSauce = true; 
                System.out.println(">>> SmoothSauce acquired! Your sauce typing will be easier."); 
                break;
            } else {
                System.out.println("Invalid choice or already chosen. Please try again.");
            }
        }
    }
    
    public static Burger runBuildStation(Scanner scanner, Random random, CustomerTicket order, Player player) {
        Burger burger = new Burger();
        ArrayList<String> remainingItems = new ArrayList<>(order.getRequestedIngredients());
        
        boolean isBuilding = true;
        while(isBuilding) {
            clearConsole();
            displayHeader("BUILD STATION");
            System.out.println("Remaining items to add:");
            
            if (remainingItems.isEmpty()) {
                System.out.println("  All items added!");
            } else {
                for (String item : remainingItems) {
                    System.out.println("  - " + item);
                }
            }
            
            System.out.println("----------------------------------");
            System.out.println("What will you add?");
            System.out.println(" 1. Beef Patty");
            System.out.println(" 2. Toppings (Lettuce, Cheese, etc.)");
            System.out.println(" 3. Sauces (Ketchup, Mustard, etc.)");
            System.out.println(" 4. I'M DONE WITH THIS BURGER");
            System.out.println("----------------------------------");
            System.out.print("> ");
            
            String choice = scanner.nextLine();
            String itemName = "";
            
            if (choice.equals("1")) {
                itemName = "Beef Patty";
            } else if (choice.equals("2")) {
                itemName = chooseTopping(scanner, player); 
            } else if (choice.equals("3")) {
                itemName = chooseSauce(scanner, player); 
            } else if (choice.equals("4")) {
                isBuilding = false;
                continue;
            } else {
                continue; 
            }
            
            if (itemName == null) continue; 
            
            Ingredient newItem;
            if (CustomerTicket.isPatty(itemName)) {
                newItem = new Patty(itemName);
            } else if (CustomerTicket.isTopping(itemName)) {
                newItem = new Topping(itemName);
            } else {
                newItem = new Sauce(itemName);
            }
            
            clearConsole();
            newItem.performMinigame(scanner, random, player);
            
            burger.addIngredient(newItem);
            remainingItems.remove(itemName);
            
            System.out.println();
            System.out.println(">>> You added a " + newItem.getQuality() + " " + newItem.getName() + "! <<<");
            pressEnterToContinue(scanner);
        }
        
        return burger;
    }
    
    public static String chooseTopping(Scanner scanner, Player player) {
        clearConsole();
        displayHeader("TOPPINGS MENU");
        System.out.println(" 1. Lettuce");
        System.out.println(" 2. Cheese");
        System.out.println(" 3. Tomatoes");
        System.out.println(" 4. Onions");
        System.out.println(" 5. Pickles");
        
        int option = 6;
        if (player.hasUnlockedExtras) {
            System.out.println(" 6. Bacon");
            System.out.println(" 7. Mushrooms");
            option = 8;
        }
        System.out.println(" " + option + ". [Back]");
        System.out.println("----------------------------------");
        System.out.print("> ");
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1": return "Lettuce";
            case "2": return "Cheese";
            case "3": return "Tomatoes";
            case "4": return "Onions";
            case "5": return "Pickles";
            default:
                if (player.hasUnlockedExtras) {
                    if (choice.equals("6")) return "Bacon";
                    if (choice.equals("7")) return "Mushrooms";
                }
                return null; 
        }
    }
    
    public static String chooseSauce(Scanner scanner, Player player) {
        clearConsole();
        displayHeader("SAUCES MENU");
        System.out.println(" 1. Ketchup");
        System.out.println(" 2. Mustard");
        System.out.println(" 3. Mayonnaise");
        System.out.println(" 4. Hot Sauce");
        
        int option = 5;
        if (player.hasUnlockedExtras) {
            System.out.println(" 5. BBQ");
            System.out.println(" 6. Ranch");
            option = 7;
        }
        System.out.println(" " + option + ". [Back]");
        System.out.println("----------------------------------");
        System.out.print("> ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1": return "Ketchup";
            case "2": return "Mustard";
            case "3": return "Mayonnaise";
            case "4": return "Hot Sauce";
            default:
                if (player.hasUnlockedExtras) {
                    if (choice.equals("5")) return "BBQ";
                    if (choice.equals("6")) return "Ranch";
                }
                return null; 
        }
    }
}
