public class Main {

    public static void main(String[] args) {
	    int[] months = {31,28,31,30,31,30,31,31,30,31,30,31};
	    int sundays = 0;
	    int day = 0; // Sun 31 Dec 1899
		int start = 1900;
        int startCounting = 1901;
        int end = 2000;

	    for (int year = start; year <= end; year++) {
	        for (int month = 0; month < 12; month++) {
				if (day == 6) sundays++; 	// if last day of prev month is Sat
	            day += months[month] % 7;
	            if (month == 1 && year % 4 == 0) {
	            	if (year % 100 == 0 && year % 1000 != 0) continue;
	                day++;
                }
                day %= 7;
            }
	        if (year < startCounting) sundays = 0;
        }
        System.out.println(sundays);
    }
}
