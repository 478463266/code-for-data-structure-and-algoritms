package sort;

import utilRW.ArrayRW;

public class mergeSort {

    public static void mergesort(int[] array, int begin, int end) {
        if (array == null || array.length <= 1)
            return;
        if (begin >= end)
            return;
        //int mid = (begin + end) / 2;
        // 计算出中间值，这种算法保证不会溢出。
        int mid = begin + ((end - begin) >> 1);

        mergesort(array, begin, mid);
        mergesort(array, mid + 1, end);
        merge(array, begin, mid, end);
    }

    public static void merge(int[] array, int begin, int mid, int end){
        //辅助数组
        int[] tmp = new int[end - begin + 1];
        int tmpPos = 0, lPos = begin, rPos = mid + 1;
        while (lPos <= mid && rPos <= end){
            if (array[lPos] <= array[rPos])
                tmp[tmpPos++] = array[lPos++];
            else
                tmp[tmpPos++] = array[rPos++];
        }

        while (lPos <= mid)
            tmp[tmpPos++] = array[lPos++];
        while (rPos <= end)
            tmp[tmpPos++] = array[rPos++];

        for (int i = 0; i < tmp.length; i++) {
            array[begin + i] = tmp[i];
        }

    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 11, 4, 6, 5};
        //Arrays.sort(test);
        mergesort(test, 0, test.length - 1);
        ArrayRW arr = new ArrayRW();
        arr.printArray(test);
    }
}
