package graph2;

//基本知识：ref:https://www.cnblogs.com/ZJUT-jiangnan/p/3632525.html
//原理介绍：https://blog.csdn.net/stevensonson/article/details/79177530?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1
//四种方法介绍：https://blog.csdn.net/yjr3426619/article/details/82808303?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1

//Ford-Fulkerson:https://blog.csdn.net/itnerd/article/details/83052413

/*
EK算法、SAP算法、DINIC算法、HLPP算法。这四种算法中，前三种基于增广路，最后一种基于预流推进
方法：求解最大流的四种算法介绍、利用最大流模型解题入门
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 为什么要增加反向边？
 * 在做增广路时可能会阻塞后面的增广路，或者说，做增广路本来是有个顺序才能找完最大流的。但我们是任意找的，为了修正，就每次将流量加在了反向弧上，让后面的流能够进行自我调整。
 * EK(最短路径增广算法))主要思路：对一张网络流图，每次找出它的最小的残量（能增广的量），对其进行增广——BFS
 *
 *
 */
public class MaxFlow {

    int[][] capacity; //容量,记录i到j的剩余流量
    int[] parent; //记录一条增广路中每个节点的前一个节点,parent[i]记录流向i点的前驱节点
    int[] alpha; //记录增广路中每个节点所能调整的流量的最大值
    //alpha[a]记录在一条增广路中，流到i点时，此刻增广路上残余量的最小值，直到i == m时就是整条增广路上的残余量最小值

    //ref：https://www.cnblogs.com/findview/p/11314610.html
    //它不会出现像Ford-Fulkerson那样的问题，如果采用EK算法, 其中的BFS获取的路径一定是最短距离的路径，因此EK算法能够避免Ford-Fulkerson遇到的问题
    public int EK(int m){
        //初始化操作
        int result = 0;
        ///EK算法的核心是通过bfs不断查找增广路，同时建立反向弧
        //每次循环都要对v数组和p数组进行清空，因为是意图查找一条新的增广路了
        while (true){

            for (int i = 0; i < m; i++) {
                parent[i] = alpha[i] = 0;
            }
            Queue<Integer> queue = new LinkedList<>();
            //从1出发，不断找可以到达m的增广路，汇入点的初始量无限大
            alpha[1] = Integer.MAX_VALUE;

            queue.offer(1);
            //BFS,每次只找一条增广路，同时修改capacity[i][j]的值
            while (!queue.isEmpty()){
                int vtop = queue.poll();
                for (int i = 1; i <= m; i++) {
                    //v[i]原本是记录增广路实时的残量最小值，v[i]==0代表这个点还没有走过，且从p到i的残量大于0说明通路
                    if (alpha[i] == 0 && capacity[vtop][i] > 0)
                    {
                        //实时更新alpha[i]的值，alpha[f]存储1条增广路中i点前所有水管残量的最小值，alpha[i]为该条增广路到i点为止，路径上的最小残量
                        alpha[i] = Math.min(alpha[i], capacity[vtop][i]);
                        //p[i]实时保存i点的前驱节点，这样就当i==m时整条增广路就被记录下来
                        parent[i] = vtop;
                        //将i点入队
                        queue.offer(i);
                    }//end if
                }// end for
            }// end inner while
            //汇点可调整流量为0，说明没有增广路了(中途出现了capacity[i][j]==0的情况)，算法结束
            if (alpha[m] == 0)
                return result;

            //汇点可调整流量不为0，那么找到了增广路，增广路上所有节点做流量调整

            for (int i = m; i != 1; i = parent[i]) {
                capacity[parent[i]][i] -= alpha[m];//前向弧流量增加
                capacity[i][parent[i]] += alpha[m];//后向弧流量减少
            }

            //由于一开始流量都为0，调整多少能量就代表整个可行流的流量增加了多少
            result += alpha[m];
        }//end outer while

    }

    //clnic算法：
    // https://blog.csdn.net/u011815404/article/details/86239356
    //https://blog.csdn.net/King8611/article/details/83177252?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1


    //ISAP:https://www.cnblogs.com/owenyu/p/6852664.html


}
