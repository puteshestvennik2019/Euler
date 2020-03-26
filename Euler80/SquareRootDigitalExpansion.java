// Japanese method for working out square roots
public class Main {
    public static void main(String[] args) {
        int res = 0;
        int root = 2;
        int square = root*root;
        for (int i = 2; i <= 100; i++) {
            if (i == square) {
                root++;
                square = root*root;
                continue;
            }
            String a = String.valueOf(5*i);
            String b = "5";

            // two more digits for precision
            while (b.length() < 102) {
                if (compare(a, b) < 0) {
                    a += "00";
                    b = b.substring(0, (b.length() - 1)) + "05";
                }
                else {
                    a = subtract(a, b);
                    b = add10(b);
                }
            }
            res += countString(b);
        }
        System.out.println(res);
    }

    private static int compare(String a, String b) {
        if (a.length() > b.length()) return 1;
        else if (a.length() < b.length()) return -1;
        else return (a.compareTo(b));
    }

    private static int countString(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            res += s.charAt(i) - '0';
        }
        return res;
    }

    private static String add10(String s) {
        int pointer = s.length() - 2;
        String res = "5";

        if (s.length() < 2) {
            res = 1 + res;
        }
        else {
            int carry = 1;

            while (pointer >= 0) {
                int dig = (s.charAt(pointer--) - '0') + carry;
                if (dig > 9) {
                    carry = 1;
                    dig = 0;
                } else {
                    carry = 0;
                }
                res = dig + res;

                // copy and exit loop
                if (carry == 0) {
                    res = s.substring(0, pointer + 1) + res;
                    break;
                }
            }
        }
        return res;
    }

    private static String subtract(String a, String b) {
        int pointerA = a.length() - 1;
        int pointerB = b.length() - 1;
        int carry = 0;
        String res = "";

        while (pointerA >= 0) {
            int aDig = (a.charAt(pointerA--) - '0') - carry;
            carry = 0;
            int bDig = 0;

            if (pointerB >= 0) bDig = b.charAt(pointerB--) - '0';

            if (aDig < bDig) {
                carry = 1;
            }
            int result = carry * 10 + aDig - bDig;

            if (pointerA < 0 && result == 0) break;
            res = result + res;
        }
        while (res.charAt(0) == '0') res = res.substring(1);
        return res;
    }
}
