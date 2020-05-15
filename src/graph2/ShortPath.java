package graph2;
//https://blog.csdn.net/qq_35710556/article/details/79583229?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-2

import graph1.GraphInterface;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 深度或广度优先搜索算法，费罗伊德Floyd算法，迪杰斯特拉算法，Bellman-Ford 算法。
 */

//单源最短路径问题
public class ShortPath {

    /**
     * 给出一个无向图，指定无向图中某个顶点作为源点。求出图中所有顶点到源点的最短路径。
     * 无权重
     * 广度优先搜索
     * ref: https://www.cnblogs.com/hapjin/p/5435724.html
     * @param g
     * @param first
     */
    public static void DFS(NonDirectedGraph g, String first){

        Queue<NonDirectedGraph.Vertex> queue = new LinkedList<>();

        NonDirectedGraph.Vertex firstVetex = g.vertices.get(first);

        firstVetex.setDist(0);
        queue.offer(firstVetex);

        while (!queue.isEmpty()){
            NonDirectedGraph.Vertex temp = queue.poll();
            for (NonDirectedGraph.Edge e: temp.getAdjEdges()) {
                if (e.getEndVertex().getDist() == Integer.MAX_VALUE)
                {
                    e.getEndVertex().setDist(temp.getDist() + 1);
                    queue.offer(e.getEndVertex());
                    e.getEndVertex().setPreNode(temp);
                }//end if
            }//end for
        }//end while
    }

    
    /**
     * ref:https://www.cnblogs.com/hapjin/p/5654756.html
     * 使用广度优先搜索解决赋权有向图或者无向图的单源最短路径问题
     * Dijkstra算法只能适用于权值为正的情况下；如果权值存在负数，则不能使用
     * 主要原理：
     * 寻找具有最小距离的未知节点-使用最小堆（优先队列）效率更高
     * 单源最短路径
     * 如果提前知道图是无环的，可以利用拓扑排序选择顶点，直接一趟完成选择和更新，时间复杂度为O(E+V)
     */
    public static void Dijkstra(NonDirectedGraph g, String first){
        NonDirectedGraph.Vertex firstVetex = g.vertices.get(first);
        firstVetex.setDist(0);
        //寻找具有最小距离的未知节点
        while (true){
            //1.寻找一个未被访问的节点作为初始的min
            NonDirectedGraph.Vertex min = null;
            for (NonDirectedGraph.Vertex v : g.vertices.values()) {
                if (v.isVisited() == false)
                    min = v;
            }//end for
            //2.寻找其中距离最小的节点
            if (min != null) {
                for (NonDirectedGraph.Vertex v : g.vertices.values()) {
                    if (v.isVisited() == false && v.getDist() < min.getDist())
                        min = v;
                }//end for
                //进行更新
                min.Visited();
                for (NonDirectedGraph.Edge e: min.getAdjEdges()){
                    NonDirectedGraph.Vertex adjNode = e.getEndVertex();
                    if (adjNode.getDist() > e.getWeight() + min.getDist()){
                        adjNode.setDist(e.getWeight() + min.getDist());
                        adjNode.setPreNode(min);
                    }
                }

            }// end if(min!=null)
            else break;//没有未被访问的点，算法结束
        }//end while

    }

