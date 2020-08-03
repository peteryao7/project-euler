import java.math.*;

class p063 {
    /*
        https://projecteuler.net/problem=63

        The 5-digit number, 16807 = 7^5 is also a fifth power. Similarly, the
        9-digit number, 134217728 = 8^9, is a ninth power.

        How many n-digit positve integers exist which are also an n-th power?

        -----

        Find the bounds:
        We only need to find the bounds for the base (n) and the power (k),
        then we can easily generate all the powers and brute force the problem.

        For the base, if we go up to 10, then any power will always be shorter
        than the result. For 10, it will always have k + 1 digits, so we can
        limit n to 1 <= n <= 9.

        For the power, let's use the upper bound of the base, 9, to find when
        the length of the result and k will always be different. At k = 21,
        9^21 is about 1.09 * 10^20, which is 21 digits long. 9^22 will not be
        22 digits long, and by extension, using k after 21 will never have the
        length of k. Therefore, the bounds of k are 1 <= k <= 21.

        TC/SC - O(1)/O(1)
    */
    public static int totalIntsEqualPower() {
        int res = 0;

        for (double n = 1; n <= 9; n++) {
            for (double k = 1; k <= 21; k++) {
                if (BigDecimal.valueOf(Math.pow(n, k)).toBigInteger().toString().length() == k)
                    res++;
            }
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(totalIntsEqualPower());
    }
}