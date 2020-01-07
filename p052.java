import java.util.Arrays;

class p052 {
    /*
     * It can be seen that the number, 125874, and its double, 251748, contain
     * exactly the same digits, but in a different order.
     * 
     * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
     * contain the same digits.
     * 
     * -----
     * 
     * Brute force it. Starting at 1, calculate its next 5 multiples of itself
     * and check if they contain the same digits by sorting them. You can easily
     * do this by changing the number into a char array.
     */
    public static int smallestMultSameDigits(int mult) {
        int i = 1;

        while (true) {
            if (i > Integer.MAX_VALUE / mult)
                throw new ArithmeticException("Overflow - input is too high");

            if (multiplesSameDigits(i, mult))
                return i;

            i++;
        }
    }

    private static boolean multiplesSameDigits(int num, int mult) {
        for (int i = 2; i <= mult; i++) {
            if (!Arrays.equals(sortDigits(num), sortDigits(i * num)))
                return false;
        }

        return true;
    }

    private static char[] sortDigits(int num) {
        char[] res = Integer.toString(num).toCharArray();
        Arrays.sort(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(smallestMultSameDigits(6));
    }
}