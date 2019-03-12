
public class VaalikoneMarkov {
    
    public static void main(String[] args) { 
        
        MarkovBuilder vaalikone = new MarkovBuilder();
        vaalikone.markovBuilder(1, 10, "vastaukset.csv", "Kokoomus");
    }
}
