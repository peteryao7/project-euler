class p015 {
    /*
        Starting in the top left corner of a 2x2 grid, and only being able to
        move right and down, there are 6 routes in total to the bottom right
        corner.

        How many such routes are there through a 20x20 grid?

        There are 2 possible ways to do this, DP and combinatorics.
        
        DP:
        Because you can only move right and down, the is no way to backtrack, 
        so you can build up to the final answer with the solutions to smaller
        problems. We will store the number of routes to reach each respective
        cell in the grid. Also note that for a 2x2 grid, there are actually
        3 rows and 3 total columns, for a total of 9 cells you can be in.
        
        The top row and leftmost column can only be reached if you
        move in the same direction m+1 or n+1 times, so mark all those with
        1. Then, for the rest of the cells, look for the element directly
        above and to the left, and add them together for the number of routes
        to reach the current cell. Keep going until you reach the bottom right
        cell and return the value in there.

        Combinatorics:
        The total number of movements required to get to the bottom right corner
        is always (m+1) + (n+1), and there are 2 possible ways to move. You'll
        never get stuck on the right or bottom borders, so you can't make 
        extraneous movements. Therefore, the total number of ways to reach
        the bottom right is (m + n) choose m, or (m + n) choose n.
    */

    public static long uniquePathsDP(long m, long n) {
        long[][] grid = new long[(int) m + 1][(int) n + 1];

        // only 1 way to reach every elt in top of grid
        for (int i = 0; i <= m; i++)
            grid[i][0] = 1;

        // only 1 way to reach every elt in left of grid
        for (int j = 0; j <= n; j++)
            grid[0][j] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        return grid[(int) m][(int) n];
    }

    // combination formula for nCr:
    // nCr = n! / r! * (n - r)!
    public static long uniquePathsComb(int m, int n) {
        int i = 0;
        long result = 1;
        int sum = m + n;
        int p = Math.min(m, n);

        for (i = 0; i < p; i++)
            result = result * (sum - i) / (i + 1);

        return result;
    }

    public static void main(String[] args) {
        System.out.println("DP solution: " + uniquePathsDP(20, 20));
        System.out.println("Combinatorics solution: " + uniquePathsComb(20, 20));
    }
}