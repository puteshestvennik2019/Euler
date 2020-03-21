public class Main {

    // Perhaps there is a way to justify the correct value of target below, but I have used it arbitrarily
    // another option would be adding primes dynamically, as we need them
    public static void main(String[] args) {
        int target = 100;
        boolean[] primes = sieve(target);
        int[] dp = new int[target + 1];
        dp[0] = 1;

        int min = Integer.MAX_VALUE;

        // dynamic programming solution, knapsack problem
        for (int i = 2; i < target; i++) {
            if (!primes[i]) continue;
            for (int j = i; j <= target; j++) {
                dp[j] += dp[j - i];
                // values in the table include the number itself (summation with 0), but we are only interested in summations of at least two primes
                if (dp[j] > 5001) {
                    min = Math.min(min, j);
                    break;
                }
            }
        }
        System.out.println(min);
    }

    private static boolean[] sieve(int limit) {
        boolean[] primes = new boolean[limit + 1];
        primes[2] = true;
        for (int i = 3; i <= limit; i += 2) {
            primes[i] = true;
        }
        for (int i = 3; i * i <= limit; i += 2) {
            for (int j = i * 2; j <= limit; j += i) {
                primes[j] = false;
            }
        }
        return primes;
    }
}