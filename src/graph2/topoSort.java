package graph2;

//ref:https://www.cnblogs.com/hapjin/tag/%E5%9B%BE/
//https://www.cnblogs.com/hapjin/p/5432996.html


import graph1.Vertex;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class topoSort {


    public static void topoSort(DirectedGraph g) throws Exception{
        int count = 0;

        Queue<DirectedGraph.Vertex> queue = new LinkedList<>();// 拓扑排序中用到的栈,也可用队列.
        //扫描所有的顶点,将入度为0的顶点入队列
        Collection<DirectedGraph.Vertex> vertexs = g.vertices.values();
        for (DirectedGraph.Vertex vertex : vertexs)
            if(vertex.getInDegree() == 0)
                queue.offer(vertex);

        while(!queue.isEmpty()){
            DirectedGraph.Vertex v = queue.poll();
            System.out.print(v.getVertexLabel() + " ");
            count++;
            for (DirectedGraph.Edge e : v.getAdjEdges()) {
                DirectedGraph.Vertex next = e.getEndVertex();
                int nextInDegree = next.getInDegree();
                next.setInDegree(--nextInDegree);
                if (next.getInDegree() == 0)
                    queue.offer(e.getEndVertex());
            }
        }
        if(count != g.vertices.size())
            throw new Exception("Graph has circle");
    }

    public static void main(String[] args) throws Exception {

        /**
         *       A------B-----C-----D
         *             / \
         *            F  E
         *            \  /
         *              G
         */
        DirectedGraph graph = new DirectedGraph();
        topoSort test = new topoSort();

        System.out.println("Adding vertexs...");
        graph.addVertex("A"); graph.addVertex("B");
        graph.addVertex("C"); graph.addVertex("D");
        graph.addVertex("E"); graph.addVertex("F");
        graph.addVertex("G");
        System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5

        /*
         *   <A,B>  <B,C>  <C,D>  <B,F>  <B,E>, <E,G>, <F,G>
         */
        System.out.println("Adding edges...");
        graph.addEdge("A", "B");graph.addEdge("B", "C");
        graph.addEdge("C", "D");graph.addEdge("B", "F");
        graph.addEdge("B", "E");graph.addEdge("E", "G");
        graph.addEdge("F", "G");
        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());//5

        graph.getDepthFirstTraversal("A");
        System.out.println();
        graph.getDepthFirstTraversal2("A");
        System.out.println();
        graph.getBreadthFirstTraversal("A");
        System.out.println();
        test.topoSort(graph);
    }

}
