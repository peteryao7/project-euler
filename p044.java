class p044 {
    /*
     * Pentagonal numbers are generated by the formula, P_n = n*(3n−1)/2. 
     * The first ten pentagonal numbers are:
     * 
     * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
     * 
     * It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their 
     * difference, 70 − 22 = 48, is not pentagonal.
     * 
     * Find the pair of pentagonal numbers, P_j and P_k, for which their sum and
     * difference are pentagonal and D = |P_k − P_j| is minimised; what is the 
     * value of D?
     * 
     * -----
     * 
     * We essentially want the first pair of pentagonal numbers that satisfy
     * every condition listed in the problem. That way, we can guarantee their
     * difference is minimized.
     * 
     * To check if a number is pentagonal, we can take the formula, 
     * P_n = n(3n-1)/2, where P_n is a constant, and isolate n:
     * 
     * (substitute P_n for x)
     * x = n(3n-1)/2
     * 2x = n(3n-1)
     * 2x = 3n^2 - n
     * 3n^2 - n - 2x = 0
     * Apply the quadratic formula and ignore the negative case:
     * n = (1 + sqrt(1 + 24x)) / 6
     * 
     * For x to be pentagonal, the right half must result in a natural number.
     * That means both 1 + 24x is a perfect square and 1 + sqrt(1 + 24x) % 6 = 0.
     * 
     * Next, let's make a fast way of generating pentagonal sequences. Let q
     * be the difference between 2 consecutive pentagonal numbers:
     * 
     * P_k+1 = P_k + q
     * (k+1)(3(k+1)-1)/2 = k(3k-1)/2 + q
     * 3k^2 + 2k + 3k + 2 = 3k^2 - k + 2q
     * q = 3k + 1
     * 
     * Therefore, we can generate pentagonal numbers up to some upper bound with
     * this formula.
     */

    public static int getMinimizedDiffPentagonal() {
        for (int j = 1; j < 5000; j++) {
            int Pj = j * (3 * j - 1) / 2;
            for (int k = j + 1; k < 5000; k++) {
                int Pk = k * (3 * k - 1) / 2;
                if (isPentagonal(Pk - Pj) && isPentagonal(Pj + Pk))
                    return Pk - Pj;
            }
        }

        return -1;
    }

    private static boolean isPentagonal(int n) {
        double r = Math.sqrt(1 + 24 * n);
        return r % 6 == 5;
    }

    public static void main(String[] args) {
        System.out.println(getMinimizedDiffPentagonal());
    }
}