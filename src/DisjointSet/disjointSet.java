package DisjointSet;
//ref：https://juejin.im/post/5d660530f265da03d42fb623

public class disjointSet {

    static int maxSize = 100;
    static int tree[] = new int[maxSize];

    public disjointSet()
    {
        set(this.tree);
    }

    public disjointSet(int tree[]){
        this.tree = tree;
        set(this.tree);
    }

    //-1说明为根节点，绝对值为高度或者大小
    public void set(int tree[]){
        int len = tree.length;
        for (int i = 0; i < len; i++) {
            tree[i] = -1;
        }
    }

    //查找所属的集合
    public int find(int num){
        if (tree[num] > 0)//子节点
        {
            return tree[num] = find(tree[num]);//路径压缩
        }
        else
            return num;
    }

    //返回num所在树的大小
    public int value(int num){
        if (tree[num] > 0)
            return value(tree[num]);
        else
            return -tree[num];
    }

    //合并a,b所在的树
    public void union(int data1, int data2){
        int root1 = find(data1);
        int root2= find(data2);

        if (root1 == root2)
            System.out.println("已经为一棵树");
        else {
            if (tree[root1] < tree[root2]){//负数，为了简单，不再调用value函数
                tree[root1] += tree[root2]; //负数相加
                tree[root2] = root1;
            }
            else {
                tree[root2] += tree[root1];
                tree[root1] = root2;
            }
            //如果按照高度合并的话，具体的方法不一样，
        }
    }

    public static void main(String[] args) {
        disjointSet test = new disjointSet();
        test.union(1,2);
        test.union(3,4);
        test.union(5,6);
        test.union(1,6);

        test.union(22,24);
        test.union(3,26);
        test.union(36,24);
        System.out.println(test.find(6));
        System.out.println(test.value(6));

        System.out.println(test.find(22));
        System.out.println(test.value(22));
    }

}
