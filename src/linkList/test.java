package linkList;

public class test {

    public static void main(String[] args) {
        linkedList test = new linkedList();
        test.addNode(9);
        test.addNode(7);
        test.addNode(88);
        test.addNode(2);
        test.addNode(7);
        test.addNode(8);
        test.printlink();
        //test.deleteNodebyIndex(2);
        //test.distinctLink();
        //System.out.println(test.findReversedNode(6).data);
        //test.reverseLink();
        //test.printlink();
        //test.reversePrint(test.head);
        //System.out.println(test.findMiddleNode1().data);
        //System.out.println(test.findMiddleNode2().data);
        //System.out.println(test.getLastNode().data);
        //System.out.println(test.getPreviousNode(9).data);
        //System.out.println(test.getPreviousNode(7).data);
        //System.out.println(test.getPreviousNode(6).data);
        //test.deleteNodebyValue(9);
        //test.deleteNodebyValue(3);
        //test.printlink();

        //归并排序
//        linkedList test2 = new linkedList();
//        test2.head = test.mergeSort(test.head);
//        test2.printlink();

        //快速排序
        test.quickSort();
        test.printlink();

        //判断链表是否有环
//        System.out.println(test.isRinged());
//        linkNode lastnode = test.getLastNode();
//        linkNode node2 = test.findNode(2);
//        lastnode.next = node2;
//        System.out.println(node2.data);
//        System.out.println(test.isRinged());
//        System.out.println(test.entryNodeofLoop(test.head).data);

        //删除指定节点
        //linkNode node = test.findNode(3);
        //System.out.println(node.data);
        //test.deleteSpecialNode2(node);
        //test.printlink();

        //相交
//        linkedList test1 = new linkedList();
//        test1.addNode(1);
//        test1.addNode(2);
//        test1.printlink();
//        System.out.println(test1.isCross(test1.head, test.head));
//        test1.findNode(2).next = test.findNode(3);
//        test1.printlink();
        //System.out.println(test.isCross(test1.head, test.head));
//        linkNode n = test.findFirstCrossPoint(test1, test);
//        if(n == null)
//            System.out.println("链表不相交");
//        else
//            System.out.println("第一个相交节点的数值为" + n.data);





    }





}
