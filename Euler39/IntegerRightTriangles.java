public class Main {

    public static void main(String[] args) {

        findMaxTriplets(1000);
//        findMaxNumberOfTriplets(1000);
    }

    public static int findMaxNumberOfTriplets(int range) {
        // Amending Euclid's formula used in problem 9 by adding k parameter, will produce all triples
        // a=m²-n², b=2mn, c=m²+n² where 0 < n < m yields p=2*k*m*(m+n)

        int maxSolutions = 0;
        int perimeterWithMaxSolutions = 0;

        // Pythagorean triplet cannot be all odd numbers, therefore their sum will not be odd
        for (int p = 2; p <= range; p += 2) {
            Set<int[]> uniqueSolutions = new HashSet<>();

            for (int m = 2; m < Math.sqrt(p / 2); m++) {
                for (int n = 1; n < m; n++) {
                    int k = 2 * m * (m + n);
                    if (p % k == 0) {
                        k = p / k;
                        getUniqueSolutions(uniqueSolutions, k, m, n);
                    }
                }
            }
            if (uniqueSolutions.size() > maxSolutions) {
                maxSolutions = uniqueSolutions.size();
                perimeterWithMaxSolutions = p;
            }
        }
        return perimeterWithMaxSolutions;
    }

    public static void getUniqueSolutions(Set<int[]> uniqueSolutions, int k, int m, int n) {
        int[] solution = new int[3];
        int a = (m*m - n*n) * k;
        int b = k * m * n * 2;
        int c = (m*m + n*n) * k;
        if (a < b) {
            solution[0] = a;
            solution[1] = b;
        }
        else {
            solution[1] = a;
            solution[0] = b;
        }
        if (solution[1] < c) solution[2] = c;
        else {
            solution[2] = solution[1];
            solution[1] = c;
        }
        if (uniqueSolutions.size() > 0) {
            for (int[] sol : uniqueSolutions) {
                if (Arrays.equals(sol, solution)) return;
            }
        }
        uniqueSolutions.add(solution);
    }

    // returns perimeter of a right angle triangle with max number of Pythagorean triplets
    // Brute force solution
    public static int findMaxTriplets(int limit) {
        int maxSolutions = 0;
        int perimeterWithMaxSolutions = 0;

        // Pythagorean triplet cannot be all odd numbers, therefore their sum will not be odd
        for (int p = 2; p <= limit; p += 2) {
            int solutions = 0;

            // a < b < c, therefore a is less than their sum
            // formula below is reached by isolating b in a^2+b^2=c^2 and a+b+c=p
            for (int a = 1; a < p / 3; a++) {
                if ((p * (p - 2 * a)) % (2 * (p - a)) == 0) solutions++;
            }
            if (maxSolutions < solutions) {
                maxSolutions = solutions;
                perimeterWithMaxSolutions = p;
            }
        }
        return perimeterWithMaxSolutions;
    }
}