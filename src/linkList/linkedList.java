package linkList;

import java.util.Hashtable;

//ref: https://www.cnblogs.com/xkzhangsanx/p/11000958.html
public class linkedList {

    linkNode head = null;


    public void printlink()
    {
        linkNode cur = head;
        while (cur != null)
        {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 链表添加结点:
     * 找到链表的末尾结点，把新添加的数据作为末尾结点的后续结点
     */
    public void addNode(int data){
        linkNode node = new linkNode(data);

        if (head == null)
        {
            head = node;
            return;
        }

        linkNode temp = head;
        while (temp.next != null)
        {
            temp = temp.next;
        }
        temp.next = node;
    }

    /**
     * 链表删除结点:
     * 把要删除结点的前结点指向要删除结点的后结点，即直接跳过待删除结点
     */

    /**
    求链表的长度
     */
    public int linkLength(){
        int length = 0;
        linkNode curNode = head;
        while (curNode != null){
            length++;
            curNode = curNode.next;
        }
        return length;
    }

    /**
     * 删除链表的节点
     */
    public boolean deleteNodebyIndex(int index)
    {
        int len = linkLength();
        if (index > len){
            System.out.println("输入索引" + index + "应该不大于链表长度" + len);
            return false;
        }
        if (index < 1){
            System.out.println("输入索引" + index + "不能小于1");
            return false;
        }

        if (index == 1)
        {
            head = head.next;
            return true;
        }

        linkNode pre = head;
        linkNode cur = pre.next;
        int i = 2;
        while (i != index){
            i++;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = cur.next;
        return true;
    }

    /**
     * 删除链表中的特定值的节点
     */
    public boolean deleteNodebyValue(int data)
    {
        if (head.data == data) {
            head = head.next;
            return true;
        }
        linkNode ptr = head;

        while (ptr.next != null)
        {
            if (ptr.next.data == data)
            {
                ptr.next = ptr.next.next;
                System.out.println("删除成功");
                return true;
            }
            ptr = ptr.next;
        }
        System.out.println("删除失败，" + data + "不存在");
        return false;
    }

    /**
     * 在不知道头结点的情况下删除指定结点：
     * 删除结点的重点在于找出其前结点，使其前结点的指针指向其后结点，即跳过待删除结点
     * 1、如果待删除的结点是尾结点，由于单链表不知道其前结点，没有办法删除
     * 2、如果删除的结点不是尾结点，则将其该结点的值与下一结点交换，然后该结点的指针指向下一结点的后续结点
     */
    public boolean deleteSpecialNode1(linkNode node)
    {
        if (node.next == null)
            return false;
        else {
            int temp = node.data;
            node.data = node.next.data;
            node.next.data = temp;
            node.next = node.next.next;
            return true;
        }
    }

    public boolean deleteSpecialNode2(linkNode node)
    {
        if (node.next == null)
            return false;
        else {
            //int temp = node.data;/
            node.data = node.next.data;
            //node.next.data = temp;
            node.next = node.next.next;
            return true;
        }
    }

    /**
     * 去掉重复元素:
     * 需要额外的存储空间hashtable，调用hashtable.containsKey()来判断重复结点
     */
    public void distinctLink()
    {
        linkNode temp = head;
        linkNode pre = null;
        Hashtable<Integer, Integer> hb = new Hashtable<Integer, Integer>();

        while (temp != null){
            if (hb.containsKey(temp.data))
            {
                pre.next = temp.next;
            }
            else {
                hb.put(temp.data, 1);
                pre = temp;
            }
            temp = temp.next;
        }
    }

    /**
     * 返回倒数第k个结点,
     * 两个指针，第一个指针向前移动k-1次，之后两个指针共同前进，
     * 当前面的指针到达末尾时，后面的指针所在的位置就是倒数第k个位置
     */

    public linkNode findReversedNode (int k)
    {
        if (k == 0 || k > linkLength())
        {
            System.out.println(k + "取值不合理");
            return null;
        }

        linkNode first = head;
        linkNode second = head;

        for (int i = 0; i < k - 1; i++) {
            second = second.next;
        }

        while (second.next != null){
            second = second.next;
            first = first.next;
        }

        return first;

    }

    /**
     * 查找正数第k个元素
     */

    public linkNode findNode(int k)
    {
        if (k == 0 || k > linkLength())
        {
            System.out.println(k + "取值不合理");
            return null;
        }

        linkNode temp = head;

        //注意这里是k - 1
        for (int i = 0; i < k - 1; i++) {
            temp = temp.next;
        }

        return temp;

    }


    /**
     * 反转链表，在翻转指针前一定要保存下个节点的指针
     */
    public void reverseLink()
    {
        linkNode cur = head;
        linkNode pre = null;

        while (cur != null)
        {
            linkNode next = cur.next;//保留下一个节点
            cur.next = pre; //指针反转
            pre = cur; //前节点后移，即变为当前节点
            cur = next; //当前节点后移
        }
        head = pre;
    }

    /**
     * 反向输出链表，三种方式：
     * 方法一、先反转链表，再输出链表，需要链表遍历两次
     * 方法二、把链表中的数字放入栈中再输出，需要维护额外的栈空间
     * 方法三、依据方法2中栈的思想，通过递归来实现，递归起始就是将先执行的数据压入栈中，再一次出栈
     */
    public void reversePrint(linkNode node)
    {
        if (node != null)
        {
            reversePrint(node.next);
            System.out.print(node.data + " ");
        }
    }

    /**
     * 寻找单链表的中间结点：
     * 方法一、先求出链表的长度，再遍历1/2链表长度，寻找出链表的中间结点
     * 方法二、：
     * 用两个指针遍历链表，一个快指针、一个慢指针，
     * 快指针每次向前移动2个结点，慢指针一次向前移动一个结点，
     * 当快指针移动到链表的末尾，慢指针所在的位置即为中间结点所在的位置
     */
    public linkNode findMiddleNode1()
    {
        linkNode slow = head;
        linkNode fast = head;
        while (slow != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //偶数个返回第二个
    public linkNode findMiddleNode2()
    {
        linkNode slow = head;
        linkNode fast = head;
        while (fast.next != null){
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null){
                fast = fast.next;
            }
        }
        return slow;
    }


    /**
     * 判断链表是否有环：
     * 设置快指针和慢指针，慢指针每次走一步，快指针每次走两步
     * 当快指针与慢指针相等时，就说明该链表有环
     */
    public boolean isRinged()
    {
        if (head == null)
        {
            return false;
        }
        linkNode slow = head, fast = head;

        while (slow.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    /**
     * 返回最后一个节点
     * @return
     */
    public linkNode getLastNode()
    {
        linkNode ptr = head;
        while (ptr.next != null)
        {
            ptr = ptr.next;
        }
        return ptr;
    }

    public linkNode getPreviousNode(int data)
    {
        if (head.data == data) {
            System.out.println(data + "为head,没有前继节点");
            return null;
        }

        linkNode pre = head;
        while (pre.next != null)
        {
            if (pre.next.data == data)
                return pre;
            pre = pre.next;
        }
        System.out.println(data + "不存在于链表中");
        return null;
    }

    /**
     * 判断两个链表是否相交：
     * 两个链表相交，则它们的尾结点一定相同，比较两个链表的尾结点是否相同即可
     */
    public boolean isCross(linkNode head1, linkNode head2)
    {
        linkNode ptr1 = head1;
        linkNode ptr2 = head2;
        while (ptr1.next != null)
            ptr1 = ptr1.next;
        while (ptr2.next != null)
            ptr2 = ptr2.next;
        if (ptr1 == ptr2)
            return true;
        return false;
    }

    /**
     * 寻找有环节点的入口节点
     * 如果两个链表相交且有环；让两个指针分别从链表头和相遇点重新出发，每次走一步，
     * 最后一定到达入口
     * ref：https://zhuanlan.zhihu.com/p/103626709
     * 设链表头到环入口的长度为a，环入口到相遇点长度为b，相遇点到环入口长度为c
     * 在相遇时，快指针的路程=a+（b+c）*k+k，k>=1，k=0说明快慢指针走的路程一样，矛盾
     * 慢指针的路程=a+b；
     * 因为快指针是慢的两倍，因此(a+b)*2 = a + (b+c)*k+b
     * 化简得a = (k-1)(b+c)+c,则链表头到环入口的距离= 相遇点到环入口的距离+ (k - 1)圈数环长度
     */

    public linkNode entryNodeofLoop(linkNode head)
    {
        linkNode fast = head;
        linkNode slow = head;

        while (fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;;
        }

        if (fast.next == null || fast.next.next == null)
            return null;

        slow = head;
        while (slow != fast)
        {
            fast = fast.next;
            slow = slow.next;
        }
        return  fast;
    }

    /**
     * ref：https://zhuanlan.zhihu.com/p/106121179
     * 如果链表相交(无环），求链表相交的起始点：
     * 1、首先判断链表是否相交，如果两个链表不相交，则求相交起点没有意义
     * 2、求出两个链表长度之差：len=length1-length2
     * 3、让较长的链表先走len步
     * 4、然后两个链表同步向前移动，没移动一次就比较它们的结点是否相等，第一个相等的结点即为它们的第一个相交点
     */
    public linkNode findFirstCrossPoint(linkedList l1, linkedList l2)
    {
        if(!isCross(l1.head, l2.head))
            return null;
        int len1 = l1.linkLength();
        int len2 = l2.linkLength();
        int len = len1 - len2;

        linkNode ptr1 = l1.head;
        linkNode ptr2 = l2.head;

        if (len > 0)//链表1比链表2长，链表1先前移len步
        {
            for (int i = 0; i < len; i++) {
                ptr1 = ptr1.next;
            }
        }else {
            for (int i = 0; i < (-len); i++) {
                ptr2 = ptr2.next;
            }
        }

        //链表1和链表2同时前移,直到找到链表1和链表2相交的结点
        while (ptr1 != ptr2)
        {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;

    }

    /**
     * 有环情况下的相交节点，共有六种情况
     * 1）两个链表无环——上一个算法
     * 2）两个链表有环——先求出入口节点；如果入口节点是同一个的话，把相同的入口结点当作是尾结点，这个问题就退化成两个链表都无环，直接判断是否相交即可；
     * 如果入口节点不是同一个的话，从第一个入口节点开始next下去，如果遇到第二个入口节点返回即可；如果回到了本身的入口节点则表示没有相交，直接返回null
     * 3）一个链表有环，一个链表无环——肯定无相交节点
     */

    public linkNode getIntersectNode(linkedList l1, linkedList l2)
    {
        if (l1 == null || l2 == null)
            return  null;

        //求l1和l2的入环节点
        linkNode loop1 = entryNodeofLoop(l1.head);
        linkNode loop2 = entryNodeofLoop(l2.head);

        //如果都没有环
        if (loop1 == null && l2 == null)
            return findFirstCrossPoint(l1, l2);

        //两个都有环
        if (loop1 != null && loop2 != null)
            return findFirstCrossPoint2(l1, l2);

        //一个有环一个没有环
        return null;
    }

    //默认l1 l2有环
    public linkNode findFirstCrossPoint2(linkedList l1, linkedList l2)
    {
        linkNode loop1 = entryNodeofLoop(l1.head);
        linkNode loop2 = entryNodeofLoop(l2.head);

        linkNode cur1 = null;
        linkNode cur2 = null;

        if (loop1 == loop2){//按照正常思路找
            cur1 = l1.head;
            cur2 = l2.head;
            int len1 = 0;
            int len2 = 0;

            while (cur1 != loop1.next){
                len1++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2.next)
            {
                len2++;
                cur2 = cur2.next;
            }

            int len = len1 - len2;
            cur1 = l1.head;
            cur2 = l2.head;
            if (len > 0){
                for (int i = 0; i < len; i++) {
                    cur1 = cur1.next;
                }
            }else {
                for (int i = 0; i < (-len); i++) {
                    cur2 = cur2.next;
                }
            }
            while (cur1 != cur2)//最后肯定可以相交，最差的情况就是相交于环的入口点
            {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;

        }else {
            cur1 = loop1.next;
            while (cur1 != loop1)
            {
                if (cur1 == loop2)
                    return loop1;
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /**
     * 链表结点排序,并返回排序后的头结点:
     * 选择排序算法,即每次都选出未排序结点中最小的结点，与第一个未排序结点交换
     * @return
     */
    public linkNode selectBort()
    {
        linkNode cur = head;
        while (cur.next != null)
        {
            linkNode next = cur.next;
            while (next != null){
                if (next.data < cur.data)
                {
                    int temp = next.data;
                    next.data = cur.data;
                    cur.data = temp;
                }
                next = next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 冒泡排序
     * 设置三个指针tail，p，cur；p和tail用于控制外循环的次数，cur用于内循环
     */

    public void bubbleSort()
    {
        if (head == null || head.next == null)
            return;
        linkNode cur = head;
        linkNode p = head;
        linkNode tail = null;
        while (p.next != null){
            boolean flag = false;
            cur = head;
            while (cur.next != tail) {

                if (cur.data > cur.next.data) {
                    int temp = cur.data;
                    cur.data = cur.next.data;
                    cur.next.data = temp;
                    flag = true;
                }
                cur = cur.next;
            }

            tail = cur;

            if (flag == false)
                return;
        }
    }

    /**
     * 合并排序
     * 需要三个
     * 找中点，合并，排序
     */

    public linkNode mergeSort (linkNode head)
    {
        if (head == null || head.next == null)
            return head;
        linkNode mid = findMiddleNode3(head);
        linkNode right = mid.next;
        mid.next = null;
        linkNode left = mergeSort(head);
        right = mergeSort(right);
        head = mergeLink(left, right);
        return head;
    }

    public linkNode mergeLink(linkNode l1, linkNode l2)
    {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.data <= l2.data) {
            l1.next = mergeLink(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeLink(l1, l2.next);
            return l2;
        }
    }

    public linkNode findMiddleNode3(linkNode head)
    {
        linkNode slow = head;
        linkNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void quickSort()
    {
        if (head == null || head.next == null)
            return;
        quickSort(head, null);
    }

    public void quickSort(linkNode left, linkNode right){
        if (left != right && left.next != right){
            linkNode pivot = partition(left, right);
            quickSort(left, pivot);
            quickSort(pivot.next, right);
        }
    }

    public linkNode partition (linkNode left, linkNode right)
    {
        int baseData = left.data;
        linkNode base = left;
        linkNode cur = left.next;
        while (cur != right)
        {
            if (cur.data < baseData)
            {
                base = base.next;
                swap(base, cur);
            }
            cur = cur.next;
        }
        swap(base, left);
        return base;
    }

    public void swap(linkNode n1, linkNode n2)
    {
        int temp = n1.data;
        n1.data = n2.data;
        n2.data = temp;
    }


}
