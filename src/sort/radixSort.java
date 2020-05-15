package sort;

//idea:

import java.util.Arrays;

public class radixSort {

    public static void radixsort(int[] array)
    {
        if (array == null || array.length == 0 || array.length == 1)
            return;

        int exp; //指数
        int max = array[0];

        //找出最大值
        for(int value : array){
            if (value > max)
                max = value;
        }

        //从个位开始对数组进行排序
        for (exp = 1; max / exp > 0; exp *= 10)
        {
            //bucket数组用来计数
            int[] bucket = new int[10];

            //bucket用来当桶，放数据，取数据
            int[] temp = new int[array.length];

            //将数据出现的次数存储在buckets中
            for (int i = 0; i < array.length; i++) {
                bucket[array[i] / exp % 10]++;
            }

            //利用bucket[i]来确定放置数据的位置,排序数组的索引值与bucket[]总的计数有关系，排序数组的值与bucket[]中的桶序号有关系
            for (int i = 1; i < 10; i++)
                bucket[i] = bucket[i] + bucket[i - 1];

            //利用循环把数据装入各个桶中，注意是从后往前装
            //这里是重点，一定要仔细理解，一是稳定性会被破坏（先放进bucket的会被后取出），二是会破坏之前已排序好的顺序（最后一次排序，除
            // 某几位，其他都为0，如果从前往后遍历（这部分0已经从前往后排好了序），会破坏之前已经排好的序）
            for (int i = array.length - 1; i >= 0 ; i--) {
                temp[bucket[array[i] / exp % 10] - 1] = array[i];
                bucket[array[i] / exp % 10]--;
            }

            //将桶中的数据取出来，赋值给arr
            for(int i=0,j=0; i< array.length;i++,j++)
            {
                array[i] = temp[j];
            }


        }
    }

    public static void main(String[] args) {
        int[] test = {2, 8, 3, 1, 9, 7, 4, 6, 5};
        radixsort(test);
        System.out.println(Arrays.toString(test));
    }
}
