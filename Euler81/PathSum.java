import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("p081_matrix.txt"));
        String line = "";
        List<int[]> grid = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(",");
            int[] row = new int[arr.length];
            int i = 0;

            for (String n: arr) {
                row[i++] = Integer.parseInt(n);
            }
            grid.add(row);
        }
        int[] row = grid.get(0);
        for (int i = 1; i < row.length; i++) {
            row[i] += row[i - 1];
        }
        for (int i = 1; i < grid.size(); i++) {
            grid.get(i)[0] += grid.get(i - 1)[0];
        }

        for (int i = 1; i < grid.size(); i++) {
            row = grid.get(i);
            for (int j = 1; j < row.length; j++) {
                int min = Math.min(row[j - 1], grid.get(i - 1)[j]);
                row[j] += min;
            }
        }
        System.out.println(grid.get(grid.size() - 1)[grid.get(0).length - 1]);
    }
}