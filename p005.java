import java.math.BigInteger;

class p005 {
    /*
     * What is the smallest positive number that is evenly divisible by all of the
     * numbers from 1 to 20?
     * 
     * The question is essentially asking for the LCM of numbers 1, 2, ..., 20.
     * We can use the Euclidean algorithm to calculate this. 
     * 
     * In general, the lcm of 2 numbers a, b can be computed as a * b / gcd(a,b)
     * So we can reduce the list as such:
     * 
     * lcm(1,2,3,4)
     *  = lcm(lcm(1,2,3),4)
     *  = lcm(lcm(lcm(1,2),3),4)
     *  = lcm(lcm(2,3),4)
     *  = lcm(6,4)
     *  = 12
     */ 
    public static int findLCM(int n) {
        // reduce the list of numbers recursively
        BigInteger allLCM = BigInteger.ONE;
        for (int i = 1; i <= n; i++)
            allLCM = lcm(BigInteger.valueOf(i), allLCM);
        return allLCM.intValue();
    }

    
    // Euclid's algorithm
    // For ints a, b, gcd(a,b) = gcd(b % a, a) for a,b > 0
    private static BigInteger lcm(BigInteger a, BigInteger b) {
        BigInteger gcd = a.gcd(b);
        return a.divide(gcd).multiply(b);
    }
    public static void main (String[] args) {
        System.out.println(findLCM(20));
    }
}