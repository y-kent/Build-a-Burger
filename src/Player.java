import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<Double> allCustomerRatings;
    double[] dailyAverageRatings;
    
    boolean hasGrillUpgrade = false;
    boolean hasCleanCut = false; 
    boolean hasSmoothSauce = false; 
    
    boolean hasUnlockedExtras = false; 
    
    int perfectPatties = 0;
    int perfectToppings = 0;
    int perfectSauces = 0;
    int totalBurgers = 0;
    
    public Player(String name) {
        this.name = name;
        this.allCustomerRatings = new ArrayList<>();
        this.dailyAverageRatings = new double[7];
    }
    
    public void addRating(double rating) {
        this.allCustomerRatings.add(rating);
    }
    
    public void setDailyAverage(int dayIndex, double avg) {
        this.dailyAverageRatings[dayIndex] = avg;
    }
}
