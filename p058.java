class p058 {
    /*
        Starting with 1 and spiralling anticlockwise in the following way, a
        square spiral with side length 7 is formed.

        37 36 35 34 33 32 31
        38 17 16 15 14 13 30
        39 18  5  4  3 12 29
        40 19  6  1  2 11 28
        41 20  7  8  9 10 27
        42 21 22 23 24 25 26
        43 44 45 46 47 48 49

        It is interesting to note that the odd squares lie along the bottom
        right diagonal, but what is more interesting is that 8 out of the 13 
        numbers lying along both diagonals are prime; that is, a ratio of 
        8/13 ≈ 62%.

        If one complete new layer is wrapped around the spiral above, a square 
        spiral with side length 9 will be formed. If this process is continued,
        what is the side length of the square spiral for which the ratio of 
        primes along both diagonals first falls below 10%?

        -----

        For an nxn square:
        The value in the bottom right corner is n^2,
        the bottom left corner is n^2 - (n - 1)
        the top left corner is n^2 - 2(n - 1)
        the top right corner is n^2 - 3(n - 1).

        Therefore, we can generate each value with those 4 equations and
        check if they're prime. 

        The total number of elements on the diagonals is 2n - 1, so we can
        divide that value with the number of prime numbers on the diagonals 
        to check if it's under 0.1.
    */
    public static int percentDiagPrimes(int min) {
        int numPrimes = 0;
        int n = 1;
        
        while (true) {
            for (int i = 0; i < 4; i++) {
                if (isPrime(n * n - i * (n - 1)))
                    numPrimes++;
            }

            if (n > 1 && numPrimes * min < 2 * n - 1)
                return n;

            n += 2;
        }
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
        System.out.println(percentDiagPrimes(10));
    }
}