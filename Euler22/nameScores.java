import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<String> names = addWords();
        sortNames(names);
        Long total = 0l;
        for (int i = 0; i < names.size(); i++) {
            int score = (i + 1) * countValue(names.get(i));
            total += score;
        }
        System.out.println(total);
    }

    public static ArrayList<String> addWords() throws IOException {
        ArrayList<String> names = new ArrayList<String>();

        BufferedReader reader = new BufferedReader(new FileReader("names.txt"));
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

    public static void sortNames(ArrayList<String> names) {
        Collections.sort(names);
    }

    public static int countValue(String name) {
        int val = 0;
        char[] nameArr = name.toCharArray();
        for (char c : nameArr) {
            val += (int) c - 'A' + 1;
        }
        return val;
    }
}
