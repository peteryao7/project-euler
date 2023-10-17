class p086 {
    /*
     * https://projecteuler.net/problem=86
     * 
     * A spider, S, sits in one corner of a cuboid room, measuring 6 by 5 by 3,
     * and a fly, F, sits in the opposite corner. By travelling on the surfaces
     * of the room the shortest "straight line" distance from S to F is
     * and the path is shown on the diagram.
     * 
     * (6 by 5 by 3 cuboid)
     * 
     * However, there are up to three "shortest" path candidates for any given
     * cuboid and the shortest route doesn't always have integer length.
     * 
     * It can be shown that there are exactly 2060 distinct cuboids, ignoring
     * rotations, with integer dimensions, up to a maximum size of M by M by M,
     * for which the shortest route has integer length when M = 100. This is
     * the least value of M for which the number of solutions first exceeds
     * two thousand; the number of solutions when M = 99 is 1975.
     * 
     * Find the least value of M such that the number of solutions first
     * exceeds one million.
     * 
     * -----
     * 
     * https://oeis.org/A143714
     * The series in the URL represents what the problem is asking for: the
     * number of cuboids of maximal side length n having the shortest path
     * from one vertex to the furthest one. If we generate this series and sum
     * up every n, then we can return the n in which the sum reaches 1000000.
     * 
     * Next, we need to account for the number of ways b+c can be unpacked when
     * generating the Pythagorean triple. Divide by 2 to account for starting
     * at different vertices on the same cuboid, and subtract b + c - a - 1 on
     * the assumption that a, b <= c.
     */
    public static int getCuboidSols(int limit) {
        int a = 2, cur = 0;

        while (cur < limit) {
            a++;
            // bc = b + c, the other 2 lines of the triangle
            for (int bc = 3; bc < 2*a; bc++) {
                if (isSquare(a, bc)) {
                    // add # ways to split b + c into components
                    cur += bc / 2 - Math.max(0, bc - a - 1);
                }
            }
        }

        return a;
    }

    public static boolean isSquare(int a, int bc) {
        double sqrt = Math.sqrt(a * a + bc * bc);
        return sqrt == (int) sqrt;
    }

    public static void main(String[] args) {
        int limit = 1000000;

        System.out.println(getCuboidSols(limit));
    }
}