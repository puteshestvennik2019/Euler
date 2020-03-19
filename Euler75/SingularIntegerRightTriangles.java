// We will use Euclid's formula for generating primitive triples. To do that, we can use the following facts:
// - a, b, c need to be coprime in order to make up a primitive prime
// - Euclid's formula's m and n also need to be coprime and NOT both odd
// - coprime pairs can be generated using reduced fractions
// - because m > n and c < (c + b + a) / 2, we only need fractions up to sqrt(1500000 / 2) => [2, 866]

import java.util.ArrayList;
import java.util.List;

public class SingularIntegerRightTriangles {
    private final int limit;
    private final int maxM;
    private List<int[]> mAndN;
    private int[] triplesSum;
    private int countSingularTriples;

    public SingularIntegerRightTriangles(int limit) {
        this.limit = limit;
        this.maxM = (int) Math.sqrt(limit / 2);
        this.triplesSum = new int[limit + 1];
        this.mAndN = generateMN();

        System.out.println(solve());
    }

    // generates all fractions, later on we have to discard fractions whose both m and n are odd
    // a/b < c/b => next term p/q => p = floor((n + b) / d)) * c - a,  q = floor((n + b) / d)) * d - b
    private List<int[]> generateMN() {
        List<int[]> fractions = new ArrayList<>();
        int a = 1;
        int b = maxM;
        int c = 1;
        int d = maxM - 1;

        fractions.add(new int[] {a, b});
        fractions.add(new int[] {c, d});


        while (true) {
            if (a == maxM - 1 && b == maxM) break;
            int k = (maxM + b) / d;
            int p = k * c - a;
            int q = k * d - b;

            fractions.add(new int[] {p, q});
            a = c;
            b = d;
            c = p;
            d = q;
        }

        return fractions;
    }

    private int solve() {
        List<Integer> sums = generatePrimitiveTriples();
        countSingularTriples = sieveSumsArray(sums);
        return countSingularTriples;
    }

    private List<Integer> generatePrimitiveTriples() {
        List<Integer> sums = new ArrayList<>();
        for (int[] f: mAndN) {
            if ((f[0] & 1) == 1 && (f[1] & 1) == 1) continue;   // both numbers are odd
            int m = f[1];
            int n = f[0];

            int c = m * m + n * n;
            int b = 2 * m * n;
            int a = m * m - n * n;

            int sum = c + b + a;

            if (sum <= limit)
                sums.add(sum);
        }
        return sums;
    }

    private int sieveSumsArray(List<Integer> sums) {
        int cnt = 0;
        for (int sum: sums) {
            int num = sum;
            while (num <= limit) {
                triplesSum[num]++;
                if (triplesSum[num] == 2) cnt--;     // this sum has more than one triple
                else if (triplesSum[num] == 1) {
                    cnt++;
                }
                num += sum;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        SingularIntegerRightTriangles solution = new SingularIntegerRightTriangles(1500000);
    }
}