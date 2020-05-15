package queue;

public interface mQueue <T>{

    void add(T t);

    T remove();

    int size();

    T front();

    boolean isEmpty();

    boolean isFull();
}
