import java.util.ArrayList;

public class Main {

    // using factorials, we can deduct the order in which digits appear in k-th lexicographic permutation
    // for example 9!-th permutation is 0987654321 and 362881-st will be 1023456789
    public static void main(String[] args) {
        int n = 10;
        int nthPerm = 10;
        int remainingPerms = nthPerm;
        ArrayList<Integer> digits = populateDigits(n);
        long[] factorials = factorials(n);
        String res = "";
        for (int i = n - 1; i > 0; i--) {
            int kth = 0;
            if (factorials[i] < remainingPerms) {
                while (remainingPerms > factorials[i]) {
                    remainingPerms -= factorials[i];
                    kth++;
                }
            }
                res += digits.get(kth);
                digits.remove(kth);
        }
        while (digits.size() > 0) {
            res += digits.get(0);
            digits.remove(0);
        }
        System.out.println(res);

    }

    public static ArrayList<Integer> populateDigits(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(i);
        }
        return res;
    }

    public static long[] factorials(int range) {
        long[] res = new long[range + 1];
        res[0] = 0;
        long p = 1;
        for (int i = 1; i < range; i++) {
            p *= i;
            res[i] = p;
        }
        return res;
    }
}