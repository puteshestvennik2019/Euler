public class Main {

    public static void main(String[] args) {
        int n = 100000;
        ArrayList<Integer> decPalindromes = generateDecimalPalindrome((int) Math.log10(n));
        System.out.println(decPalindromes);
        ArrayList<Integer> binaryDecimalPalindromes = findBinaryPalindromes(decPalindromes);
        System.out.println(binaryDecimalPalindromes);

        int sum = 0;
        for (int p : binaryDecimalPalindromes) sum += p;

        System.out.println(sum);
    }

    public static ArrayList<Integer> findBinaryPalindromes(ArrayList<Integer> decimalNumbers) {
        ArrayList<Integer> returnPalindromes = new ArrayList<>();
        for (int p : decimalNumbers) {
            boolean palindrome = true;
            String decPal = Integer.toBinaryString(p);
            int left = 0;
            int right = decPal.length() - 1;
            while (left < right) {
                if (decPal.charAt(left) == decPal.charAt(right)) {
                    left++;
                    right--;
                }
                else {
                    palindrome = false;
                    break;
                }
            }
            if (palindrome) returnPalindromes.add(p);
        }
        return returnPalindromes;
    }

    public static ArrayList<Integer> generateDecimalPalindrome(int digits) {
        int halfDigits = digits / 2 + (digits % 2);
        int maxHalfNumber = (int) Math.pow(10, halfDigits);
        int maxHalfDigitNoCeil = (int) Math.pow(10, digits / 2);
        int trackDigits = 10;
        ArrayList<Integer> palindromes = new ArrayList<>();

        for (int i = 1; i < maxHalfNumber; i++) {
            if (i >= trackDigits) trackDigits *= 10;
            if (i < 10) palindromes.add(i); // add single digits

            int reversed = 0;
            int reverse = i;
            while (reverse > 0) {
                reversed *= 10;
                reversed += reverse % 10;
                reverse /= 10;
            }
            // even-digit palindromes
            palindromes.add(i * trackDigits + reversed);

            // odd-digit palindromes
            if (i < maxHalfDigitNoCeil) {
                for (int j = 0; j < 10; j++) {
                    palindromes.add(i * trackDigits * 10 + trackDigits * j + reversed);
                }
            }
        }
        return palindromes;
    }
}
