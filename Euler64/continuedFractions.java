public class Main {

    // I could have reused one of the methods, but decided to write another one based on Wiki-sourced algorithm
    public static void main(String[] args) {
        int limit = 10000;
        int root = 1;
        int nextSqr = 4;
        int incr = 5;

        for (int i = 2; i <= limit; i++) {
            if (i == nextSqr) {
                root++;
                nextSqr += incr;
                incr += 2;
                continue;
            }
            // System.out.println("Periodic continued fraction for sqrt(" + i + ") = [" + root + ";" + printContinuedFraction(i, root) + "]");
        }
        System.out.println(countOddPeriods(limit));
    }

    public static int countOddPeriods(int limit) {
        int res = 0;

        int root = 1;
        int nextSqr = 4;
        int incr = 5;

        for (int i = 2; i < limit; i++) {
            if (i == nextSqr) {
                root++;
                nextSqr += incr;
                incr += 2;
                continue;
            }
            int cnt = 0;
            int d = 1;
            int m = 0;
            int a = root;

            int cond = root << 1;

            while (a != cond) {
                m = d * a - m;
                d = (i - m * m) / d;
                a = (root + m) / d;
                cnt++;
            }
            if ((cnt & 1) == 1) res++;
        }
        return res;
    }

    public static String printContinuedFraction(int sqr, int root) {
        int denom = root;
        int nom = 1;
        String ans = "";

        while (true) {
            int tmp = denom;
            denom = (sqr - denom * denom) / nom;
            nom = tmp;
            int res = (root + nom) / denom;
            tmp = res * denom - nom;
            ans += res;

            if (denom == 1) break;
            else ans += ',';

            nom = denom;
            denom = tmp;
        }
        return ans;
    }
}