package stack;

import java.util.*;
//ref：https://blog.csdn.net/Coder_Dacyuan/article/details/79941743?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task


public class postfixExp {
    /**
     * 中缀转后缀代码码实现
     * 1.遇到操作数：添加到后缀表达式中或直接输出
     * 2.栈空时：遇到运算符，直接入栈
     * 3.遇到左括号：将其入栈
     * 4.遇到右括号：执行出栈操作，输出到后缀表达式，直到弹出的是左括号
     * 注意：左括号不输出到后缀表达式
     * 5.遇到其他运算符：弹出所有优先级大于或等于该运算符的栈顶元素，然后将该运算符入栈
     * 6.将栈中剩余内容依次弹出后缀表达式
     */

    //优先级
    static Map<Character, Integer> p;

    public static void setPriority()
    {
        p = new HashMap<>();
        p.put('+', 1);
        p.put('-', 1);
        p.put('*', 2);
        p.put('/', 2);
        //p.put('(', )
    }

    //初始化
    static class Node{
        long num;//操作数
        char op;//操作符
        boolean flag; //true为操作数，false为操作符
    }

    static Stack<Node> s; //操作符栈
    static Queue<Node> q; //后缀表达式队列

    public static void transform(String str)
    {
        s = new Stack<>();
        q = new LinkedList<>();
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length;) {
            Node temp = new Node();
            char c = array[i];
            if (c == '(') //3. 遇到左括号 将其入栈
            {
                temp.flag = false;
                temp.op = c;
                s.push(temp);
                i++;
            }
            else if (c == ')') //4.遇到右括号，执行出栈操作，输出到后缀表达式，直到弹出的是左括号
            {
                while (!s.empty() && s.peek().op != '(')
                {
                    q.add(s.peek());
                    s.pop();
                }
                s.pop(); //弹出左括号
                i++;
            }
            else if (c >= '0' && c <= '9') //如果是操作数
            {
                temp.flag = true;
                temp.num = c - '0';
                i++; //后移一位，因为数字不一定是个位数
                while (i < array.length && array[i] >= '0' && array[i] <= '9')
                {
                    temp.num = temp.num * 10 + (array[i] - '0');
                    i++;
                }
                q.offer(temp); //操作数进入后缀表达式
            }
            else {
                //如果是操作符；弹出所有优先级大于等于该运算符的栈顶元素，然后将该运算符入栈
                temp.flag = false;
                while (!s.empty() && s.peek().op != '(' && p.get(s.peek().op) >= p.get(c)){
                    q.add(s.peek());
                    s.pop();
                }
                temp.op = c;
                s.push(temp);
                i++;
            }

        }
        //6.将栈中剩余内容依次弹出后缀表达式
        while (!s.empty())
        {
            q.add(s.peek());
            s.pop();
        }
    }

    /**
     *
     //后缀表达式的计算
     /*
     从左到右扫描后缀表达式
     1）若是操作数，就压栈，
     2）若是操作符，就连续弹出两个操组数
     3）栈顶的值即为所需结果
     注：先弹出的是第一操作数，后弹出的是第二操作数
     */

    public static long calculate(Queue<Node> q)
    {
        Stack<Node> s = new Stack<Node>();
        Node cur = new Node();
        while (!q.isEmpty()){
            cur = q.peek();
            q.remove();
            if (cur.flag == true)//操作数{
            {
                s.push(cur);
            }else { //操作符
                long n1 = s.pop().num;
                long n2 = s.pop().num;
                Node temp = new Node();
                temp.flag = true;
                if (cur.op == '+')
                    temp.num = n1 + n2;
                else if (cur.op == '-')
                    temp.num = n2 - n1;
                else if (cur.op == '*')
                    temp.num = n2 * n1;
                else
                    temp.num = n2 / n1;
                s.push(temp);//计算结果压栈
            }
        }
        return s.peek().num;
    }

    public static void main(String[] args) {

        setPriority();

        String s = "1+2*3+(4*5+6)*7"; //和为189
        transform(s);
        //System.out.println(calculate(q));//计算也会使发送变化
        Node cur;
        while (!q.isEmpty())
        {
            cur = q.peek();
            if (cur.flag)
                System.out.print(cur.num + " ");
            else
                System.out.print(cur.op + " ");
            q.remove();
        }
        System.out.println();
        //打印后会导致q为空

    }

}
