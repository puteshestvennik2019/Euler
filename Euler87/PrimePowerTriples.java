public class Main {

    public static void main(String[] args) {
        int target = 50000000;
        System.out.println(findCountBF(target));
    }

    private static int findCountBF(int target) {
        List<Integer> primes = sieveOfEratosthenes((int) Math.sqrt(target));
        HashSet<Integer> results = new HashSet<>();
        int[] squares = new int[primes.size()];
        squares[0] = 4;
        int[] cubes = new int[primes.size()];
        for (int i = 0; i < primes.size(); i++) {
            int forth = primes.get(i);
            int forthPower = squares[i] * squares[i];
            if (forthPower >= target) break;
            for (int j = 0; j < primes.size(); j++) {
                // compute results in the first loop
                if (forth == 2) {
                    cubes[j] = squares[j] * primes.get(j);
                }
                int sum2 = cubes[j] + forthPower;
                if (sum2 >= target) break;
                for (int k = 0; k < primes.size(); k++) {
                    int second = primes.get(k);
                    // compute results in the first loop
                    if (j == 0) {
                        squares[k] = second * second;
                    }
                    int sum = sum2 + squares[k];
                    if (sum >= target) break;
                    // if sum not computed before
                    results.add(sum);
                }
            }
        }
        return results.size();
    }

    private static List<Integer> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}