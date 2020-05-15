package priority_queue;

//ref:https://blog.csdn.net/qq_30322803/article/details/78243656

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class myleftHeap {


    public myLeftHeapNode root;
    public myleftHeap(int val){
        root = new myLeftHeapNode(val);
    }
    public myleftHeap(myLeftHeapNode h){
        root = h;
    }


    public void merge(myleftHeap h){
        if (h != null)
            root = internalMerge(root, h.root);
    }

    public myLeftHeapNode internalMerge(myLeftHeapNode h1, myLeftHeapNode h2){
        if (h1 == null)
            return h2;
        if (h2 == null)
            return h1;
        myLeftHeapNode result = null;

        if (h1.val >= h2.val){
            h2.right = internalMerge(h2.right, h1);
            result = h2;
        }
        else {
            h1.right = internalMerge(h1.right, h2);
            result = h1;
        }

        //如果不满足结构性，则调整
        int leftNPL = result.left == null ? -1 : result.left.npl;
        int rightNPL = result.right == null ? -1 : result.right.npl;

        if (leftNPL < rightNPL)
        {
            myLeftHeapNode temp = result.right;
            result.right = result.left;
            result.left = temp;
        }

        //更新NPL值
        result.npl = rightNPL + 1;
        return result;
    }

    public void insert(int val){
        myleftHeap h = new myleftHeap(val);
        merge(h);
    }

    public int deleteMin(){
        if (root == null)
            return -1;
        myLeftHeapNode node = root;
        root = internalMerge(root.left, root.right);
        return node.val;
    }

    public boolean isEmpty()
    {
        return root == null ? true : false;
    }

    public void print()
    {
        if (root == null)
            System.out.println("优先队列为空");
        Queue<myLeftHeapNode> q = new ArrayDeque<>();
        //另一种初始化方法：new Linstedlink<>()；
        q.add(root);
        while (!q.isEmpty()){
            myLeftHeapNode n = q.poll();
            System.out.print(n.val + " ");
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }

    }

}
