import java.util.Arrays;

class p049 {
    /*
     * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
     * increases by 3330, is unusual in two ways: 
     *     - each of the three terms are prime 
     *     - each of the 4-digit numbers are permutations of one another
     * 
     * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes,
     * exhibiting this property, but there is one other 4-digit increasing sequence.
     * 
     * What 12-digit number do you form by concatenating the three terms in this
     * sequence?
     * 
     * -----
     * 
     * Do as the question says. The pattern, although not explicitly stated, is
     * that each term is separated by 3330, so that limits the search space 
     * significantly. Start at the max for the third value, 9999, calculate the
     * other 2 numbers by subtracting 3330, and check if they are prime and 
     * contain the same digits.
     */
    public static String secondPrimePermuteSeq() {
        for (int k = 9999; k > 6660; k -= 2) {
            if (k != 8147 && isPrime(k)) {
                int j = k - 3330;
                int i = j - 3330;
                
                if ((isPrime(j) && isPrime(i)) && 
                    (hasSameDigits(i, k) && hasSameDigits(j, k)))
                    return "" + i + j + k;
            }
        }

        return new String("Not found...");
    }

    private static boolean isPrime(int n) {
        if (n == 0 || n == 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;

        int sqrt = (int) Math.sqrt(n);

        for (int i = 3; i <= sqrt; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    private static boolean hasSameDigits(int m, int n) {
        char[] mDigits = Integer.toString(m).toCharArray();
        char[] nDigits = Integer.toString(n).toCharArray();
        Arrays.sort(mDigits);
        Arrays.sort(nDigits);
        return Arrays.equals(mDigits, nDigits);
    }

    public static void main(String[] args) {
        System.out.println(secondPrimePermuteSeq());
    }
}