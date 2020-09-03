import java.math.*;

class p064 {
    /*
        https://projecteuler.net/problem=64

        All square roots are periodic when written as continued fractions and
        can be written in the form:
        sqrt(N) = a_0 + 1 / (a_1 + 1 / (a_2 + 1 / (a_3 + ...)))

        For example, let us consider sqrt(23):
        sqrt(23) = 4 + sqrt(23) - 4 = 4 + 1 / (1 / (sqrt(23) - 4)) = 4 + 1 / (1 + (sqrt(23) - 3) / 7)

        If we continue we would get the following expansion:
        sqrt(23) = 4 + 1 / (1 + 1 / (3 + 1 / (1 + 1 / (8 + ...))))

        The process can be summarised as follows:
        a_0 = 4, 1 / (sqrt(23) - 4) = (sqrt(23) + 4) / 7 = 1 + (sqrt(23) - 3) / 7
        a_1 = 1, 7 / (sqrt(23) - 3) = 7 * (sqrt(23) + 3) / 14 = 3 + (sqrt(23) - 3) / 2
        a_2 = 3, 2 / (sqrt(23) - 3) = 2 * (sqrt(23) + 3) / 14 = 1 + (sqrt(23) - 4) / 7
        a_3 = 1, 7 / (sqrt(23) - 4) = 7 * (sqrt(23) + 4) / 7 = 8 + (sqrt(23) - 4)
        a_4 = 8, 1 / (sqrt(23) - 4) = (sqrt(23) + 4) / 7 = 1 + (sqrt(23) - 3) / 7
        a_5 = 1, 7 / (sqrt(23) - 3) = 7 * (sqrt(23) + 3) / 14 = 3 + (sqrt(23) - 3) / 2
        a_6 = 3, 2 / (sqrt(23) - 3) = 2 * (sqrt(23) + 3) / 14 = 1 + (sqrt(23) - 4) / 7
        a_7 = 1, 7 / (sqrt(23) - 4) = 7 * (sqrt(23) + 4) / 7 = 8 + sqrt(23) - 4

        It can be seen that the sequence is repeating. For conciseness, we use
        the notation sqrt(23) = [4;(1,3,1,8)], to indicate that the block
        (1,3,1,8) repeats indefinitely.

        The first ten continued fraction representations of (irrational)
        square roots are:
        sqrt(2) = [1;(2)], period = 1
        sqrt(3) = [1;(1,2)], period = 2
        sqrt(5) = [2;(4)], period = 1
        sqrt(6) = [2;(2,4)], period = 2
        sqrt(7) = [2;(1,1,1,4)], period = 4
        sqrt(8) = [2;(1,4)], period = 2
        sqrt(10) = [3;(6)], period = 1
        sqrt(11) = [3;(3,6)], period = 2
        sqrt(12) = [3;(2,6)], period = 2
        sqrt(13) = [3;(1,1,1,1,6)], period = 5

        Exactly four continued fractions, for N <= 13, have an odd period.

        How many continued fractions for N <= 10000 have an odd period?

        -----

        Continued fraction expansion in canonical form:
        There are some handy formulas in this Wikipedia article that will
        calculate the length of the period of a continued fraction expansion:
        https://en.wikipedia.org/wiki/Periodic_continued_fraction#Canonical_form_and_repetend

        For some natural number S, let m_0 = 0, d_0 = 1, a_0 = sqrt(S). Then:
        m_(n+1) = d_n * a_n - m_n
        d_(n+1) = (S - m_(n+1) * m_(n+1)) / d_n
        a_(n+1) = floor((a_0 + m_(n+1)) / d_(n+1))
        until a_i = 2 * a_0.

        The computations will terminate when these triplet of equations is 
        the same as the one encountered before, or when a_i = 2 * a_0.

        Perform this subroutine on every single number from 2 to 10000
        excluding perfect squares and count the numbers where the length of
        the period is odd.

        TC/SC - O(n*sqrt(n))/O(1)
    */
    public static int oddPeriodSquareRoots(int max) {
        int res = 0;

        for (int i = 2; i <= max; i++) {
            int limit = (int) Math.sqrt(i);
            if (limit * limit == i)
                continue;

            int period = 0;
            int d = 1, m = 0, a = limit;

            do {
                m = d * a - m;
                d = (i - m * m) / d;
                a = (limit + m) / d;
                period++;
            } while (a != 2 * limit);

            if (period % 2 == 1)
                res++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(oddPeriodSquareRoots(10000));
    }
}