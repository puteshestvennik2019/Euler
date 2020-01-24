public class Main {

    public static void main(String[] args) {
        String factorial = factorial(75);
        int result = digitSum(factorial);
        System.out.println(result);
    }

    public static int digitSum(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            int dig = Integer.parseInt(String.valueOf(str.charAt(i)));
            res += dig;
        }
        return res;
    }


    public static String factorial(int range) {
        Long maxLong = Long.MAX_VALUE;
        Long prod = 1l;
        Long factor = 2l;

        while (prod * factor > 0) {
            prod *= factor;
            factor++;
            if (factor > range) break;
        }
        String res = Long.toString(prod);

        while (factor <=range) {
            String f = factor.toString();
            String[] toSum = multiplyStrings(res, f);
            res = stringAddition(toSum);
            factor++;
        }

        while (res.charAt(0) == '0') {
            res = res.substring(1, res.length());
        }

        return res;
    }


    public static String[] multiplyStrings(String factorOne, String factorTwo) {
        int lenOne = factorOne.length();
        int lenTwo = factorTwo.length();
        // array to store products of multiplication of factorOne by individual digits of factorTwo
        String[] toSum = new String[lenTwo];
        int carry;

        for (int i = lenTwo - 1; i >= 0; i--) {
            int dig = Integer.parseInt(String.valueOf(factorTwo.charAt(i)));
            carry = 0;
            String sum = "";
            int pad = lenTwo - 1 - i;

            // pad zeros
            while (pad > 0) {
                sum += "0";
                pad--;
            }

            for (int j = lenOne - 1; j >= 0; j--) {
                int d = Integer.parseInt(String.valueOf(factorOne.charAt(j)));
                int prod = d * dig + carry;
                int ch = prod % 10;
                sum = ch + sum;
                carry = prod / 10;
            }
            while (carry > 0) {
                sum = (carry % 10) + sum;
                carry /= 10;
            }

            // pad zeros in the beginning
            pad = lenOne + lenTwo - sum.length();
            while (pad > 0) {
                sum = "0" + sum;
                pad--;
            }
            toSum[i] = sum;
        }
        return toSum;
    }


    public static String stringAddition(String[] arr) {
        int lenAr = arr.length;
        int lenStr = arr[0].length(); // same length of all numbers/strings is assumed
        String res = "";
        long carry = 0;

        for (int i = lenStr - 1; i >= 0; i--) {

            long temp = 0;
            long ch;
            for (int numb = 0; numb < lenAr; numb++) {
                long num = Long.parseLong(String.valueOf(arr[numb].charAt(i)));
                temp += num;
            }
            temp += carry;
            ch = temp % 10;
            carry = temp / 10;
            res = ch + res;
            if (i == 0) {
                while (temp > 9) {
                    temp /= 10;
                    ch = temp % 10;
                    res += ch;
                }
            }
        }
        return res;
    }
}