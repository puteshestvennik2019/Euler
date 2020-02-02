public class Main {

    public static void main(String[] args) {
        int n = 1000000;
        int digitCounter = 10;
        int count = 0;
        boolean[] primes = sieveOfEratosthenes(n);
        for (int i = 2; i <= n; i++) {
            boolean isCircular = true;
            if (i >= digitCounter) digitCounter *= 10;
            if (primes[i]) {
                int prime = i;
                int rotations = digitCounter / 10;
                while (rotations > 1) {
                    int reminder = prime % 10;
                    prime /= 10;
                    prime += reminder * digitCounter / 10;
                    rotations /= 10;
                    if (!primes[prime]) isCircular = false;
                }
                if (isCircular) count++;
            }
        }
        System.out.println(count);
    }

    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    primes[i] = false;
                }
            }
        }
        return primes;
    }
}