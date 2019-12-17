class p034 {
    /*
     * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
     * 
     * Find the sum of all numbers which are equal to the sum of the factorial of
     * their digits.
     * 
     * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
     * 
     * Do as the question says, but we need to find an upper bound.
     * 
     * We want to find the lowest constant c such that c * 9! < 10^c. 
     * c * 9! is the largest possible factorial digit sum we can obtain
     * with a number with c digits, so we want to find at what point will
     * the number itself always be larger than the sum of the factorial
     * digits. 
     * 
     * The lowest constant that satisfies the inequality is 7, 
     * and 7 * 9! = 2540160, so we'll set that to be our upper bound.
     */
    private static final int[] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static int sumFactorialDigitSum() {
        int sum = 0;

        // start at 3 since 1! and 2! aren't sums
        for (int i = 3; i < 2540160; i++) {
            if (i == factorialDigitSum(i))
                sum += i;
        }

        return sum;
    }

    private static int factorialDigitSum(int n) {
        int sum = 0;

        while (n != 0) {
            sum += FACTORIAL[n % 10];
            n /= 10;
        }
        
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumFactorialDigitSum());
    }
}