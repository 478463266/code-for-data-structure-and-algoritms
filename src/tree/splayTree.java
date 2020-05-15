package tree;

/**
 * 实现方式一：多次旋转,不是最好的
 * https://blog.csdn.net/funnyrand/article/details/82421319
 * 实现方式二：更好的方法，可以按照访问的前后顺序依次反转节点
 * https://blog.csdn.net/u012124438/article/details/78067998
 * 进一步解释：https://www.cnblogs.com/hustcat/archive/2010/05/23/1742012.html
 */

/*
当我们沿着树向下搜索某个节点X的时候，我们将搜索路径上的节点及其子树移走。我们构建两棵临时的树──左树和右树。没有被移走的节点构成的树称作中树。在伸展操作的过程中：
(1)当前节点X是中树的根。
(2)左树L保存小于X的节点。
(3)右树R保存大于X的节点。
 */

public class splayTree <T extends  Comparable<T>> {

    /*
     * 旋转key对应的节点为根节点，并返回根节点。
     *
     * 注意：
     *   (a)：伸展树中存在"键值为key的节点"。
     *          将"键值为key的节点"旋转为根节点。
     *   (b)：伸展树中不存在"键值为key的节点"，并且key < tree.key。
     *      b-1 "键值为key的节点"的前驱节点存在的话，将"键值为key的节点"的前驱节点旋转为根节点。
     *      b-2 "键值为key的节点"的前驱节点存在的话，则意味着，key比树中任何键值都小，那么此时，将最小节点旋转为根节点。
     *   (c)：伸展树中不存在"键值为key的节点"，并且key > tree.key。
     *      c-1 "键值为key的节点"的后继节点存在的话，将"键值为key的节点"的后继节点旋转为根节点。
     *      c-2 "键值为key的节点"的后继节点不存在的话，则意味着，key比树中任何键值都大，那么此时，将最大节点旋转为根节点。
     */

    public treeNodeCom<T> splay(treeNodeCom<T> root, T value)
    {
        if (root == null)
            return null;

        treeNodeCom<T> N = new treeNodeCom<T>();
        treeNodeCom<T> l = N;
        treeNodeCom<T> r = N;
        treeNodeCom<T> c;
        //节点N是用来保存l 和 r的指针的，N的右指针指向l（保存小于value的节点）；N的左指针指向r（保存大于value的节点）——这是因为r.leftchile
        //一直保存大于value的节点；l.rightchild一直保存小于value的节点，l和r初始是指向N的（第一次l.rightchild即为N.rightchild存储了小值，
        // 第一次r.leftchild即为N.leftchild存储了大值）

        for (;;){
            int cmp = value.compareTo(root.value);
            if (cmp < 0){
                if (root.leftChild == null)
                    break;
                if (value.compareTo(r.leftChild.value) < 0) //rotete right，value还在root左孩子的左孩子
                {
                    c = root.leftChild;
                    root.leftChild = c.rightChild;
                    c.rightChild = root;
                    if (root.leftChild == null)
                        break;
                }
                //否则情况一：大于root的左孩子，同时小于root，说明该node不存在，返回root的左孩子；情况二：与root左孩子相等的话，直接返
                //回；情况三：原本的左左左-变成了中间平衡性（进行了右旋）
                r.leftChild = root; //先使r的左孩子指向当前节点，保存大于value的节点
                r = root; // r = r.leftchild，再使r往左下移动
                root = root.leftChild; //root往下迭代
            }else if (cmp > 0){//value大于root的值，即用l存储小于value的值
                if (root.rightChild == null)
                    break;

                if (value.compareTo(root.rightChild.value) > 0){
                    c = root.rightChild;
                    root.rightChild = c.leftChild;
                    c.leftChild = root;
                    root = c;

                    if (root.rightChild == null)
                        break;
                }


                //link left
                l.rightChild = root; //先初始化l的右孩子，保存小于value的节点
                l = root; // l向下移动
                root = root.rightChild; //root往下迭代

            }else {
                break;
            }
        }
        l.rightChild = root.leftChild; //把最后的右孩子放到l上
        r.leftChild = root.rightChild; //把最后的左孩子放到r上
        root.leftChild = N.rightChild; //N的右孩子即为l
        root.rightChild = N.leftChild; //N的左孩子即为l

        return root;

    }

    //插入的另外一种方法：数据结构书第十二章的第一小节——先进行旋转得到最接近value的节点（这时已经成为根节点），再进行判断大小和替换
    public treeNodeCom<T> insert(treeNodeCom<T> root, treeNodeCom<T> node){
        int cmp;
        treeNodeCom<T> y = null;
        treeNodeCom<T> x= root;

        //查找value插入的位置
        while (x != null){
            y = x;
            cmp = node.value.compareTo(x.value);
            if (cmp < 0)
                x = x.leftChild;
            else if (cmp > 0)
                x = x.rightChild;
            else {
                System.out.println("不允许插入相同节点");
                node = null;
                return root;
            }
        }

        if (y == null) //处理没有进while循环的情况，即root本身为空
            root = node;
        else {
            cmp = node.value.compareTo(y.value);
            if (cmp < 0)
                y.leftChild = node;
            else
                y.rightChild = node;
        }

        return root;
    }

    public void insert(treeNodeCom<T> root, T value)
    {
        treeNodeCom<T> node = new treeNodeCom<T>(value);
        if (node == null)
            return;
        //插入
        root = insert(root, node);
        //旋转
        root = splay(root, value);
    }

    public treeNodeCom<T> remove(treeNodeCom<T> root, T value){
        //先在伸展树中查找键值为key的节点。若没有找到的话，则直接返回。若找到的话，则将该节点旋转为根节点，然后再删除该节点
        if (root == null)
            return null;

        if (find(root, value) == null)
            return root;

        //将value对应的节点旋转为根节点
        root = splay(root, value);

        treeNodeCom<T> temp;
        if (root.leftChild != null){
            //将root的前驱节点旋转为根节点
            temp = splay(root.leftChild, value);
            temp.rightChild = root.rightChild;
        }else
            temp = root.rightChild;
        root = null;

        return temp;
    }

    //递归
    public treeNodeCom<T> find(treeNodeCom<T> root, T data){
        if (root == null)
            return null;
        if (data.compareTo(root.value) < 0)
            return find(root.leftChild, data);
        else if (data.compareTo(root.value) > 0)
            return find(root.rightChild, data);
        else
            return root;
    }
}
