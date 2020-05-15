package sort;

import utilRW.ArrayRW;

public class quickSort {

    //小数组用插排更快——Java中的宏定义
    //public static final int cutOff = 3;

    //数组交换的方法，不能直接交换
    public static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    //利用三数中值分割方法求出array快排的枢纽元
    public static int median3(int[] array, int left, int right)
    {
        int center = (left + right) / 2;
        if (array[left] > array[center])
            swap(array, left, center);
        if (array[left] > array[right])
            swap(array, left, right);
        if (array[center] > array[right])
            swap(array, center, right);

        swap(array, center, right - 1);
        return array[right - 1];
    }

    //三数中值分割法求枢纽元,直接用
    public static void quicksort(int[] array, int left, int right){

        if (left >= right)
            return;

        int pivot = median3(array, left, right);

        int i = left, j = right - 1;

        for (; ; ) {
            while (i < right && array[++i] < pivot) {
            }
            while (j > left && array[--j] > pivot) {
            }
            if (i < j)
                swap(array, i, j);
            else
                break;
        }

        if (i < right) //因为会先对i进行自加，i可能与right相等，交换后会出现问题
            swap(array, i, right - 1);

        quicksort(array, left, i - 1);
        quicksort(array, i + 1, right);
    }

    public static void quicksort(int[] array){
        int len = array.length;
        if (array == null || len == 0 || len == 1)
            return;
        quicksort2(array, 0 ,len - 1);
    }

    //普通快排法
    public static void quicksort2(int[] array, int left, int right)
    {

        if (left >= right)
            return;

        int pivot = partition2(array, left, right);
        quicksort2(array, left, pivot - 1);
        quicksort2(array, pivot + 1, right);
    }

    public static int partition1(int[] array, int left, int right)
    {
        int pivot = array[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot){
                swap(array, j, i);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }

    public static int partition2(int[] array, int left, int right)
    {
        int pivot = median3(array, left, right);
        int i = left;
        for (int j = left; j < right - 1; j++) {
            if (array[j] < pivot){
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right - 1);
        return i;
    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 11, 5};
        ArrayRW arr = new ArrayRW();
        quicksort(test);
        arr.printArray(test);

//        int[] a = {1, 2};
//        swap(a, 0, 1);
//
//        System.out.println(median3(test, 0 , 9));
//        arr.printArray(a);
    }
}
