public class Main {

    public static void main(String[] args) throws IOException {
        int[][] grid = new int[80][80];

        BufferedReader br = new BufferedReader(new FileReader("p082_matrix.txt"));
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

    // because we can move in three directions, we need to traverse the height of the grid up and down
    // on first iteration down, values are updated and min found between current row and the min above it
    // on iteration down, we only check if we have a better value in cells below current
    private static int minimalPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = grid[i][0];
        }

        for (int col = 1; col < m; col++) {
            res[0] += grid[0][col];
            for (int row = 1; row < n; row++) {
                res[row] = grid[row][col] + Math.min(res[row - 1], res[row]);
            }
            res[n - 1] = Math.min(res[n - 1], res[n - 2] + grid[n - 1][col]);
            for (int row = n - 2; row > 0; row--) {
                res[row] = Math.min(res[row], res[row + 1] + grid[row][col]);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i: res) {
            min = Math.min(i, min);
        }
        return min;
    }
}