public class Main {

    public static void main(String[] args) {
        int target = 100;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        // partition function, Euler's pentagonal number theory
        // p(n) = BIG SUM(k != 0, k => INF)  ((-1)^(k+1))*p(n-g(k)) => g(k) = k*(3k-1)/2
        // p(n) = p(n-1) + p(n-2) - p(n-5)... where 1, 2, 5, 7, 11, 15... are generalized pentagonal numbers g(k)
        for (int i = 1; i <= target; i++) {
            int n = i;
            int k = 1;
            int g = 0;
            while (true) {
                g = k * (3 * k - 1) / 2;
                // for (int g: gk), if g is computed beforehand
                if (n - g < 0) break;
                dp[i] += (int) Math.pow(-1, k + 1) * dp[n - g];
                k *= -1;
                if (k > 0) k++;
            }
        }
        System.out.println(dp[target]-1);

        // dynamic programming solution, knapsack problem
	    for (int i = 1; i < target; i++) {
	        for (int j = i; j <= target; j++) {
	            dp[j] += dp[j - i];
            }
        }
        System.out.println(dp[target]);
    }
}