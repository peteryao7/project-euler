class p030 {
    /*
     * Surprisingly there are only 3 numbers that can be written as the sum of
     * fourth powers of their digits:
     * 
     * 1634 = 1^4 + 6^4 + 3^4 + 4^4 
     * 8208 = 8^4 + 2^4 + 0^4 + 8^4 
     * 9474 = 9^4 + 4^4 + 7^4 + 4^4 
     * 
     * As 1 = 14 is not a sum it is not included.
     * 
     * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
     * 
     * Find the sum of all the numbers that can be written as the sum of fifth
     * powers of their digits.
     * 
     * Brute force it. However, we can decrease our upper bound so we don't need
     * to perform as many iterations. We cn form a number n = d * 9^5 for some
     * number of digits d, and bind it to the length of n:
     * 
     * 10^(d-1) <= d * 9^5 < 10^d
     * (d-1)*log(10) <= log(d) + 5*log(9) < d*log(10)
     * d-1 <= log_10(d) + 5*log_10(9) < d
     * d - log_10(d) <= 5.77 <= d - log_10(d) + 1
     * d <= 6.47 < d + 1
     * 
     * The upper bound can only have at most 6 digits, resulting in a 6 digit
     * number of all 9s. Thus, 6 * 9^5 = 354294, and we have cut off about 650k
     * worth of iterations and calculations.
     */
    public static int sumFifthPowerDigits(int digitMax) {
        int sum = 0;
        for (int i = 2; i < 354294; i++) {
            if (i == fifthPower(i))
                sum += i;
        }
        return sum;
    } 

    private static int fifthPower(int n) {
        int sum = 0;

        while (n != 0) {
            int m = n % 10;
            sum += Math.pow(m, 5);
            n /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumFifthPowerDigits(5));
    }
}