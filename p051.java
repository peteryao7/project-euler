class p051 {
    /*
     * By replacing the 1st digit of the 2-digit number *3, it turns out that six of
     * the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.
     * 
     * By replacing the 3rd and 4th digits of 56**3 with the same digit, this
     * 5-digit number is the first example having seven primes among the ten
     * generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663,
     * 56773, and 56993. Consequently 56003, being the first member of this family,
     * is the smallest prime with this property.
     * 
     * Find the smallest prime which, by replacing part of the number (not
     * necessarily adjacent digits) with the same digit, is part of an eight prime
     * value family.
     * 
     * -----
     * 
     * Brute force: generate all prime numbers under 1 million with a sieve, then
     * find the first value that satisfies the requirements for every possible
     * repeating pattern.
     * 
     * That takes quite a while to do for every single prime, so let's cut down
     * our search space.
     * 
     * We want to generate potential candidates and see if they're primes and
     * fulfill the repeating digit requirements. 
     * 
     * Generate all patterns of repeating and non-repeating digits, then loop for
     * all 5 and 6 digit numbers.
     * 
     * Check inline and function comments for more info.
     */
    public static int first8PrimeSmallest() {
        int[][] fiveDigitPatterns = get5Patterns();
        int[][] sixDigitPatterns = get6Patterns();

        for (int i = 11; i < 1000; i += 2) {
            if (i % 5 == 0)
                continue;

            int[][] patterns = (i < 100) ? 
                fiveDigitPatterns : sixDigitPatterns;
            
            for (int j = 0; j < patterns.length; j++) {
                // only go through 0-2 since that's what the lowest values 
                // of the repeating digit in the 8-family can be
                for (int k = 0; k <= 2; k++) {
                    if (patterns[j][0] == 0 && k == 0)
                        continue;
                    
                    int[] pattern = fillPattern(patterns[j], i);
                    int candidate = generateNumber(k, pattern);

                    if (candidate < Integer.MAX_VALUE && isPrime(candidate)) {
                        if (familySize(k, pattern) == 8)
                            return candidate;
                        break;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * Pattern generation: 0 means a repeating digit, 1 a nonrepeating digit.
     * 
     * Why are we using 3 repeating digits for both? For any number, we can check if
     * a number is divisible by 3 if the sum of its digits is divisible by 3.
     * 
     * If we added the repeating digits for a number, then if we took mod 3 of that,
     * we would always get 0. That makes it easy for us to check if the nonrepeating
     * digits are also divisible by 3. If so, then we know the number isn't prime.
     * 
     * We also restrict the last digit to only being nonrepeating. Because we want
     * to find an 8-prime value family, then the last digit cannot be an even
     * number. Thus we only look at numbers with 3 repeating digits where the last
     * digit must be nonrepeating.
     */
    private static int[][] get5Patterns() {
        int[][] arr = {
            {1,0,0,0,1},
            {0,1,0,0,1},
            {0,0,1,0,1},
            {0,0,0,1,1},
        };

        return arr;
    }

    private static int[][] get6Patterns() {
        int[][] arr = {
            {0,0,0,1,1,1},
            {0,0,1,0,1,1},
            {0,0,1,1,0,1},
            {0,1,0,0,1,1},
            {0,1,0,1,0,1},
            {0,1,1,0,0,1},
            {1,0,0,0,1,1},
            {1,0,0,1,0,1},
            {1,0,1,0,0,1},
            {1,1,0,0,0,1},
        };

        return arr;
    }

    /**
     * Fills a pattern with the nonrepeating digits. Put a -1 if the digit 
     * is meant to be a repeating digit (i.e. it's 0).
     */
    private static int[] fillPattern(int[] pattern, int num) {
        int[] filledPattern = new int[pattern.length];
        
        for (int i = filledPattern.length - 1; i >= 0; i--) {
            if (pattern[i] == 1) {
                filledPattern[i] = num % 10;
                num /= 10;
            } else {
                filledPattern[i] = -1;
            }
        }

        return filledPattern;
    }

    /**
     * Creates the number based on the filled out pattern and the value
     * for the repeated digit to replace the -1s.
     */
    private static int generateNumber(int repNum, int[] filledPattern) {
        int res = 0;

        for (int i = 0; i < filledPattern.length; i++) {
            res *= 10;
            res += (filledPattern[i] == -1) ? repNum : filledPattern[i];
        }

        return res;
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

    /**
     * Takes a pattern and a repeating digit, representing the family. 
     * Returns the number of primes in its family.
     */
    private static int familySize(int repNum, int[] pattern) {
        int familySize = 1;

        for (int i = repNum + 1; i < 10; i++) {
            if (isPrime(generateNumber(i, pattern)))
                familySize++;
        }

        return familySize;
    }

    public static void main(String[] args) {
        System.out.println(first8PrimeSmallest());
    }
}