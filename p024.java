class p024 {
    /*
     * A permutation is an ordered arrangement of objects. For example, 3124 is one
     * possible permutation of the digits 1, 2, 3 and 4. If all of the permutations
     * are listed numerically or alphabetically, we call it lexicographic order. The
     * lexicographic permutations of 0, 1 and 2 are:
     * 
     * 012 021 102 120 201 210
     * 
     * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4,
     * 5, 6, 7, 8 and 9?
     * 
     * Do as the question says. Assuming 0123456789 is the very first permutation, 
     * we can calculate the next lexicographical permutation. More details below.
     * 
     * Then we just need to find the next 999999 permutations in lexicograhic order,
     * and return the last one.
     */
    public static String getPermutation(int n) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++)
            arr[i] = i;

        for (int i = 0; i < 999999; i++) {
            permuteNext(arr);
        }

        String res = "";

        for (int i = 0; i < arr.length; i++)
            res += arr[i];

        return res;
    }

    /*
        Finding the next lexicographical permutation:
        We want to compute the next permutation by increasin the sequence
        as little as possible, where we try to modify the rightmost elements
        and leave the left side unchanged.

        First, identify the longest non-increasing suffix. This suffix will
        already be the highest permutation, so we can't make a next permutation
        simply by modifying it, we need to modify elements to the left of it.

        Second, look at the element immediately to the left of the suffix and
        use it as a pivot to swap with the smallest element in the suffix. This
        minimizes the prefix. 

        Finally, we sort the suffix in non-decreasing order since we increased the
        prefix, so we want the make the new suffix as low as possible. As a 
        matter of fact, we can just reverse the suffix, since the replaced element
        respects the non-increasing order.
    */
    private static boolean permuteNext(int[] arr) {
        // find longest non-increasing suffix
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            i--;
        // i is now the head index of the suffix
        
        // return false if we're at the final permutation (strictly decreasing)
        if (i <= 0)
            return false;

        // set arr[i - 1] to be the pivot
        // and find the rightmost elements greater than the pivot
        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1])
            j--;
        // set arr[j] to be the new pivot

        // swap pivot with j
        swap(arr, i - 1, j);

        // reverse the suffix
        j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }

        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(1000000));
    }
}