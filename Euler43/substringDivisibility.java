import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] divisors = {17, 13, 11, 7, 5, 3, 2};
	    long[] res = {0};

	    for (int a = 17; a < 1000; a += 17) {
            boolean[] availableDigits = new boolean[10];
            Arrays.fill(availableDigits, true);

	        if (!notAvailable(a, availableDigits)) continue;
	        getDigit(a, 1, divisors, availableDigits, res, a);
        }
        System.out.println(res[0]);
    }

    private static boolean notAvailable(int n, boolean[] arr) {
        if (n < 100) {
            if (!arr[0]) return false;
            arr[0] = false;
        }
        while (n > 0) {
            if (!arr[n % 10]) return false;
            arr[n % 10] = false;
            n /=10;
        }
        return true;
    }

    private static void getDigit(int n, int level, int[] divisors, boolean[] availableDigits, long[] res, long total) {
        if (level > 6) {
            int index = -1;
            while (!availableDigits[++index]) {
            }
            if (index == 0) return;
            long result = index;
            result *=  1000000000;
            res[0] += result + total;
            // System.out.println(result + total);
        } else {
            int ending = n / 10;
            for (int i = 0; i < 10; i++) {
                if (availableDigits[i]) {
                    int num = i * 100 + ending;
                    if (num % divisors[level] == 0) {
                        availableDigits[i] = false;
                        getDigit(num, level + 1, divisors, availableDigits, res, i * (int) Math.pow(10, 2 + level) + total);
                        availableDigits[i] = true;
                    }
                }
            }
        }
    }
}