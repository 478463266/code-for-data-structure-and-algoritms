package stack;

public class myLinkedStack {
    private final class Node{
        private Node pre;
        private long data;
    }

    /**
     * 栈顶指针
     */
    private Node top;
    /**
     * 栈的长度
     */
    private int size;

    public myLinkedStack()
    {
        top = null;
        size = 0;
    }

    public void clear()
    {
        top = null;
        size = 0;
    }

    public int length()
    {
        return size;
    }

    public long peek()
    {
        return top.data;
    }

    public long pop()
    {
        if (top != null)
        {
            Node node = top;
            top = top.pre;
            size--;
            return node.data;
        }
        return 0;
    }

    public void push (long data)
    {
        Node node = new Node();
        node.data = data;
        node.pre = top;
        top = node;
        size++;
    }

}
