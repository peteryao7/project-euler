class p045 {
    /*
        Triangle, pentagonal, and hexagonal numbers are generated 
        by the following formulae:

        Triangle	 	T_n = n(n+1)/2	 	1, 3, 6, 10, 15, ...
        Pentagonal	 	P_n = n(3n−1)/2	 	1, 5, 12, 22, 35, ...
        Hexagonal	 	H_n = n(2n−1)	 	1, 6, 15, 28, 45, ...
        It can be verified that T_285 = P_165 = H_143 = 40755.

        Find the next triangle number that is also pentagonal and hexagonal.

        -----

        The set of hexagonal numbers is actually a subset of triangular numbers.
        We can substitute n = 2m - 1 into the triangle number function and get
        the hexagonal function:

        T_n = (2m - 1) * (2m) / 2
            = (4m^2 - 2m) / 2
            = 2m^2 - m
            = m(2m - 1)

        That means we don't need to check if a number is hexagonal, since all
        triangular numbers will be hexagonal. All we need to check for now is
        whether n is pentagonal using our function from Q44 and return the
        result.
    */
    public static long getSecondTriPentHexNum() {
        long m = 144; // the first valid answer is given in the question

        while (true) {
            long res = 2 * m * m - m;
            if (isPentagonal(res))
                return res;
            m++;
        }
    }

    private static boolean isPentagonal(long n) {
        double r = Math.sqrt(1 + 24 * n);
        return r % 6 == 5;
    }

    public static void main(String[] args) {
        System.out.println(getSecondTriPentHexNum());
    }
}