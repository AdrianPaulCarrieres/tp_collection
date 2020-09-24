package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    private HashMultiSet<String> ms;

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
        } catch (Exception e) {
            System
        }
        
        
    }
}