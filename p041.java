import java.util.Arrays;

class p041 {
    /*
     * We shall say that an n-digit number is pandigital if it makes use of all the
     * digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
     * also prime.
     * 
     * What is the largest n-digit pandigital prime that exists?
     * 
     * -----
     * 
     * The brute force solution is to find every single prime number under a billion, 
     * then start at the end and find the first pandigital prime number. But we can
     * drastically cut down our search space and restrict our bounds.
     * 
     * We know that if the digit sum is divisible by 3, then the number itself is
     * also divisible by 3. We know what all the digits are, so let's check if they're
     * divisible by 3:
     * 
     * 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45, 45 % 3 = 0
     * 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 36, 36 % 3 = 0
     * 7 + 6 + 5 + 4 + 3 + 2 + 1 = 28, 28 % 3 != 0
     * 6 + 5 + 4 + 3 + 2 + 1 = 21, 421 % 3 = 0
     * 5 + 4 + 3 + 2 + 1 = 15, 15 % 3 = 0
     * 4 + 3 + 2 + 1 = 10, 10 % 3 = 0
     * 3 + 2 + 1 = 6, 6 % 3 = 0
     * 2 + 1 = 3, 3 % 3 = 0
     * 1 = 1, 1 % 3 != 0
     * 
     * Thus, the only pandigital numbers we can form are of length 1, 4, and 7.
     * The problem already gave us that 2143 is a 4-digit pandigital number, so
     * we can eliminate looking through 1-digit numbers.
     * 
     * Now we can simply start at 7654321, check for pandigitality and primality,
     * and decrement the number by 2 while checking for those properties until we
     * hit the first number satisfying both conditions.
     * 
     * A more efficient way to find the solution is to write a function that immediately 
     * gives you the next lowest pandigital number, then check for primality as
     * normal.
     */
    public static int largestPandigitalPrime() {
        int max = 7654321;

        for (int i = max; i >= 1234567; i -= 2) {
            if (isPandigital(Integer.toString(i)) && isPrime(i))
                return i;
        }

        max = 4321;

        for (int j = max; j >= 1234; j -= 2) {
            if (isPandigital(Integer.toString(j)) && isPrime(j))
                return j;
        }

        return -1;
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

    private static boolean isPandigital(String s) {
        if (s.length() != 7 && s.length() != 4)
            return false;
        char[] c = s.toCharArray();
        Arrays.sort(c);
        String sorted = new String(c);
        if (sorted.equals("1234567") || sorted.equals("1234"))
            return true; // 1 through 7 or 1 through 4 pandigital

        return false;
    }

    public static void main(String[] args) {
        System.out.println(largestPandigitalPrime());
    }
}