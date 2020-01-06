public class Main {

    public static void main(String[] args) {
        int n = 3;
        int half = generateFirstHalf(n);
        while (half > Math.pow(10, n - 1)) {
            int palind = generatePalindrome(half, n);
            if (hasFactors(palind, n)) {
                System.out.println(palind);
                break;
            } else {
                half--;
            }
        }
    }

    public static boolean hasFactors(int palindrome, int n) {
        int factor = (int) Math.pow(10, n) - 1; // factor initialized to its max value in n-digit range
        while (factor * factor >= palindrome) {
            if (palindrome % factor == 0)
                return true;
            else
                factor--;
        }
        return false;
    }

    public static int generateFirstHalf(int n) {
        // largest number produced by two n-digit factors has max 2*n digits
        return ((int) Math.pow(10, n) - 1);  // 99*99 < 9889, 999*999 < 998899 etc, so in this case, we could start checking from 10^n - 3
    }

    public static int generatePalindrome(int firstHalf, int n) {
        int secondHalf = reverse(firstHalf);
        return (firstHalf * (int) Math.pow(10, n) + secondHalf);
    }

    public static int reverse(int num) {
        int reversed = 0;
        while (num > 0) {
            reversed *=10;
            reversed += (num % 10);
            num /= 10;
        }
        return reversed;
    }

}