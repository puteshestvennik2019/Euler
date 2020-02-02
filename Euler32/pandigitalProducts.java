public class Main {

    // number of digits in a product of multiplications is equal to or one less than the sum of digits of its factors
    // to satisfy constraints of the problem, we are only looking for 4-digit product in range {1234 - 9876}

    // this program could be further optimised by sourcing numbers for the second loop (j) from the remaining digits in numbersLL
    // which would also speed up checks if two lists are equal (line 46)
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        int prodDigits = 4;
        int resultSum = 0;
        Set results = new HashSet();

        for (int i = 2; i < 99; i++) {
            boolean cont = false;
            LinkedList<Integer> numbersLL = getLinkedList(numbers);
            if (i < 10) numbersLL.removeFirstOccurrence(i);
            else {
                int k = i;
                while (k > 0) {
                    if (!numbersLL.removeFirstOccurrence(k % 10)) {
                        cont = true;
                        break;
                    }
                    k /= 10;
                }
                if (cont) {
                    continue;
                }
            }
            int secondFactorDigits = numbersLL.size() - prodDigits;
            int maxSecondFactor = maxNumber(secondFactorDigits, numbersLL);
            int minSecondFactor = minNumber(secondFactorDigits, numbersLL);
            int maxProd = maxNumber(prodDigits, numbersLL);

            for (int j = minSecondFactor; j < maxSecondFactor; j++) {
                if (j % 5 == 0) continue; // resulting prod would end with 0 or (second) 5

                int prod = j * i;
                if (prod % 10 == 0 || prod > maxProd || prod < Math.pow(10, prodDigits - 1)) continue;

                int[] digits = parseDigits(j * (int) Math.pow(10, prodDigits) + prod); // array to store digits;
                if (digits == null) continue;

                LinkedList<Integer> digitsLL = getLinkedList(digits);
                if (digitsLL.equals(numbersLL)) {
                    if (!results.contains(prod)) {
                        resultSum += prod;
                        results.add(prod);
                    }
                }
            }
        }
        System.out.println(resultSum);
    }

    public static int[] parseDigits(int num) {
        int[] arr = new int[10];
        while (num > 0) {
            int a = num % 10;
            if (arr[a] == a || a == 0) return null;
            else arr[a] = a;
            num /= 10;
        }
        return arr;
    }

    public static int maxNumber(int digits, LinkedList<Integer> numbers) {
        int number = 0;
        int last = numbers.size() - 1;
        for (int i = 0; i < digits; i++) {
            number *= 10;
            number += (i == 0) ? numbers.getLast() : numbers.get(last - i);
        }
        return number;
    }

    public static int minNumber(int digits, LinkedList<Integer> numbers) {
        LinkedList<Integer> copy = numbers;
        int number = 0;
        for (int i = 0; i < digits; i++) {
            number *= 10;
            number += numbers.get(i);
        }
        return number;
    }

    public static LinkedList<Integer> getLinkedList(int[] numbers) {
        LinkedList<Integer> numbersLL = new LinkedList<>();
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != 0) numbersLL.add(numbers[i]);
        }
        return numbersLL;
    }
}
