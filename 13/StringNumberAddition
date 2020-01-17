    public static void stringAddition(String[] arr) {
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
            res += ch;
            if (i == 0) {
                while (temp > 9) {
                    temp /= 10;
                    ch = temp % 10;
                    res += ch;
                }
            }
        }
        for (int i = res.length() - 1; i > res.length() - 11; i--)
        System.out.print(res.charAt(i));
    }