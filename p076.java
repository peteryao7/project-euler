import java.util.*;

class p076 {
    /*
        It is possible to write five as a sum in exactly six different ways:

        4 + 1
        3 + 2
        3 + 1 + 1
        2 + 2 + 1
        2 + 1 + 1 + 1
        1 + 1 + 1 + 1 + 1

        How many different ways can one hundred be written as a sum of at
        least two positive integers?

        -----

        DP:
        This is a fairly standard DP problem. You can think of it as a coin
        change problem where the target is 100 and the denominations are every
        integer from 1 to 99 inclusive.
    */
    public static long waysToCount(int target) {
        long[] dp = new long[target + 1];
        dp[0] = 1;

        for (int i = 1; i < target; i++) {
            for (int j = i; j <= target; j++) {
                dp[j] += dp[j - i];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(waysToCount(100));
    }
}