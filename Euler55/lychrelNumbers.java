import java.util.Arrays;
import java.util.Stack;

// because we need to check for palindromes on every iteration, I decided to work with arrays rather than integers
// a combination of the two might yield better performance (operations on integers while number < 2^31)
// I have added an auxiliary array of booleans to store numbers which become palindromes within 50 iterations
// I am not sure why this has decreased the overal speed... without lines (17, 22), 38-41, 46-50, 
// answer is generated in 50% less time...

public class Main {

    public static void main(String[] args) {

        int limit = 10000;
        int n = 50; // to meet condition 1: 50 iterations
        int count = 0;
        boolean[] nonLychrel = new boolean[limit + 1];

        long st = System.nanoTime();

        for (int number = 1; number < limit; number++) {
            if (nonLychrel[number]) continue;

            int l = getArrayLength(number);
            int[] num = numberToArray(number, l);
            boolean reachedPalindrome = false;

            Stack<Integer> nonLychrelList = new Stack<>();

            for (int i = 0; i < n; i++) {
                int[] sum = addReverse(num);

                if (isPalindrome(sum)) {
                    reachedPalindrome  = true;
                    break;
                }
                
                // if number is smaller than 10000, at the end of this loop add it to visited, if it ends in palindromic state
                if (num.length < 5) {
                    nonLychrelList.add(toInt(num));
                }

                num = sum;
            }
            if (!reachedPalindrome) count++;
            else {
                while (!nonLychrelList.isEmpty()) {
                    nonLychrel[nonLychrelList.pop()] = true;
                }
            }
        }
        System.out.println(System.nanoTime() - st);
        System.out.println(count);
    }

    public static int[] addReverse(int[] number) {
        int len = number.length;

        int[] res = new int[len + 1];
        int i = 0;

        while (i < number.length) {
            int sum = number[i] + number[len - 1] + res[len];
            res[len] = sum % 10;
            res[len - 1] = sum / 10;
            i++;
            len--;
        }
        if (res[0] == 0) res = Arrays.copyOfRange(res, 1, i + 1);
        return res;
    }

    public static boolean isPalindrome(int[] arr) {
        int j = arr.length - 1;
        int i = 0;

        while (i < j) {
            if (arr[i] != arr[j]) return false;
            i++;
            j--;
        }
        return true;
    }

    public static int getArrayLength(int number) {
        if (number < 10) return 1;
        if (number < 100) return 2;
        if (number < 1000) return 3;
        if (number < 10000) return 4;
        return 0;
    }

    public static int[] numberToArray(int number, int lastIndex) {
        int[] arr = new int[lastIndex];

        while (number > 0) {
            arr[--lastIndex] = number % 10;
            number /= 10;
        }
        return arr;
    }

    public static int toInt(int[] num) {
        int res = 0;
        int i = num.length - 1;
        int j = i;

        while (j >= 0) {
            res += num[j] * (int) Math.pow(10, i - j);
            j--;
        }
        return res;
    }
}
