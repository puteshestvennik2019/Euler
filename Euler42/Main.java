// Contains some functions I used for another project
// This may not be the most efficient data strcture for such a small word base, but finally I managed to get my trie working

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        long st = System.nanoTime();
        Trie words = new Trie();
        insertToTrie(addWords(), words);
        long end = System.nanoTime();
        System.out.println(end-st);

        System.out.println(words.getTriangleCounter());
    }

    public static void insertToTrie(ArrayList<String> names, Trie trie) {
        for (String name : names) {
            trie.insert(name);
        }
        System.out.println("inserted names");
    }

    public static ArrayList<String> addWords() throws IOException {
        ArrayList<String> names = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
        String line = "";

        while ((line = reader.readLine()) != null) {
            String[] dataNames = line.split(",");

            for (String item : dataNames) {
                String newItem = item.substring(1, item.length() - 1);
                names.add(newItem);
            }
        }
        reader.close();
        return names;
    }
}
