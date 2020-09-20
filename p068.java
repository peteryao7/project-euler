/*
    https://projecteuler.net/problem=68

    Consider the following "magic" 3-gon ring, filled with the numbers 1 to 6,
    and each line adding to nine.

      4
       \
        3
       / \
      1 - 2 - 6
     /
    5

    Working clockwise, and starting from the group of three with the
    numerically lowest external node (4,3,2 in this example), each solution
    can be described uniquely. For example, the above solution can be
    described by the set: 4,3,2; 6,2,1; 5,1,3.

    It is possible to complete the ring with four different totals: 9, 10, 11,
    and 12. There are eight solutions in total.

    Total	   Solution Set
      9	    4,2,3; 5,3,1; 6,1,2
      9	    4,3,2; 6,2,1; 5,1,3
      10	2,3,5; 4,5,1; 6,1,3
      10	2,5,3; 6,3,1; 4,1,5
      11	1,4,6; 3,6,2; 5,2,4
      11	1,6,4; 5,4,2; 3,2,6
      12	1,5,6; 2,6,4; 3,4,5
      12	1,6,5; 3,5,4; 2,4,6

    By concatenating each group it is possible to form 9-digit strings; the
    maximum string for a 3-gon ring is 432621513.

    Using the numbers 1 to 10, and depending on arrangements, it is possible
    to form 16- and 17-digit strings. What is the maximum 16-digit string for
    a "magic" 5-gon ring?

    -----

    Pen and paper:
    The ring is small enough such that we can get the solution by reasoning
    through it in a greedy fashion without writing any code. This line of
    thought may not work if we expand the problem to larger magic n-gon rings.

    The numbers we have to work with are 1-10, and the solution must be 16
    digits long. We reuse the inner 5 numbers in the ring, and if we reuse 10,
    we'll end up with 17 digits, so 10 must be one of the outer nodes.

    If we start on the numerically lowest external node, the lowest possible
    node that can be is 6, where the other nodes are 7-10. Ideally, those 5
    numbers will be in the exterior as we want to maximize the string, so we
    give the most significant digits to the largest ones. Also ideally, after
    6, the external nodes we visit will go in descending order, so
    6 -> 10 -> 9 -> 8 -> 7.

    If we can keep the external nodes as such, then 1-5 will be in the inner
    rings and added twice. That means the total sum of the 5-gon ring is
    2 * (1 + 2 + 3 + 4 + 5) + 6 + 7 + 8 + 9 + 10 = 70. Then for each branch of
    the 5-gon ring, they must add up to 70 / 5 = 14.

    Our 5-gon ring looks like this:

        6
         \
          x   10
        /  \ /
      x     x
     / \   /
    7   x-x - 9
         \
          8
    
    Let's start with the first branch. 14 - 6 = 8, and only 2 numbers, 3 and 5,
    work here. Because we want to maximize the string, put 5 first.

        6
         \
          5   10
        /  \ /
      x     3
     / \   /
    7   x-x - 9
         \
          8

    For the 10 branch, 14 - 10 - 3 = 1, so the missing number there must be 1.
    For the 9 branch, 14 - 9 - 1 = 4, so the missing number must be 4.
    For the 7 branch, 14 - 7 - 4 = 2, which is the very last number missing.

        6
         \
          5   10
        /  \ /
      2     3
     / \   /
    7   4-1 - 9
         \
          8

    The string is constructed as follows:
    6,5,3; 10,3,1; 9,1,4; 8,4,2; 7,2,5
*/

// No code necessary.