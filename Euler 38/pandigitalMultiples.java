public class Main {

    public static void main(String[] args) {
        int max = 0;
        for (int i = 2; i < 10; i++) {
            int[] startEnd = getMaxNumberForSet(i);
            for (int num = startEnd[0]; num <= startEnd[1]; num++) {

                if (num % 5 == 0) continue; // number divided by 5 would produce two '5' or a '0' in the result
                int[] count = new int[10];
                boolean duplicate = false;
                String res = "";

                for (int mult = 1; mult <= i; mult++) {
                    int prod = mult * num;
                    res += prod;

                    // count frequency of digits, break when 0 or duplicate
                    while (prod > 0) {
                        int ind = prod % 10;
                        count[ind]++;
                        prod /= 10;
                        if (count[ind] > 1 || ind == 0) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (duplicate) break;
                }
                if (!duplicate) {
                    int resToInt = Integer.parseInt(res);
                    max = Math.max(max, resToInt);
                }
            }
        }
        System.out.println(max);

    }



    // n - upper bound in a set of integers, n > 1
    public static int[] getMaxNumberForSet(int n) {
        int totalDigits = 9; //
        int digitsPerFactor = totalDigits / n;
        int extraDigits = totalDigits - n * digitsPerFactor;
        int targetDigitsInLastProduct = digitsPerFactor + (extraDigits > 0 ? 1 : 0);
        int[] range = new int[2];
        int multiplier = (int) Math.pow(10, digitsPerFactor);
        // the below limits are imposed by the multiplication by 1
        range[0] = (int) (0.123456789 * multiplier);
        range[1] = (int) (0.987654321 * multiplier);
        if (targetDigitsInLastProduct > digitsPerFactor) {

            // get min number for range and adjust max
            range[0] = range[0] * 10 / (n - extraDigits + 1); // extraDigits + 1, because the very if condition "adds" one digit to the last product
            range[1] /= n - totalDigits % n;
        }
        else range[1] /= n;
        return range;
    }
}