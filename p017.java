class p017 {
    /*
        If the numbers 1 to 5 are written out in English, then there are 
        19 letters used in total.

        If all the numbers from 1 to 1000 inclusive were written out in
        words, how many letters would be used?

        17 => 90
        123 => 1304

        Loop through each number from 1 to 1000 and find the English conversion,
        then add its length to an accumulator.

        To prevent writing a ton of if/case statements, we can use an array of
        objects and use the number to access the number's English translation
        with its respective index.

        Alternatively, instead of using recursion and calling the same function
        for smaller values, we can write a getTens() or getHundreds() function,
        although we'd need to write multiple ones as the input gets higher.
    */
    private final static String[] ONES = {"zero", "one", "two", "three",
        "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", 
        "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
        "eighteen", "nineteen"};

    // ten is already in ONES, but keep index 1 empty to preserve order
    private final static String[] TENS = {"", "", "twenty", "thirty", "forty",
        "fifty", "sixty", "seventy", "eighty", "ninety"};

    public static int lengthInEnglish(int max) {
        if (max < 0)
            return -1;

        int sum = 0;
        for (int i = 1; i <= max; i++) {
            sum += getEnglish(i).length();
        }
        return sum;
    }

    private static String getEnglish(int n) {
        if (n < 20)
            return ONES[n];
        else if (20 <= n && n < 100)
            return TENS[n / 10] + (n % 10 != 0 ? ONES[n % 10] : "");
        else if (100 <= n && n < 1000)
            return ONES[n / 100] + "hundred" + (n % 100 != 0 ? "and" + getEnglish(n % 100) : "");
        else if (1000 <= n && n < 1000000)
            return getEnglish(n / 1000) + "thousand" + (n % 1000 != 0 ? getEnglish(n % 1000) : "");
        else {
            return "Overflow";
        }
    }
    public static void main(String[] args) {
        System.out.println(lengthInEnglish(1000));
    }
}