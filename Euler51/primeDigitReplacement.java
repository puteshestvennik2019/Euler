// If we look at the family listed in the problem (56003, 56113, 56333, 56443, 56663, 56773, and 56993),
// we notice that the remaining would be members (56223, 56553, 56883) are divided by 3.
// We know this because sums of their digits % 3 = 0.
// Maximal numbers of primes by number of digits replaced is given below (note that they may not exist - this is only theoretical calculation):
// 1 : 7
// 2 : 7
// 3: 10 (every possible replacement permutation will be divided by 3, therefore the rest cannot be)
// 4: 7
// Because last digit is limited to 1, 3, 7, 9, we know that the number cannot be smaller than 4 digits.
// 4-dig numbers are trivial
// 5-dig numbers can have remaining numbers as follows: when 1 or 7 at the back, {0, 1, 3, 4, 6, 7, 9} in the middle/front
// when 3 or 9 at the back, {1, 2, 4, 5, 7, 8} in the middle/front
// middle/front digit can appear in 4 positions (minus 0 as the first dig) so total number of operations:
// T = 4 * 7 * 2 * 2 * 8
// Searching through 6-dig numbers would require 4 nested loops, so a different approach would need to be taken

public class Main {

    public static void main(String[] args) {

        boolean[] sieve = sieveOfEratosthenes(1000000);

        List<List<int[]>> bitmask = new ArrayList<>();

        // generate 'positions for digits which won't be replaced
        for (int i = 0; i < 3; i++) {
            bitmask.add(generate(3 + i, i));
        }

        // last digit can only be one of the four below
        int[] lastDig = {1,3,7,9}; // add back 1
        int res = 0;
        boolean brokenLoop = false;

        for (int last : lastDig) {
            // this will inspect numbers up to 1000000
            for (int i = 0; i < 100; i++) {
                if ((i + last) % 3 == 0) continue; // sum of 'replaced' digits % 3 = 0, so the remaining sum mustn't be devided by two without remaider

                int digits = getDigits(i);
                res = checkWithBitmask(i, bitmask.get(digits), sieve, last);
                if (res > 0) {
                    brokenLoop = true;
                    break;
                }
            }
            if (brokenLoop) break;
        }
        System.out.println(res);
    }

    public static int checkWithBitmask(int n, List<int[]> bitmask, boolean[] sieve, int last) {
        int repeatedDigits = 3;
        int digLength = bitmask.get(0).length + repeatedDigits;
        int generateNumber = 0;

        // initialize number with all '1'
        while (digLength-- > 0) {
            generateNumber = generateNumber * 10 + 1;
        }

        for (int[] mask : bitmask) {
            int number = 0;
            int num = n;

            // isolate digits and insert them in correct positions
            for (int i = 0; i < mask.length; i++) {
                int digPosition = (num % 10) * (int) Math.pow(10, mask[i]);
                num /= 10;
                number += digPosition;
            }

            // make room to insert rest of the number
            int unfilledNumber = generateNumber;

            for (int m: mask) {
                unfilledNumber -= Math.pow(10, m);
            }

            unfilledNumber *= 10; // make rook for last digit
            number *= 10;
            number += last;

            // this will be returned once successful
            int base = number;

            int counter = 0; // count failed attempts to find prime

            int loop = 10;
            boolean brokeLoop = false;

            while (loop-- > 0) {
                if (counter >= 3) {
                    brokeLoop = true;
                    break;
                }

                if (sieve[number/2]) counter++; // this modified sieve returns true if number is NOT prime (number/2 because we don't store even numbers)

                number += unfilledNumber;
            }
            if (!brokeLoop && counter < 3) return unfilledNumber + base; // check counter again in case it was incremented on last loop
        }
        return -1;
    }

    public static int getDigits(int i) {
        if (i == 0) return 0;
        int n = 0;
        while (i > 0) {
            n++;
            i /= 10;
        }
        return n;
    }

    // except for 2 (which is stored in 0th index), all primes are (!notPrime) at indexes i/2 ->
    // these are retrieved with prime = i * 2 + 1
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] notPrime = new boolean[n / 2];
        for (int p = 3; p * p <= n; p += 2) {
            for (int i = p * p; i <= n; i += 2 * p) {
                notPrime[i/2] = true;
            }
        }
        return notPrime;
    }

    public static List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        helper(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }

    private static void helper(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }
}