    /**
     * ref: https://blog.csdn.net/yuewenyao/article/details/81026278
     * 解决带有负权的图的最短路径;带负可能导致无法收敛
     * 可以用来判断是否有环：经过n-1轮后，依然存在if(dis[v[i]] > dis[u[i]] + w[i]) dis[v[i]] = dis[u[i]] + w[i];
     * 以下算法改进：没有改进的话直接跳出循环
     * 单源
     * @param g
     * @param first
     */
    public static void Bellman_Ford(NonDirectedGraph g, String first){


        NonDirectedGraph.Vertex beginVertex = g.vertices.get(first);
        beginVertex.setDist(0);

        int numofVertex = g.vertices.size();

        //进行V-1次循环, 每一次循环求出从起点到其余所有点
        for (int i = 1; i < numofVertex; i++) {
            // 每次循环中对所有的边进行一遍松弛操作
            // 遍历所有边的方式是先遍历所有的顶点, 然后遍历和所有顶点相邻的所有边
            for (NonDirectedGraph.Vertex v : g.vertices.values()) {
                for (NonDirectedGraph.Edge edge: v.getAdjEdges()) {
                    NonDirectedGraph.Vertex adjV = edge.getEndVertex();
                    if (adjV.getDist() > v.getDist() + edge.getWeight())
                    {
                        adjV.setDist( v.getDist() + edge.getWeight());
                        adjV.setPreNode(v);
                    }
                }
            }
            
        }
        System.out.println("是否有负权图：" + detectNegativeCycle(g, first));

    }

