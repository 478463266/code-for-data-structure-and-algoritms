package priority_queue;

//ref: https://blog.csdn.net/u013728021/article/details/84034420

/**
 * 二叉堆：编号从0开始：左孩子：（i+1)*2-1; 右孩子：（i+1)*2; 父节点：（i-1)/2；
 * 编号从1开始：左孩子：i*2；右孩子：i*2+1；父节点：i/2
 */
public class test {

    public static void main(String[] args) {
//        myHeap test = new myHeap(10);
//        System.out.println(test.isEmpty());
//        for (int i = 9; i >= 0; i--) {
//            test.insert(i);
//        }
//        System.out.println(test.isEmpty());
//        System.out.println(test.isFull());
//
////        try {
////            test.insert(10);
////        }catch (Exception e){
////
////        }
//
//        System.out.println(test.findMin());
//        test.deleteMin();
//        System.out.println(test.findMin());
//        test.deleteMin();
//        System.out.println(test.findMin());

//        myleftHeap h1 = new myleftHeap(3);
////        h1.insert(10);
////        h1.insert(8);
////        h1.insert(21);
////        h1.insert(14);
////        h1.insert(17);
////        h1.insert(23);
////        h1.insert(26);
//        h1.print();
//        System.out.println();
//        while (!h1.isEmpty())
//        {
//            System.out.print(h1.deleteMin() + " ");
//        }

        myLeftHeapNode h1 = new myLeftHeapNode(3);
        h1.setLeft(10);
        h1.setRight(8);
        h1.right.setLeft(17);
        h1.right.left.setLeft(26);
        h1.left.setLeft(21);
        h1.left.setRight(14);
        h1.left.right.setLeft(23);
        myleftHeap test1 = new myleftHeap(h1);
        test1.print();
        System.out.println();

        myLeftHeapNode h2 = new myLeftHeapNode(6);
        h2.setLeft(12);
        h2.setRight(7);
        h2.right.setLeft(37);
        h2.right.setRight(18);
        h2.left.setLeft(18);
        h2.left.setRight(24);
        h2.left.right.setLeft(33);
        myleftHeap test2 = new myleftHeap(h2);
        test2.print();
        System.out.println();

        test1.merge(test2);
        //test1.print();
        while (!test1.isEmpty())
        {
            System.out.print(test1.deleteMin() + " ");
        }

    }
}
