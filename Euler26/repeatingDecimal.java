public class Main {

    public static void main(String[] args) {
        int range = 1000;
        System.out.println(longestRecurringCycle(range));

    }

    public static int longestRecurringCycle(int range) {
        int max = 0;
        int hasLongestCycle = 0;
        for (int i = 2; i < range; i++) {
            int cycle = unitFraction(i);
            if (cycle > max) {
                max = cycle;
                hasLongestCycle = i;
            }
        }
        return hasLongestCycle;
    }

    // returns recurring cycle length
    public static int unitFraction(int n) {
        String quotient = "0.";
        int divident = 1;
        boolean[] dividents = new boolean[n];
        boolean cyclic = false;

        while (divident != 0) {
            dividents[divident] = true;

            divident *= 10;
            int res = divident / n;
            quotient += res;
            divident -= (n * res);
            if (dividents[divident] == true) {
                cyclic = true;
                break;
            }

        }
        if (cyclic) {
            return quotient.length() - 2;
        }
        return 0;
    }
}