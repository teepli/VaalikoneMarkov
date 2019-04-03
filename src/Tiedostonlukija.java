import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Tiedostonlukija {
    

    public static ArrayList<String> lueTiedosto (String puolue) {
        ArrayList<String> sanat = new ArrayList<>();       
        String line = "";
        
        try {
            BufferedReader tiedostonLukija = new BufferedReader(new FileReader("vastaukset.csv")); 
           
        while ((line = tiedostonLukija.readLine()) != null) {                                 
            line = cleanSentence(line);
            String[] osat = line.split(";");
            
            if (osat[4].contains(puolue)) {
                for (int i = 11; i < osat.length; i++) {
                    String osa[] = osat[i].split(" ");
                    if (osa.length > 0)
                        sanat.addAll(Arrays.asList(osa));
                }                 
            }
        }
        }   catch (Exception e) {   
                System.out.println("Virhe: " + e.getMessage());
        }
        return sanat;
    }

    public static String cleanSentence(String sentence) {
        sentence = sentence.replace("samaa mieltä", "").replace("eri mieltä", "").
                replace("jokseenkin", "").replace("," ,"").replace(".", "").trim();

        return sentence;
    }
}
