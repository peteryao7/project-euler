public class p073 {
    /*
        https://projecteuler.net/problem=73

        Consider the fraction, n/d, where n and d are positive integers. If
        n < d and HCF(n,d) = 1, it is called a reduced proper fraction.

        If we list the set of reduced proper fractions for d ≤ 8 in ascending
        order of size, we get:
        1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8,
        2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

        It can be seen that there are 3 fractions between 1/3 and 1/2.

        How many fractions lie between 1/3 and 1/2 in the sorted set of
        reduced proper fractions for d ≤ 12,000?

        -----

        Stern-Brocot Tree:
        A Stern-Brocot tree is a kind of binary tree that generates all
        positive rational numbers in reduced actions (m + m')/(n + n') between
        two adjacent fractions m/n and m'/n'. Set the limit of the denominator
        to be 12000 and count each valid fraction between 1/3 and 1/2.
    */
    public static int sternBrocotCount(int lNum, int lDen, int rNum, int rDen, int d) {
        int num = lNum + rNum;
        int den = lDen + rDen;

        if (den > d) {
            return 0;
        }

        return 1 + sternBrocotCount(num, den, rNum, rDen, d) + sternBrocotCount(lNum, lDen, num, den, d);
    }

    public static void main(String[] args) {
        System.out.println(sternBrocotCount(1, 3, 1, 2, 12000));
    }
}
