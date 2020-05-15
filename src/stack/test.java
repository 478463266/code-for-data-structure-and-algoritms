package stack;

public class test {

    public static void main(String[] args) {
        myStack s = new myStack(5);
        //System.out.println(s.isEmpty());
        s.push(1);
        s.push(2);
        s.push(5);
        //System.out.println(s.isFull());
        s.push(4);
        s.push(3);
//        System.out.println(s.isEmpty());
//        System.out.println(s.isFull());
//        System.out.println(s.peek());
//        System.out.println(s.pop());
//        System.out.println(s.peek());
//        System.out.println(s.length());
        //s.clear();
        System.out.println(s.toString());

//        s.clear();
//        System.out.println(s.toString());
//        System.out.println(s.isEmpty());
        //System.out.println(s.printMaxSize());
        System.out.println(s.printMaxSize());
        s.resize();
        System.out.println(s.printMaxSize());
        System.out.println(s.toString());

    }

}
