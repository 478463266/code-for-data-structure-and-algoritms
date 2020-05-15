package search;

//常用hash法：直接定址法，平方取中法，折叠法，除留取余法等方法确定哈希函数
//参考：https://www.cnblogs.com/nnngu/p/8307743.html
public class hashSearch {
    //采用取模法建立hash
    //hash长度定义，越大越不容易发送冲突，可以用最大最小值求
    public static final int hashLength = 100;
    public static int[] hashTable = new int[hashLength];

    public static int myhash(int data){
        return data % hashLength;
    }

    public static void insertHash(int data){
        int hashAddress = myhash (data);

        while (hashTable[hashAddress] != 0)
            // 利用 开放定址法 解决冲突
            hashAddress = (++hashAddress) % hashLength;

        hashTable[hashAddress] = data;
    }

    public static void hashBuild(int[] array)
    {
        for (int i = 0; i < hashLength; i++) {
            hashTable[i] = 0;
        }

        //建立哈希
        for (int i = 0; i < array.length; i++) {
            insertHash(array[i]);
        }
    }

    public static int hashSearch(int[] array, int value)
    {
        int hashAddress = value % hashLength;

        while (hashTable[hashAddress] != value){
            hashAddress = ++hashAddress % hashLength;
            //查找到开放单元 或者 循环回到原点，表示查找失败
            if (hashTable[hashAddress] == 0 || hashAddress == myhash(value))
                return -1;
        }

        return hashAddress;
    }

    public static void main(String[] args) {
        int[] test = {1, 4, 5, 11, 19, 15, 121, 99, 77, 101, 23};
        hashBuild(test);

        System.out.println(hashSearch(test, 101));
        System.out.println(hashSearch(test, 1));
        System.out.println(hashSearch(test, 121));
        System.out.println(hashSearch(test, 99));
        System.out.println(hashSearch(test, 3));
    }
    //待解决的问题：无法返回在原数组中的位置，解决思路：变为二维hash数组，第二维长度为1，存储原数组中的位置，
}
