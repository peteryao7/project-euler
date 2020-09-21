class p069 {
    /*
        https://projecteuler.net/problem=69

        Euler's Totient function, φ(n) [sometimes called the phi function], is
        used to determine the number of numbers less than n which are
        relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all
        less than nine and relatively prime to nine, φ(9)=6.

        n	Relatively Prime	φ(n)	n/φ(n)
        2	1	                1	    2
        3	1,2	                2	    1.5
        4	1,3	                2	    2
        5	1,2,3,4	            4	    1.25
        6	1,5	                2	    3
        7	1,2,3,4,5,6	        6	    1.1666...
        8	1,3,5,7	            4	    2
        9	1,2,4,5,7,8	        6	    1.5
        10	1,3,7,9	            4	    2.5

        It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.

        Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.

        -----

        Totient function definition:
        One way to represent the totient function is this:
        Let p_1, p_2, ..., p_k be distinct primes dividing n, then

        φ(n) = n * (1 - 1 / p_1) * (1 - 1 / p_2) * ... * (1 - 1 / p_k)
        φ(n) / n = (1 - 1 / p_1) * (1 - 1 / p_2) * ... * (1 - 1 / p_k)
        n / φ(n) = 1 / [(1 - 1 / p_1) * (1 - 1 / p_2) * ... * (1 - 1 / p_k)]

        We want to maximize the last product as much as possible. That means
        minimizing the denominator as much as possible by including as many
        prime factors as we can fit in, as multiplying fractions makes the
        product smaller.

        If we take a greedy approach and keep multiplying the smallest prime
        factors together, we can eventually get a number <= n that contains
        all of them as factors. In this case, the factors will be
        2 * 3 * 5 * 7 * 11 * 13 * 17.

        TC/SC - O(sqrt(n))/O(1)
    */
    public static int totientMaximum(int max) {
        int n = 1;
        int i = 2;

        while (true) {
            if (isPrime(i)) {
                n *= i;
                if (n > max)
                    break;
            }

            i++;
        }

        return n / i;
    }

    private static boolean isPrime(int n) {
        if (n == 0 || n == 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        
        int sqrt = (int) Math.sqrt(n);

        for (int i = 3; i <= sqrt; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(totientMaximum(1000000));
    }
}