package tree;

public class testBinary {
    public static void main(String[] args) {
        treeNodeCom<Integer> a = new treeNodeCom<>(6);
//        treeNodeCom<Integer> b = new treeNodeCom<>(5);
//        treeNodeCom<Integer> c = new treeNodeCom<>(7);
//        treeNodeCom<Integer> d = new treeNodeCom<>(4);
//        treeNodeCom<Integer> e = new treeNodeCom<>(8);
//        treeNodeCom<Integer> f = new treeNodeCom<>(3);
//        treeNodeCom<Integer> g = new treeNodeCom<>(9);

        a.addLeft(5);
        a.addRight(7);
        a.leftChild.addLeft(4);
        a.rightChild.addLeft(8);
        a.rightChild.addRight(9);

        binaryTree by = new binaryTree();
        System.out.println(by.findMax(a).value);
        System.out.println(by.findMin(a).value);

        System.out.println(by.find(a, 7));
        System.out.println(by.find(a, 10));
        by.levelTravle(a);
        System.out.println();

        by.insert(a, 1);
        by.insert(a, 10);
        by.levelTravle(a);

        System.out.println();
        by.delete(a, 7);
        by.levelTravle(a);

    }
}
