import java.util.ArrayList;

public class Burger {
    private ArrayList<Ingredient> ingredients;

    public Burger() {
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredientToAdd) {
        this.ingredients.add(ingredientToAdd);
    }

    public void displayBurger() {
        System.out.println();
        System.out.println("   //============================\\\\");
        System.out.println("  //       PLAYER'S BURGER      \\\\");
        System.out.println(" //==============================");
        System.out.println();
        System.out.println("   (Top Bun)");
        for (Ingredient item : this.ingredients) {
            System.out.println("     - " + item.getQuality() + " " + item.getName());
        }
        System.out.println("   (Bottom Bun)");
        System.out.println();
        System.out.println(" \\\\==============================//");
        System.out.println("  \\\\============================//");
        System.out.println();
    }
    
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }
}