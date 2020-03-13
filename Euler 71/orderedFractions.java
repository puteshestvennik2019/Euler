public class Main {

	// calculating mediant of 3/7 and fraction immediately to the left
    public static void main(String[] args) {
	    int num = 2;
	    int den = 5;

	    while (den <= 1000000) {
	        num += 3;
	        den += 7;
        }
        System.out.println((num - 3) + "/" + (den - 7));
    }
}
