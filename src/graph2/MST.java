package graph2;

public class MST {

    /**
     * kruskal算法的思想是找最小边，且每次找到的边不会和以找出来的边形成环路，利用一个一维数组group存放当前顶点所在连通图标示（每条最小边，属于一个连通图），直到顶点都找完
     * 选择最小边，但保证不形成环路，直到所有点都选完。保证不形成环路的思路是，利用一个数组group存放每个顶点的连通图标示，当所有的顶点的连通图标识都一样，才算全通路，结束
     * ref:https://www.cnblogs.com/yesun/p/3200052.html
     *  @param arcs
     */
    //V:图中点的集合；E:图中边的集合
    public static void Kruskal(int[][] arcs){

        //顶点个数
        int num = arcs.length;
        //存放对应顶点所在的连通图标识
        int[] group = new int[num];

        int sum = 0, n1 = 0, n2 = 0;
        boolean finished = false;
        int groupNum = 1;

        while (!finished){
            int min = Integer.MAX_VALUE;
            //找出所有边中的最小值
            for (int i = 0; i < num; i++) {
                for (int j = i + 1; j < num; j++) {
                    if (arcs[i][j] > 0 && arcs[i][j] < min)
                    {
                        //如果group相同，则表示处理过，不相同或都为0都表示没处理过
                        //如果arcs[i][j]形成环路，则之前group[i]与group[j]的值已经相同（形成了一个连通分量）
                        if (group[i] != group[j] || group[i] == 0 && group[j] == 0){
                            min = arcs[i][j];
                            n1 = i;
                            n2 = j;
                        }//end inner if
                    }//end outer if
                }//end inner forj
            }// end outer fori

            if (min == Integer.MAX_VALUE)
                break;

            System.out.println(n1 + "--->" + n2 + " " + min);
            sum += min;

            //找到了最小值，设置连通标记
            if (group[n1] == 0 && group[n2] == 0){
                group[n1] = groupNum;
                group[n2] = groupNum;
                groupNum++;
            }
            else if (group[n1] > 0 && group[n2] > 0){
                int tmp = group[n2];
                //将所有属于group[n2]的节点的连通分量置为group[n1]
                for (int i = 0; i < group.length; i++) {
                    if (group[i] == tmp)
                        group[i] = group[n1];
                }
            }
            else {
                if (group[n1] == 0)
                    group[n1] = group[n2];
                else
                    group[n2] = group[n1];
            }

            //检测是否到达同一个分量
            for (int i = 0; i < group.length; i++) {
                if (group[i] != group[0]){
                    finished = false;
                    //System.out.println(group[i] + " " + group[0]);
                    break;

                }
                else
                    finished = true;
            }

            if (finished)
                break;

        }
        System.out.println("sum: " + sum);
    }

    /**
     * Kruskal和Prim实现上的最大区别是Kruskal不需要搜索每个顶点的邻接节点
     * 而Prim中需要，所以Prim图构建时需要利用邻接链表进行构建，Kruskal不用！
     * 使用邻接链表构建无向加权图,prim构建MST过程中始终只有一棵树
     * ref:https://blog.csdn.net/weixin_44270248/article/details/89644629?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2
     */
    public static void prim(int[][] arcs){
        int num = arcs.length;
        int[] lowCost = new int[num]; //到新集合的最小权值
        int[] adjVextex = new int[num]; //保存最小权值的顶点编号的前驱节点
        int sum = 0; //记录总权值
        int minID = 0; //保存最小权值的顶点编号
        int min = Integer.MAX_VALUE; //保存lowcost数组中的最小值

        //初始化
        for (int i = 0; i < num; i++) {
            lowCost[i] = arcs[0][i];
        }

        //共需要加入n-1个点
        for (int i = 1; i < num; i++) {
            min = Integer.MAX_VALUE;
            minID = 0;
            for (int j = 1; j < num; j++) {
                if (lowCost[j] != 0 && lowCost[j] < min) { //找到lowcost中最小有效权值
                    min = lowCost[j]; //记录最小值
                    minID = j; //记录最小权值的顶点编号
                }
            }
            lowCost[minID] = 0;
            sum += min;
            System.out.println("连接顶点：" + adjVextex[minID] + "-" + minID + "，权值： " + min);
            //加入该点后，更新其它点到集合的距离
            for (int j = 1; j < num; j++) {
                if (lowCost[j] != 0 && arcs[minID][j] < lowCost[j])
                {
                    lowCost[j] = arcs[minID][j];
                    adjVextex[j] = minID;
                }
            }
        }
        System.out.println("总权值为：" + sum);
    }


    public static void main(String[] args) {

        int MAX = Integer.MAX_VALUE;

        int[][] map = new int[][]{
                {0,10,MAX,MAX,MAX,11,MAX,MAX,MAX},
                {10,0,18,MAX,MAX,MAX,16,MAX,12},
                {MAX,MAX,0,22,MAX,MAX,MAX,MAX,8},
                {MAX,MAX,22,0,20,MAX,MAX,16,21},
                {MAX,MAX,MAX,20,0,26,MAX,7,MAX},
                {11,MAX,MAX,MAX,26,0,17,MAX,MAX},
                {MAX,16,MAX,MAX,MAX,17,0,19,MAX},
                {MAX,MAX,MAX,16,7,MAX,19,0,MAX},
                {MAX,12,8,21,MAX,MAX,MAX,MAX,0}
        };
        Kruskal(map);
        System.out.println();
        prim(map);
    }

}
