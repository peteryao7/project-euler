import java.util.Arrays;

class p032 {
    /*
     * We shall say that an n-digit number is pandigital if it makes use of all the
     * digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1
     * through 5 pandigital.
     * 
     * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing
     * multiplicand, multiplier, and product is 1 through 9 pandigital.
     * 
     * Find the sum of all products whose multiplicand/multiplier/product identity
     * can be written as a 1 through 9 pandigital.
     * 
     * Brute force it and do as the question says. Use division instead of
     * multiplication to check for products.
     * 
     * We still need a reasonable upper bound to stop iterations at. 
     * 
     * Assume a 1 through 9 pandigital candidate (x, y, z) has z >= 10000.
     * Then x*y must be at least 5 digits. With the other 4 or fewer digits left,
     * even the upper bound of x = 99 and y = 99 means that x * y < 10000, 
     * which cannot be z. Therefore, the product z must be less than 10000 to
     * find any legitimate x and y multiplicands and multipliers.
     */
    public static int sumPandigitalProds() {
        int sum = 0;

        for (int i = 1; i < 10000; i++) {
            if (hasPandigitalProd(i))
                sum += i;
        }

        return sum;
    }

    private static boolean hasPandigitalProd(int n) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && isPandigital("" + n + i + n/i)) // "multiplying"
                return true;
        }

        return false;
    }

    private static boolean isPandigital(String s) {
        if (s.length() != 9)
            return false;
        char[] c = s.toCharArray();
        Arrays.sort(c);
        
        return new String(c).equals("123456789"); // 1 through 9 pandigital
    }

    public static void main(String[] args) {
        System.out.println(sumPandigitalProds());
    }
}