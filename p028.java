class p028 {
    /*
        Starting with the number 1 and moving to the right in a clockwise
        direction, a 5x5 spiral is formed as follows:

        21 22 23 24 25
        20  7  8  9 10
        19  6  1  2 11
        18  5  4  3 12
        17 16 15 14 13

        The sum of the numbers on the diagonals is 101.

        What is the sum of the numbers on the diagonals in a 1001 by 1001 
        spiral formed in the same way?

        Induction:
        Let's look at smaller spirals first. We will only consider 
        odd-dimensional matrices. For a 1x1 spiral, the sum is 1.
        For a 3x3 spiral, the outer ring's 4 corners are 3, 5, 7, 9. For a 
        5x5 spiral, the 4 corners are 13, 17, 21, 25. For 7x7, it's 31, 37,
        43, 49. The order of the corners are bottom right, bottom left, top
        left, top right.

        We can see a pattern in the values. Let n be the dimensions of the nxn 
        spiral. The top right corner is always n^2, top left is n^2 - (n - 1), 
        bottom left n^2 - 2(n - 1), bottom right n^2 - 3(n - 1). If we add up
        all the values of the outer ring, we get 4n^2 - 6n + 6. Since this 
        only works for square matrices of odd dimension greater than 3, we 
        can call that formula (n-1)/2 times and keep adding to get our answer.

        Using induction, we can find a closed form summation to calculate the
        sum in O(1) time. The formula from above can be expressed as:

        1 + sum(i=1, s) 4(2i+1)^2 - 6(2i+1)+6, where s = (n-1)/2

        Simplifying the summation, we get:

        1 + sum(i=1, s) 16*i^2 + 4*i + 4
        1 + 16 * sum(i=1, s) i^2 + 4 * sum(i=1, s) i + sum(i=1, s) 4

        Substituting the summations with the following arithmetic series, which
        can also be derived through induction:
        sum(i=1, n) i = n*(n+1)/2
        sum(i=1, n) i^2 = n*(n+1)(2n+1)/6

        We get:
        1 + [16 * s(s+1)(2s+1) / 6] + [4 * s(s+1) / 2] + 4s

        Substitute (n-1)/2 back for s, and we get the closed form summation:
        (4*n^3 + 3*n^2 + 8*n - 9) / 6
        2/3 * n^3 + 1/2 * n^2 + 4/3 * n - 3/2

        For more info on these arithmetic series, see:
        https://en.wikipedia.org/wiki/Summation#Powers_and_logarithm_of_arithmetic_progressions
    */
    public static int getSumDiagSpiral(int dim) {
        int res = (int) ((2 * Math.pow(dim, 3)) / 3.0 + Math.pow(dim, 2) / 2.0 + (4 * dim) / 3.0 - 3/2.0);
        return res;
    }

    public static void main (String[] args) {
        System.out.println(getSumDiagSpiral(1001));
    }
}