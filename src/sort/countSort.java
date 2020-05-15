package sort;

import utilRW.ArrayRW;

//计数排序需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]，[10000~19999] 这样的数据
//idea: 直接把 arr[i] 放到它输出数组中的位置上。假设有5个数小于 arr[i]，所以 arr[i] 应该放在数组的第6个位置上
public class countSort {
    public static void countsort(int[] array)
    {
        if (array == null || array.length == 0 || array.length == 1)
            return;

        int len = array.length;
        int min = array[0], max = array[0];
        for (int i = 0; i < len; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }

        int[] help = new int[max - min + 1];

        for (int i = 0; i < len; i++) {
            int position = array[i] - min;
            help[position]++;
        }

        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i] > 0)
            {
                help[i]--;
                array[index++] = i + min;
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 5};
        //Arrays.sort(test);
        countsort(test);
        ArrayRW arr = new ArrayRW();
        arr.printArray(test);
    }
}