    // 判断图中是否有负权环
    public static boolean detectNegativeCycle(NonDirectedGraph g, String first){
        for (NonDirectedGraph.Vertex v : g.vertices.values()) {
            for (NonDirectedGraph.Edge edge: v.getAdjEdges()) {
                NonDirectedGraph.Vertex adjV = edge.getEndVertex();
                if (adjV.getDist() > v.getDist() + edge.getWeight())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * SPFA算法：
     * 可计算带有负边值的图
     * 单源最短路径
     * 时间复杂度比bellman-ford更小
     */
    public static void SPFA(NonDirectedGraph g, String first){
        NonDirectedGraph.Vertex beginVertex = g.vertices.get(first);
        Queue<NonDirectedGraph.Vertex> queue = new LinkedList<>();
        queue.clear();
        queue.offer(beginVertex);
        beginVertex.setDist(0);

        while (!queue.isEmpty()){
            NonDirectedGraph.Vertex v = queue.poll();
            for (NonDirectedGraph.Edge e :
                    v.getAdjEdges()) {
                NonDirectedGraph.Vertex adjV = e.getEndVertex();
                if (adjV.getDist() > e.getWeight() + v.getDist())
                {
                    adjV.setDist(e.getWeight() + v.getDist());
                    adjV.setPreNode(v);
                    if (!queue.contains(adjV))
                        queue.offer(adjV);
                }
            }
        }
    }

    /**
     * 利用动态规划的思想寻找给定的加权图中多源点之间最短路径
     * 一种在具有正或负边缘权重（但没有负环）的加权图中找到最短路径的算法，即支持负权值但不支持负权环
     * 多源最短路径，其时间复杂度为O(n³)
     * ref:https://blog.csdn.net/sinat_22013331/article/details/51000331
     */

    public static int[][] Floyd(int[][] adjMatrix){
        int size = adjMatrix.length;
        int[][] distance = new int[size][size];

        // 初始化距离矩阵
        for(int i=0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                distance[i][j] = adjMatrix[i][j];
            }
        }

        //循环更新矩阵的值
        for(int k=0; k<size; k++){
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    int temp = (distance[i][k] == Integer.MAX_VALUE || distance[k][j] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : distance[i][k] + distance[k][j];
                    if(distance[i][j] > temp){
                        distance[i][j] = temp;
                    }
                }//end 3th for
            }//end 2th for
        }//end 1th for
        return distance;
    }



    /**
     * 根据邻接表计算邻接矩阵
     * new TreeMap：自然顺序，即按字符串中的字母和数字的大小来排序
     * new hashMap: 乱序
     * new LinkedHashMap：按照插入顺序
     * @param g
     * @return
     */
    public static int[][] adjMatrix(NonDirectedGraph g){
        int numofNodes = g.vertices.size();
        int[][] result = new int[numofNodes][numofNodes];
        for (int i = 0; i < numofNodes; i++) {
            for (int j = 0; j < numofNodes; j++) {
                if (i == j)
                    result[i][j] = 0;
                else
                    result[i][j] = Integer.MAX_VALUE;
            }
        }

        //showMatrix(result);

        //由于是LinkedHashMap，按照插入顺序
        int i = 0;
        for (Map.Entry entry: g.vertices.entrySet()){
            NonDirectedGraph.Vertex node = (NonDirectedGraph.Vertex)entry.getValue();
            for (NonDirectedGraph.Edge e: node.getAdjEdges()){
                NonDirectedGraph.Vertex temp = e.getEndVertex();
                int j = (int)(temp.getVertexLabel().toCharArray()[0]) - (int)'A';
                if (i != j)//因为是无向边，会导致matrix[i][i]发生变化
                    result[i][j] = e.getWeight();
            }
            i++;
        }
        return result;
    }

    //打印图中所有顶点到源点的距离及路径
    public static void showDistance(NonDirectedGraph g, String first)
    {
        NonDirectedGraph.Vertex firstVetex = g.vertices.get(first);
        System.out.print(first + "<--");
        Collection<NonDirectedGraph.Vertex> vertices = g.vertices.values();
        for (NonDirectedGraph.Vertex v: vertices){

            System.out.print(v.getVertexLabel() + "<--");
            NonDirectedGraph.Vertex tmpPreNode = v.getPreNode();
            while (tmpPreNode != null){
                System.out.print(tmpPreNode.getVertexLabel() + "<--");
                tmpPreNode = tmpPreNode.getPreNode();
            }//end while
            System.out.println("distance = " + v.getDist());
        }
    }


    public static void showMatrix(int[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {

        /**
         *       A------B-----C-----D
         *             / \
         *            F  E
         *            \  /
         *              G
         */
        NonDirectedGraph graph = new NonDirectedGraph();
        ShortPath test = new ShortPath();

        System.out.println("Adding vertexs...");
        graph.addVertex("A"); graph.addVertex("B");
        graph.addVertex("C"); graph.addVertex("D");
        graph.addVertex("E"); graph.addVertex("F");
        graph.addVertex("G");
        System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5

        //测试BFS
        /*
         * 无向
         *  <A,B>  <B,C>  <C,D>  <B,F>  <B,E>, <E,G>, <F,G>
         */
//        System.out.println("Adding edges...");
//        graph.addEdge("A", "B");graph.addEdge("B", "C");
//        graph.addEdge("C", "D");graph.addEdge("B", "F");
//        graph.addEdge("B", "E");graph.addEdge("E", "G");
//        graph.addEdge("F", "G");
//        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());//5
        //test.DFS(graph, "A");
        //test.showDistance(graph, "A");

        //测试Dijkstra
        System.out.println("Adding edges...");
        graph.addEdge("A", "B",1);graph.addEdge("B", "C",1);
        graph.addEdge("C", "D",1);graph.addEdge("B", "F", 2);
        graph.addEdge("B", "E", 1);graph.addEdge("E", "G", 1);
        graph.addEdge("F", "G",1);
        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());
        test.Dijkstra(graph, "A");
        test.showDistance(graph, "A");
        graph.reset();
        System.out.println();
        test.Bellman_Ford(graph, "A");
        test.showDistance(graph,"A");
        graph.reset();
        System.out.println();
        test.SPFA(graph, "A");
        test.showDistance(graph, "A");


        //测试Floyd
//        System.out.println("Adding edges...");
//        graph.addEdge("A", "B",1);graph.addEdge("B", "C",1);
//        graph.addEdge("C", "D",1);graph.addEdge("B", "F", 2);
//        graph.addEdge("B", "E", 1);graph.addEdge("E", "G", 1);
//        graph.addEdge("F", "G",1);
//        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());
//        //其实这里按照有向图计算了
//        test.showMatrix(test.adjMatrix(graph));
//        test.showMatrix(test.Floyd(test.adjMatrix(graph)));

    }


}
