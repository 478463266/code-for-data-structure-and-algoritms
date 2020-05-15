package search;

//优势：不用除法实现,硬件上更快
//https://www.cnblogs.com/han200113/p/11745808.html
//斐波那契查找改变了二分查找中原有的中值 mid 的求解方式，其 mid 不再代表中值，而是代表了黄金分割点
//mid = left + F_{block - 1} - 1
//斐波那契查找，以黄金分割点来分割数组
//要求数组长度为f(k) - 1; mid为一个，mid前为f(k - 1) - 1个，mid后为 f（k - 2）- 1个，加起来刚好f（k）- 1个
//[2]>，low=mid+1,k-=2;
//说明：low=mid+1说明待查找的元素在[mid+1,high]范围内，k-=2 说明范围[mid+1,high]内的元素个数为n-(F(k-1))= Fk-1-F(k-1)=Fk-F(k-1)-1=F(k-2)-1个，所以可以递归的应用斐波那契查找。
//[3]<，high=mid-1,k-=1。
//说明：low=mid+1说明待查找的元素在[low,mid-1]范围内，k-=1 说明范围[low,mid-1]内的元素个数为F(k-1)-1个，所以可以递归 的应用斐波那契查找。

import java.util.Arrays;

public class FibonacciSearch {

    public static final int maxSize = 20;//fibonacci array length
    public static int[] fibonacci()
    {
        int[] f = new int[maxSize];
        f[0] = f[1] = 1;
        for (int i = 2; i < maxSize - 1; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int  fibonaccisearch(int[] array, int value)
    {
        if (array == null || array.length == 0)
            return -1;

        int left = 0, right = array.length - 1;
        int k = 0;
        int[] f = fibonacci();

        while (array.length > f[k] - 1) ///这里的f[k]是arr距离斐波那契数列最近的数值，为什么-1，为了符合数组特性(数组最大元素下标是数组长度-1)
            k++;

        ///这里的f[k]是arr距离斐波那契数列最近的数值，为什么-1，为了符合数组特性(数组最大元素下标是数组长度-1)
        int[] temp = Arrays.copyOf(array, f[k] - 1);

        for (int i = array.length; i < temp.length; i++) {
            temp[i] = array[array.length - 1];
        }

        while (left <= right)
        {
            int mid = left + f[k - 1] - 1;

            if (temp[mid] > value){
                right = mid - 1;
                k--;
            }
            else if (temp[mid] < value){
                left = mid + 1;
                k = k - 2;
            }
            else {
                if (mid < array.length)
                    return mid;
                else
                    return array.length - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] test = {1, 4, 5, 55, 7, 0, 21, 3};
        Arrays.sort(test);
        System.out.println(Arrays.toString(test));
        System.out.println(fibonaccisearch(test, 7));
        System.out.println(fibonaccisearch(test, 8));
    }
}
