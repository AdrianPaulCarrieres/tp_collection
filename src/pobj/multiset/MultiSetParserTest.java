package pobj.multiset;

import pobj.multiset.MultiSetParser.InvalidMultiSetFormat;

public class MultiSetParserTest {
    public static void main(String[] args){
        try{
            MultiSet<String> ms = MultiSetParser.parse("./data/toParse.txt");
            System.out.println(ms);
        }
        catch(InvalidMultiSetFormat e){
            System.out.println(e);
        }
    }
}
