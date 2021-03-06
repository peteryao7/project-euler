class p012 {

    /*
        The sequence of triangle numbers is generated by adding the natural
        numbers. So the first 7 triangle numbers are 1, 3, 6, 10, 15, 21, 28.

        The factors for these 7 numbers are:
         1: 1
         3: 1, 3
         6: 1, 2, 3, 6
        10: 1, 2, 5, 10
        15: 1, 3, 5, 15
        21, 1, 3, 7, 21
        28: 1, 2, 4, 7, 14, 28

        What is the value of the first triangle number to have over 500 divisors?

        Brute force it. Calculate the triangle numbers by adding the next
        natural number for every iteration, then count its factors by going through
        the first sqrt(n) numbers.

        Note that we want to count every divisor, so if we find a number that n
        is divisible by, then we need to count the other factor that will be
        >sqrt(n).

        A more efficient way to do this is to sieve the number of divisors for each
        triangle number. For 500 divisors, though, brute forcing is enough.
    */
    public static int triangleMinDivisors(int minFactors) {
        int triangle = 0;
        int i = 1;

        while (true) {
            triangle += i;
            if (countDivisors(triangle) > minFactors)
                return triangle;
            i++;
        }
    }

    private static int countDivisors(int triangle) {
        int count = 0;
        int sqrt = (int) Math.sqrt(triangle);

        // perfect square counts as only 1 factor
        if (sqrt * sqrt == triangle)
            count++;

        // if you find a factor, count the other factor >sqrt at the same time
        for (int i = 1; i < sqrt; i++) {
            if (triangle % i == 0)
                count += 2;
        }
        
        return count;
    }

    public static void main(String[] args) {
        System.out.println(triangleMinDivisors(500));
    }
}