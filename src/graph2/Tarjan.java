package graph2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//ref：https://blog.csdn.net/u013376508/article/details/50995675
//https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/

public class Tarjan {
    private int numOfNode;
    private List< ArrayList<Integer> > graph;//图
    private List<ArrayList<Integer>> result;//保存极大强连通图
    private boolean[] inStack;//节点是否在栈内，因为在stack中寻找一个节点不方便。这种方式查找快
    private Stack<Integer> stack;
    private int[] dfn;
    private int[] low;
    private int time;//

    public Tarjan(List< ArrayList<Integer> > graph,int numOfNode) {
        this.graph = graph;
        this.numOfNode = numOfNode;
        this.inStack = new boolean[numOfNode];
        this.stack = new Stack<Integer>();
        dfn = new int[numOfNode]; //第一次dfn节点的编号
        low = new int[numOfNode]; //节点（包括其子节点）前向边的最小编号
        Arrays.fill(dfn, -1);//将dfn所有元素都置为-1，其中dfn[i]=-1代表i还有没被访问过。
        Arrays.fill(low, -1);
        result = new ArrayList<ArrayList<Integer>>();
    }

    //因为可能存在不连通的情况，所以需要对每个节点进行尝试
    public List< ArrayList<Integer> > run(){
        for(int i=0;i<numOfNode;i++){
            if(dfn[i]==-1){
                tarjan(i);
            }
        }
        return result;

    }

    private void tarjan(int current) {
        dfn[current] = low[current] = time++;
        inStack[current] = true;
        stack.add(current);

        for (int i = 0; i < graph.get(current).size(); i++) {
            int next = graph.get(current).get(i);
            if (dfn[next] == -1){//没有被访问过
                tarjan(next);
                low[current] = Math.min(low[current], low[next]);
            }else if (inStack[next]){//访问过并且在栈中，没有出栈
                low[current] = Math.min(low[current], dfn[next]);
            }
        }

        if (low[current] == dfn[current]){
            ArrayList<Integer> temp = new ArrayList<>();
            int j = -1;
            while (current != j){
                j = stack.pop();
                inStack[j] = false;
                temp.add(j);
            }
            result.add(temp);
        }
    }

    public static void main(String[] args) {
        //创建图
        int numOfNode = 6;
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numOfNode; i++) {
            graph.add(new ArrayList<Integer>());
        }
        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(2).add(3);
        graph.get(2).add(4);
        graph.get(3).add(0);
        graph.get(3).add(5);
        graph.get(4).add(5);
        //调用Tarjan算法求极大连通子图
        Tarjan t = new Tarjan(graph, numOfNode);
        List<ArrayList<Integer>> result = t.run();
        //打印结果
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
