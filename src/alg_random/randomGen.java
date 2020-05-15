package alg_random;

import java.util.Random;
//ref1: https://www.cnblogs.com/blogxjc/p/9687297.html
//ref2: https://blog.csdn.net/zmazon/article/details/17383521

public class randomGen {

    /**
     * 真随机数发生器
     * 伪随机数发生器：
     * 1.取中法：平方取中；常数取中，乘法取中
     * 2.同余法：线性同余发生器
     * 3.移位法
     * 4.梅森旋转
     */
    public static void random(){
        long mrandom = System.currentTimeMillis() / Integer.MAX_VALUE;
        final int A = 3, M = (1 << 31) - 1;
        for (int i = 0; i < 2; i++){
            mrandom = (mrandom * A) % M;
            System.out.println(mrandom);
        }
    }


    public static void main(String[] args) {

        /**
         * java本身随机数产生方法1
         * 第一种需要借助java.util.Random类来产生一个随机数发生器，也是最常用的一种，构造函数有两个，Random()和Random(long seed)。
         * 第一个就是以当前时间为默认种子，第二个是以指定的种子值进行。产生之后，借助不同的语句产生不同类型的数
         */
        Random r = new Random(1);
        for (int i = 0; i < 5; i++) {
            int ran1 = r.nextInt(100);
            System.out.print(ran1 + "\t");
        }
        System.out.println();
        /**
         * 方法2
         * 返回的数值是[0.0,1.0）的double型数值，由于double类数的精度很高，可以在一定程度下看做随机数，
         * 借助（int）来进行类型转换就可以得到整数随机数了
         */
        int max = 100, min = 1;
        int ran2 = (int) (Math.random() * (max - min) + min);
        System.out.println(ran2);

        /**
         * 方法3
         * 方法返回从1970年1月1日0时0分0秒（这与UNIX系统有关）到现在的一个long型的毫秒数，取模之后即可得到所需范围内的随机数
         */
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum % (max - min) + min);
        System.out.println(ran3);

        random();

    }
}
