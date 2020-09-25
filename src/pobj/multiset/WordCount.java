package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WordCount {
    private HashMultiSet<String> ms;

    private static final int NB_A_AFFICHER = 10;

    Logger logger = Logger.getLogger(WordCount.class.getName());

    LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

    public WordCount() {
        ms = new HashMultiSet<>();
    }

    public void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {
            String line;
            while((line = br.readLine())!=null){
                for(String word : line.split("\\P{L}+")){
                    if(word.equals("")) continue;
                    ms.add(word);
                }
            }
            System.out.println(ms);
            logger.info("Executed");
        } catch (Exception e) {
            logger.severe("Erreur");
        }
    }

    private void sort(){
        logger.info("Waiting to sort");

        /*
        * get the private hashmap from our multi set
        * get an entry set (word, occurence count)
        * we transform it into a stream, which is able to iterate over itself, kinda functionnal
        * sorted() sorts the elements by their natural order or using a comparator we're providing
        * here, we compare our entries by their values, but reversed to get the highest values first
        * then, because of the stream listeners approach, we're able, at each finished sort, to apply a function to the element (again, very functionnal)
        * Here, we put the sorted element into an empty linked map to keep its sort order 
        */

        ms.getMap().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        logger.info("Executed");
    }

    public void getHighest(){
        logger.info("Getting the highest counted words");
        sort();
        int compteur = 0;
        StringBuilder strb = new StringBuilder();
        for(Map.Entry<String, Integer> entry : reverseSortedMap.entrySet()){
            if(compteur++ < NB_A_AFFICHER){
                strb.append(compteur);
                strb.append(" : ");
                strb.append(entry.getKey());
                strb.append("\n");
            }
        }
        System.out.println(strb.toString());
        logger.info("Executed");
    }
}