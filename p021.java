class p021 {
    /*
        Let d(n) be defined as the sum of proper divisions of n. If d(a) = b
        and d(b) = a, where a != b, then a and b are an amicable pair and each
        of a and b are called amicable numbers.

        Ex. the divisors of 220 add up to 284, and the divisors of 284 add up 
        to 220, so both numbers are amicable.

        Evaluate the sum of all amicable numbers under 10000.

        Brute force it. Loop through every number 1 to 10000 and check if they're
        amicable. If they are, add them to an accumulator. To check if a number
        is amicable with another, calculate the sum of all its factors in another 
        function by brute force checking every number from 1 to n. Then find the
        sum of factors for that number, and check if it's the origin number.
    */
    public static int sumAmicableNums(int max) {
        int amicableSum = 0;

        for (int i = 1; i <= max; i++) {
            if (isAmicable(i))
                amicableSum += i;
        }

        return amicableSum;
    }

    private static boolean isAmicable(int n) {
        int m = sumFactors(n);
        return m != n && sumFactors(m) == n;
    }

    private static int sumFactors(int n) {
        int factorSum = 0;

        for (int i = 1; i < n; i++) {
            if (n % i == 0)
                factorSum += i;
        }

        return factorSum;
    }

    public static void main(String[] args) {
        System.out.println(sumAmicableNums(10000));
    }
}