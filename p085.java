class p085 {
    /*
     * https://projecteuler.net/problem=85
     * 
     * By counting carefully it can be seen that a rectangular grid measuring 3 by 2
     * contains eighteen rectangles:
     * 
     * 1x1 - 6 rectangles
     * 2x1 - 4 rectangles
     * 3x1 - 2 rectangles
     * 1x2 - 3 rectangles
     * 2x2 - 2 rectangles
     * 3x2 - 1 rectangle
     * 
     * Although there exists no rectangular grid that contains exactly two million
     * rectangles, find the area of the grid with the nearest solution.
     * 
     * -----
     * 
     * Combinatorics:
     * To form a rectangle, you have to choose 2 horizontal and vertical sides.
     * Because there will be i + 1 horizontal and j + 1 vertical sides, we can
     * model the number of rectangles as (i + 1 choose 2) * (j + 1 choose 2).
     * Expanding this formula, we get:
     * 
     * (i + 1)! / ((i - 1)! * 2!) * (j + 1)! / ((j - 1)! * 2!)
     * = i(i + 1) / 2 * j(j + 1) / 2
     * = i(i + 1) * j(j + 1) / 4
     * 
     * We'll use the last formula and iterate over different rectangle side
     * values to find the answer. I chose sqrt(target) as the cap for the
     * rectangle sides as it's obvious the number of rectangles will quickly
     * exceed the area of the rectangle, and the limit wasn't too high.
     * 
     * TC/SC - O(target)/O(1)
     * sqrt(target) * sqrt(target) / 2 => O(target)
     */
    public static int areaOfGridWithNearestNumberOfRectangles(int target) {
        int res = 0;
        int diff = Integer.MAX_VALUE;

        for (int i = 1; i <= Math.sqrt(target); i++) {
            for (int j = i; j <= Math.sqrt(target); j++) {
                int numRects = i * (i + 1) * j * (j + 1) / 4;
                if (Math.abs(numRects - target) < diff) {
                    res = i * j;
                    diff = Math.abs(numRects - target);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(areaOfGridWithNearestNumberOfRectangles(2000000));
    }
}