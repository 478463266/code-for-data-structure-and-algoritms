package tree;

//B+树的实现
//ref: https://www.cnblogs.com/jing99/p/11741685.html

/*
M阶B+树的定义：
 * 1.任意非叶子结点最多有M个子节点；且M>2；M为B+树的阶数
 * 2.除根结点以外的非叶子结点至少有 (M+1)/2个子节点；
 * 3.根结点至少有2个子节点；
 * 4.除根节点外每个结点存放至少（M-1）/2和至多M-1个关键字；（至少1个关键字）
 * 5.非叶子结点的子树指针比关键字多1个；
 * 6.非叶子节点的所有key按升序存放，假设节点的关键字分别为K[0], K[1] … K[M-2],指向子女的指针分别为P[0], P[1]…P[M-1]。则有：
 * 　　　P[0] < K[0] <= P[1] < K[1] …..< K[M-2] <= P[M-1]
 * 7.所有叶子结点位于同一层；
 * 8.为所有叶子结点增加一个链指针；
 * 9.所有关键字都在叶子结点出现

B+树是对B树的一种变形树，它与B树的差异在于：
    有k个子结点的结点必然有k个关键码；
    非叶结点仅具有索引作用，跟记录有关的信息均存放在叶结点中。
    树的所有叶结点构成一个有序链表，可以按照关键码排序的次序遍历全部记录
 */

/*
 B: 有序数组+平衡多叉树
 B+：有序链表+平衡多叉树
 B*：更加丰满的B+树，非叶子节点也有了指向兄弟在指针-非根和非叶子结点再增加指向兄弟的指针；B*树定义了非叶子结点关键字个数至少为(2/3)*M，即块的最低使用率为2/3（代替B+树的1/2）
 R: 可以存储空间信息的树（矩形空间）
 */

public class BPlusTree<K extends  Comparable<K>, V> {

    //根节点
    protected BPlusNode<K, V> root;

    //阶数-M值
    protected  int order;

    //叶子节点的链表头
    protected BPlusNode<K, V> head;

    //树高
    protected int height = 0;

    public BPlusNode<K, V> getHead() {
        return head;
    }

    public void setHead(BPlusNode<K, V> head) {
        this.head = head;
    }

    public void setRoot(BPlusNode<K, V> root) {
        this.root = root;
    }

    public BPlusNode<K, V> getRoot(){
        return root;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public V get(K key){
        return root.get(key);
    }

    public V remove(K key){
        return root.remove(key, this);
    }

    public void insertOrUpdate(K key, V value){
        root.insertOrUpdate(key, value, this);
    }

    public BPlusTree(int order){
        if (order < 3){
            System.out.println("order must be greater than 2");
            return;
        }
        this.order = order;
        root = new BPlusNode<>(true, true);
        head = root;
    }

    public void printBTree(){
        this.root.printBPlusTree(0);
    }
}
