package graph2;

import java.util.*;

public class DFS {


    //判断图是否连通
    //从图中的任意一个顶点开始进行深度优先遍历搜索，如图中所有点都可以遍历到，即只有一个连通分量，则说明这个图连通
    public static boolean judgeConnect(DirectedGraph g, String origin) {
        g.reset();

        //DFS
        DirectedGraph.Vertex beginVertex = g.vertices.get(origin);
        Stack<DirectedGraph.Vertex> result = new Stack<>();

        result.push(beginVertex);
        beginVertex.Visited();

        while (!result.isEmpty()) {
            DirectedGraph.Vertex temp = result.peek();
            boolean flag = false; //表明temp是否有未被访问的邻居节点
            for (DirectedGraph.Edge e : temp.getAdjEdges()) {
                DirectedGraph.Vertex newVertex = e.getEndVertex();
                if (newVertex.getVisited() == false) {
                    result.push(newVertex);
                    newVertex.Visited();
                    flag = true;
                    break;
                }
            }// end for
            if (!flag)
                result.pop();
        }//end while

        //从任意一点遍历，这里从下标为0的点开始
        for (DirectedGraph.Vertex v :
                g.vertices.values()) {
            if (!v.getVisited())
                return false;
        }
        
        return true;
    }

    //无向图
    public static boolean judgeConnect(NonDirectedGraph g, String origin) {
        g.reset();

        //DFS
        NonDirectedGraph.Vertex beginVertex = g.vertices.get(origin);
        Stack<NonDirectedGraph.Vertex> result = new Stack<>();

        result.push(beginVertex);
        beginVertex.Visited();

        while (!result.isEmpty()) {
            NonDirectedGraph.Vertex temp = result.peek();
            boolean flag = false; //表明temp是否有未被访问的邻居节点
            for (NonDirectedGraph.Edge e : temp.getAdjEdges()) {
                NonDirectedGraph.Vertex newVertex = e.getEndVertex();
                if (newVertex.isVisited() == false) {
                    result.push(newVertex);
                    newVertex.Visited();
                    flag = true;
                    break;
                }
            }// end for
            if (!flag)
                result.pop();
        }//end while

        //从任意一点遍历，这里从下标为0的点开始
        for (NonDirectedGraph.Vertex v :
                g.vertices.values()) {
            if (!v.isVisited())
                return false;
        }

        return true;
    }

    //割点检测
    //ref: https://www.cnblogs.com/collectionne/p/6837787.html
    //确定u是否为articulation point
    // A recursive function that find articulation points using DFS
    /**
     *
     * @param u: The vertex to be visited next
     * @param visited:keeps tract of visited vertices
     * @param disc: tores discovery times of visited vertices
     * @param low
     * @param parent: Stores parent vertices in DFS tree
     * @param ap: Store articulation points
     */
    private static int time;
    public static void APutil(int u, int disc[],
                       int low[], boolean ap[],
                       DirectedGraph graph){
        // Count of children in DFS Tree
        int children = 0;

        // Mark the current node as visited
        int ascii = 'A';
        String currentVertex = String.valueOf((char)(ascii + u));
        DirectedGraph.Vertex uNode = graph.vertices.get(currentVertex);
        //System.out.println(vertexNode);
        uNode.Visited();

        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;

        // Go through all vertices aadjacent to this
       for (DirectedGraph.Edge edge:
        graph.vertices.get(currentVertex).getAdjEdges()){
           // nextVertex is current adjacent of u
           DirectedGraph.Vertex nextVertex = edge.getEndVertex();
           String nextName = nextVertex.getVertexLabel();
           int next = (int)nextName.charAt(0);
           next = next -  - (int)'A';
           // If nextVertex is not visited yet, then make it a child of u
           // in DFS tree and recur for it
            if (!nextVertex.getVisited()){
                //前向边
                children++;
                nextVertex.setPreNode(uNode);
                APutil(next, disc, low, ap, graph);

                // Check if the subtree rooted with nextVertex has a connection to
                // one of the ancestors of u
                low[u] = Math.min(low[u], low[next]);

                // u is an articulation point in following cases

                //// (1) u is root of DFS tree and has two or more chilren.
                if (uNode.getPreNode() == null && children > 1)
                    ap[u] = true;

                // (2) If u is not root and low value of one of its child
                // is more than discovery value of u.
                if (uNode.getPreNode() != null && low[next] > disc[u])
                    ap[u] = true;

            }
            // Update low value of u for parent function calls
            else if (nextVertex != uNode.getPreNode())
                low[u] = Math.min(low[u], disc[next]);
        }
    }

