class p023 {
    /*
     * A perfect number is a number for which the sum of its proper divisors is
     * exactly equal to the number. For example, the sum of the proper divisors of
     * 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
     * 
     * A number n is called deficient if the sum of its proper divisors is less than
     * n and it is called abundant if this sum exceeds n.
     * 
     * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
     * number that can be written as the sum of two abundant numbers is 24. By
     * mathematical analysis, it can be shown that all integers greater than 28123
     * can be written as the sum of two abundant numbers. However, this upper limit
     * cannot be reduced any further by analysis even though it is known that the
     * greatest number that cannot be expressed as the sum of two abundant numbers
     * is less than this limit.
     * 
     * Find the sum of all the positive integers which cannot be written as the sum
     * of two abundant numbers.
     * 
     * -----
     * 
     * Do what the question asks, nothing fancy here.
     * 
     * Define a hard cap of 28123 as the question states that all numbers greater than
     * it will not be part of the solution. Define a boolean array that indicates
     * whether the number (represented by the index) is abundant.
     * 
     * For every number below 28123, find its factors, sum them up, and check if they're
     * larger than the number to see if it's abundant. You only need to go up to sqrt(n)
     * since we can add the other factor by calculating n / i while we're looking between 
     * 1 and sqrt(n). 
     * 
     * After we found every abundant number, we want to check which numbers can't be 
     * represented by the sum of 2 abundant numbers, so we will essentially use a 
     * start and end pointer to add up 2 unique abundant numbers and see if it equals
     * the input. Only add the number if this returns false.
     */
    public static final int HARD_CAP = 28123;

    public static int sumNotAbundant() {
        boolean[] abundantNums = new boolean[HARD_CAP + 1];
        // ignore 0
        for (int i = 1; i < abundantNums.length; i++) {
            abundantNums[i] = isAbundant(i);
        }

        int notAbundantSum = 0;

        for (int i = 1; i < abundantNums.length; i++) {
            if (!isSumOfTwoAbundants(i, abundantNums))
                notAbundantSum += i;
        }

        return notAbundantSum;
    }

    private static boolean isAbundant(int n) {
        int factorSum = 0;
        int sqrt = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0)
                factorSum += i + n / i;
        }

        if (sqrt * sqrt == n)
            factorSum -= sqrt;

        return factorSum > n;
    }

    private static boolean isSumOfTwoAbundants(int n, boolean[] abundantNums) {
        for (int i = 1; i <= n / 2; i++) {
            if (abundantNums[i] && abundantNums[n - i])
                return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sumNotAbundant());
    }
}