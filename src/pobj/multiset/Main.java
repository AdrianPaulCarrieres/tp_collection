package pobj.multiset;

public class Main {
    public static void main(String[] args){
        WordCount wc = new WordCount();

        wc.readFile("./data/WarAndPeace.txt");
        wc.getHighest();
    }
}