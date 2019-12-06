class p003 {
    // What is the largest prime factor of the number 600851475143?
    public static long largestPrime(long n) {
        long factor = 0;
        while (n > 1) {
            factor = smallestFactor(n);
            if (factor < n)
                // dividing out smaller prime factors shrinks n
                // until only the largest prime is left
                n /= factor;
            else if (factor == n)
                return n;
        }
        return -1;
    }

    // returns the smallest factor of n, which is always prime
    private static long smallestFactor(long n) {
        // only need to go up to sqrt(i), it's the largest possible
        // number where sqrt(i)^2 <= n
        for (long i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0)
                return i;
        }
        // n is prime
        return n; 
    }

    public static void main(String[] args) {
        // all numbers larger than int max need L at the end
        long n = 600851475143L;
        System.out.println(largestPrime(n));
    }
}