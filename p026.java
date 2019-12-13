class p026 {
    /*
        A unit fraction contains 1 in the numerator. For example, 1/6 is a
        unit fraction, and its decimal representation is 0.1(6), where 6 
        repeating. It has a 1-digit recurring cycle. 

        1/7 is also a unit fraction and its decimal representation is 
        0.(142857), which has a 6-digit recurring cycle.

        Find the value of d < 1000 for which 1/d contains the longest 
        recurring cycle in its decimal fraction part.

        Fermat's Little Theorem:
        FLT states that if p is a prime number, then for any integer a, then
        a^p - a is an integer multiple of p. In other words,

        a^p ≡ a (mod p)

        Applying it to our problem, we c an say that 1/d has a cycle of n
        digits if 10^n - 1 mod d = 0 for prime d. d is known as a full reptend
        prime. What we need to find now is the first reptend prime below the limit.

        To speed things up, when finding the recurrence cycle for each i, we can
        sieve i to make sure i is actually prime first.
    */
    public static int getMaxRecurrenceCycle(int max) {
        int maxCycleIdx = 0;
        int maxCycle = 0;

        for (int i = 2; i < max; i++) {
            int recurringCycle = getRecurringCycle(i);
            if (recurringCycle > maxCycle) {
                maxCycle = recurringCycle;
                maxCycleIdx = i;
            }
        }

        return maxCycleIdx;
    }

    private static int getRecurringCycle(int n) {
        int[] arr = new int[n + 1];
        int i = 1;
        int mod = 1;

        while (mod != 0 && arr[mod] == 0) {
            arr[mod] = i++;
            mod = mod * 10 % n;
        }

        if (mod == 0)
            return 0;
        
        return i - arr[mod];
    }

    public static void main(String[] args) {
        System.out.println(getMaxRecurrenceCycle(1000));
    }
}