import java.util.*;

class p078 {
    /*
        https://projecteuler.net/problem=78

        Let p(n) represent the number of different ways in which n coins can be
        separated into piles. For example, five coins can be separated into
        piles in exactly seven different ways, so p(5)=7.

        OOOOO
        OOOO   O
        OOO   OO
        OOO   O   O
        OO   OO   O
        OO   O   O   O
        O   O   O   O   O

        Find the least value of n for which p(n) is divisible by one million.

        -----

        DP:
        This is another DP problem like the previous 2 problems, but here, we
        need to find the first value where dp[i] % 1000000 == 0. Because of how
        high the limit can be, we either need to use BigInteger or take mod 
        1000000 of each dp[i] as we add each number of ways. This ensures dp[i]
        will stay below 1000000 and won't affect whether it's divisible by it.
        Then we can loop through dp[] and find the first value containing 0,
        then return its index.
    */
    public static int partitionsDivisibleBy(int limit) {
        int MOD = 1000000;
        int[] dp = new int[limit + 1];
        dp[0] = 1;

        for (int i = 1; i < limit; i++) {
            for (int j = i; j <= limit; j++) {
                dp[j] = (dp[j] + dp[j - i]) % MOD;
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(partitionsDivisibleBy(69420));
    }
}