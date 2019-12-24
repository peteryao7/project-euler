import java.util.ArrayList;

class p050 {
    /*
        The prime 41, can be written as the sum of six consecutive primes:
        41 = 2 + 3 + 5 + 7 + 11 + 13

        This is the longest sum of consecutive primes that adds to a prime 
        below one-hundred.

        The longest sum of consecutive primes below one-thousand that adds 
        to a prime, contains 21 terms, and is equal to 953.

        Which prime, below one-million, can be written as the sum of the 
        most consecutive primes?

        -----

        Get a list of every prime number up to a million and keep a second 
        array whose index represents the prime number, and mark it true if 
        it's prime.

        Keep track of the biggest sum seen so far, and the longest consecutive
        length of that sum. Iterate through every possible series of 
        consecutive prime numbers and return the sum with the longest 
        series length.
    */
    public static long highestSumConsPrime(int max) {
        boolean[] isPrime = indexPrimes(max);
        ArrayList<Integer> primes = new ArrayList<>(listAllPrimes(max));
        long maxSum = 0;
        int len = -1;

        for (int i = 0; i < primes.size(); i++) {
            int sum = 0;
            // iterate through each series of primes
            for (int j = i; j < primes.size(); j++) {
                sum += primes.get(j);
                if (sum > max)
                    break;
                // sum must be prime, sum is larger than the max, and
                // the length of consecutive primes must be longer than
                // that in maxSum
                if (isPrime[sum] && sum > maxSum && j - i > len) {
                    len = j - i;
                    maxSum = sum;
                }
            }
        }

        return maxSum;
    }

    // array of prime numbers up to n
    private static ArrayList<Integer> listAllPrimes(int n) {
        boolean[] isPrime = indexPrimes(n);
        ArrayList<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i])
                res.add(i);
        }

        return res;
    }

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
        System.out.println(highestSumConsPrime(1000000));
    }
}