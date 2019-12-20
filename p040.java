class p040 {
    /*
     * An irrational decimal fraction is created by concatenating the positive
     * integers:
     * 
     * 0.123456789101112131415161718192021...
     * 
     * It can be seen that the 12th digit of the fractional part is 1.
     * 
     * If d_n represents the n-th digit of the fractional part, find the value 
     * of the following expression.
     * 
     * d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000
     * 
     * -----
     * 
     * The brute force option is to simply generate all numbers up to 1000000
     * and retrieve the necessary digits. This is pretty expensive time and space
     * wise to calculate, though. 
     * 
     * Note that for this question, you only need to append numbers up to 185185.
     * 
     * A more efficient way is to calculate the digits beforehand using a closed 
     * formula. Note that there's 9 1-digit numbers, 90 2-digits numbers, 900 
     * 3-digit numbers and so on, so we can make a closed formula to determine
     * the number of digits. You can also find the digit you want with this formula:
     * 
     * t = 10^(k-1) + floor((n-r-1) / k)
     * 
     * where:
     * t = the newly created number
     * k = the current "block" of digits, in this case those with 6 digits
     * 10^(k-1) = the offset of digits
     * n = original index
     * r = upper bound of the previous "block" of digits, in this case 99999
     */
    public static int prodChampernowneConstantBF(int pow) {
        StringBuilder sb = new StringBuilder();

        // i < 185186 works too
        for (int i = 1; i < Math.pow(10, pow); i++) {
            sb.append(i);
        }

        int res = 1;
        String s = sb.toString();

        for (int i = 0; i <= pow; i++) {
            res *= s.charAt((int) (Math.pow(10, i) - 1)) - '0';
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(prodChampernowneConstantBF(6));
    }
}