    // The function to do DFS traversal. It uses recursive function APUtil()
    public static void AP(DirectedGraph graph){
        int num = graph.vertices.size();
        // Mark all the vertices as not visited
        int disc[] = new int[num];
        int low[] = new int[num];
        boolean ap[] = new boolean[num]; // To store articulation points

        // Initialize ap(articulation point） arrays
        graph.reset();
        for (int i = 0; i < num; i++)
        {
            ap[i] = false;
        }

        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (DirectedGraph.Vertex currentNode :
                graph.vertices.values()) {
            if (currentNode.getVisited() == false) {
                String currentName = currentNode.getVertexLabel();
                int u = (int) currentName.charAt(0);
                u = u -(int) 'A';
                APutil(u, disc, low, ap, graph);
            }
        }

        // Now ap[] contains articulation points, print them
        for (int i = 0; i < num; i++){
            if (ap[i] == true)
                System.out.print(i+" ");
        }
    }

    /**
     * 欧拉回路：图G的一个回路，若它恰通过G中每条边一次,则称该回路为欧拉回路。
     * 一个无向图存在欧拉回路，当且仅当该图所有顶点度数都是偶数且该图是连通图
     * 常用方法：1）BFS；2）
     * 判断是否有欧拉回路：
     * 1（无向图）.https://www.cnblogs.com/liuzhen1995/p/6752914.html
     * 2（有向图）.https://blog.csdn.net/seagal890/article/details/94874690
     * 寻找欧拉回路：
     * 1.https://blog.csdn.net/u012505660/article/details/80456783
     * @param g
     */
    public static boolean judgeEulerTour(NonDirectedGraph g){
        //判断给定图的每个顶点的度是否均为偶数
        for (NonDirectedGraph.Vertex v :
                g.vertices.values()) {
            if (v.getDegree() % 2 != 0)
                return false;
        }
        ///使用DFS遍历，判断给定图是否为连通图;
        String origin = "A"; //假定"A"是图中某个点
        if(!judgeConnect(g, origin))
            return false;
        return true;
    }
    public static boolean judgeEulerTour(DirectedGraph g){
        //判断给定图的每个顶点的度是否均为偶数
        for (DirectedGraph.Vertex v :
                g.vertices.values()) {
            int indegree = v.getInDegree();
            int outdegree = v.getAdjEdges().size();
            if (indegree != outdegree)
                return false;
        }
        ///使用DFS遍历，判断给定图是否为连通图;
        String origin = "A"; //假定"A"是图中某个点
        if(!judgeConnect(g, origin))
            return false;
        return true;
    }

