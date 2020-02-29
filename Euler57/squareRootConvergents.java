import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // finally solution which computes in decent time
    public static void main(String[] args) {

        long st = System.nanoTime();

        int expansions = 1000;
        int count = 0;
        BigInteger numerator = new BigInteger("3");
        BigInteger denominator = new BigInteger("2");

        BigInteger powTen = new BigInteger("10");
        BigInteger ten = new BigInteger("10");

        for (int i = 0; i < expansions; i++) {

            while (powTen.compareTo(denominator) <= 0) {
                powTen = powTen.multiply(ten);
            }
            if (powTen.compareTo(numerator) <= 0) count++;

            BigInteger temp = denominator;
            denominator = denominator.add(numerator);
            numerator = denominator.add(temp);
        }
        System.out.println(System.nanoTime() - st);
        System.out.println(count);
    }


//=======================================================================================
//    public static void main(String[] args) {
//
//        long st = System.nanoTime();
//
//        int expansions = 1000;
//        int count = 0;
//        List<Integer> numerator = new ArrayList<>();
//        numerator.add(3);
//        List<Integer> denominator = new ArrayList<>();
//        denominator.add(2);
//
//        for (int i = 0; i < expansions; i++) {
//            if (numerator.size() > denominator.size()) count++;
//
//            List<Integer> temp = denominator;
//            denominator = addArrays(temp, numerator);
//            numerator = addArrays(denominator, temp);
//        }
//        System.out.println(System.nanoTime() - st);
//        System.out.println(count);
//    }
//
//    // numbers are stored in reversed order
//    public static List<Integer> addArrays(List<Integer> arr1, List<Integer> arr2) {
//        List<Integer> sum = new ArrayList<>();
//        sum.add(0);
//
//        int i = 0;
//        while (i < arr1.size() && i < arr2.size()) {
//            int ones = sum.get(i) + arr1.get(i) + arr2.get(i);
//            sum.set(i, ones % 10);
//            sum.add(ones / 10);
//            i++;
//        }
//        int ones = 0;
//        if (i < arr1.size()) {
//            ones = arr1.get(i) + sum.get(i);
//            sum.set(i, ones % 10);
//        }
//        else if (i < arr2.size()) {
//            ones = arr2.get(i) + sum.get(i);
//            sum.set(i, ones % 10);
//        }
//        ones /= 10;
//        if (ones > 0) sum.add(ones);
//        while (sum.get(sum.size() - 1) == 0) sum.remove(sum.size() - 1);
//        return sum;
//    }


//=======================================================================================


// converting BI to string it costly

//    public static void main(String[] args) {
//
//        long st = System.nanoTime();
//
//        int expansions = 1000;
//        int count = 0;
//        BigInteger numerator = new BigInteger("3");
//        BigInteger denominator = new BigInteger("2");
//
//        for (int i = 0; i < expansions; i++) {
//            int n = numerator.toString().length();
//            int d = denominator.toString().length();
//
//            if (n > d) count++;
//
//            BigInteger temp = denominator;
//            denominator = denominator.add(numerator);
//            numerator = denominator.add(temp);
//        }
//        System.out.println(System.nanoTime() - st);
//        System.out.println(count);
//    }

    
//=======================================================================================
    
    // long overfills after 48 terms
//    public static void main(String[] args) {
//
//        int expansions = 1000;
//        int count = 0;
//        long numerator = 3;
//        long denominator = 2;
//
//        for (int i = 0; i < expansions; i++) {
//            int exponent = (int) Math.ceil(Math.log10(denominator)); // smallest integer for which 10^exponent > denominator
//            int powOfTen = (int) Math.pow(10, exponent);
//            if (powOfTen > denominator && powOfTen <= numerator) count++;
//
//            long temp = denominator;
//            denominator += numerator;
//            numerator = denominator + temp;
//
//            if (numerator < 0) System.out.println(i);
//        }
//        System.out.println(count);
//
//    }
}
