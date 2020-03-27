import java.util.Arrays;
import java.util.BitSet;

class p060 {
    /*
     * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes
     * and concatenating them in any order the result will always be prime. For
     * example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these
     * four primes, 792, represents the lowest sum for a set of four primes with
     * this property.
     * 
     * Find the lowest sum for a set of five primes for which any two primes
     * concatenate to produce another prime.
     * 
     * -----
     * 
     * This solution is essentially brute force. but we're using a BitSet for
     * storing valid primes. First, we generate a set of 5 primes, then go
     * through each pair and concatenate them both ways. Store the primality
     * of the concatenated number in the BitSet if it doesn't exist, and
     * store the state of whether the number is prime or not. Keep generating
     * sets of 5 primes, concatenating pairs, and checking for primality until
     * there's a set where every pair is prime, and return the sum of the
     * set.
     */
    public static void main(String[] args) {
        System.out.println(getSum5Primes());
    }

    private static final int PRIME_LIMIT = 100000; // arbitrary cutoff

    private static int[] primes = listPrimes(PRIME_LIMIT);

    // set and get in BitSet is O(1)
    // so if we calculated the primality of a concatenated value beforehand,
    // we can just retrieve the value instead of calculating it again
    // it's like a HashMap<Integer, Boolean> combined with a Set
    private static BitSet isConcatPrimeKnown;
    private static BitSet isConcatPrime;

    public static String getSum5Primes() {
        // arbitrary limits
        isConcatPrimeKnown = new BitSet(primes.length * primes.length);
        isConcatPrime = new BitSet(primes.length * primes.length);

        int sumLimit = PRIME_LIMIT;

        while (true) {
            int sum = findSetSum(new int[] {}, 5, sumLimit - 1);
            if (sum == -1) // No smaller sum found
                return Integer.toString(sumLimit);
            sumLimit = sum;
        }
    }

    /* 
     * Tries to find any suitable set and return its sum, or -1 if none is
     * found. A set is suitable if it contains only primes, its size is
     * 'targetSize', its sum is less than or equal to 'sumLimit', and each
     * pair concatenates to a prime. 'prefix' is an array of ascending indices
     * into the 'primes' array, which describes the set found so far. The
     * function blindly assumes that each pair of primes in 'prefix'
     * concatenates to a prime.
     * 
     * For example, findSetSum(new int[]{1, 3, 28}, 5, 10000) means "find the sum of
     * any set where the set has size 5, consists of primes with the lowest elements
     * being {3, 7, 109}, has sum 10000 or less, and has each pair concatenating to
     * form a prime".
     */
    private static int findSetSum(int[] prefix, int targetSize, int sumLimit) {
        if (prefix.length == targetSize) {
            int sum = 0;
            for (int i : prefix)
                sum += primes[i];
            return sum;

        } else {
            int i = prefix.length == 0 ? 0 : prefix[prefix.length - 1] + 1;

            outer: 
            while (i < primes.length && primes[i] <= sumLimit) {
                for (int j : prefix) {
                    if (!isConcatPrime(i, j) || !isConcatPrime(j, i)) {
                        i++;
                        continue outer;
                    }
                }

                int[] appended = Arrays.copyOf(prefix, prefix.length + 1);
                appended[appended.length - 1] = i;
                int sum = findSetSum(appended, targetSize, sumLimit - primes[i]);
                if (sum != -1)
                    return sum;

                i++;
            }
            return -1;
        }
    }

    // concats the primes together, tests whether that's prime
    // then set the bitsets depending if the value is prime or not
    private static boolean isConcatPrime(int x, int y) {
        int index = x * primes.length + y;
        if (isConcatPrimeKnown.get(index))
            return isConcatPrime.get(index);

        x = primes[x];
        y = primes[y];
        int mult = 1;

        for (int temp = y; temp != 0; temp /= 10)
            mult *= 10;

        boolean result = isPrime((long) x * mult + y);
        isConcatPrimeKnown.set(index);
        isConcatPrime.set(index, result);
        return result;
    }

    private static boolean isPrime(long x) {
        if (x == 0 || x == 1)
            return false;
        else {
            long end = (long) Math.floor(Math.sqrt(x));

            for (int p : primes) {
                if (p > end)
                    break;
                if (x % p == 0)
                    return false;
            }

            for (long i = primes[primes.length - 1] + 2; i <= end; i += 2) {
                if (x % i == 0)
                    return false;
            }

            return true;
        }
    }

    public static int[] listPrimes(int n) {
        boolean[] isPrime = listPrimality(n);
        int count = 0;
        for (boolean b : isPrime) {
            if (b)
                count++;
        }

        int[] result = new int[count];

        for (int i = 0, j = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                result[j] = i;
                j++;
            }
        }

        return result;
    }

    public static boolean[] listPrimality(int n) {
        boolean[] result = new boolean[n + 1];
        if (n >= 2)
            result[2] = true;
        for (int i = 3; i <= n; i += 2)
            result[i] = true;
        // sieve
        for (int i = 3, end = (int) Math.floor(Math.sqrt(n)); i <= end; i += 2) {
            if (result[i]) {
                for (int j = i * i, inc = i * 2; j <= n; j += inc)
                    result[j] = false;
            }
        }
        return result;
    }
}