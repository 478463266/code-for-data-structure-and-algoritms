package sort;

import utilRW.ArrayRW;

public class bubbleSort {

    public static void bubblesort(int[] array)
    {
        int len = array.length;

        if (array == null || len == 1 || len == 0)
            return;;

        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (array[j] > array[j + 1])
                {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 11, 5};
        ArrayRW arr = new ArrayRW();
        bubblesort(test);
        arr.printArray(test);
    }
}
