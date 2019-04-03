
public class VaalikoneMarkov {
    
    public static void main(String[] args) { 
        
        MarkovBuilder vaalikone = new MarkovBuilder();
        vaalikone.markovBuilder(1, 20, "vastaukset.csv", "Perussuomalaiset");
        vaalikone.markovBuilder(1, 20, "vastaukset.csv", "Kokoomus");
        vaalikone.markovBuilder(1, 20, "vastaukset.csv", "Keskusta");
        vaalikone.markovBuilder(1, 20, "vastaukset.csv", "Sosialidemokraattinen");
        vaalikone.markovBuilder(1, 20, "vastaukset.csv", "Kristillis");

    }
}
