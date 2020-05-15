package alg_random;

import java.util.Random;

public class isPrime {
    /*
    方法一：常规的方法为试除法。即，让n依次除以2到sqrt（n）以内的整数。如果有出现除尽的情况，则为合数
     */

    /*
    小费马定理：
    若有素数p，则对任意的数a( a为正整数 ，且a < p)，a^( p-1 ) ≡ 1( mod p )。反之，若有任意的a( a为正整数 ，且a < p)使得p不满足
     a^( p-1 ) ≡ 1( mod p )，p一定为合数。
     */

    /*
    在小费马定理的基础上有人设计出米勒拉宾随机素数测试法。可以大大的提高检测素数的正确性，但是同样并非一定正确，错误可能性却小到可以接受
     */
    //ref: https://blog.csdn.net/qq_37957829/article/details/77335072
    //ref: https://my.oschina.net/floor/blog/835919
    /**
     * 一种概率，测试一个数是否是素数
     * 依据
     * 1.费马小定理：如果P是素数，且0<A<P,那么A^(P-1)≡(1 mod P)<br/>
     * 2. 如果P是素数且 0<A<P,那么X^2≡(1 mod P),仅有两个解X=1，P-1<br/>
     */

    /**
     * A^(P-1)≡(1 mod P)
     * 此处P-1 对应变量n
     */
    private static long witness(long a, long n, long p){
        if (n == 0)
            return 1;
        long x = witness(a, n / 2, p);

        if (x == 0)
            return 0;
        //校验定理2
        long y = (x * x) % p;
        if (y == 1 && x != 1  && x != p - 1)
            return 0;
        //校验定理2结束
        if (n % 2 != 0){
            //奇数，修正A^(P-1)
            y = (a * y) % p;
        }
        return y;
    }

    /**
     * 尝试5次
     */
    public static final int TRIALS = 5;
    //素性测试
    public static boolean isPrime(long n){
        Random r = new Random();
        for (int counter = 0; counter < TRIALS; counter++)
        {
            if (witness(r.nextInt((int) n - 3) + 2, n - 1, n) != 1)
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
        for(int i=100;i<200;i++){
            if(isPrime(i)){
                //101 103 107 109 113
                //127 131 137 139 149 151 157 163 167 173 179 181 191 193 197
                //199
                System.out.println(i);
            }
        }
    }
}
