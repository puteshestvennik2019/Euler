import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    
    // basically, Dijkstra's algorithm
    // about 15-20x faster than with regular queue 

    public static void main(String[] args) throws IOException {
        int[][] grid = new int[80][80];
        //int[][] grid = {{131,673,234,103,18},{201,96,342,965,150},{630,803,746,422,111},{537,699,497,121,956},{805,732,524,37,331}};

        BufferedReader br = new BufferedReader(new FileReader("p083_matrix.txt"));
        String line = br.readLine();
        int row = 0;
        while (line != null && line.length() > 0) {
            String[] arr = line.split(",");
            int col = 0;
            for (String s: arr) {
                grid[row][col++] = Integer.parseInt(s);
            }
            row++;
            line = br.readLine();
        }
        System.out.println(minimalPathSum(grid));
    }


    private static int minimalPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // initialize array to 'infinity'
        int[][] res = new int[m][n];
        for (int[] row: res) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // items in the queue: i, j, grid[i][j]
        PriorityQueue<Vertex> queue = new PriorityQueue<>(new CellComparator());
        queue.add(new Vertex(0,0,0));

        // poll item from queue and update value in res if necessary, add neighbours to queue
        while (!queue.isEmpty()) {
            Vertex cell = queue.poll();
            int i = cell.row;
            int j = cell.col;
            int val = cell.val + grid[i][j];
            if (val >= res[i][j]) continue;

            res[i][j] = val;

            if (i > 0) queue.add(new Vertex(i - 1, j, val));
            if (j > 0) queue.add(new Vertex(i, j - 1, val));
            if (i < m - 1) queue.add(new Vertex(i + 1, j, val));
            if (j < m - 1) queue.add(new Vertex(i, j + 1, val));
        }
        return res[m-1][n-1];
    }


}

class Vertex {
    int row;
    int col;
    int val;

    public Vertex(int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}

class CellComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        if (o1.val > o2.val) return 1;
        else return -1;
    }
}
