import java.util.*;

class p075 {
    /*
        https://projecteuler.net/problem=75
        
        It turns out that 12 cm is the smallest length of wire that can be bent
        to form an integer sided right angle triangle in exactly one way, but
        there are many more examples.

        12 cm: (3,4,5)
        24 cm: (6,8,10)
        30 cm: (5,12,13)
        36 cm: (9,12,15)
        40 cm: (8,15,17)
        48 cm: (12,16,20)

        In contrast, some lengths of wire, like 20 cm, cannot be bent to form
        an integer sided right angle triangle, and other lengths allow more
        than one solution to be found; for example, using 120 cm it is possible
        to form exactly three different integer sided right angle triangles.

        120 cm: (30,40,50), (20,48,52), (24,45,51)

        Given that L is the length of the wire, for how many values of L ≤
        1,500,000 can exactly one integer sided right angle triangle be formed?

        -----

        Euclid's Formula:
        One way to generate unique Pythagorean triples is with Euclid's
        formula, which you can find here:
        https://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple

        In summary, for integers m, n where m > n > 0 and m, n are coprime, 
        then you can generate a Pythagorean triple with sides a, b, c such
        that:
        a = m^2 - n^2, b = 2mn, c = m^2 + n^2

        Multiples of a, b, c will generate non-primitive triples for the same
        m, n. To find new, primitive triples, generate a unique pair of
        coprime integers for m, n.

        We can use the above to generate every triple whose sums add up to at
        most 1500000, and keep track of the frequency of each perimeter. Count
        the number of entries with only 1 triple and return that as the answer.
    */
    public static int singularIntegerTriplets(int limit) {
        int[] triples = new int[limit + 1];
        int res = 0;
        int mLimit = (int) Math.sqrt(limit / 2);

        for (int m = 2; m < mLimit; m++) {
            for (int n = 1; n < m; n++) {
                if (((m + n) % 2) == 1 && gcd(m, n) == 1) {
                    int a = m * m - n * n;
                    int b = 2 * m * n;
                    int c = m * m + n * n;
                    int p = a + b + c;

                    while (p <= limit) {
                        triples[p]++;
                        p += a + b + c;
                    }
                }
            }
        }

        for (int t : triples) {
            if (t == 1) {
                res++;
            }
        }

        return res;
    }

    public static int gcd(int m, int n) {
        while (n != 0) {
            int a = m % n;
            m = n;
            n = a;
        }

        return m;
    }

    public static void main(String[] args) {
        System.out.println(singularIntegerTriplets(1500000));
    }
}