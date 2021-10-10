import java.util.*;

class p074 {
    /*
        https://projecteuler.net/problem=74

        The number 145 is well known for the property that the sum of the
        factorial of its digits is equal to 145:

        1! + 4! + 5! = 1 + 24 + 120 = 145

        Perhaps less well known is 169, in that it produces the longest chain
        of numbers that link back to 169; it turns out that there are only
        three such loops that exist:

        169 → 363601 → 1454 → 169
        871 → 45361 → 871
        872 → 45362 → 872

        It is not difficult to prove that EVERY starting number will eventually
        get stuck in a loop. For example,

        69 → 363600 → 1454 → 169 → 363601 (→ 1454)
        78 → 45360 → 871 → 45361 (→ 871)
        540 → 145 (→ 145)

        Starting with 69 produces a chain of five non-repeating terms, but the
        longest non-repeating chain with a starting number below one million
        is sixty terms.

        How many chains, with a starting number below one million, contain
        exactly sixty non-repeating terms?

        -----

        Brute Force:
        For each starting number below 1 million, use a set to track the chain
        and return the size of the set when a previously seen value is found,
        indicating a cycle.

        To speed up the solution, we can memoize previously seen chain lengths
        in an array or map so we don't need to recalculate them. Some of the
        lengths are already given in the problem statement, so we can just set
        them manually.
    */
    static int[] FACTORIALS = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static int totalMaxFactorialChain(int chainTargetLength, int startMax) {
        int res = 0;

        for (int i = 0; i < startMax; i++) {
            if (getChainLength(i, chainTargetLength) == chainTargetLength) {
                res++;
            }
        }

        return res;
    }

    public static int getChainLength(int n, int chainTargetLength) {
        Set<Integer> set = new HashSet<>();

        while (set.size() <= chainTargetLength) {
            if (!set.add(n)) {
                return set.size();
            }

            n = getFactorialSum(n);
        }

        return set.size();
    }

    public static int getFactorialSum(int n) {
        int res = 0;

        while (n > 0) {
            res += FACTORIALS[n % 10];
            n /= 10;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(totalMaxFactorialChain(60, 1000000));
    }
}