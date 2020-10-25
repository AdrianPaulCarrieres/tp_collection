package pobj.multiset;

import java.io.*;

public class MultiSetParser {
    public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
            HashMultiSet<String> m = new HashMultiSet<>();
            String line;
            while((line = br.readLine())!=null){
                String[] parts = line.split(":");
                if(parts.length != 2){
                    throw new InvalidMultiSetFormat("Une ligne 0 ou plus d'un ':'");
                }
                m.add(parts[0], Integer.parseInt(parts[1]));
            }
            return m;
        }
        catch(FileNotFoundException e){
            throw new InvalidMultiSetFormat("Fichier fourni non trouvé", e);
        }
        catch(IOException e){
            throw new InvalidMultiSetFormat("Erreur d'entrée/sortie", e);
        }
        catch(IllegalArgumentException e){
            throw new InvalidMultiSetFormat("Une des valeurs est négative", e);
        }
    }

    static class InvalidMultiSetFormat extends Exception{
        public InvalidMultiSetFormat(String message) {
            super(message);
        }
    
        public InvalidMultiSetFormat(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
}
