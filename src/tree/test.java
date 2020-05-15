package tree;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        treeNode<Integer> root = new treeNode<>(1);
        root.addLeft(2);
        root.addRight(3);
        root.leftChild.addLeft(4);
        root.leftChild.addRight(5);
        root.rightChild.addLeft(6);

        System.out.println("中序遍历：");
        treeOperations t = new treeOperations();
        t.midOrderTravel(root);
        System.out.println("\n前序遍历");
        t.preOrderTravel(root);
        System.out.println("\n后序遍历");
        t.postOrderTravel(root);
        System.out.println("\n层次遍历");
        t.levelTravle(root);

        System.out.println("\n树的深度：" + t.getTreeDepth(root));

        System.out.println("\n树的叶子个数：" + t.getNumofLeaf(root));;

        System.out.println("\n树的节点个数：" + t.getTreeNum(root));

        System.out.println("\n树的第二层节点个数：" + t.getNumofKLevel(root, 2));

//        t.exchange(root);
//        t.levelTravle(root);
        System.out.println(t.nodeIsChild(root, new treeNode<Integer>(7)));
        System.out.println(t.nodeIsChild(root, new treeNode<Integer>(5)));
//
        treeNode<Integer> n1 = new treeNode<>(4);
        treeNode<Integer> n2 = new treeNode<>(5);
        treeNode<Integer> n3 = new treeNode<>(6);
        treeNode<Integer> n4 = new treeNode<>(2);
        System.out.print(t.findAllFartherNode(root, n1, n2).value);
        System.out.println();
        System.out.print(t.findAllFartherNode(root, n1, n3).value);
        System.out.println();
        System.out.print(t.findAllFartherNode(root, n1, n4).value);

        List<Integer> pre = new ArrayList<>();
        pre.add(1);
        pre.add(2);
        pre.add(4);
        pre.add(5);
        pre.add(3);
        pre.add(6);

        List<Integer> mid = new ArrayList<>();
        mid.add(4);
        mid.add(2);
        mid.add(5);
        mid.add(1);
        mid.add(6);
        mid.add(3);

        List<Integer> post = new ArrayList<>();
        post.add(4);
        post.add(5);
        post.add(2);
        post.add(6);
        post.add(3);
        post.add(1);

        treeNode<Integer> root2 = t.getTreefromPreandMid(pre, mid);
        System.out.println("\n新树：");
        t.levelTravle(root2);

        treeNode<Integer> root3 = t.getTreefromPostandMid(post, mid);
        System.out.println("\n新树：");
        t.levelTravle(root3);

//        System.out.println();
//        System.out.println(t.equalTrees(root,root2));
    }
}
