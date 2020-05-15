package queue;

import java.util.LinkedList;
import java.util.Queue;

public class app_CounOff {
    /**
     * 有 n 个小朋友做游戏，他们的编号分别是1,2,3…n。他们按照编号从小到大依次顺时针围成一个圆圈，从第一个小朋友开始从1 报数，
     * 依次按照顺时针方向报数(加一)，报 m 的人会离开队伍，然后下一个小朋友会继续从 1 开始报数，直到只剩一个小朋友为止。
     * 输入格式
     * 第一行输入俩个整数，n，m。（1≤n,m≤1000）
     * 输出格式
     * 输出最后一个小朋友的编号，占一行。
     * 样例输入
     * 10 3
     * 样例输出
     * 3
     */
    public static int countOff(int n, int m) throws Exception {
        if (n < 1 || m > 1000)
            throw new Exception("输入参数错误");
        if (n == 1)
            return 1;

        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++)
            q.add(i);

        int count = 0;

        while (q.size() > 1)
        {
            count++;

            if (count == m)
            {
                System.out.println("删除："+q.peek());
                q.remove();
                count = 0;
            }
            else {
                System.out.println("当前队头："+ q.peek());
                int temp = q.peek();
                q.remove();
                q.add(temp);
            }
        }
        return q.peek();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(countOff(10,3));
    }
}
