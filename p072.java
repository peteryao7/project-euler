class p072 {
    /*
        https://projecteuler.net/problem=72

        Consider the fraction, n/d, where n and d are positive integers. If
        n<d and HCF(n,d)=1, it is called a reduced proper fraction.

        If we list the set of reduced proper fractions for d ≤ 8 in ascending
        order of size, we get:

        1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8,
        2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

        It can be seen that there are 21 elements in this set.

        How many elements would be contained in the set of reduced proper
        fractions for d ≤ 1,000,000?

        -----

        Calculate totients:
        What do totients have to do with reduced proper fractions? The fraction
        is defined as a / b, where a and b have a GCD of 1 and a < b, so they
        are relatively prime. This is the definition of the totient function,
        so the question can be rephrased as calculating this:

        sum(i=2, 1000000) φ(i)

        We can use the sieve implemented from Q70 and add up all the totients
        in this range to get our answer. It will also be very large, so use
        a long.

        TC/SC - O(d*sqrt(d))/O(d)
    */
    public static long numReducedProperFractions(int d) {
        long res = 0;

        // sieve
        long[] phi = new long[d + 1];

        for (int i = 0; i < phi.length; i++) {
            phi[i] = (long) i;
        }

        for (int i = 2; i <= d; i++) {
            if (phi[i] == (long) i) {
                for (int j = i; j <= d; j += i) {
                    // p - 1 / p
                    phi[j] -= phi[j] / i;
                }
            }
            res += phi[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(numReducedProperFractions(1000000));
    }
}