import java.math.*;

class p080 {
    /*
        https://projecteuler.net/problem=80

        It is well known that if the square root of a natural number is not an
        integer, then it is irrational. The decimal expansion of such square
        roots is infinite without any repeating pattern at all.

        The square root of two is 1.41421356237309504880..., and the digital
        sum of the first one hundred decimal digits is 475.

        For the first one hundred natural numbers, find the total of the
        digital sums of the first one hundred decimal digits for all the
        irrational square roots.

        -----

        Finding square roots by subtraction:
        The difficulty of the question comes from finding a method that will
        give us the first 100 digits of sqrt(n) without losing any precision.
        It turns out there's a method of doing that, explained by Frazer
        Jarvis, here:

        http://www.afjarvis.staff.shef.ac.uk/maths/jarvisspec02.pdf

        The algorithm goes:
        Let a = 5 * n, b = 5. Repeat the following steps:
        - if a >= b, then a = a - b, b += 10
        - if a < b, then a *= 100, b = (b / 10) * 100 + 5

        We can run it as long as we want, but since we only want 100 digits,
        we can cap it off at 101 digits and when we break out of the algorithm
        loop, divide the result by 100 to remove extraneous digits.
    */
    static final BigInteger FIVE = new BigInteger("5");
    static final BigInteger HUNDRED = new BigInteger("100");

    public static int sqrtDigitExpansion(int n) {
        int res = 0;

        for (int i = 1; i <= n; i++) {
            if (Math.sqrt(i) - Math.floor(Math.sqrt(i)) == 0)
                continue;

            BigInteger sqrtVal = bigIntSqrt(i, 101);
            res += digitSum(sqrtVal);
        }

        return res;
    }

    private static BigInteger bigIntSqrt(int i, int digits) {
        BigInteger limit = (new BigInteger("10")).pow(digits);
        BigInteger a = BigInteger.valueOf(5 * i);
        BigInteger b = BigInteger.valueOf(5);

        while (b.compareTo(limit) == -1) {
            if (a.compareTo(b) >= 0) {
                a = a.subtract(b);
                b = b.add(new BigInteger("10"));
            }
            else {
                a = a.multiply(new BigInteger("100"));
                b = b.divide(BigInteger.TEN).multiply(HUNDRED).add(FIVE);
            }
        }

        return b.divide(HUNDRED);
    }

    private static int digitSum(BigInteger n) {
        String s = n.toString();
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i) - '0';
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(sqrtDigitExpansion(100));
    }
}