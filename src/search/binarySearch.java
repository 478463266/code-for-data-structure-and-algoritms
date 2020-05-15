package search;

import java.util.Arrays;

public class binarySearch {

    public static int binarysearch(int[] array, int value, int left, int right){
        //recursion
        if (array == null || array.length == 0)
            return -1;

        int mid = left + (right - left) / 2;

        //一定要有判断条件
        if (left <= right) {
            if (array[mid] == value)
                return mid;
            else if (array[mid] > value)
                return binarysearch(array, value, left, mid - 1);
            else
                return binarysearch(array, value, mid + 1, right);
        }
        else
            return -1;
    }

    public static int binarysearch2(int[] array, int value)
    {
        //non-recursion
        if (array == null || array.length == 0)
            return -1;

        int left = 0, right = array.length;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            if (array[mid] == value)
                return mid;
            else if (array[mid] > value)
                right = mid - 1;
            else left = mid + 1;
        }

        return -1;
    }


    public static void main(String[] args) {
        int[] test = {1, 4, 5, 55, 7, 0, 21, 3};
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
        //System.out.println(binarysearch(test, 55, 0, test.length - 1));
        //System.out.println(binarysearch(test, 9, 0, test.length - 1));

        System.out.println(binarysearch2(test, 7));
        System.out.println(binarysearch2(test, 10));
    }

}
