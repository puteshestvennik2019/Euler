    public static long sumPrimesInRange(int range) {
        List<Integer> primes = sieveOfEratosthenes(range);
        long sum = 0;
        
        for (int prime : primes) {
            sum += prime;
        }
        //System.out.println(sum);
        return sum;

    }

    public static List<Integer> sieveOfEratosthenes(int n) {
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