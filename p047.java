class p047 {
    /*
     * The first two consecutive numbers to have two distinct prime factors are:
     * 
     * 14 = 2 × 7 
     * 15 = 3 × 5
     * 
     * The first three consecutive numbers to have three distinct prime factors are:
     * 
     * 644 = 2^2 × 7 × 23 
     * 645 = 3 × 5 × 43 
     * 646 = 2 × 17 × 19
     * 
     * Find the first four consecutive integers to have four distinct prime factors
     * each. What is the first of these numbers?
     * 
     * -----
     * 
     * We can use a modified version of the sieve that will keep track of the
     * consecutive number of numbers that have 4 distinct prime factors. The
     * code below works for any number of distinct prime factors and length
     * of the sequence you want.
     * 
     * The only issue is you need to specify a high enough max value to 
     * stop iterating, since you don't know where the sequence will be.
     */
    public static int getFirstDistinctPrimeSeq(int max, int numFactors, int seqLength) {
        int count = 0;
        int[] primes = new int[max + 1];

        for (int i = 2; i <= max; i++) {
            if (primes[i] == numFactors) {
                count++;
                if (count == seqLength)
                    return i - seqLength + 1;
            }
            else {
                count = 0;
                if (primes[i] == 0) {
                    for (int j = i; j <= max; j += i)
                        primes[j]++;
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) {
        System.out.println(getFirstDistinctPrimeSeq(1000000, 4, 4));
    }
}