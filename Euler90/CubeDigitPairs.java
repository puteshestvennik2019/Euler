public class Main {

    public static void main(String[] args) {
        int n = 10;
        int k = 6;
	    List<int[]> combinations = generateCombinations(n, k);
	    int l = combinations.size();
	    int ans = 0;
	    for (int i = 0; i < l - 1; i++) {
	        int[] arr1 = combinations.get(i);
	        // combinations came in in a sorted order
            // because second loop starts iterations from i + 1, once first loop has gone past combinations including 0,
            // there will be no more results so we can break out
	        if (arr1[0] > 0) break;
            for (int j = i + 1; j < l; j++) {
                if (canRepresentSquares(arr1, combinations.get(j))) ans++;
            }
        }
        System.out.println(ans);
    }

    public static List<int[]> generateCombinations(int n, int k) {
        List<int[]> res = new ArrayList<>();
        int[] comb = new int[k];
        for (int i = 0; i < k; i++) comb[i] = i;
        while (true) {
            res.add(Arrays.copyOfRange(comb, 0, k));
            int index = k - 1;
            while (index > 0 && comb[index] >= n - k + index) index--;
            comb[index]++; // increase last element which is not at its max
            if (comb[0] > n - k) break;

            // reset numbers after the one just increase
            for (int i = index + 1; i < k; i++) {
                comb[i] = comb[i-1] + 1;
            }
        }
        return res;
    }

    public static boolean canRepresentSquares(int[] c1, int[] c2) {
        int[][] squares = {{0,1},{0,4},{2,5},{8,1}};
        int[][] upDown = {{4,9,6},{0,9,6},{1,6,9},{3,6,9},{4,6,9}};
        for (int[] sq: squares) {
            if ((contains(c1, sq[0]) && contains(c2, sq[1])) || (contains(c2, sq[0]) && contains(c1, sq[1]))) continue;
            else return false;
        }
        for (int[] sq: upDown) {
            if ((contains(c1, sq[0]) && (contains(c2, sq[1]) || contains(c2, sq[2])))
                    || (contains(c2, sq[0]) && (contains(c1, sq[1]) || contains(c1, sq[2])))) continue;
            else return false;
        }
        return true;
    }

    private static boolean contains(int[] arr, int num) {
        for (int n: arr) {
            if (n == num) return true;
        }
        return false;
    }
}