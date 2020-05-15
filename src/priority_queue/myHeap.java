package priority_queue;

public class myHeap {

    private static final int defaultCapacity = 10;
    private int size;
    private Comparable[] array;

    public int getSize()
    {
        return size;
    }

    public myHeap()
    {
        this(defaultCapacity);
    }

    public myHeap(int capacity)
    {
        size = 0;
        array = new Comparable[capacity + 1];//堆中第一个元素用于存放一个很小的值，称为标记，用于终止插入操作
    }

    public myHeap(Comparable[] items)
    {
        size = items.length;
        array = new Comparable[size + 1];//这里可能有问题

        for (int i = 0; i < size; i++) {
            array[i + 1] = items[i];
        }
        buildHeap();
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean isFull()
    {
        return size == array.length - 1;
    }

    public void makeEmpty()
    {
        size = 0;
    }

    //上滤 percolate up
    public boolean insert(Comparable data)
    {
        if (isFull()){
            System.out.println("优先队列已满！");
        }

        int i;
        for (i = ++size; i > 1 && data.compareTo(array[i / 2]) < 0; i /= 2) //创建一个空穴用于存放，紧挨着原有数据以保证必须是完全树
        {
            array[i] = array [i / 2];
        }
        array[i] = data;
        return true;
    }


    public Comparable findMin()
    {
        if (isEmpty())
            return null;
        return array[1];
    }

    public Comparable deleteMin()
    {
        if (isEmpty())
        {
            System.out.println("优先队列为空");
            return null;
        }

        Comparable minElement = array[1];
        Comparable lastElement = array[size--];

        int i, child;

        //下滤 percolate down
        for(i = 1; i * 2 <= size; i = child)
        {
            child = i * 2;
            if (child != size && array[child + 1].compareTo(array[child]) < 0) //获得最小的孩子
                child++;
            if (array[child].compareTo(lastElement) < 0) //最小的孩子是否小于最后一个元素
                array[i] = array[child];
            else
                break;;
        }
        array[i] = lastElement; //i为叶节点或者i的孩子都比最后一个元素大
        return minElement;
    }

    public void buildHeap()
    {
        for (int i = size / 2; i > 0 ; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int hole)
    {
        int child;
        Comparable temp = array[hole];
        for (; hole * 2 <= size; hole = child) {
            child = hole * 2;
            if (child !=  size && array[child + 1].compareTo(array[child]) < 0)
                child++;
            if (array[child].compareTo(temp) < 0)
                array[hole] = array[child];
            else
                break;
        }
        array[hole] = temp;
    }
}
