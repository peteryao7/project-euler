class p031 {
    /*
     * In the United Kingdom the currency is made up of pound (£) and pence (p).
     * There are eight coins in general circulation:
     * 
     * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p). 
     * 
     * It is possible to make £2 in the following way:
     * 
     * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p 
     * 
     * How many different ways can £2 be made using any number of coins?
     * 
     * DP:
     * This is a classic DP question. The order of the coins doesn't matter,
     * but which coins are included in a solution is important.
     * 
     * We want to reduce the problems into easier-to-solve subproblems.
     * The set of solutions for the problem can be partitioned into 2 sets,
     * one that doesn't contain coin S_m, and another that contains at least
     * 1 S_m. If it's the former, we can solve the subproblem of making the
     * amount with S_m, and it we do use it, we can solve the subproblem of
     * making the amount - S_m with our entire set of coins.
     * 
     * Therefore, we can formulate the following, for N = amount and 
     * m = number of types of coins:
     * C(N, m) = C(N, m-1) + C(N-S_m, m)
     * 
     * with the base cases:
     * 
     * C(N,m) = 1, N = 0 - we have one solution, mark it as 1
     * C(N,m) = 0, N < 0 - no solution, negative money
     * C(N,m) = 0, N >= 1 and m <= 0 - no solution, we have money but no change
     */
    public static int totalCoinWays(int[] coins, int amount) {
        int[][] totalWays = new int[coins.length + 1][amount + 1];
        totalWays[0][0] = 1; // base case
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            for (int j = 0; j <= amount; j++) {
                totalWays[i + 1][j] = totalWays[i][j];
                if (j >= coin)
                    totalWays[i + 1][j] += totalWays[i + 1][j - coin];
            }
        }
        return totalWays[coins.length][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        int amount = 200;
        System.out.println(totalCoinWays(coins, amount));
    }
}