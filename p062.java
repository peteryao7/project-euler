import java.util.*;
import java.math.BigInteger;

class p062 {
    /*
        https://projecteuler.net/problem=62

        The cube, 41063625 (345^3), can be permuted to produce two other
        cubes: 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the
        smallest cube which has exactly three permutations of its digits which
        are also cube.

        Find the smallest cube for which exactly five permutations of its
        digits are cube.

        -----

        Brute force:
        Generate every base from 1 to infinity and compute its cube. To compare
        digits, we can convert them into a char array and sort all the digits.

        To keep track of which cubes generate the same digits, use a map where
        the digits represented as a string is the key, and a list of its bases
        as the value.

        Using long here was mostly as a preliminary test for the bounds. If we
        had to go higher, then we could've used BigInteger instead.
    */
    public static long findSmallestCubePermutations(int n) {
        // <digits in sorted order, list of numbers that generate the digits>
        Map<String, List<Long>> freqs = new HashMap<>();
        long i = 0;

        while (true) {
            String digits = getDigits(i);

            freqs.computeIfAbsent(digits, k -> new ArrayList<Long>()).add(i);

            // get the first (smallest) number and return its cube
            if (freqs.get(digits).size() == n) {
                long base = freqs.get(digits).get(0);
                return base * base * base;
            }

            i++;
        }
    }

    private static String getDigits(long i) {
        long cube = i * i * i;
        char[] digits = Long.toString(cube).toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }

    public static void main(String[] args) {
        System.out.println(findSmallestCubePermutations(5));
    }
}