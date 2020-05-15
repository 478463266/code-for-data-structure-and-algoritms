package search;

import java.util.Arrays;

public class insertSearch {
    //针对二分查找的改进
    //基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。当然，差值查找也属于有序查找
    //mid=low+(key-a[low])/(a[high]-a[low])*(high-low)
    //对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择

    public static int insertsearch(int[] array, int value)
    {
        if (array == null || array.length == 0)
            return -1;

        int len = array.length;
        int left = 0, right = len - 1;

        while (right > left)
        {
            int mid = left + (value - array[left]) / (array[right] -  array[left]) * (right - left);
            if (array[mid] == value)
                return mid;
            else if (array[mid] > value)
                right = mid - 1;
            else
                left = mid + 1;
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] test = {1, 4, 5, 55, 7, 0, 21, 3};
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
        System.out.println(insertsearch(test, 7));
        System.out.println(insertsearch(test, 8));
    }
}
