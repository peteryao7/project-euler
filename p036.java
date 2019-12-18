class p036 {
    /*
     * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
     * 
     * Find the sum of all numbers, less than one million, which are palindromic in
     * base 10 and base 2.
     * 
     * Leading 0s are not included.
     * 
     * Do as the question says. The Integer class has a handy method called toString 
     * that not only converts an integer to a string, but also lets us convert between
     * different bases. Then we can simply reverse the string and check if they are
     * equal. 
     */
    public static int sumBinaryDecimalPalindromes(int max) {
        int sum = 0;

        for (int i = 0; i < max; i++) {
            if (isPalindrome(Integer.toString(i, 10)) && isPalindrome(Integer.toString(i, 2)))
                sum += i;
        }

        return sum;
    }

    private static boolean isPalindrome(String s) {
        StringBuilder reverse = new StringBuilder(s).reverse();
        return s.equals(reverse.toString());
    }

    public static void main(String[] args) {
        System.out.println(sumBinaryDecimalPalindromes(1000000));
    }
}