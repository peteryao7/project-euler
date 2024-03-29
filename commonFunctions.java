import java.util.*;

class commonFunctions {
    /*
        This is a list of functions I have compiled while solving Project Euler
        problems that I have needed repeatedly.
    */

    // checks if an integer is prime
    private static boolean isPrime(int n) {
        if (n < 2 || n % 2 == 0)
            return n == 2;

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }

        return true;
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

    // Sieve of Eratosthenes
    // 0 = prime, 1 = not prime
    private static int[] sieve(int max) {
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

        return allPrimes;
    }

    // uses sieve() to generate list of prime integers
    private static List<Integer> sieveValues(int max) {
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

    // tests if a string is a palindrome
    public static boolean isPalindrome(String s) {
        String sb = new StringBuilder(s).reverse().toString();
        return s.equals(sb);
    }

    // tests if an integer is a palindrome
    public static boolean isPalindrome(int n) {
        String s = Integer.toString(n);
        String sb = new StringBuilder(s).reverse().toString();
        return s.equals(sb);
    }

    // returns the largest non-negative integer dividing both m and n
    public static int gcd(int m, int n) {
        while (n != 0) {
            int a = m % n;
            m = n;
            n = a;
        }

        return m;
    }

    // returns true if a and b share the same digits, false otherwise
    private static boolean hasSameDigits(int a, int b) {
        int[] digits = new int[10];

        while (a > 0 && b > 0) {
            digits[a % 10]++;
            digits[b % 10]--;
            a /= 10;
            b /= 10;
        }

        for (int i : digits) {
            if (i != 0)
                return false;
        }

        return true;
    }
}