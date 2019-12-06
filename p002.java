class p002 {
    /*
     * By considering the terms in the Fibonacci sequence whose values 
     * do not exceed four million, find the sum of the even-valued terms.
     */
    public static int sumEvenFibos(int n) {
        int sum = 0;
        int x = 1; // second 1 in the Fibonacci sequence
        int y = 2;
        int z;

        while (x <= n) {
            if (x % 2 == 0)
                sum += x;
            z = x + y;
            x = y;
            y = z;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumEvenFibos(4000000));
    }
}