public class Main {

    // tried various methods to compare performance
    // an array int[4] with mod(4) for computing the arithmetic sign has fared best
    
    // once generalized pentagonal numbers are generated, all left to do is summation, so, in order to avoid using BigInt,
    // we can employ mod(1000000) - once it hits 0, we break out
    public static void main(String[] args) {
        int target = 1000000;
        List<Integer> dp = new ArrayList<>();
        dp.add(1);
        List<Integer> gk = new ArrayList<>();
        int n = 0;
        int[] sign = {1,1,-1,-1};

        // partition function, Euler's pentagonal number theory
        // p(n) = BIG SUM(k != 0, k => INF)  ((-1)^(k+1))*p(n-g(k)) => g(k) = k*(3k-1)/2
        // p(n) = p(n-1) + p(n-2) - p(n-5)... where 1, 2, 5, 7, 11, 15... are generalized pentagonal numbers g(k)

        // using booleans for sign evaluation is slightly slower by a constant factor on every iteration
        while (true) {
//            boolean changeSign = false;
//            boolean plus = true;
            ++n;
            int k = (n + 1) / 2;
            if (n > gk.size()) {
                gk.add(k*(3*k-1)/2);
                gk.add(k*(3*k+1)/2);
            }
            int pn = 0;
            for (int i = 0; i < gk.size(); i++) {
                int g = gk.get(i);
                int m = n - g;
                if (m < 0) break;

//                int sign = (plus) ? 1 : -1;
                pn += sign[i % 4] * dp.get(m);

//                if (i % 4 < 2) {
//                    pn += dp.get(m);
//                }
//                else pn -= dp.get(m);

//                if (changeSign) {
//                    changeSign = false;
//                    plus = !plus;
//                }
//                else
//                    changeSign = true;
            }
            pn %= target;
            dp.add(pn);
            if (pn == 0) {
                System.out.println(n);
                break;
            }
        }
    }
}