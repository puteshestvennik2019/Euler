 // find largest product of 'elems' adjacent cells (diagL/R, horiz and vert) in a matrix m x n
    public static int findGreatestProdInMatrix(int m, int n, int[][] arr, int elems) {
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int col = arr[i][j];
                int row = arr[i][j];
                int diagR = arr[i][j];
                int diagL = arr[i][j];
                
                if (j <= n - elems) {
                    for (int d = 1; d < elems; d++){
                        row *= arr[i][j+d];
                    }
                    max = Math.max(row, max);
                }
                if (i <= m - elems) {
                    for (int d = 1; d < elems; d++){
                        col *= arr[i+d][j];
                    }
                    max = Math.max(max, col);
                }
                // diagonal from left to right
                if ((i <= m - elems) && (j <= n - elems)) {
                    for (int d = 1; d < elems; d++) {
                        diagR *= arr[i + d][j + d];
                    }
                }
                // diagonal from right to left
                if ((j >= elems - 1) && (i <=  m - elems)) {
                    for (int d = 1; d < elems; d++) {
                        diagL *= arr[i + d][j - d];
                    }
                }
                max = Math.max(Math.max(diagL, diagR), max);
            }
        }
        return max;
    }