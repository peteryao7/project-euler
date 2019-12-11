import java.math.BigInteger;

class p020 {
    /*
        Find the sum of the digits in the number 100!

        Brute force it. Factorial goes up extremely fast, so we
        can store 100! in a BigInteger, then convert it into a string and 
        add each digit.
    */
    public static int sumOfFactorialDigits(int n) {
        BigInteger prod = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            prod = prod.multiply(BigInteger.valueOf(i));
        }

        String prodString = prod.toString();
        int sum = 0;

        for (int i = 0; i < prodString.length(); i++) {
            sum += prodString.charAt(i) - '0';
        }

        return sum;
    }
    public static void main(String[] args) {
        System.out.println(sumOfFactorialDigits(100));
    }
}