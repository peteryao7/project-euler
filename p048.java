import java.math.BigInteger;

class p048 {
    /*
     * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
     * 
     * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
     * 
     * -----
     * 
     * BigInteger can contain numbers up to 10^10 if we only use the last 10 
     * digits every time we calculate each i^i. Because every digit before the 
     * last 10 digits doesn't factor into the sum we want, we can ignore 
     * those digits.
     */
    public static String selfPowersFirst10(int n) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger modLastTen = BigInteger.TEN.pow(10);
        for (int i = 1; i <= n; i++)
            sum = sum.add(BigInteger.valueOf(i).modPow(BigInteger.valueOf(i), modLastTen));
        
        return sum.mod(modLastTen).toString();
    }

    public static void main(String[] args) {
        System.out.println(selfPowersFirst10(1000));
    }
}