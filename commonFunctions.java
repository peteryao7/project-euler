class commonFunctions {
    /*
        This is a list of functions I have compiled while solving Project Euler
        problems that I have needed repeatedly.
    */

    // checks if an integer is prime
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
}