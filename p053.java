import java.math.BigInteger;

class p053 {
    /*
     * There are exactly ten ways of selecting three from five, 12345:
     * 
     * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
     * 
     * In combinatorics, 5 choose 3 = 10.
     * 
     * In general, (n choose r) = n! / (r!(n−r)!), where r ≤ n, 
     * n! = n × (n−1) × ... × 3 × 2 × 1, and 0! = 1.
     * 
     * It is not until n = 23, that a value exceeds one million: 
     * 23 choose 10 = 1144066
     * 
     * How many, not necessarily distinct, values of (n choose r) for 
     * 1 ≤ n ≤ 100, are greater than one million?
     * 
     * -----
     * 
     * Brute force it and do as the question says. Go through all
     * n from 1 to 100 and all r from 0 to n and calculate n choose k.
     * If the result is larger than a million, add it to an accumulator.
     * 
     * You can also interpret the formula of n choose k as:
     * 
     * (n * n-1 * n-2 * ... * n-k+1) / k!
     */
    public static int combinationsGreaterThanMillion(int n_max) {
        BigInteger MIL = BigInteger.TEN.pow(6);
        int count = 0;

        for (int n = 1; n <= n_max; n++) {
            for (int r = 0; r <= n; r++) {
                if (binomial(n, r).compareTo(MIL) > 0)
                    count++;
            }
        }

        return count;
    }

    // calculates n!
    public static BigInteger factorial(int n) {
        BigInteger product = BigInteger.ONE;

        for (int i = 2; i <= n; i++)
            product = product.multiply(BigInteger.valueOf(i));
        
        return product;
    }

    // calculates n choose k
    private static BigInteger binomial(int n, int k) {
        BigInteger product = BigInteger.ONE;

        // calculates n * n-1 * ... * n-k+1
        for (int i = 0; i < k; i++)
            product = product.multiply(BigInteger.valueOf(n - i));
        
        return product.divide(factorial(k));
    }

    public static void main(String[] args) {
        System.out.println(combinationsGreaterThanMillion(100));
    }
}