package priority_queue;

public class myLeftHeapNode {
    //节点的npl是指该节点到没有两个儿子的子节点(包括自身)的最短路径长
    //Null节点的npl=-1；叶子节点的npl = 0；只有一个子节点的npl = 0
    public int npl;
    public myLeftHeapNode left;
    public myLeftHeapNode right;
    public int val;
    public myLeftHeapNode(int val){
        this.val = val;
        left = null;
        right = null;
        npl = 0;
    }

    public void setLeft(int left) {
        myLeftHeapNode h = new myLeftHeapNode(left);
        this.left = h;
    }

    public void setRight(int right)
    {
        myLeftHeapNode h = new myLeftHeapNode(right);
        this.right = h;
    }
}
