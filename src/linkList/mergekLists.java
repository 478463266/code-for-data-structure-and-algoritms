package linkList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目要求：合并 k 个排序链表，返回合并后的排序链表
 */

public class mergekLists {


    public linkNode mergekLists1(linkNode[] lists) {

        //方法一：优先队列
    PriorityQueue<linkNode> qp = new PriorityQueue<>(new Comparator<linkNode>() {
        @Override
        public int compare(linkNode l1, linkNode l2) {
            return l1.data - l2.data;
        }
    });

        for (linkNode ln : lists) {
            if (ln != null)
                qp.add(ln);
        }

        linkNode head = new linkNode(0);
        head.next = null;
        linkNode tail = head;

        while (!qp.isEmpty()){
            tail.next = qp.poll(); //因为是优先队列，弹出的就是最小的
            tail = tail.next;
            if (tail.next != null)
                qp.add(tail.next);
            tail.next = null;
        }
        return head.next;
    }

    //方法二：归并
    linkNode mergekLists2(linkNode[] lists)
    {
        if (lists == null)
            return null;

        int len = lists.length;

        if (len == 1)
            return lists[0];
        return splitandConquer(lists, 0, len - 1);
    }

    //分治递归
    public linkNode splitandConquer(linkNode [] lists, int left, int right){
        if (left == right)
            return lists[left];
        int mid = left + (right - left) / 2;
        linkedList ll = new linkedList();
        return ll.mergeLink(splitandConquer(lists, left, mid), splitandConquer(lists, mid + 1, right));
    }

}
