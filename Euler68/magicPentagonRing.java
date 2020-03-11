import java.util.ArrayList;
import java.util.List;

public class Main {

    // because all 5 paths' sums are equal, 5 divides the sum of all paths
    // because sum [1,10] % 5 = 0, digits in the inner circle sum up to a multiple of 5
    // moreover, looking for the largest 16-dig number with the first digit being the lowest value of outside node,
    // max of lowest values = 6, so all we need to do is find combinations of 6-10 on the outside and 1-5 on the inside
    // sums of individual lines = ((10*(10 + 1) / 2) + (5*(5 + 1) / 2)) / 5 = (55 + 15) / 5 = 14
    public static void main(String[] args) {

        long[] max = {0};

        List<int[]> outerPent = generateOuterNodes();

        for (int[] outer: outerPent) {
            int[][] res = new int[5][3];
            res[0][0] = 6;
            int i = 0;
            for (int d: outer) {
                res[++i][0] = d;
            }
            getMax(res, max, 0, 0);
        }
        System.out.println(max[0]);

    }

    // inner pentagon
    public static void getMax(int[][] res, long[] max, int bitmask, int i) {
        if (bitmask == (1 << 6) - 2) {
            res[4][2] = res[0][1];      // add value skipped 10 lines down
            long sol = getRes(res);
            max[0] = Math.max(max[0], sol);
            return;
        }

        for (int k = 1; k < 6; k++) {
            if ((bitmask & (1 << k)) != 0) continue;    // digit already used

            if (bitmask > 0) {          // don't access index -1
                if (res[i-1][0] + res[i-1][1] + k == 14) {
                    // add middle element in this line, which is also the last element in prev line
                    res[i][1] = k;
                    res[i - 1][2] = k;
                }
                else continue;
            }
            else
                res[i][1] = k;
            getMax(res, max, bitmask | (1 << k), i + 1);
//            res[i][1] = 0;
//            res[i][2] = 0;
        }
        return;
    }

    public static long getRes(int[][] res) {
        long sol = 0;

        for (int[] line: res) {
            for (int dig: line) {
                if (dig == 10) sol *= 10;
                sol *= 10;
                sol += dig;
            }
        }
        return sol;
    }

    public static List<int[]> generateOuterNodes() {
        List<int[]> res = new ArrayList<>();

        for (int i = 7; i < 11; i++) {
            for (int j = 7; j < 11; j++) {
                if (j == i) continue;
                for (int k = 7; k < 11; k++) {
                    if (k == i || k == j) continue;
                    for (int l = 7; l < 11; l++) {
                        if (l == i || l == j || l == k) continue;
                        res.add(new int[]{i,j,k,l});
                    }
                }
            }
        }
        return res;
    }
}