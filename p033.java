class p033 {
    /*
        The fraction 49/98 is a curious fraction, where 4/8 is a correct
        simplification if you cancel the 9s.

        Consider fractions like 30/50 = 3/5 to be trivial examples.

        There are 4 non-trivial examples of this type of fraction, <1 in value,
        and contain 2 digits in the numerator and denominator.

        If the product of these 4 fractions is given in its lowest common terms,
        find the value of the denominator.

        Proof by contradiction:
        Let the fraction n/d be irreducible and 10 <= n < d <= 99. n/d can be
        represented as (10*n_0 + n_1)/(10*d_0 + d_1), where n_0 < d_0 and all
        constants are natural numbers between 1 and 9 inclusive. 

        Trivial examples are those where n_0 = d_0 = 0, so we will ignore those.

        There are 4 cases to consider:
            - n_0 = d_0
            - n_0 = d_1
            - n_1 = d_0
            - n_1 = d_1
        
        Cases 1 and 4 cannot be true. Let's prove the case for case 4 by contradiction.

        Assume n_1 = d_1. Then we can cancel n_1 and d_1 and get:
            n / d = (10 * n_0 + n_1) / (10 * d_0 + d_1)
            n_0 * (10 * d_0 + d_1) = d_0 * (10 * n_0 + n_1)
            10 * n_0 * d_0 + n_0 * d_1 = 10 * n_0 * d_0 + n_1 * d_0
            n_0 * d_1 = n_1 * d_0
            n_0 * n_1 = n_1 * d_0
            n_0 = d_0
        
        But since this implies n = d, we have contradicted the fact that n < d. We
        can contradict case 1 the same way.

        Therefore, we only need to care about whether n_0 = d_1 and n_1 = d_0, so
        for these cases:
            - if n_0 = d_1, check if n_1/d_0 = n/d
            - if n_1 = d_0, check if n_0/d_1 = n/d
    */
    public static int getDenomCuriousFraction() {
        int num = 1;
        int den = 1;
        for (int d = 10; d <= 99; d++) {
            for (int n = 10; n < d; n++) {
                int n_0 = n % 10, n_1 = n / 10;
                int d_0 = d % 10, d_1 = d / 10;

                if ((n_0 == d_1 && n_1 * d == d_0 * n) || (n_1 == d_0 && n_0 * d == d_1 * n)) {
                    num *= n;
                    den *= d;
                }
            }
        }
        return den / getGCD(num, den);
    }

    private static int getGCD(int num, int denom) {
        while (denom != 0) {
            int a = num % denom;
            num = denom;
            denom = a;
        }

        return num;
    }

    public static void main(String[] args) {
        System.out.println(getDenomCuriousFraction());
    }
}