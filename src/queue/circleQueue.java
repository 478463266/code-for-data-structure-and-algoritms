package queue;

//ref: https://baijiahao.baidu.com/s?id=1637126358227832423&wfr=spider&for=pc
public class circleQueue {

    private int[] array;
    private int front;
    private int rear;

    public circleQueue(int maxsize){
        front = rear = 0;
        array = new int[maxsize];
    }

    public void clear(){
        front = rear = 0;
    }

    //首尾相同为空
    public boolean isEmpty()
    {
        return front == rear;
    }

    public int length()
    {
        return (rear - front + array.length) % array.length;
    }

    public int peek() throws Exception {
        if (isEmpty())
            throw new Exception("队列为空");
        else
            return array[front];

    }

    public void offer(int data) throws Exception{
        //队列已满
        if ((rear + 1) % array.length == front)
            throw new Exception("队列已满");
        else {
            array[rear] = data;
            rear = (rear + 1) % array.length;
        }
    }

    public int poll() throws Exception {
        if(isEmpty())
            throw new Exception("队列为空");
        else {
            int res = array[front];
            front = (front + 1) % array.length;
            return res;
        }
    }

    public void display()
    {
        if (!isEmpty())
        {
            for (int i = front; i != rear ; i = (i + 1) % array.length) {
                System.out.print(array[i] + " ");
            }
        }
        else {
            System.out.println("此队列为空");
        }
    }



}
