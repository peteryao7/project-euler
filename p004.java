class p004 {
    // Find the largest palindrome made from the product of two 3-digit numbers.
    public static int largestPali(int start, int end) {
        int max = 0, num = 0;

        for (int i = start; i < end; i++) {
            for (int j = start; j < end; j++) {
                num = i * j;
                if (isPali(num) && num > max)
                    max = num;
            }
        }

        return max;
    }

    private static boolean isPali(int num) {
        String str = Integer.toString(num);
        StringBuilder reverse = new StringBuilder(str).reverse();

        return str.equals(reverse.toString());
    }

    public static void main(String[] args) {
        System.out.println(largestPali(100, 1000));
    }
}