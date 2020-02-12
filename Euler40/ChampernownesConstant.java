public class Main {

    public static void main(String[] args) {
        int[] indices = {1,10,100,1000,10000,100000,1000000};
        // char[] res = champerIndexBF(indices);
        int solution = 1;

        for (int i : indices) solution *= findDigit(i, 1, 0);
        System.out.println(solution);
    }

    public static int findDigit(int num, int digits, int total) {
        int maxPos = 9 * digits * (int) Math.pow(10, digits - 1) + total;
        if (num <= maxPos) {
            int rem = (num - total) % digits;
            int number = (num - total) / digits + ((digits > 1) ? (int) Math.pow(10, digits - 1) : 0); // "padding" for numbers with more than 1 digit
            while (rem++ % digits != 0) {
                number /= 10;
            }
            return number % 10;
        } else {
            return findDigit(num, digits + 1, maxPos);
        }
    }

    public static char[] champerIndexBF(int[] indices) {
        String str = "0";
        int l = indices.length;
        char[] res = new char[l];

        int i = 1;

        while (str.length() <= indices[l-1]) {
            str += i++;
        }

        int cnt = 0;
        for (int ind : indices) {
            res[cnt++] = str.charAt(ind);
        }
        return res;
    }
}