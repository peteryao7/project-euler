class p046 {
    /*
     * It was proposed by Christian Goldbach that every odd composite number can be
     * written as the sum of a prime and twice a square.
     * 
     * 9 = 7 + 2×1^2 
     * 15 = 7 + 2×2^2 
     * 21 = 3 + 2×3^2 
     * 25 = 7 + 2×3^2 
     * 27 = 19 + 2×2^2 
     * 33 = 31 + 2×1^2
     * 
     * It turns out that the conjecture was false.
     * 
     * What is the smallest odd composite that cannot be written as the sum of a
     * prime and twice a square?
     * 
     * -----
     * 
     * Do as the question says. 9 is the first number that satisfies the
     * conjecture, so start there. Go through every usable square when checking
     * a number, and test if n - sq^2 ends up with a prime number (gotten through
     * rearranging the formula). Return the first result that doesn't satisfy
     * the conjecture.
     */
    public static int firstGoldbachWrong() {
        int n = 9;

        while (true) {
            if (!satisfyConjecture(n))
                return n;
            n++;
        }
    }

    private static boolean satisfyConjecture(int n) {
        // check if n is an odd composite number
        if (n % 2 == 0 || isPrime(n))
            return true;
        
        for (int i = 1; i * i * 2 <= n; i++) {
            // n - 2*sq^2 should be prime
            if (isPrime(n - i * i * 2))
                return true;
        }

        return false;
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
        System.out.println(firstGoldbachWrong());
    }
}