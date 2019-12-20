class p043 {
    /*
     * The number, 1406357289, is a 0 to 9 pandigital number because it is made up
     * of each of the digits 0 to 9 in some order, but it also has a rather
     * interesting sub-string divisibility property.
     * 
     * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note
     * the following:
     * 
     * d2d3d4=406 is divisible by 2
     * d3d4d5=063 is divisible by 3
     * d4d5d6=635 is divisible by 5
     * d5d6d7=357 is divisible by 7
     * d6d7d8=572 is divisible by 11
     * d7d8d9=728 is divisible by 13
     * d8d9d10=289 is divisible by 17
     * 
     * Find the sum of all 0 to 9 pandigital numbers with this property.
     * 
     * -----
     * 
     * The brute force option is to simply permute all 9 * 9! possible 0 through 9
     * pandigital numbers, then check each section of digits to see if
     * they're divisible by their respective prime number. But that can be 
     * slow and take forever to compute.
     * 
     * Let's try to limit our search space by making some observations:
     * 
     * Note 1: d4d5d6 must be divisible by 5. 
     *         Thus, d6 = {0, 5}
     * 
     * Note 2: d6d7d8 must be divisible by 11. 
     *         If d6 = 0, then d7 and d8 will be the same, and all digits must be unique.
     *         Therefore, d6 = 5 and we should look at all 3-digit numbers that 
     *         begin with 5, divisible by 11, and don't repeat digits.
     *         d6d7d8 = {506, 517, 528, 539, 561, 572, 583, 594}
     * 
     * Note 3: d7d8d9 must be divisible by 13.
     *         The digits must also contain no 5s and only contain unique digits.
     *         Because of note 2, d7d8 = {06, 17, 28, 39, 61, 72, 83, 94}
     *         d7d8d9 = {286, 390, 728, 832}
     * 
     * Note 4: d8d9d10 must be divisible by 17.
     *         The digits must also contain no 5s and only contain unique digits.
     *         Because of note 3, d8d9 = {86, 90, 28, 32}
     *         d8d9d10 = {289, 867, 901}
     * 
     * Note 5: d5d6d7 must be divisible by 7.
     *         By note 2, d6d7 = {50, 51, 52, 53, 56, 57, 58, 59}
     *         By note 3, d7d8 = {28, 39, 72, 83}
     *         Thus, d6d7 = {52, 53, 57, 58}
     *         d5d6d7 = {357, 952}
     * 
     * Note 6: Combining terms with unique digits, we get for the last 6 digits:
     *         d5d6d7d8d9d10 = {357289, 952867}
     * 
     * Note 7: d3d4d5 must be divisible by 3.
     *         From note 6, digits {2, 5, 7, 8} cannot be in any digit and 
     *         {3, 9} can only be in d5. That leaves 4 digits: 0, 1, 4, 6.
     *         d3d4d5 = {063, 309, 603}
     * 
     * Note 8: The only digits left are 1 and 4 for d1d2.
     *         d1d2 = {14, 41}
     * 
     * Now we make all 12 possible numbers left and remove numbers with dupe
     * digits, and our final list is:
     * 
     * {1430952867, 1460357289, 1406357289, 4130952867, 4160357289, 4106357289}
     */
    public static long sumPandigitalPrime() {
        return 1430952867L + 1460357289L + 1406357289L + 4130952867L + 
            4160357289L + 4106357289L;
    }

    public static void main(String[] args) {
        System.out.println(sumPandigitalPrime());
    }
}