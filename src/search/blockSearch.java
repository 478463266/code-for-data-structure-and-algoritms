package search;

public class blockSearch {

    ////index代表索引数组，array代表待查找数组，value代表要查找的元素，size_block代表每块大小
    //索引保存的是该块中最大元素
    public static int blocksearch(int[] array, int[] index, int value, int size_block)
    {
        int i = indexsearch(index, value);

        if (i >= 0) {
            System.out.println(value + "可能在第" + i + "块");

            int begin = i * size_block;
            int len = (i + 1) * size_block;

            for (int j = begin; j < len; j++) {
                if (array[j] == value)
                    return j;
            }
        }
        return -1;
    }

    public static int indexsearch(int[] array, int value)
    {
        if (array == null || array.length == 0)
            return  -1;
        if (array[0] >= value)
            return  0;
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i - 1] < value && value <= array[i])
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] test = {1, 4, 5, 11, 19, 15, 121, 99, 77};
        int[] block = {5, 19, 121};
        System.out.println(blocksearch(test, block, 3,3));
        System.out.println(blocksearch(test, block, 15, 3));
        System.out.println(blocksearch(test, block, 19, 3));
        System.out.println(blocksearch(test, block, 5, 3));
    }
}
