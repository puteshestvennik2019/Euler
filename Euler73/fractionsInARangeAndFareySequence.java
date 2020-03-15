public class Main {

    // Now that I have completed the below, I realized that there is not only mediant but also 'next term' in Farey sequence
    // therefore, once we know first two fractions and order n, we can easily compute the sequence:
    // a/b < c/b => next term p/q => p = floor((n + b) / d)) * c - a,  q = floor((n + b) / d)) * d - b
    // ============================================================================================================
    
    // for each number between 2 and 12000, using sieve, I will produce a list of prime factors
    // then, for each number, I will establish corresponding ranges of valid numerators, ie
    // when denominator = 12000, numerator will be found within the set (4000, 6000), where 4000 = 12000 / 3, 6000 = 12000 / 2
    // based on prime factors of denominator, non coprime numbers will be then excluded from the ranges
    
    public static void main(String[] args) {

        final int limit = 12000;
        // range bounds represented in common divisor form
        final int[] lowerBound = {2,6};     // 1/3
        final int[] upperBound = {3,6};     // 1/2
        int res = 0;

        List<List<Integer>> primeFactors = computePrimeFactors(limit);

        long st = System.nanoTime();

        for (int i = 2; i <= limit; i++) {
            // for each denominator i, compute smallest fraction > 1/3 and largest fraction < 1/2
            // multiply fractions 1/3, 1/2 and ?/i so they have common denominator
            // lowest fraction in the range = (2 * i + 1) / (2 * 3), while the largest = (3 * i - 1) / (2 * 3)
            // for reduced fractions, take max = floor(largest) and min = ceil(smallest)
            // no fraction exists if min > max

            int minInRangeNumerator = (int) Math.ceil((double) (i * lowerBound[0] + 1) / lowerBound[1]);
            int maxInRangeNumerator = (int) Math.floor((double) (i * upperBound[0] - 1) / upperBound[1]);

            int numeratorsInRange = 1 + maxInRangeNumerator - minInRangeNumerator;
            if (numeratorsInRange < 1) continue;

            // for small numbers with few factors, we can subtract factor's multiples from the total in range
            // (if range is 0-30, 30 / 2 removes all multiples of 2) and then add back numbers removed twice
            // (as factors of 2 and 3 for example), by returning all multiples of a*b (2*3).
            // this approach would be very complicated for numbers with, say 1000 factors, because we would need to
            // consider every single subset. Therefore, generating an array of booleans and sieving off multiples of
            // individual prime factors is easier to implement and faster, especially for larger figures

            boolean[] invalidNumeratorCandidates = new boolean[numeratorsInRange]; // minInRangeNumerator indexed 0
            List<Integer> factors = primeFactors.get(i);
            int counter = numeratorsInRange;

            for (int p: factors) {
                int index = (p - (minInRangeNumerator % p)) % p;
                // minInRangeNumerator + (p - (minInRangeNumerator % p)) % p - first number in range that divides by p
                for (int numerator = minInRangeNumerator + index; numerator <= maxInRangeNumerator; numerator += p) {
                    if (!invalidNumeratorCandidates[index]) {
                        counter--;      // this numerator is not coprime with denominator i
                        invalidNumeratorCandidates[index] = true;
                    }
                    index += p;
                }
            }
            res += counter;
        }
        System.out.println(System.nanoTime() - st);
        System.out.println(res);
    }

    public static List<List<Integer>> computePrimeFactors(int limit) {
        List<List<Integer>> primeFactors = new ArrayList<>();

        // initialize empty lists for each number
        for (int i = 0; i <= limit; i++) {
            primeFactors.add(new ArrayList<>());
        }

        for (int i = 2; i <= limit; i++) {
            if (primeFactors.get(i).size() > 0) continue;

            for (int j = i; j <= limit; j += i) {
                (primeFactors.get(j)).add(i);
            }
        }
        return primeFactors;
    }
}