import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Arrays;
import java.lang.StringBuilder;

public class MarkovBuilder {
    private Random r = new Random();
    
    public void markovBuilder(int keySize, int outputSize, String filePath, String puolue) {
        if (keySize < 1) throw new IllegalArgumentException("Key size have to be more than 1");
        
        // Reads csv file and makes new ArrayList
        ArrayList<String> filteredWords = Tiedostonlukija.lueTiedosto(puolue);       
        // TODO: Clean up words (Links, basic statements)
        
        String[] words = (String[]) filteredWords.toArray(new String[0]);
        if (outputSize < keySize || outputSize >= words.length) {
            throw new IllegalArgumentException("Too short source material");
        }
        
        // Builds new HashMap, where key is words equal to keySize, and values
        // are words next in sentences. At the moment only keySize 1 works with 
        // this data.
        keySize = 1;
        Map<String, List<String>> dict = new HashMap<>();
        for (int i = 0; i < (words.length - keySize); ++i) {
            StringBuilder key = new StringBuilder(words[i]);
            for (int j = i + 1; j < i + keySize; ++j) {
                key.append(' ').append(words[j]);
            }
            String value = (i + keySize < words.length) ? words[i + keySize] : "";
            if (!dict.containsKey(key.toString())) {
                ArrayList<String> list = new ArrayList<>();
                list.add(value);
                dict.put(key.toString(), list);
            } else {
                dict.get(key.toString()).add(value);
            }
        }
        
        // Generates new sentence from HashMap, length is outputSize
        int random = r.nextInt(dict.size());       
        String prefix = (String) dict.keySet().toArray()[random];
        List<String> markovSentence = new ArrayList<>(Arrays.asList(prefix.split(" ")));
        
        for (int i = 1; i < outputSize; i++) {                               
            List<String> suffix = dict.get(prefix);         
            if (suffix.isEmpty()) throw new IllegalArgumentException("Suffix is empty");
            
            if (suffix.size() == 1) {             
                markovSentence.add(suffix.get(0));
            } else   {
                random = r.nextInt(suffix.size());              
                markovSentence.add(suffix.get(random));
            }
            prefix = markovSentence.stream().skip(i).reduce("", (a, b) -> a + " " + b).trim();

        
        }
      // String lause = markovSentence.stream().reduce("", (a, b) -> a + " " + b)
      //          .replace(",", "").trim();

      //  String aa = lause.substring(0,1).toUpperCase() + lause.substring(1);

        System.out.println(markovSentence.stream().reduce("", (a, b) -> a + " " + b).replace(",", "").trim() + ".");
    }
}

