package hash;
//ref: https://www.jianshu.com/p/a89e9487a06c

/**
 * 一个好的散列函数的值应尽可能平均分布
 * 主要散列函数构造方法：直接寻址法；数字分析法；平方取中；折叠；随机数；取模（素数）
 * 处理冲突的方法：链地址、开放定址（线性探测、平方探测、双散列）、再散列
 */

//这里使用链地址法
public class myHashMap<K, V> {

    private hashLinkedNode<K, V>[] nodes; //存储头结点
    private int size; //元素个数
    private static int defaultCapacity = 10; //默认容量
    private static float defaultLoadFactor = 0.75f; //扩展因子

    public myHashMap(){}
    public myHashMap(int capacity, int loadFactor){
        defaultCapacity = capacity;
        defaultLoadFactor = loadFactor;
    }

    //对于字符串：s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
    //s[i]为字符串的第i个字符，n为字符串长度，^为求幂
    public int hash(K key)
    {
        int code = key.hashCode();
        return code % (defaultCapacity - 1);
    }



    //添加元素
    public boolean put(K key, V value) throws Exception {
        //初始化数组
        if (nodes == null)
            nodes = new hashLinkedNode[defaultCapacity];

        int index = hash(key); //计算存储角标
        hashLinkedNode<K, V> node = nodes[index];//获取元素，视为头结点

        while (node != null)
        {
            //存在重复key则将旧value替换
            if (node.key.equals(key)){
                node.value = value;
                return true;
            }
            else {
                node = node.next;
            }
        }

        //判断是否需要扩容
        if (size >= defaultCapacity * defaultLoadFactor)
            resize();

        //将新添加的元素作为头结点添加到头节点中
        nodes[index] = new hashLinkedNode<>(key, value, nodes[index]);
        size ++;
        return true;

    }

    //获取元素
    public V get(K key)
    {
        int index = hash(key);

        //获取头节点
        hashLinkedNode node = nodes[index];

        if (node != null){
            while (node != null && !node.key.equals(key)){
                node = node.next;
            }
            if (node == null)
                return null;
            else
                return (V) node.value;
        }
        return null;
    }

    public void resize() throws Exception {

        //扩容后需要对元素重新put（重新散列），因此要将size设为0
        size = 0;
        //记录之前的数组
        hashLinkedNode<K, V>[] oldNodes = nodes;
        defaultCapacity = nextPrime(defaultCapacity);

        nodes = new hashLinkedNode[defaultCapacity];
        for (int i = 0; i < oldNodes.length; i++) {
            //扩容后hash值会变化，因此要重新散列
            hashLinkedNode node = oldNodes[i];
            while (node != null)
            {
                hashLinkedNode<K, V> temp = node;
                put((K) node.key, (V) node.value);//重新散列
                node = node.next;
                temp.next = null;//将当前散列的节点的next置为空
            }
        }
    }

    public int nextPrime(int n) throws Exception {
        if (n == 0 || n == 1 || n == 2)
            return 2;
        for (int i = n; i < 2 * n; i++) {
            if (isPrime(i))
                return i;
        }
        throw new Exception("没有找到下一个素数");
    }

    public boolean isPrime(int x)
    {
        if (x == 1 || x == 2)
            return true;

        for (int i = 0; i * i < x; i += 2) {
            if (x % i == 0)
                return false;
        }
        return false;
    }
}
