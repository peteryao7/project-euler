class p014 {

    /*
        The Collatz sequence is defined for the set of positive integers:

        n -> n/2 if n is even
        n -> 3n + 1 if n is odd

        Ex. 13 => 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1

        Although it hasn't been proven yet, all starting numbers will finish at 1.

        Which starting number under 1 million produces the longest chain?

        Brute force it. The starting number cap isn't high, but some starting 
        numbers can create a chain with a number >2^31, so we use longs.

        A more efficient way to do this is to start from the cap and use a 
        hash table containing every number from 1 to the cap, then go down.
        If we see a number in our current chain, then take it out of the 
        hash table because the chain is deterministic. If we started at a
        number that was the middle of a previous chain, then its chain can't 
        possibly be longer than the previous one since everything after it 
        will be the same, so the previous chain will be longer.
    */
    public static long longestCollatz(long max) {
        long maxChain = 0;
        long currChain = 0;
        long maxIdx = 0;


        for (long i = 1; i <= max; i++) {
            long temp = i;
            while (temp != 1) {
                if (temp % 2 == 0)
                    temp /= 2;
                else
                    temp = 3 * temp + 1;
                currChain++;
            }
            if (currChain > maxChain) {
                maxChain = currChain;
                maxIdx = i;
            }
            currChain = 0;
        }

        return maxIdx;
    }
    public static void main(String[] args) {
        System.out.println(longestCollatz(1000000));
    }
}