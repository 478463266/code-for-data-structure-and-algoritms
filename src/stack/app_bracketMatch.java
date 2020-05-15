package stack;

import java.util.Stack;

/*
检验符号是否匹配.
'['和']', '('和')'成对出现时字符串合法. 例如"[][]()", "[[([]([])()[])]]"是合法的; "([(])", "[())"是不合法的.

遍历字符串的每一个char, 将char与栈顶元素比较. 如果char和栈顶元素配对, 则char不入栈, 否则将char入栈. 当遍历完成时栈为空说明字符串是合法的.
 */

public class app_bracketMatch {

    public static boolean isMatch(String str) {

        Stack<Character> s = new Stack<>();
        char[] array = str.toCharArray();
        for (char c: array) {
            //栈空时将c入栈
            if (s.isEmpty())
                s.push(c);
            //配对时不如栈
            else if (s.peek() == '[' && c == ']'){
                s.pop();
            }
            else if (s.peek() == '(' && c == ')'){
                s.pop();
            }
            //不配对时c入栈
            else {
                s.push(c);
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        String test1 = "[][]()";
        String test2 = "[[()]]";
        String test3 = "[()]]";
        System.out.println(isMatch(test1));
        System.out.println(isMatch(test2));
        System.out.println(isMatch(test3));

    }


}
