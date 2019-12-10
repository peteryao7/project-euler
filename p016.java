import java.math.BigInteger;

class p016 {
    /*
        2^15 = 32768 and the sum of its digits is 26.

        What is the sum of the digits of the number 2^1000?

        BigInteger and bit manipulation:
        2^1000 is a huge number, so we can use BigInteger to store it.
        shiftLeft will essentially shift the number n times, the number
        passed into the function. This means we're multiplying the
        current number by 2 n times, so we can start with 1 and shiftLeft
        1000 times.

        Next, we convert it to a string so we can parse each digit at
        a time, then add it to an accumulator and return it.
    */
    public static int digitSumPowerTwo(int n) {
        String num = BigInteger.ONE.shiftLeft(1000).toString();
        int sum = 0;

        for (int i = 0; i < num.length(); i++)
            sum += num.charAt(i) - '0';
        
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(digitSumPowerTwo(1000));
    }
}