package hash;

/**
 * 平方探测：collisionNum=0; cur+= (++CollisionNum<<1) - 1; 这样做的原因是乘方的代价太大，转换成减法：(i*i)-(i-1)(i-1)= 2*i-1
 * 应用到put和get中即可
 * @param <K>
 * @param <V>
 */

public class myHashMap2<K, V> {
    private hashNode<K, V>[] array;
    private static int defaultCapacity = 10;
    private int size;
    private static float defaultLoadFactor = 0.75f; //扩展因子

    public myHashMap2(){}
    public myHashMap2(int capacity, int loadFactor){
        defaultCapacity = capacity;
        defaultLoadFactor = loadFactor;
        array = new hashNode[defaultCapacity];
    }

    public myHashMap2(int capacity){
        defaultCapacity = capacity;
        array = new hashNode[defaultCapacity];
        makeEmpty();
    }

    public void makeEmpty(){
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public int hash(K key){
        int code = key.hashCode();
        return code = code % defaultCapacity;
    }

    public boolean put(K key, V value) throws Exception {
        if (array == null)
            array = new hashNode[defaultCapacity];

        int index = hash(key);

        hashNode<K, V> node = array[index];

        while (node != null && node.isActive)
        {
            int temp = index;
            if (node.key.equals(key)){
                node.value = value;
                node.isActive = true;
                return true;
            }else {
                index = (index + 1) % defaultCapacity;
                node = array[index];
            }

            if (index == temp) //转了一圈
                return false;
        }

        if (size >= defaultCapacity * defaultLoadFactor)
            resize();

        array[index] = new hashNode<>(key,value);
        size++;
        return true;

    }

    public V get(K key){
        int index = hash(key);
        int temp = index;

        while (array[index] != null && !array[index].key.equals(key)){
            index = (index + 1) % array.length;
            if (index == temp) //转了一圈,且满了
                return null;
        }
        if (array[index] == null)
            return null;
        else return array[index].value;
    }

    public int getPosition(K key) throws Exception{
        int index = hash(key);
        int temp = index;

        while (array[index] != null && !array[index].key.equals(key)){
            index = (index + 1) % array.length;
            if (index == temp) //转了一圈,且满了
                throw new Exception("元素不存在");
        }
        if (array[index] == null)
            throw new Exception("元素不存在");
        else return index;
    }

    public void remore(K key) throws Exception {
        int pos = getPosition(key);
        array[pos].isActive = false;
        size--;
    }


    public void resize() throws Exception {
        hashNode<K, V>[] oldArray = array;
        array = new hashNode[2 * array.length]; //更好的方式是取素数
        size = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null && oldArray[i].isActive == true)
                put(oldArray[i].key, oldArray[i].value);
        }
    }



}
