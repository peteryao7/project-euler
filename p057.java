import java.math.BigInteger;

class p057 {
    /*
        It is possible to show that the square root of 2 can be expressed as
        an infinite continued fraction:

        sqrt(2) = 1 + 1/(2 + 1/(2 + 1/(2 + ...)))

        By exapnding this for the first 4 iterations, we get:
        3/2 = 1.5
        7/5 = 1.4
        17/12 = 1.416...
        41/29 = 1.41379...

        The next 3 expansions are 99/70, 239/169, and 577/408, but the 8th
        expansion, 1393/985, is the 1st example where the number of digits in 
        the numerator exceeds the number of digits in the denominator.

        In the first 1000 expansions, how many fractions contain a numerator
        with more digits than the denominator?

        -----

        There is a pattern in each fraction for each iteration. The numerator
        of the next iteration is equal to:
        2 * current denominator + current numerator

        And the denominator of the next iteration is equal to:
        current numerator + current denominator

        We can perform these operations, then compare the length of both and
        increment the count of len(n) > len(d).
    */
    public static int numLongerNumerator(int iter) {
        BigInteger n = BigInteger.ONE;
        BigInteger d = BigInteger.ONE;
        int count = 0;

        for (int i = 0; i < iter; i++) {
            BigInteger temp = d.multiply(BigInteger.valueOf(2)).add(n);
            d = n.add(d);
            n = temp;

            if (n.toString().length() > d.toString().length())
                count++;
        }

        return count;
    }
    public static void main (String[] args) {
        System.out.println(numLongerNumerator(1000));
    }
}