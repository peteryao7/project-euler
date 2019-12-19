class p039 {
    /*
     * If p is the perimeter of a right angle triangle with integral length 
     * sides, {a,b,c}, there are exactly three solutions for p = 120.
     * 
     * {20,48,52}, {24,45,51}, {30,40,50}
     * 
     * For which value of p ≤ 1000, is the number of solutions maximised?
     * 
     * -----
     * 
     * Using what we know about the Pythagorean theorem and the triangle 
     * inequality theorem, we can deduce a more efficient way of solving the
     * problem instead of brute force.
     * 
     * Pythagorean theorem: a^2 + b^2 = c^2
     * Triangle inequality theorem: a + b > c, assume c = hypotenuse
     * 
     * We know that because p is the perimeter, p = a + b + c. Moving a and b
     * to the other side, we get c = p - a - b. Substitute c with this value,
     * and we get:
     * 
     * b = p(p-2a) / 2(p-a)
     * 
     * As long as b is an integer, we know that any triangle with parameters
     * a and p will result in a Pythagorean triplet. 
     * 
     * What about the parity of a and b?
     *     - If either a or b is odd, then c will be odd and p is even.
     *     - If a and b are both odd, then c will be even and p is even.
     *     - If a and b are both even, then c will be even and p is even.
     * Thus, p will be even no matter what a and b are, so we can skip all
     * odd numbers. 
     * 
     * We know, as a property of right triangles, that a < c and b < c. If 
     * we always make a to be the smaller of a and b, we can say that 
     * a <= b < c, implying that a < p/3 and all a's greater than p/3 are
     * not necessary to check.
     */
    public static int maxRightTriangleSolns(int max) {
        int pMax = 0, cMax = 0;

        for (int p = 2; p <= max; p += 2) {
            int c = 0;
            for (int a = 2; a < p / 3; a++) {
                if (p * (p - 2 * a) % (2 * (p - a)) == 0)
                    c++;
            }

            if (c > cMax) {
                cMax = c;
                pMax = p;
            }
        }

        return pMax;
    }
    public static void main(String[] args) {
        System.out.println(maxRightTriangleSolns(1000));
    }
}