class p037 {
    /*
     * The number 3797 has an interesting property. Being prime itself, it is
     * possible to continuously remove digits from left to right, and remain prime
     * at each stage: 3797, 797, 97, and 7. Similarly we can work from right to
     * left: 3797, 379, 37, and 3.
     * 
     * Find the sum of the only eleven primes that are both truncatable from left to
     * right and right to left.
     * 
     * Note: 2, 3, 5, and 7 are not considered to be truncatable primes.
     * 
     * Do as the question says. Because single digit primes aren't truncatable, start
     * at 10. Stop looping when you get to the 11th prime and return the sum.
     */
    public static int sumTruncatablePrimes() {
        int sum = 0;
        int count = 0;
        int n = 10;

        while (count < 11) {
            if (isTruncatablePrime(n)) {
                sum += n;
                count++;
            }
            n++;
        }

        return sum;
    }

    private static boolean isTruncatablePrime(int n) {
        // left truncatable: 3797 -> 797 -> 97 -> 7
        for (int i = 10; i <= n; i *= 10) {
            if (!isPrime(n % i))
                return false;
        }

        // right truncatable: 3797 -> 379 -> 37 -> 3
        while (n != 0) {
            if (!isPrime(n))
                return false;
            n /= 10;
        }

        return true;
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

    public static void main(String[] args) {
        System.out.println(sumTruncatablePrimes());
    }
}