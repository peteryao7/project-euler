class p035 {
    /*
     * The number, 197, is called a circular prime because all rotations of the
     * digits: 197, 971, and 719, are themselves prime.
     * 
     * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71,
     * 73, 79, and 97.
     * 
     * How many circular primes are there below one million?
     * 
     * First, we want to get every prime number below one million using the sieve. 
     * Then, we can convert each number to a string, check for circularity by
     * appending substrings to form each respective number, and check for primality
     * in the array we constructed.
     * 
     * One way to improve this algorithm is when we check for circularity, we should
     * make sure that every digit is odd. Since we're checking for circularity, if
     * the number has an even number, then it will inevitably be in the ones digit,
     * meaning the number cannot be circular. One such example is 29.
     */
    public static int totalCircularPrimes(int max) {
        int[] allPrimes = sieve(max);
        int count = 0;

        for (int i = 0; i < allPrimes.length; i++) {
            if (isCircularPrime(i, allPrimes))
                count++;
        }

        return count;
    }

    private static boolean isCircularPrime(int n, int[] allPrimes) {
        String s = Integer.toString(n);
        for (int i = 0; i < s.length(); i++) {
            if (allPrimes[Integer.parseInt(s.substring(i) + s.substring(0, i))] == 1)
                return false;
        }
        return true;
    }

    // 0 = prime, 1 = not prime
    private static int[] sieve(int max) {
        int[] allPrimes = new int[max + 1];
        allPrimes[0] = 1;
        allPrimes[1] = 1;
        int bound = (int) Math.floor(Math.sqrt(max));

        for (int i = 4; i <= max; i += 2) {
            allPrimes[i] = 1;
        }

        for (int i = 3; i <= bound; i += 2) {
            if (allPrimes[i] == 0) {
                for (int j = i * i; j <= max; j += 2 * i) {
                    allPrimes[j] = 1;
                }
            }
        }

        return allPrimes;
    }

    public static void main(String[] args) {
        System.out.println(totalCircularPrimes(1000000));
    }
}