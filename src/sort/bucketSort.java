package sort;

import utilRW.ArrayRW;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//桶排序要求数据的分布必须均匀，否则可能导致数据都集中到一个桶中
//idea：把数组 arr 划分为n个大小相同子区间（桶），每个子区间各自排序，最后合并
//1.找出待排序数组中的最大值max、最小值min
//2.我们使用 动态数组ArrayList 作为桶，桶里放的元素也用 ArrayList 存储。桶的数量为(max-min)/arr.length+1
//3.遍历数组 arr，计算每个元素 arr[i] 放的桶
//4.每个桶各自排序
//5.遍历桶数组，把排序好的元素放进输出数组
public class bucketSort {

    public static void bucketsort(int[] array)
    {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        //桶数
        int bucketNum = (max - min) / array.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<> (bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }

        //将每个元素放入桶
        for (int i = 0; i < array.length; i++) {
            int num = (array[i] - min) / (array.length);
            bucketArr.get(num).add(array[i]);
        }

        //对每个桶进行排序
        for (int i = 0; i < bucketArr.size(); i++) {
            Collections.sort(bucketArr.get(i));
        }

        System.out.println(bucketArr.toString());

    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 11, 5};
        bucketsort(test);
    }
}
