import java.util.Arrays;

class p018 {
    /*
        Find the maximum total from top to bottom of the triangle below.
        From the top of the triangle, you can move to adjacent numbers
        in the row below, similar to traversing a binary tree. 

        DP:
        Let's first try a greedy solution where we pick the larger number
        between the 2 children.

        The problem here is that we have no way of seeing the lower levels, 
        so there may be a huge value near the bottom of the tree that we 
        skipped over because the initial values are larger.

        Ex:

            1
          5   7
        9   3   4

        We'd go down 7, but the larger sum goes through the smaller number 
        initially. We need a way to "scout" the values in the lower levels.

        To circumvent this problem, we can calculate the max for the sum 
        starting at the bottom. For each cell, we consider the sub-traingle
        whose top is at this cell, calculate the max path sum when starting
        from this cell, and store the result in the current cell.

        For example:
        3
        7 5
        2 4 6
        8 5 9 3

        We go to the second to last level and calculate which of the 2 numbers
        below each cell will give a larger sum. The tree now looks like this:

        3
        7 5
        10 13 15

        Then we go up a row to the second row and do the same thing:

        3
        20 20

        Then for our last row at the top, we get 23 and return whatever is
        at the root.
    */
    private static final int[][] triangle = {
        {75},
        {95,64},
        {17,47,82},
        {18,35,87,10},
        {20, 4,82,47,65},
        {19, 1,23,75, 3,34},
        {88, 2,77,73, 7,63,67},
        {99,65, 4,28, 6,16,70,92},
        {41,41,26,56,83,40,80,70,33},
        {41,48,72,33,47,32,37,16,94,29},
        {53,71,44,65,25,43,91,52,97,51,14},
        {70,11,33,28,77,73,17,78,39,68,17,57},
        {91,71,52,38,17,14,91,43,58,50,27,29,48},
        {63,66, 4,68,89,53,67,30,73,16,69,87,40,31},
        { 4,62,98,27,23, 9,70,98,73,93,38,53,60, 4,23},
    };

    public static int maxSum(int[][] triangle) {
        for (int i = triangle.length - 2; i >= 0; i--) {
            for (int j = 0; j < triangle[i].length; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }
        
        return triangle[0][0];
    }
    public static void main(String[] args) {
        System.out.println(maxSum(triangle));
    }
}