// for the purpose of this challenge, I will hardcode some calculations, ie how to get from 2^62 to 2^1000
// individual methods could be established that would create smaller intervals/factors for dynamic calculations
// any exponent can be obtained recursively in combination with multiplication by 2 (raising power by 1)
// I will indeed improve this code when time allows ;-)
public class Main {

    public static void main(String[] args) {

        String result = multiplyPowersOfTwo();
        int digitSum = digitSum(result);
        System.out.println(digitSum);
    }

    public static int digitSum(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            int dig = Integer.parseInt(String.valueOf(str.charAt(i)));
            res += dig;
        }
        return res;
    }

    public static String multiplyPowersOfTwo() {
        long limit = (long) Math.pow(2, 62);
        // to reach the power of 1000 we need 5 operations:
        // (((((62 + 1) + 62) + 125) + 250) + 500)
        // this could be done with less coding but more processing via 62+62+128+246+496 and finishing with 8 multiplications by 2

        String sixtyThree = "";
        String sixtyTwo = "" + limit;
        int carry = 0;
        while (limit > 0) {
            int toStr = (int) ((limit % 10) * 2) + carry;
            limit /= 10;
            sixtyThree = (toStr % 10) + sixtyThree;
            carry = toStr / 10;
        }
        if (carry > 0) {
            sixtyThree = carry + sixtyThree;
        }

        // Let's initialize two new generic variables
        String factorOne = sixtyThree;
        String factorTwo = sixtyTwo;

        for (int multiplications = 0; multiplications < 4; multiplications++) {
            int lenOne = factorOne.length();
            int lenTwo = factorTwo.length();
            // array to store products of multiplication of factorOne by individual digits of factorTwo
            String[] toSum = new String[lenTwo];

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
            factorOne = stringAddition(toSum);
            if (factorOne.charAt(0) == '0') {
                factorOne = factorOne.substring(1, factorOne.length());
            }
            factorTwo = factorOne;
            System.out.println(factorOne);
        }
        return factorOne;
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