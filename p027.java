class p027 {
    /*
     * Euler discovered the remarkable quadratic formula:
     * 
     * n^2 + n + 41 
     * 
     * It turns out that the formula will produce 40 primes for the consecutive 
     * integer values 0 <= n <= 39. However, when n = 40, 
     * 
     * 40^2 + 40 + 41 = 40(40 + 1) + 41
     * 
     * is divisible by 41, and certainly when n = 41,
     * 
     * 41^2 + 41 + 41
     * 
     * is clearly divisible by 41.
     * 
     * The incredible formula n^2 − 79n + 1601 was discovered, which produces 
     * 80 primes for the consecutive values 0 <= n <= 79. The product of the 
     * coefficients, −79 and 1601, is −126479.
     * 
     * Considering quadratics of the form:
     * 
     * n^2 + a*n + b, where |a| < 1000 and |b| <= 1000
     * 
     * Find the product of the coefficients, a and b, for the quadratic 
     * expression that produces the maximum number of primes for consecutive 
     * values of n, starting with n = 0.
     * 
     * -----
     * 
     * Reducing the constraints:
     * Do as the question says, but there are some specifics in the problem 
     * where we can significantly reduce the number of iterations we need 
     * to perform.
     * 
     * Because we start at n = 0, the first value we will be checking for 
     * primality is 0^2 + a*0 + b = b. Thus, for the formula to produce
     * a prime number, b must be prime.
     * 
     * If we increment n to n = 1, then we get 1 ± a + b. We know b has to
     * be prime, and all prime numbers are odd, so b + 1 will be even. So
     * a must be odd because that's the only way we can get an odd number.
     * Also, since prime numbers can't be negative, and a has the potential
     * to be negative, then |a| < b.
     * 
     * With those constraints in mind, we can now iterate through all possible
     * a, b and return a*b.
     */
    public static int prodMaxQuadraticPrimes(int max) {
        if (max <= 1)
            return -1;
        
        int bestN = 0, bestA = 0, bestB = 0;

        for (int a = -max; a < max; a++) {
            for (int b = -max; b <= max; b++) {
                // b must be prime
                // a must be odd
                // |a| < b
                if (!isPrime(b) || a % 2 == 0 || Math.abs(a) >= b)
                    continue;
                int n = numConsecutivePrimes(a, b);
                if (n > bestN) {
                    bestN = n;
                    bestA = a;
                    bestB = b;
                }
            }
        }

        return bestA * bestB;
    }
    
    private static int numConsecutivePrimes(int a, int b) {
        int i = 0;

        while (true) {
            int n = i * i + i * a + b;
            // negative numbers can't be prime
            if (n < 0 || !isPrime(n))
                return i;
            i++;
        }
    }

    private static boolean isPrime(int n) {
        int sqrt = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(prodMaxQuadraticPrimes(1000));
    }
}