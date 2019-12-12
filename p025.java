class p025 {
    /*
     * What is the index of the first term in the Fibonacci sequence to contain 1000
     * digits?
     * 
     * Binet's Formula: We don't actually need to compute any Fibonacci number. A
     * variation of Binet's formula will give us the n-th Fibonacci number.
     * 
     * For reference:
     * https://en.wikipedia.org/wiki/Fibonacci_number#Computation_by_rounding
     * 
     * From Wikipedia, the n-th Fibonacci number is:
     * F_n = φ^n / sqrt(5) where n >= 0 and for φ = (1 + sqrt(5))/2, the golden ratio
     * 
     * We want the first Fibonacci number with 1000 digits, so we can define an
     * inequailty to find the first number that exceeds 10^(d-1), where d = 1000.
     * 
     * φ^n / sqrt(5) > 10^(d-1)
     * 
     * Simplifying and getting n onto one side:
     * 
     * φ^n > sqrt(5) * 10^(d-1)
     * log(φ) * n > log(5)/2 + log(10)*(d-1)
     * n > log(5)/2 + log(10)*(d-1) / log(φ)
     * 
     * Note that the log here is of base 10, not in base 2.
     * 
     * We want the closest integer, so use the ceiling function to get your answer:
     * 
     * n = ceil(log(5)/2 + log(10)*(d-1) / log(φ))
     */
    public static double firstFiboDigits(int d) {
        double phi = (1 + Math.sqrt(5)) / 2;
        return Math.ceil((Math.log10(5) / 2 + d - 1) / Math.log10(phi));
    }
    public static void main(String[] args) {
        System.out.println(firstFiboDigits(1000));
    }
}