class p009 {
    /*
        There exists only one Pythagorean triplet for which a + b + c = 1000.
        Find the product abc.

        Euclid's formula:
        This is a formula for generating Pythagorean triples given a pair of
        integers m, n where m > n > 0.

        The integers a, b, c such that:
        a = m^2 - n^2
        b = 2mn
        c = m^2 + n^2
        will generate a Pythagorean triple. 

        Another way to generate Pythagorean triples is Dickson's method, where
        to find integer solutions to a^2 + b^2 = c^2, find positive integers
        r, s, t such that r^2 = 2st is a perfect squaure, then:
        a = r + s
        b = r + t
        c = r + s + t
    */
    public static int findPythagTripletProd(int sum) {
        // 550 is an arbitrary limit, but it's enough
        // to generate a, b, c for sum < 3000
        for (int n = 0; n < 550; n++) {
            for (int m = n + 1; m < 550; m++) {
                int a = m * m - n * n;
                int b = 2 * m * n;
                int c = m * m + n * n;
                if (a + b + c == sum)
                    return a * b * c;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findPythagTripletProd(1000));
    }
}