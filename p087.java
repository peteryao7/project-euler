import java.util.*;

class p087 {
    /*
     * The smallest number expressible as the sum of a prime square, prime
     * cube, and prime fourth power is 28. In fact, there are exactly four
     * numbers below fifty that can be expressed in such a way:
     * 
     * 28 = 2^2 + 2^3 + 2^4
     * 33 = 3^2 + 2^3 + 2^4
     * 49 = 5^2 + 2^3 + 2^4
     * 47 = 2^2 + 3^3 + 2^4
     * 
     * How many numbers below fifty million can be expressed as the sum of a prime
     * square, prime cube, and prime fourth power?
     * 
     * -----
     * 
     * Brute force:
     * Find all the primes whose squares, cubes, and fourths are less than
     * 50m, and find the sums of each permutation using nested loops. Use a set
     * to filter out duplicate sums.
     */
    public static int getNumPrimePowerTriples(int limit) {
        List<Integer> squares = listAllPrimes((int) Math.pow(limit, (double) 1 / 2));
        List<Integer> cubes = listAllPrimes((int) Math.pow(limit, (double) 1 / 3));
        List<Integer> fourths = listAllPrimes((int) Math.pow(limit, (double) 1 / 4));
        Set<Double> set = new HashSet<>();

        for (Integer s : squares) {
            for (Integer c : cubes) {
                for (Integer f : fourths) {
                    double sum = Math.pow(s, 2) + Math.pow(c, 3) + Math.pow(f, 4);
                    if (sum < limit) {
                        set.add(sum);
                    }
                }
            }
        }

        return set.size();        
    }

    // returns an array of prime numbers up to n
    private static ArrayList<Integer> listAllPrimes(int n) {
        boolean[] isPrime = indexPrimes(n);
        ArrayList<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i])
                res.add(i);
        }

        return res;
    }

    // returns a boolean array whose indices are prime numbers
    // true = index is a prime number
    // false = index is not a prime number
    private static boolean[] indexPrimes(int n) {
        boolean[] res = new boolean[n + 1];
        if (n >= 2)
            res[2] = true;
        for (int i = 3; i <= n; i += 2)
            res[i] = true;

        // sieve
        int sqrt = (int) Math.sqrt(n);

        for (int i = 3; i <= sqrt; i += 2) {
            if (res[i]) {
                for (int j = i * i; j <= n; j += 2 * i)
                    res[j] = false;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int limit = 50000000;

        System.out.println(getNumPrimePowerTriples(limit));
    }
}