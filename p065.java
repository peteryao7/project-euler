import java.math.*;

class p065 {
    /*
        https://projecteuler.net/problem=65

        The square root of 2 can be written as an infinite continued fraction.

        sqrt(2) = 1 + 1 / (2 + 1 / (2 + 1 / (2 + 1 / (2 + ...))))

        The infinite continued fraction can be written, sqrt(2) = [1;(2)], (2)
        indicates that 2 repeats ad infinitum. In a similar way, sqrt(23) = 
        [4;(1,3,1,8)].

        It turns out that the sequence of partial values of continued
        fractions for square roots provide the best rational approximations.
        Let us consider the convergents for sqrt(2).

        1 + 1 / 2 = 3/2
        1 + 1 / (2 + 1 / 2) = 7/5
        1 + 1 / (2 + 1 / (2 + 1 / 2)) = 17/12
        1 + 1 / (2 + 1 / (2 + 1 / (2 + 1 / 2))) = 41/29

        Hence the sequence of the first ten convergents for sqrt(2) are:
        1, 3/2, 7/5, 17/12, 41/29, 99/70, 239/169, 577/408, 1393/985, 3363/2378, ...

        What is most surprising is that the important mathematical constant,
        e = [2;1,2,1,1,4,1,1,6,1,...,1,2k,1,...].

        The first ten terms in the sequence of convergents for e are:
        2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...

        The sum of digits in the numerator of the 10th convergent is
        1 + 4 + 5 + 7 = 17.

        Find the sum of the digits in the numerator of the 100th convergent
        of the continued fraction for e.

        -----

        Find the pattern:
        This problem comes down to finding the formula to generate the
        numerators. There exists a multiplier m in the sequence
        [2;1,2,1,1,4,1,1,6,1,...,1,2k,1,...] that starts at index 2, m_2. The
        numerator follows the formula below:

        n_i = m_i * n_(i-1) + n_(i-2)
        for n_0 = 1, n_1 = 2

        So n_9 = 6 * 193 + 106 = 1264 and n_10 = 1 * 193 + 1264 = 1457

        TC/SC - O(n)/O(log(n))
    */
    public static int digitSumNumeratorOfE(int max) {
        BigInteger n0 = BigInteger.valueOf(1);
        BigInteger n1 = BigInteger.valueOf(2);

        for (int i = 2; i <= max; i++) {
            BigInteger temp = n0;
            BigInteger m = BigInteger.valueOf((i % 3 == 0) ? 2 * i / 3 : 1);

            n0 = n1;
            n1 = m.multiply(n0).add(temp);
        }

        return sumDigits(n1);
    }

    private static int sumDigits(BigInteger n) {
        char[] ch = n.toString().toCharArray();
        int sum = 0;

        for (char c : ch)
            sum += c - '0';

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(digitSumNumeratorOfE(100));
    }
}