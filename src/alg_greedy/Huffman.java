package alg_greedy;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

//ref：https://blog.csdn.net/xundaozhishang/article/details/72486618
public class Huffman {

    private static class TreeNode implements Comparable<TreeNode>{
        //ch保存相应字符。code保存0或1，方便打印字符编码。
        TreeNode left;
        TreeNode right;
        int weight;
        char ch;
        String code;

        public TreeNode(int weight,TreeNode left,TreeNode right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
            this.code = "";
        }
        @Override
        public int compareTo(TreeNode o) {
            if (this.weight > o.weight) {
                return 1;
            }else if (this.weight < o.weight) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    //这里用了一个TreeSet的容器装节点，因为它自带排序功能。（前提是对象为Comparable的子类）
    public static TreeNode huffman(TreeMap<Integer, Character> data){
        TreeSet<TreeNode> tNodes = new TreeSet<>();
        Set<Integer> weights = data.keySet();
        Iterator<Integer> iterator = weights.iterator();

        while (iterator.hasNext()){
            int weight = iterator.next();
            TreeNode tmp = new TreeNode(weight, null, null);
            tmp.ch = data.get(weight);
            tNodes.add(tmp);
        }

        while (tNodes.size() > 1){
            TreeNode leftNode = tNodes.pollFirst();
            leftNode.code = "0";
            TreeNode rightNode = tNodes.pollFirst();
            rightNode.code = "1";
            TreeNode newNode = new TreeNode(leftNode.weight + rightNode.weight,
                    leftNode, rightNode);
            tNodes.add(newNode);
        }
        return tNodes.first();
    }

    private static void code(TreeNode t){
        if (t.left != null){
            t.left.code = t.code + t.left.code;
            code(t.left);
        }
        if (t.right != null){
            t.right.code = t.code + t.right.code;
            code(t.right);
        }
    }

    public static void print(TreeNode root){
        if (root != null){
            if (root.left == null & root.right == null)
                System.out.println(root.ch + "编码：" + root.code);
            else {
                print(root.left);
                print(root.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeMap<Integer, Character> test = new TreeMap<>();
        test.put(5, 'f');
        test.put(9, 'e');
        test.put(12, 'c');
        test.put(13, 'b');
        test.put(16, 'd');
        test.put(45, 'a');

        TreeNode root = huffman(test);
        code(root);
        print(root);
    }
}
