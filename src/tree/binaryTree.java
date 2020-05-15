package tree;

//ref: https://www.cnblogs.com/skyice/p/10618303.html
import java.util.LinkedList;
import java.util.Queue;

public class binaryTree {

    public <T> void makeEmpty(treeNode<T> root){
        if (root != null)
        {
            makeEmpty(root.leftChild);
            makeEmpty(root.rightChild);
            root = null;
        }
    }

    //递归
    public <T extends Comparable> treeNodeCom<T> find(treeNodeCom<T> root, T data){
        if (root == null)
            return null;
        if (data.compareTo(root.value) < 0)
            return find(root.leftChild, data);
        else if (data.compareTo(root.value) > 0)
            return find(root.rightChild, data);
        else
            return root;
    }

    //非递归
    public <T extends Comparable> treeNodeCom<T> find2(treeNodeCom<T> root, T data){
        if (root == null)
            return null;
        while (!root.value.equals(data))
        {
            if (data.compareTo(root.value) < 0)
                root = root.leftChild;
            else
                root = root.rightChild;
            if (root == null)
                return null;
        }
        return root;
    }

    //递归实现
    public <T extends Comparable> treeNodeCom findMin(treeNodeCom<T> root)
    {
        if (root == null)
            return null;
        else if (root.leftChild == null)
            return root;
        else
            return findMin(root.leftChild);
    }

    //非递归实现
    public <T extends Comparable> treeNodeCom findMax(treeNodeCom<T> root){
        if (root != null){
            while (root.rightChild != null)
                root = root.rightChild;
        }
        return root;
    }

    //默认值不相等
    public <T extends Comparable> treeNodeCom insert(treeNodeCom<T> root, T data){

        treeNodeCom newNode = new treeNodeCom(data);
        if (root == null)
        {
            root = newNode;
            root.leftChild = null;
            root.rightChild = null;
        }

        else if (data.compareTo(root.value) < 0){
            root.leftChild = insert(root.leftChild, data);
        }
        else if (data.compareTo(root.value) > 0){
            root.rightChild = insert(root.rightChild, data);
        }
        //else存在于树种，we'll do nothing
        return root;

    }

    public <T extends Comparable> treeNodeCom delete(treeNodeCom<T> root, T data){
        if (root == null)
            System.out.println("元素未找到");
        else if (data.compareTo(root.value) < 0) {
            root.leftChild = delete(root.leftChild, data);
        }
        else if (data.compareTo(root.value) > 0){
            root.rightChild = delete(root.rightChild, data);
        }
        else{//找到该元素
            if (root.leftChild != null && root.rightChild != null){
                //两个孩子，replace with smallest in right subtree
                treeNodeCom temp = findMin(root.rightChild);
                root.value = (T) temp.value;
                root.rightChild = delete(root.rightChild, root.value);
            }
            else {//one or zero children
                if (root.leftChild == null)
                    root = root.rightChild; //also handles 0 children
                else if (root.rightChild == null)
                    root = root.leftChild;
            }
        }
        return root;
    }

    public <T extends Comparable> void levelTravle(treeNodeCom<T> root)
    {
        if (root == null)
            return;

        Queue<treeNodeCom<T>> q = new LinkedList<treeNodeCom<T>>();
        q.add(root);
        while (!q.isEmpty())
        {
            treeNodeCom<T> temp = q.poll();
            System.out.print(temp.value + "\t");
            if (temp.leftChild != null)
                q.add(temp.leftChild);
            if (temp.rightChild != null)
                q.add(temp.rightChild);
        }
    }
}
