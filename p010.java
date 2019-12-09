import java.util.Arrays;

class p010 {
    /*
        Find the sum of all the primes below 2 million.
        
        Sieve of Eratosthenes:
        This is one of the most efficient ways to find primes up to n < 10 mil.

        Algorithm:
        Create a list of consecutive integers from 2 to n (with indices 
            of a boolean array, all set to true, meaning unmarked)
        Let p = 2 initially, the first prime number
        Starting from p^2, count up increments of p and mark each number 
            greater than or equal to p^2 itself in the list
        Find the first number greater than p in the list not marked. If no
            such number exists, stop. Otherwise, let p now equal this number,
            which is the next prime, and repeat the last step.
        
        All numbers not marked are prime, which you can retrieve by their index.
    */
    public static long sieveOfEratosthenes(int n) {
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);

        // start with p = 2, the first prime number
        for (int p = 2; p * p <= n; p++) {
            if (primes[p]) {
                for (int i = p * p; i <= n; i += p)
                    primes[i] = false;
            }
        }

        return sumOfPrimes(primes);
    }

    private static long sumOfPrimes(boolean[] primes) {
        long sum = 0;
        for (int i = 2; i < primes.length; i++) {
            if (primes[i])
                sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sieveOfEratosthenes(2000000));
    }
}