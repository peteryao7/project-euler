class p070 {
    /*
        https://projecteuler.net/problem=70

        Euler's Totient function, φ(n) [sometimes called the phi function], is
        used to determine the number of positive numbers less than or equal to
        n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and
        8, are all less than nine and relatively prime to nine, φ(9)=6.

        The number 1 is considered to be relatively prime to every positive
        number, so φ(1)=1.

        Interestingly, φ(87109)=79180, and it can be seen that 87109 is a
        permutation of 79180.

        Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of
        n and the ratio n/φ(n) produces a minimum.

        -----

        Simulation:
        Calculate all the totients up to 10^7 and calculate the minimum
        fraction that way. We can either use a double to make the fraction,
        or cross multiply the current numerator and denominator of the fraction
        with the minimum one. This will also give us easy access to the
        numerator, which should be n, to return at the end.

        Checking if n and φ(i) have the same digits is simple: count the
        frequency of the digits in one, subtract each occurrence in the other,
        and all 10 digits should be 0 if they have the same count of digits.

        TC/SC - O(n*sqrt(n))/O(n) for n = max
    */
    private static long totientPermutation(long max) {
        long minNum = 1; // i
        long minDen = 0; // φ(i)
        long[] totients = getTotients(max);

        for (int i = 2; i < totients.length; i++) {
            long t = totients[i];

            // cross multiply minNum / minDen = i / φ(i)
            // to minimize i / φ(i), i should be small and φ(i) large
            if (i * minDen < minNum * t && hasSameDigits(i, t)) {
                minNum = i;
                minDen = t;
            }
        }

        return minNum;
    }

    // brute force using gcd is O(n^2*log(n^2)) and too slow
    // use sieve instead
    private static long[] getTotients(long max) {
        long[] res = new long[(int) max + 1];

        for (int i = 0; i <= max; i++) {
            res[i] = i;
        }

        for (int i = 2; i <= max; i++) {
            if (res[i] == i) {
                for (int j = i; j <= max; j += i) {
                    res[j] -= res[j] / i;
                }
            }
        }

        return res;
    }

    private static boolean hasSameDigits(long a, long b) {
        int[] digits = new int[10];

        while (a > 0 && b > 0) {
            digits[(int) a % 10]++;
            digits[(int) b % 10]--;
            a /= 10;
            b /= 10;
        }

        for (long i : digits) {
            if (i != 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(totientPermutation(10000000));
    }
}