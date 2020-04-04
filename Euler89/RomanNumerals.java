public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("p089_roman.txt"));

        String line = br.readLine();
        int res = 0;
        while (line != null && line.length() > 0) {
            char[] arr = line.toCharArray();
            int cnt = 0;
            boolean five = false;
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];

                if (c == 'X' || c == 'I' || c == 'C') {
                    if (i > 0 && c != arr[i - 1]) {
                        if (cnt > 3) res += score(cnt, five);

                        // if earlier digit adds 5 to the total value
                        String pair = "" + arr[i - 1] + c;
                        if (pair.equals("VI") || pair.equals("DC") || pair.equals("LX")) five = true;
                        else five = false;
                        cnt = 1;
                    }
                    else if (cnt == 0 || c == arr[i - 1]) cnt++;
                }

                // reset counter if prev digit is different
                else if (i > 0 && c != arr[i - 1]) {

                    // if count was greater than 3, we have a better version of numeral
                    if (cnt > 3) {
                        res += score(cnt, five);
                    }
                    // reset variables
                    five = false;
                    cnt = 0;
                }
                // when this is the last digit
                if (cnt > 3 && i == arr.length - 1) res += score(cnt, five);
            }
            line = br.readLine();
        }
        System.out.println(res);
    }

    public static int score(int cnt,boolean five) {
        if (cnt == 4) {
            if (!five) return  2;         // IIII => IV
            else return  3;               // VIIII => IX
        } else if (cnt == 5) {
            if (!five) return  4;        // IIIII => V
            else return  5;              // VIIIII => X
        } else if (cnt < 9) return  4;     // IIIIIIII => VIII
        else if (cnt == 9) return  7;    // IIIIIIIII => IX
        else return  9;                  // IIIIIIIIII => X
    }
}