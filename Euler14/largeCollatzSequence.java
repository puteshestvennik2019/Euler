public class Main {

    public static void main(String[] args) {
        int N = 1000000; // limit
	    int[] chain = new int [N];
        chain[0] = 0;
        chain[1] = 1;
        int number = 0;
        int max = 0;

        for (int n = 3; n < N; n++) {
            long i = n;
            int counter = 0;
            if (i % 2 == 0) {
                counter++;
                i /= 2;
            }
            else {
                while (i >= n) {
                    i *= 3;
                    i++;
                    counter++;
                    while (i % 2 == 0) {
                        i /= 2;
                        counter++;
                    }
                }
            }
            counter += chain[(int) i - 1];
            chain[n - 1] = counter;
            if (counter > max) {
                max = counter;
                number = n;
            }
        }
        System.out.println(number);
    }
}