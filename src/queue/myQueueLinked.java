package queue;

public class myQueueLinked {
    private mQueueNode first;
    private mQueueNode last;
    private int nodeNum;

    public boolean isEmpty(){
        return  first == null;
    }

    public int size(){
        return nodeNum;
    }

    public void enqueue(int data){
        mQueueNode temp = last;
        last = new mQueueNode();
        last.data = data;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            temp.next = last;
        nodeNum++;
    }

    public int dequeue(){
        int res = first.data;
        first = first.next;
        if (isEmpty())
            last = null;
        nodeNum--;
        return res;
    }
}