    /**
     * 求欧拉回路
     * https://blog.csdn.net/u011466175/article/details/18861415
     * 两种方法：DFS 搜索及Fleury（佛罗莱）算法
     * DFS: 利用欧拉定理判断出一个图存在欧拉通路或回路后，选择一个正确的起始顶点，用DFS 算法遍历所有的边（每条边只遍历一次），遇到走不通就回退。
     * 在搜索前进方向上将遍历过的边按顺序记录下来。这组边的排列就组成了一条欧拉通路或回路
     * 代码：https://www.geeksforgeeks.org/eulerian-path-undirected-graph/?ref=rp
     * Fleury：https://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/
     *
     * @param graph
     */
    public static void findEulerPath(int[][] graph, int n){
        // Find out number of edges each vertex has
        Vector<Integer> numofEdge = new Vector<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += graph[i][j];
            }
            numofEdge.add(sum);
        }

        // Find out how many vertex has odd number edges
        int startPoint = 0, numofOdd = 0;
        for (int i  = n - 1; i >= 0 ; i--) {
            if (numofEdge.elementAt(i) % 2 == 1){
                numofOdd++;
                startPoint = i;
            }
        }

        // If number of vertex with odd number of edges
        // is greater than two return "No Solution".
        if (numofOdd > 2){
            System.out.println("No solution");
            return;
        }

        // If there is a path find the path
        // Initialize empty stack and path
        // take the starting current as discussed
        Stack<Integer> stack = new Stack<>();
        Vector<Integer> path = new Vector<>();
        int cur = startPoint;

        // Loop will run until there is element in the stack
        // or current edge has some neighbour.
        while (!stack.isEmpty() || accumulate(graph[cur], 0) != 0){
            // If current node has not any neighbour
            // add it to path and pop stack
            // set new current to the popped element
            if (accumulate(graph[cur], 0) == 0)
            {
                path.add(cur);
                cur = stack.pop();
            }
            // If the current vertex has at least one
            // neighbour add the current vertex to stack,
            // remove the edge between them and set the
            // current to its neighbour.
            else {
                for (int i = 0; i < n; i++) {
                    if (graph[cur][i] == 1){
                        stack.add(cur);
                        graph[cur][i] = 0;
                        graph[i][cur] = 0;
                        cur = i;
                        break;
                    }
                }
            }
        }
        // print the path
        for (int ele : path)
            System.out.print(ele + " -> ");
        System.out.println(cur);//打印返回的节点

    }

    static int accumulate(int[] arr, int sum)
    {
        for (int i : arr)
            sum += i;
        return sum;
    }

    //判断图是否有回路：方法一：dfs；方法二：拓扑排序（bfs）
    //分析：https://blog.csdn.net/ddppqq/article/details/20579809
    //https://www.cnblogs.com/TenosDoIt/p/3644225.html
    public static boolean judegeCycle(int[][] g, int start, int[] visited,
                                      int[] farther){
        int n = g.length;
        for (int i = 0; i < n; i++) {
            if (i != start && g[start][i] != Integer.MAX_VALUE){
                if (visited[i] == 1 && i != farther[start]){//找到一个环
                    return true;
                }
                else if (visited[i] == 0){
                    farther[i] = start;
                    Cycle(g, i, visited, farther);

                }
            }
        }
        visited[start] = 1;

        return false;
    }

    public static void Cycle(int[][] g, int start, int[] visited,
                                   int[] farther){
        int n = g.length;

         for (int i = 0; i < n; i++) {
             if (i != start && g[start][i] != Integer.MAX_VALUE){
                 if (visited[i] == 1 && i != farther[start]){//找到一个环
                     int temp = start;
                     System.out.print("Cycle: ");
                    while (temp != i){
                        System.out.print(temp + "->");
                        temp = farther[temp];
                    }
                     System.out.println();
                 }
                 else if (visited[i] == 0){
                     farther[i] = start;
                     Cycle(g, i, visited, farther);

                 }
             }
         }
         visited[start] = 1;
    }
    public static void cycleDFS(int[][] g){
        int numofNodes = g.length;
        //-1（白色）：未访问；0：正在访问（灰色）；1：访问完成（黑色，所有邻接点访问完）
        // 如果遍历的过程中发现某个节点有一条边指向颜色为灰的节点，那么存在环
        int[]  visited = new int[numofNodes];
        // father[i] 记录遍历过程中i的父节点
        int[] farther = new int[numofNodes];
        for (int i = 0; i <numofNodes; i++) {
            visited[i] = -1;
            farther[i] = -1;
        }
        //判断是否有环
        judegeCycle(g, 0, visited, farther);

        //判断有多少环
        for (int i = 0; i < numofNodes; i++) {
            if (visited[i] == -1)
                Cycle(g, i, visited, farther);
        }
    }


    //查找有向图中的所有的环
    //https://blog.csdn.net/weixin_30768661/article/details/95003822?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1
    // 从出发节点到当前节点的轨迹
    //缺陷：必须是连通图；非连通图（森林）不能找到
    public static ArrayList<String> trace = new ArrayList<>();
    public static void findCycle(DirectedGraph g, String vertexName){
        DirectedGraph.Vertex vertex = g.vertices.get(vertexName);

        if (vertex.getVisited() == true && !trace.contains(vertexName)){
            vertex.setVisited(false);
        }

        if (vertex.getVisited() == true){
            int flag = trace.indexOf(vertexName);
            if (flag != -1){//当前节点出现在了trace当中
                System.out.print("Cycle:");
                while (flag < trace.size()){
                    System.out.print(trace.get(flag) + "\t");
                    flag++;
                }
                System.out.println();
                return;//输出一个Cicle后返回，继续递归查询
            }
            return;//该节点被访问过，但没有出现在trace中，说明它构成了其他的Cicle，
        }
        vertex.Visited();
        trace.add(vertexName);

        for (DirectedGraph.Edge edge :
                vertex.getAdjEdges()) {
            DirectedGraph.Vertex nextVertex = edge.getEndVertex();
            String nextName = nextVertex.getVertexLabel();
            findCycle(g, nextName);
        }
        trace.remove(trace.size() - 1);
    }

    //连通图的判定
    //单连通：G单向连通当且仅当G中存在一条路，它通过所有节点
    //弱连通：将有向图的所有的有向边替换为无向边，所得到的图称为原图的基图。如果一个有向图的基图是连通图，则有向图是弱连通图
    //强连通：任意两个顶点正反互通
    //方法：https://blog.csdn.net/yugemengjing/article/details/68954277
    /**
     * 输出
     * 输出有一行，数字1,2,3
     * 1代表强连通图
     * 2代表单向连通图
     * 3代表弱连通图
     *
     * 但必须保证g是无向连通的
     */
    public static int judgeConnected(int[][] g){
        int n = g.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (g[i][j] != 0 && g[j][k] != 0)
                        g[i][k] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0 && g[j][i] == 0)
                    return 3;
                if (g[i][j] + g[j][i] == 1)
                    return 2;
            }
        }
        return 1;
    }

    /**
     * 判定强连通：
     * 方法一：TarJan
     * 方法二：（Kosaraju）如果图能从节点1出发寻找到所有的点，然后现在将有向边反向，如果还能搜到所有的点的话，就是一个强连通图，因为能够通过1搜索所有点，
     * 然后反边就说明能够从任一点到达1点
     *
     */
    //方法二：https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/
    //其中的getTranspose()图反转函数是新建了一个图，因此不包含原链路信息
    //方法一：https://www.geeksforgeeks.org/tarjan-algorithm-find-strongly-connected-components/



    //求连通分量个数
    //ref:https://blog.csdn.net/chaiqunxing51/article/details/52813202
    public int CountConnected1(int[][] g){
        boolean[] visited = new boolean[g.length];
        int count = 0;
        for (int i = 0; i < g.length; i++) {
            if (!visited[i]){
                dfs(i, g, visited);
                count++;
            }
        }
        return count;
    }

    public void dfs(int i, int[][] g, boolean[] visited){
        visited[i] = true;
        for (int j = 0; j < g.length; j++) {
            if (!visited[j] && g[i][j] == 1){
                dfs(j, g, visited);
            }
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
        DirectedGraph graph = new DirectedGraph();
        DFS test = new DFS();

        System.out.println("Adding vertexs...");
        graph.addVertex("A"); graph.addVertex("B");
        graph.addVertex("C"); graph.addVertex("D");
        graph.addVertex("E"); graph.addVertex("F");
        graph.addVertex("G");
        System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5

        /*
         * 无向
         *  <A,B>  <B,C>  <C,D>  <B,F>  <B,E>, <E,G>, <F,G>
         */
        System.out.println("Adding edges...");
        graph.addEdge("A", "B");graph.addEdge("B", "C");
        //graph.addEdge("C", "D");
        graph.addEdge("B", "F");
        graph.addEdge("B", "E");graph.addEdge("E", "G");
        graph.addEdge("F", "G");
        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());//5

        System.out.println(test.judgeConnect(graph, "A"));

        }

}
