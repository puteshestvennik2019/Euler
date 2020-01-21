public class Main {

    public static void main(String[] args) {
	    int[] ones = {0,3,3,5,4,4,3,5,5,4,3,6,6,8,8,7,7,9,8,8};
	    int[] tens = {0,0,6,6,5,5,5,7,6,6};
	    int total = 0;
	    int limit = 1000;

	    for (int num = 1; num <= limit; num++) {
			int count = 0;
			int digits = num;
			int hun = digits % 100;
			if (hun < 20) count += ones[hun];
			else count += ones[hun % 10] + tens[hun / 10];
			if ((num > 99) && (num % 1000 != 0)) {
				count += 7 + ones[num / 100]; // "hundred" +...
				if (num % 100 != 0) count += 3; // "and"
			}
			if (num > 999) {
				count += 8 + ones[num / 1000]; // "thousand"
			}
			total += count;
	    }
        System.out.println(total);
    }
}