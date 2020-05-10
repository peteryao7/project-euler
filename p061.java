import java.util.List;
import java.util.ArrayList;

class p061 {
    /*
        Triangle, square, pentagonal, hexagonal, heptagonal, and octagonal
        numbers are all figurate (polygonal) numbers and are generated by the
        following formulae:

        Triangle:   P_3,n = n * (n + 1) / 2       1, 3, 6, 10, 15, ...
        Square:     P_4,n = n * n                 1, 4, 9, 16, 25, ...
        Pentagonal: P_5,n = n * (3 * n - 1) / 2   1, 5, 12, 22, 35, ...
        Hexagonal:  P_6,n = n * (2 * n - 1)       1, 6, 15, 28, 45, ...
        Heptagonal: P_7,n = n * (5 * n - 3) / 2   1, 7, 18, 34, 55, ...
        Octagonal:  P_8,n = n * (3 * n - 2)       1, 8, 21, 40, 65, ...

        The ordered set of three 4-digit numbers: 8128, 2882, 8281, has three
        interesting properties.

        1. The set is cyclic, in that the last two digits of each number is the
        first two digits of the next number (including the last number with
        the first).

        2. Each polygonal type: triangle (P3,127=8128), square (P4,91=8281),
        and pentagonal (P5,44=2882), is represented by a different number in
        the set.

        3. This is the only set of 4-digit numbers with this property.

        Find the sum of the only ordered set of six cyclic 4-digit numbers for
        which each polygonal type: triangle, square, pentagonal, hexagonal,
        heptagonal, and octagonal, is represented by a different number in the
        set.

        -----

        (Mostly) Brute force:
        First, we should generate all the cyclic numbers with 4 digits. It's
        fairly simple to do brute force: define a 2D array using each polygon
        as a bucket and store every number we need in them.

        Then, we can start at any number we want, and test if it's cyclic
        with any other number in the other buckets. If we reach a chain of
        length 6, check if it could potentially be closed. Keep checking the
        constraints for a chain number defined in the problem until you can
        close it, otherwise remove numbers and keep trying.
    */
    private static int[] solution;
    private static int[][] nums;

    private static int sumSixCyclicFourDigitNums() {
        int res = 0;
        solution = new int[9]; // first 3 elements aren't used
        nums = new int[9][]; // first 3 elements aren't used

        for (int i = 3; i < solution.length; i++) {
            nums[i] = generateCyclicNums(i);
        }

        for (int j = 0; j < nums[8].length; j++) {
            // start with octagonal bucket
            // we could start with any of the others, but the octagonal
            // bucket will have the fewest elements
            solution[8] = nums[8][j];

            if (findNext(8, 1))
                break;
        }

        for (int i = 3; i < solution.length; i++) {
            res += solution[i];
        }

        return res;
    }

    /*
        Finds the next number in the chain.
    */
    private static boolean findNext(int last, int length) {
        // goes through all possible numbers generated in nums
        for (int i = 3; i < solution.length; i++) {
            // don't include numbers from the same polygon bucket
            if (solution[i] != 0)
                continue;

            for (int j = 0; j < nums[i].length; j++) {
                boolean unique = true;

                // ensures the number is different from others in the chain
                for (int k = 3; k < solution.length; k++) {
                    if (nums[i][j] == solution[k]) {
                        unique = false;
                        break;
                    }
                }

                // checks if the number is cyclic
                // if so, add it to the chain in solution[]
                if (unique && ((nums[i][j] / 100) == (solution[last] % 100))) {
                    solution[i] = nums[i][j];

                    // length = length of current chain
                    // if it's currently 5, see if we can close it
                    if (length == 5) {
                        if (solution[8] / 100 == solution[i] % 100)
                            return true;
                    }
                        
                    // recursion
                    // if it returns true, we found a valid chain

                    // if it returns false, remove the last number added to the
                    // chain and continue the search until we find a valid
                    // number, or if we couldn't find any numbers (return false)
                    if (findNext(i, length + 1))
                        return true;
                }
            }

            solution[i] = 0;
        }

        return false;
    }

    private static int[] generateCyclicNums(int polygon) {
        List<Integer> nums = new ArrayList<>();

        int n = 0;
        int number = 0;

        while (number < 10000) {
            n++;
            switch (polygon) {
                case 3:
                    number = n * (n + 1) / 2;
                    break;
                case 4:
                    number = n * n;
                    break;
                case 5:
                    number = n * (3 * n - 1) / 2;
                    break;
                case 6:
                    number = n * (2 * n - 1);
                    break;
                case 7:
                    number = n * (5 * n - 3) / 2;
                    break;
                case 8:
                    number = n * (3 * n - 2);
                    break;
            }

            if (number > 999 && number < 10000)
                nums.add(number);
        }

        // converts List<Integer> to int[] using stream from Java 8
        return nums.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(sumSixCyclicFourDigitNums());
    }
}