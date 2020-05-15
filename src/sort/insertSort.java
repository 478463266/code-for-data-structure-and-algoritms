package sort;

import utilRW.ArrayRW;

public class insertSort {

    public static void insertsort(int[] array){
        int i, j;
        int temp;
        int len = array.length;
        System.out.println(len);

        if (array == null || len == 0 || len == 1)
            return;

        for (i = 1; i < len; i++) {
            temp = array[i];
            for (j = i;  j > 0 && array[j - 1] > temp; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }

    }

    public static void main(String[] args) {
        int[]  test = {2, 8, 3, 1, 9, 7, 4, 6, 11, 5};
        insertsort(test);
        ArrayRW arr = new ArrayRW();
        arr.printArray(test);
    }

}
