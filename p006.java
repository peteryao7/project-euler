class p006 {
    /*
     * Find the difference between the sum of the squares of the first one hundred
     * natural numbers and the square of the sum.
     * 
     * The sum of squares of the first 10 natural numbers is:
     * 1^2 + 2^2 + ... + 10^2 = 385
     * 
     * The square of the sum of the first 10 natural numbers is:
     * (1 + 2 + ... + 10)^2 = 3025
     * 
     * 3025 - 386 = 2640
     * 
     * We can brute force the solution for 100 since it's not a large number, but
     * we can generalize the solution.
     * 
     * Let n be the last number of the series, so in this case we are calculating
     * the answer for 100.
     * 
     * Derived with induction:
     * The sum of squares can be calculated with the formula n(n+1)/2
     * The square of sums can be calculated with the formula n(n+1)(2n+1)/6
     * 
     * Square the sum and subtract the square from it to get your answer.
     */
    public static int sumSqDiff(int n) {
        int sumOfSq = n * (n + 1) / 2;
        int sqOfSum = n * (n + 1) * (2 * n + 1) / 6;
        return sumOfSq * sumOfSq - sqOfSum;
    }

    public static void main(String[] args) {
        System.out.println(sumSqDiff(100));
    }
}