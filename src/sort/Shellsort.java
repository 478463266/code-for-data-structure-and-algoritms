package sort;
import utilRW.ArrayRW;
import java.util.Arrays;

public class Shellsort {

    public static void shellSort(int[] array){
        int i,j,gap;
        int size = array.length;
        for (gap = size/ 2;  gap > 0; gap = gap/ 2) {
            //将数组变为gap有序
            for (i = gap; i < size; i++) {
                //将array[i]插入到array[i-gap], a[i-2*gap], a[i-3*gap]中
                //从第gap个元素，逐个对其所在组进行直接插入排序操作
                int tmp = array[i];
                for(j = i; j >= gap; j -= gap){
                    //移动法插入有序
                    if(tmp < array[j - gap]){
                        array[j] = array[j - gap];
                    }
                    else
                        break;
                }
                array[j] = tmp;
            }

        }
    }


    public static void main(String[] args) {

        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 5};
        //Arrays.sort(test);
        shellSort(test);
        ArrayRW arr = new ArrayRW();
        arr.printArray(test);

    }
}
