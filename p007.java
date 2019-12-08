class p007 {
    /* 
        What is the 10001st prime number?

        Brute force it. There are more efficient ways to calculate
        this, like the Sieve of Eratosthenes, but since the input
        is low, more efficient methods aren't necessary.
    */
    public static int getNthPrime(int n) {
        int count = 0;
        int prime = 2;

        while (true) {
            if (isPrimeNumber(prime)) {
                count++;
                if (count == 10001)
                    return prime;
            }
            prime++;
        }
    }

    // helper function for determining if the number is prime
    // even numbers can't be prime, 2 and 3 will be prime
    // only need to iterate up to sqrt(num) + 1 since factors
    // past that have already been considered with smaller factors
    private static boolean isPrimeNumber(int num) {
        if (num <= 1)
            return false;
        else if (num == 2 || num == 3)
            return true;
        else if (num % 2 == 0)
            return false;

        int sqrt = (int) Math.sqrt(num) + 1;

        for (int i = 3; i < sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(getNthPrime(10001));
    }
}