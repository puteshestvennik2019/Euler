import java.util.ArrayList;

// Slightly faster approach would be to generate only the numbers we need, instead of storing 10000 ArrayLists
// However, even if we used BigInteger, to calculate 99^99, we would need to make the same number of calculations
// solution with BigInteger takes about 100ms, whereas this one only about 10ms, once the table has been generated.

// numbers are stored in reversed order to facilitate the use of ArrayList
// once n x n matrix has been generated, we iterate the largest products, updating max digit sum until no larger sum is obtainable
// f.ex. if current max is 1000 and only numbers with < 111 digits are left, we have found the max sum
public class Main {

    public static void main(String[] args) {

        long st = System.nanoTime();

        int n = 100;
        ArrayList<Integer>[][] aPowB = new ArrayList[n][n];

        for (int base = 0; base < n; base++) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(1);
            aPowB[base][0] = res;
            ArrayList<Integer> number = getNumberArrayList(base);

            for (int exp = 1; exp < n; exp++) {
                aPowB[base][exp] = multiply(aPowB[base][exp - 1], number);
            }
        }

        int maxDigitSum = 0;

        for (int base = n - 1; base >= 0; base--) {
            boolean terminate = false;

            for (int exp = n - 1; exp >= 0; exp--) {

                // break out if current number has fewer digits than needed to generate a new max
                int numberOfDigits = aPowB[base][exp].size();
                if (numberOfDigits < getMinArrLength(maxDigitSum)) {

                    // if this loop has not made a single iteration, break out of the outer loop
                    if (exp == n - 1) terminate = true;
                    
                    break;
                }

                int sum = sumDigits(aPowB[base][exp]);
                maxDigitSum = Math.max(maxDigitSum, sum);
            }
            if (terminate) break;
        }
        System.out.println(System.nanoTime() - st);
        System.out.println(maxDigitSum);
    }

    public static ArrayList<Integer> multiply(ArrayList<Integer> base, ArrayList<Integer> multiplicator) {
        int n = base.size();
        int m = multiplicator.size();
        ArrayList<Integer> res = new ArrayList<>();
        res.add(0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int prod = base.get(i) * multiplicator.get(j);
                int sum = prod + res.get(i + j);
                res.set(i + j, sum % 10);
                if (i + j + 1 >= res.size()) res.add(sum / 10);
                else {
                    int carry = sum / 10 + res.get(i + j + 1);
                    res.set(i + j + 1, carry);
                }
            }
        }
        if (res.get(res.size() - 1) == 0) res.remove(res.size() - 1);
        return res;
    }

    public static int sumDigits(ArrayList<Integer> number) {
        int sum = 0;
        for (int n : number) sum += n;

        return sum;
    }

    // returns reversed representation of number
    public static ArrayList<Integer> getNumberArrayList(int n) {
        ArrayList<Integer> number = new ArrayList<>();
        while (n > 0) {
            number.add(n % 10);
            n /= 10;
        }
        return number;
    }

    // returns min number of digits needed to find max greater than current
    public static int getMinArrLength(int number) {
        return number / 9 + (number % 9 == 0 ? 0 : 1);
    }
}