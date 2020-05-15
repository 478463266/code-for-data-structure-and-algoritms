package stack;

import java.util.Arrays;

/**栈的特点就是先进后出，后进先出，分别用push和pop表示进栈和出栈操作。
 *应用：
 子程序的调用：
 处理递归调用：
 表达式的转换[中缀表达式转后缀表达式]与求值
 二叉树的遍历。
 图的深度优先(depth-first)搜索法。
 数制转换：通过求余法，每次将余数进栈，最后将所有余数出栈即可。
 括号匹配校验
 迷宫求解
 实现递归-汉诺塔
 */

public class myStack {
    private int maxSize;
    private long[] stackArray;
    private int top;

    public myStack(int s){
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;
    }

    public void push(long data){
        stackArray[++top] = data;
    }

    public long pop()
    {
        return stackArray[top--];
    }

    public long peek()
    {
        return stackArray[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return (top == maxSize - 1);
    }

    public int length()
    {
        return top + 1;
    }

    public void clear(){
        for (int i = 0; i < maxSize; i++) {
            stackArray[i] = 0;
        }
        maxSize = 0;
        top = -1;
    }

    public int printMaxSize()
    {
        return maxSize;
    }

    /**
     * 扩容
     */
    public void resize()
    {
        int size = maxSize;
        maxSize = maxSize * 3 / 2 + 1;
        long[] temp = new long[maxSize * 3 / 2 + 1];
        //copy
        for (int i = 0; i < size; i++) {
            temp[i] = stackArray[i];
            stackArray[i] = 0;
        }
        //将stackArray重新设置为栈空间
        stackArray = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyStack: [");
        for (int i = 0; i < maxSize; i++) {
            sb.append( stackArray[i]);
            if (i != maxSize - 1)
            {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
