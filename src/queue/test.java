package queue;

public class test {
    public static void main(String[] args) throws Exception {
        circleQueue cq = new circleQueue(5);
        System.out.println(cq.length());
        System.out.println(cq.isEmpty());
        cq.offer(1);
        cq.offer(2);
        cq.offer(5);
        cq.offer(4);
        cq.display();
        System.out.println(cq.length());
        System.out.println(cq.peek());
        System.out.println(cq.poll());
        System.out.println(cq.peek());
        cq.clear();
        System.out.println(cq.isEmpty());
        //cq.offer(3);
        //cq.display();
        //System.out.println(cq.isEmpty());
    }
}
