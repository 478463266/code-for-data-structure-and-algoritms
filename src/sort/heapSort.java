package sort;

import utilRW.ArrayRW;

//建堆的时间复杂度是O(n)(调用一次);调整堆的时间复杂度是lgn,调用了n-1次,所以堆排序的时间复杂度是O(n)+O(nlgn) ~ O(nlgn)

public class heapSort {

    public static void heapsort(int[] array){
        //1. build heap
        int size = array.length;
        //从第一个非叶子结点从下至上，从右至左调整结构，i为size的父节点
        for (int i = (size - 1) / 2; i  >= 0; i--) {
            adjustHeap(array, i, size);
        }

        //2. deleteMax
        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = size - 1; i >= 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;
            //重新对堆进行调整
            adjustHeap(array,0, i);
        }
    }

    public static void adjustHeap(int[] array, int parent, int length)
    {
        //将temp作为父节点
        int tmp = array[parent];
        int lChild = 2 * parent + 1;
        while (lChild < length){ //如果有左孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && array[lChild] < array[rChild]){
                lChild++;
            }
            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (tmp > array[lChild]){
                break;
            }
            // 把孩子结点的值赋给父结点
            array[parent] = array[lChild];
            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        array[parent] = tmp;

    }


    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 11, 5};
        //Arrays.sort(test);
        heapsort(test);
        ArrayRW arr = new ArrayRW();
        arr.printArray(test);
    }
}
