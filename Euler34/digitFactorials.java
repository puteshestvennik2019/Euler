public class Main {

    public static void main(String[] args) {
	    int[] factorials = populateFactorials(9);
	    int res = findSumDigitFactorials(factorials);
        System.out.println(res);
    }

    public static int findSumDigitFactorials(int[] factorials) {
        int upperBoundDigits = upperLimit(factorials[9]);
        int highestNumber = upperBoundDigits * factorials[9];
        int minSum = 10;
        int sum = 0;
        for (int i = minSum; i < highestNumber; i++) {
            int sumOfFactorials = 0;
            int num = i;
            while (num > 0) {
                sumOfFactorials += factorials[num % 10];
                num /= 10;
            }
            if (i == sumOfFactorials) sum += sumOfFactorials;
        }
        return sum;
    }

    public static int upperLimit(int nine) {
        int digits = 1;
        while (nine * digits > Math.pow(10, digits)) {
            digits++;
        }
        return digits;
    }

    public static int[] populateFactorials(int lastNum) {
        int[] arr = new int[lastNum + 1];
        arr[0] = 1;
        for (int i = 1; i <= lastNum; i++)
            arr[i] = i * arr[i-1];
        return arr;
    }
}
