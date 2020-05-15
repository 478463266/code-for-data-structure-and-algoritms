package tree;
//ref:https://www.cnblogs.com/dawnyxl/p/9047437.html

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class treeOperations {

    public <T> int getTreeNum(treeNode<T> root){
        if (root == null)
            return 0;
        return getTreeNum(root.leftChild) + getTreeNum(root.rightChild) + 1;
    }

    public <T> int getTreeDepth(treeNode<T> root)
    {
        if (root == null)
            return -1;
        return Math.max(getTreeDepth(root.rightChild) + 1, getTreeDepth(root.leftChild) + 1);
    }

    public <T> void preOrderTravel(treeNode<T> root)
    {
        if (root == null)
            return;
        System.out.print(root.value + "\t");
        preOrderTravel(root.leftChild);
        preOrderTravel(root.rightChild);
    }

    public <T> void midOrderTravel(treeNode<T> root)
    {
        if (root == null)
            return;

        midOrderTravel(root.leftChild);
        System.out.print(root.value + "\t");
        midOrderTravel(root.rightChild);
    }

    public <T> void postOrderTravel(treeNode<T> root)
    {
        if (root == null)
            return;

        postOrderTravel(root.leftChild);
        postOrderTravel(root.rightChild);
        System.out.print(root.value + "\t");
    }

    public <T> void levelTravle(treeNode<T> root)
    {
        if (root == null)
            return;

        Queue<treeNode<T>> q = new LinkedList<treeNode<T>>();
        q.add(root);
        while (!q.isEmpty())
        {
            treeNode<T> temp = q.poll();
            System.out.print(temp.value + "\t");
            if (temp.leftChild != null)
                q.add(temp.leftChild);
            if (temp.rightChild != null)
                q.add(temp.rightChild);
        }
    }

    //求第k层节点个数
    public <T> int getNumofKLevel(treeNode<T> root, int k){
        if (root == null || k < 1)
            return 0;
        if (k == 1)
            return 1;
        int leftofNum = getNumofKLevel(root.leftChild, k - 1);
        int rightofNum = getNumofKLevel(root.rightChild, k - 1);
        return leftofNum + rightofNum;
    }

    public <T> int getNumofLeaf(treeNode<T> root)
    {
        if (root == null)
            return 0;
        if (root.leftChild == null && root.rightChild == null)
            return 1;
        return getNumofLeaf(root.leftChild) + getNumofLeaf(root.rightChild);
    }

    //交换根节点的左右子树
    public <T> treeNode<T> exchange(treeNode<T> root){
        if (root == null)
            return null;
        treeNode<T> l = exchange(root.leftChild);
        treeNode<T> r = exchange(root.rightChild);
        root.leftChild = r;
        root.rightChild = l;
        return root;
    }

    //node是否为root的子节点
    public <T> boolean nodeIsChild(treeNode<T> root, treeNode<T> node){
        if (root == null || node == null)
            return false;
        if (root.value == node.value)
            return true;
        boolean isFind = nodeIsChild(root.leftChild, node);
        if (!isFind)
            isFind = nodeIsChild(root.rightChild, node);
        return isFind;
    }

    //返回以Lnode和rode以root为根节点的公共父节点
    public <T> treeNode<T> findAllFartherNode(treeNode<T> root,
                                              treeNode<T> lNode, treeNode<T> rNode)
    {
        if (root == null || lNode == null || rNode == null)
            return null;
        if (lNode.value == root.value || rNode.value == root.value)
            return root;

        //如果lnode是左子树的节点
        if (nodeIsChild(root.leftChild, lNode)){
            if (nodeIsChild(root.rightChild, rNode))
                return root;
            else
                return findAllFartherNode(root.leftChild, lNode, rNode);
        }else {
            if (nodeIsChild(root.leftChild, rNode))
                return root;
            else
                return findAllFartherNode(root.rightChild, lNode, rNode);
        }
    }

    //查看两棵树是否相等
    public <T> boolean equalTrees(treeNode<T> node1, treeNode<T> node2){
        if (node1 == null && node2 == null)
            return true;
        else if (node1 == null || node2 == null)
            return false;
        boolean isEqual = node1.value.equals(node2.value);
        boolean isLeftEqual = equalTrees(node1.leftChild, node2.leftChild);
        boolean isRightEqual = equalTrees(node1.rightChild, node2.rightChild);
        return isEqual && isLeftEqual && isRightEqual;
    }

    //根据前、中序构建二叉树
    public <T> treeNode<T> getTreefromPreandMid(List<T> pre, List<T> mid)
    {
        if (pre == null || mid == null || pre.size() == 0 || mid.size() == 0)
            return null;
        if (pre.size() == 1)
            return new treeNode<T>(pre.get(0));

        treeNode<T> root = new treeNode<>(pre.get(0));

        //找出根节点在中序中的位置,这里的index超过了根节点，是其索引值+1的结果
        int index = 0;
        while (!mid.get(index++).equals(pre.get(0))){}
        System.out.println("中序index：" + index);

        //构建左子树的前序
        List<T> preLeft = new ArrayList<>(index);
        for (int i = 1; i < index; i++) {
            preLeft.add(pre.get(i));
        }
        //左子树的中序
        List<T> midLeft = new ArrayList<>(index);
        for (int i = 0; i < index - 1; i++) {
            midLeft.add(mid.get(i));
        }
        //重建左子树
        root.leftChild = getTreefromPreandMid(preLeft, midLeft);

        //右子树的前序
        List<T> preRight = new ArrayList<>(pre.size() - index);
        for (int i = 0; i < pre.size() - index; i++) {
            preRight.add(pre.get(index + i));
        }

        //右子树的中序
        List<T> midRight = new ArrayList<>(pre.size() - index);
        for (int i = 0; i < pre.size() - index; i++) {
            midRight.add(mid.get(index + i));
        }
        //重建右子树
        root.rightChild = getTreefromPreandMid(preRight, midRight);
        return root;

    }

    public <T> treeNode<T> getTreefromPostandMid(List<T> post, List<T> mid)
    {
        if (post == null || mid == null || post.size() == 0 ||mid.size() == 0)
            return null;
        if (post.size() == 1)
            return new treeNode<T>(post.get(0));

        treeNode<T> root = new treeNode<>(post.get(post.size() - 1));

        //找出根在中序中的位置
        int index = 0;
        while (mid.get(index) != post.get(post.size() - 1))
        {
            index = index + 1;
        }

        //构建左子树的中序
        List<T> midLeft = new ArrayList<>(index);
        for (int i = 0; i < index; i++) {
            midLeft.add(mid.get(i));
        }

        //构建左子树的后序
        List<T> postLeft = new ArrayList<>(index);
        for (int i = 0; i < index; i++) {
            postLeft.add(post.get(i));
        }

        //构建左子树
        root.leftChild = getTreefromPostandMid(postLeft, midLeft);

        //构建右子树的中序
        List<T> midRight = new ArrayList<>(mid.size() - index - 1);
        for (int i = index + 1; i < mid.size(); i++) {
            midRight.add(mid.get(i));
        }

        List<T> postRight = new ArrayList<>(mid.size() - index - 1);
        for (int i = index; i < mid.size() - 1; i++) {
            postRight.add(post.get(i));
        }

        //构建右子树
        root.rightChild = getTreefromPostandMid(postRight, midRight);
        return root;
    }

}
