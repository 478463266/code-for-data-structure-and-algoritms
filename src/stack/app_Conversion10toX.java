package stack;

import java.util.Stack;

public class app_Conversion10toX {

/**
 *栈的应用举例-将10进制正整数num转换为n进制
 * 超过10进制的会有问题，因为10-15在16进制会有特殊表示
 * @param num 待转化十进制数
 * @param n 转化进制
 * @return
 */

    public static String conversion10toX(int num, int n)
    {
        Stack<Integer> s = new Stack<Integer>();

        Integer result = num;

        while (true){
            //将余数入栈
            s.push(result % n);
            result = result / n;
            if (result == 0)
                break;
        }

        // StringBuffer线程安全，StringBuilder线程不安全效率高
        StringBuilder sb = new StringBuilder();
        //按照出栈顺序排列
        while (!s.isEmpty())
            sb.append(s.pop());
        return sb.toString();

    }

    public static void main(String[] args) {
        int test = 11;
        System.out.println(conversion10toX(test,8));
    }
}
