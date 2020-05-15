package alg_greedy;


//ref:cnblogs.com/pacoson/p/5005289.html
public class ApproPacking {


    public static int[] buildBasicArray(int size, int initKey){
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = initKey;
        }

        return result;
    }

    public static void printBox(int size){

    }

    /**
     * 下项适合算法
     * 2.1）算法描述： 当处理任一物品时， 我们检查看他是否还能装进刚刚装进物品的同一个箱子中去。 如果能够装进去， 那么就把它装入该箱子中，
     * 否则，就开辟一个新箱
     */


    //public void nextfix()


    /**
     * 首次适合算法的策略是　依序扫描这些箱子但吧新的一项物品放入足够盛下它的第一个箱子中。因此，只有当先前放置物品的结果已经没有再容得下
     * 当前物品余地的时候，　我们才开辟一个新箱子；（一句话，　这里是从头到尾扫描箱子）
     */

    //firstfix

    /**
     * 算法描述：该方法不是吧一项新物品放入所发现的第一个能够容纳它的箱子， 而是放到所有箱子中能够容纳它的最满的箱子中
     */
}

