import java.math.BigInteger;

class p056 {
    /*
     * A googol (10^100) is a massive number: one followed by one-hundred 
     * zeros; 100^100 is almost unimaginably large: one followed by two-hundred
     * zeros. Despite their size, the sum of the digits in each number is only 
     * 1.
     * 
     * Considering natural numbers of the form, a^b, where a, b < 100, what is 
     * the maximum digital sum?
     * 
     * -----
     * 
     * Do as the question says. Go through every possible a^b and calculate its
     * digit sum. Return the max sum.
     * 
     * The absolute maximum the digit sum can be is 9 * 101 < 2^31, so we can
     * use an int for our return type, but a^b can be far larger than a long,
     * so use BigInteger.
     */
    public static int maxDigitSum(int numMax) {
        int maxSum = 0;
        
        for (int a = 1; a < numMax; a++) {
            for (int b = 1; b < numMax; b++) {
                BigInteger pow = BigInteger.valueOf(a).pow(b);
                maxSum = Math.max(getDigitSum(pow), maxSum);
            }
        }
        
        return maxSum;
    }

    private static int getDigitSum(BigInteger n) {
        int sum = 0;
        String s = n.toString();
        
        for (int i = 0; i < s.length(); i++)
            sum += s.charAt(i) - '0';
            
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(maxDigitSum(100));
    }
}