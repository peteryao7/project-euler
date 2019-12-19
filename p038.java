import java.util.Arrays;

class p038 {
    /*
     * Take the number 192 and multiply it by each of 1, 2, and 3:
     * 
     * 192 × 1 = 192 
     * 192 × 2 = 384 
     * 192 × 3 = 576 
     * 
     * By concatenating each product we get the 1 to 9 pandigital, 192384576. 
     * We will call 192384576 the concatenated product of 192 and (1,2,3)
     * 
     * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
     * and 5, giving the pandigital, 918273645, which is the concatenated product of
     * 9 and (1,2,3,4,5).
     * 
     * What is the largest 1 to 9 pandigital 9-digit number that can be formed as
     * the concatenated product of an integer with (1,2, ... , n) where n > 1?
     * 
     * -----
     * 
     * We know from Q32 how to check if a number is pandigital, but how can we 
     * limit our constraints?
     * 
     * From the question, we know that there is a 1 through 9 pandigital number
     * that starts with 9, which removes any possibility of the first digit
     * being anything else. Is there a pandigital number greater than 918273645?
     * We know that because n > 1, we cannot do 987654321 * 1. Since that means
     * n >= 2, then the max digit count of the fixed number x must be <= 4.
     * 
     * If x is a 2-digit number, all resulting numbers will have < 9 digits with
     * n <= 3, and > 9 with n >= 4, excluding the range 90 <= x < 100. 
     * 
     * If x is a 3-digit number, all resulting numbers will not have 9 digits, 
     * excluding the range 900 <= x < 1000.
     * 
     * If x is a 4-digit number, you are guaranteed to result in a 9-digit number,
     * so our first valid range is 9000 <= x < 10000. Furthermore, because the 
     * result must be pandigital, our range is now 9123 <= x <= 9876. Because 
     * n >= 2, the second digit must be <= 4, so that reduces the range to 
     * 9123 <= x <= 9487. Additionally, we can raise the lower bound to 9213, as
     * 9123 will not result in a 9-digit number. Finally, none of the first 4
     * digits can contain a 1, as we will otherwise end up with two 1s in the solution.
     * 
     * Our final search space is 9234 <= x <= 9487
     * 
     * If we multiply the inequality by 2, we get the interval 18468 <= x <= 18974.
     * Concatenating x and 2*x can be done with 100000*x + 2*x, or 100002*x, so we
     * can plug in our 4-digit numbers from the range above, and check if the result
     * is pandigital.
     */
    public static int getLargestPandigital() {
        int res = 0;

        for (int i = 9487; i >= 9234; i--) {
            res = 100002 * i;
            if (isPandigital(Integer.toString(res)))
                return res;
        }

        return -1;
    }

    private static boolean isPandigital(String s) {
        if (s.length() != 9)
            return false;
        char[] c = s.toCharArray();
        Arrays.sort(c);

        return new String(c).equals("123456789"); // 1 through 9 pandigital
    }

    public static void main(String[] args) {
        System.out.println(getLargestPandigital());
    }
}