package sort;

import utilRW.ArrayRW;

public class selectSort {

    public static void selectsort(int[] array){
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {

            for (int j = i + 1; j < len - 1; j++) {
                if (array[j] < array[i])
                {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            //方法二,通过在第二个for循环中记录min的下标即可，最后一次交换即可，这样能避免不必要的交换操作
        }
    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 11, 5};
        ArrayRW arr = new ArrayRW();
        selectsort(test);
        arr.printArray(test);
    }
}

