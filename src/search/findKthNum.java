package search;

/*
方法一：利用快排的思想，时间复杂度O(n),如下所示
方法二：利用冒泡排序或者简单选择排序，K次选择后即可得到第k大的数。总的时间复杂度为O(n*k)
方法三：维护一个k大小的最小堆，对于数组中的每一个元素判断与堆顶的大小，若堆顶较大，则不管，否则，弹出堆顶，将当前值插入到堆中。时间复杂度O(n * logk)
方法四：对这个乱序数组用堆排序、快速排序、或者归并排序算法按照从大到小先行排序，然后取出前k大，总的时间复杂度为O(n*logn + k)
方法五：利用hash保存数组中元素Si出现的次数，利用计数排序的思想，线性从大到小扫描过程中，前面有k-1个数则为第k大数，平均情况下时间复杂度O(n)
方法六：用O(4*n)的方法对原数组建最大堆，然后pop出k次即可。时间复杂度为O(4*n + k*logn)。
方法七：大数据量的情况
    最直观：小顶堆（大顶堆 -> 最小100个数）；
    较高效：Quick Select算法；

    e.g.,先拿10000个数建堆，然后一次添加剩余元素，如果大于堆顶的数（10000中最小的），将这个数替换堆顶，并调整结构使之仍然是一个最小堆，
这样，遍历完后，堆中的10000个数就是所需的最大的10000个。建堆时间复杂度是O（mlogm），算法的时间复杂度为O（nmlogm）（n为10亿，m为10000）

进一步优化：
：可以把所有10亿个数据分组存放，比如分别放在1000个文件中。这样处理就可以分别在每个文件的10^6个数据中找出最大的10000个数，
合并到一起在再找出最终的结果。

 */
public class findKthNum {

    public static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static int partition(int[] array, int left, int right)
    {
        int pivot = array[right];
        int i = left;//i是分界线，j是循环
        for (int j = left; j < right; j++) {
            if (array[j] > pivot){
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }

    public static int findknum(int[] array, int k, int left, int right)
    {
        int len = array.length;
        if (array == null || len == 0 || k > len)
            return -1;

        if (left == right) {
            printkArr(array, left + 1);
            System.out.println();
            return array[left];
        }

        int pivot = partition(array, left, right);

        //注意上面的partition是递减排序
        int index = pivot + 1;

        if (index == k) {
            printkArr(array, pivot + 1);
            System.out.println();
            return array[pivot];
        }
        else if (index < k)
            return findknum(array, k - index , pivot + 1,  right);
        else
            return findknum(array, k, left, pivot - 1);
    }

    //返回前k大
    public static void printkArr(int[] array, int k)
    {
        for (int i = 0; i < k; i++) {
            System.out.print(array[i]+" ");
        }
    }

    public static void main(String[] args) {
        int[] test = {9, 8, 7, 111, 3, 1, 6, 4, 2};
        System.out.println(findknum(test, 1, 0 , test.length - 1));
    }
}
