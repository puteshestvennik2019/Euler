/*
based on Euclid's formula: 
a = m^2 - n^2
b = 2mn
c = m^2 + n^2

to meet the constraint a + b + c = 1000, 
2mn + 2m^2 = 1000, so   n = 500/m - m
because m > n > 0,   500/m > m => m^2 < 500
m > n > 0 <=> 15 < m < 23
only one pair of integers satisfies n = 500/m - m, therefore n = 5, m = 20

this program was written to complete the particular challenge and does NOT return all possible triplets
*/

public static int PythagoreanTriplet(int num) {
    int m = findM(num);
    int n = num/2/m - m;
    int a = m*m - n*n;
    int b = 2*m*n;
    int c = m*m + n*n;
    if ((a*a + b*b == c*c) && (m > 0)){
        System.out.println("Found triplet: A=" + a + ", B=" + b + ", C=" + c);
        System.out.print("ABC product is:");
        return  a * b * c;
    }
	else
        System.out.println("No match found");
	return -1;
}

public static int findM(int num) {
    for (int m = (int) Math.floor(Math.sqrt((double)(num)/2)); m > Math.floor(Math.sqrt((double)(num)/4)); m--) {
        int n = num / 2 / m - m;
        if (m*n*2 + 2*m*m == num)
            return m;
    }
    return -1;
}