package queue;

//ref:https://baijiahao.baidu.com/s?id=1637126358227832423&wfr=spider&for=pc
public class myQueue<T> implements mQueue<T> {

    private  T[] data;
    private int size; //队列已有数据本身的大小，而不是数组的长度
    private int front;
    private int rear;

    public myQueue(){
        data = (T[]) new Object[10];
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public void add(T t) {
        if (isFull()){
            resize();
            front = 0;
        }
        rear = (front + size) % data.length;
        data[rear] = t;
        size++;
    }

    @Override
    public T remove() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        T temp = data[front];
        data[front] = null;
        front = (front + 1) % (data.length);
        size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T front() {
    if (isEmpty())
        throw new RuntimeException("队列为空");
    return data[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    public void resize(){
        T[] temp = (T[]) new Object[data.length * 2];
        System.arraycopy(data, 0 , temp, 0, data.length);
        data = temp;
        temp = null;
    }
}
