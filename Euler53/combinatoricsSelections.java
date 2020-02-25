public class Main {

    public static void main(String[] args) {
        int limit = 1000000;
        int m = 100;
        int[][] dp = new int[m + 1][m + 1];
        dp[0][0] = dp[0][1] = dp[1][0] = dp[1][1] = 1;

        int n = findFirstN(limit, dp);
        int k = findSmallestK(n, n / 2, limit, n, n / 2 - 1, dp);
        int res = getCombinations(n, k);

        while (n < m) {
            binominalCoefficient(n, k, ++n, k, dp);
            k = findSmallestK(n, k, limit, n, --k, dp);
            res += getCombinations(n, k);
        }
        System.out.println(res);
    }

    // because largest number of combinations falls for 2*n choose n, we are only interested in this binominal coefficient
    public static int findFirstN(int limit, int[][] dp) {
        int n = 1;
        
        while (dp[n][n/2] < limit) {
            binominalCoefficient(n, n / 2, ++n, n / 2, dp);
        }
        return n;
    }

    // returns smallest k which passes condition for the given n
    public static int findSmallestK(int prevN, int prevK, int limit, int n, int k, int[][] dp) {
        binominalCoefficient(n, prevK, n, k, dp);

        while (dp[n][k] >= limit)
            binominalCoefficient(n, k, n, --k, dp);

        return k+1;
    }

    // this method uses already calculated values and multiplies/divides them by integers generated by helper function 
    public static void binominalCoefficient(int prevN, int prevK, int n, int k, int[][] dp) {
        dp[n][k] = dp[prevN][prevK] * getFactorialDifference(prevN, n);

        if (prevK < k) dp[n][k] /= getFactorialDifference(prevK, k);
        else  dp[n][k] *= getFactorialDifference(prevK, k);

        dp[n][k] /= getFactorialDifference(n - k, prevN - prevK);;
    }
    
    // generates integer difference between two factorials, f.ex. 17! - 15! = 16x17
    public static int getFactorialDifference(int prev, int cur) {
        int max = Math.max(prev, cur);
        int min = Math.min(prev, cur);
        int res = 1;

        while (max > min) {
            res *= max--;
        }
        return res;
    }

    // returns number of sets falling between (n choose k) and (n choose n-k)
    // even n produces odd results
    public static int getCombinations(int n, int k) {
        int res = (n % 2 == 0) ? 1 : 2;
        return res + 2 * (n / 2 - k);
    }
}