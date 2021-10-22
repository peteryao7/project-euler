import java.util.*;

class p077 {
    /*
        It is possible to write ten as the sum of primes in exactly five
        different ways:
        7 + 3
        5 + 5
        5 + 3 + 2
        3 + 3 + 2 + 2
        2 + 2 + 2 + 2 + 2

        What is the first value which can be written as the sum of primes in
        over five thousand different ways?

        -----

        DP:
        This is another coin change problem like problem 76. The twist is that
        we want the target value in the DP to be 5000 or more, so we can loop
        over the target values and use the prime numbers as denominations
        until we get dp[target] >= 5000.
    */
    public static int waysToCountPrime(int ways) {
        List<Integer> primes = sieve(ways);

        for (int target = 1; target < 9001; target++) {
            int[] dp = new int[target + 1];
            dp[0] = 1;

            for (int i = 0; i < primes.size(); i++) {
                for (int j = primes.get(i); j <= target; j++) {
                    dp[j] += dp[j - primes.get(i)];
                }
            }

            if (dp[target] >= ways) {
                return target;
            }
        }

        return -1;
    }

    private static List<Integer> sieve(int max) {
        int[] allPrimes = new int[max + 1];
        allPrimes[0] = 1;
        allPrimes[1] = 1;
        int bound = (int) Math.floor(Math.sqrt(max));

        for (int i = 4; i <= max; i += 2) {
            allPrimes[i] = 1;
        }

        for (int i = 3; i <= bound; i += 2) {
            if (allPrimes[i] == 0) {
                for (int j = i * i; j <= max; j += 2 * i) {
                    allPrimes[j] = 1;
                }
            }
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 2; i < allPrimes.length; i++) {
            if (allPrimes[i] == 0) {
                res.add(i);
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(waysToCountPrime(5000));
    }
}