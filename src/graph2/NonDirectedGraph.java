package graph2;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NonDirectedGraph {
    protected class Vertex{
        private String vertexLabel;// 顶点标识
        private List<Edge> adjEdges; //与该顶点邻接的边
        private int dist; //顶点距离（该顶点到起始顶点的距离）
        private Vertex preNode; //用于追溯最短路径
        private int degree; //节点的度
        private boolean visited;

        public Vertex(String vertexLabel) {
            this.vertexLabel = vertexLabel;
            adjEdges = new LinkedList<>();
            dist = Integer.MAX_VALUE;
            preNode = null;
            visited = false;
            degree = 0;
        }

        public List<Edge> getAdjEdges() {
            return adjEdges;
        }

        public String getVertexLabel() {
            return vertexLabel;
        }

        public int getDist() {
            return dist;
        }

        public Vertex getPreNode() {
            return preNode;
        }

        public int getDegree() {
            return degree;
        }

        public boolean isVisited() {
            return visited;
        }

        public void Visited(){
            this.visited = true;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public void setPreNode(Vertex preNode) {
            this.preNode = preNode;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }
    }

    protected class Edge {
        private Vertex endVertex;
        private int weight;

        // private double weight;
        public Edge(Vertex endVertex, int weight) {
            this.endVertex = endVertex;
            this.weight = weight;
        }

        public Vertex getEndVertex() {
            return endVertex;
        }

        public int getWeight() {
            return weight;
        }
    }

    //保存了图中所有的顶点，边的关系以List形式保存在Vertex类中
    public Map<String, Vertex> vertices;
    private int edgeCount;

    public NonDirectedGraph(){
        vertices = new LinkedHashMap<>();
    }

    public void addVertex(String vertexLabel) {
        //若顶点相同时,新插入的顶点将覆盖原顶点,这是由LinkedHashMap的put方法决定的
        //每添加一个顶点,会创建一个LinkedList列表,它存储该顶点对应的邻接点,或者说是与该顶点相关联的边
        vertices.put(vertexLabel, new Vertex(vertexLabel));//new Vertex 对象,会创建一个LinkedList,该LinkedList用来表示该顶点的邻接表
    }

    public void addEdge(String begin, String end, int edgeWeight) {
        Vertex beginVertex = vertices.get(begin);//获得边的起始节点
        Vertex endVertex = vertices.get(end);//获得边的终点

        if (beginVertex == null)
            addVertex(begin);
        if (endVertex == null)
            addVertex(end);

        Edge e = new Edge(endVertex, edgeWeight);
        //对于无向图而言,起点和终点都要添加边
        beginVertex.adjEdges.add(e);
        endVertex.adjEdges.add(e);
        edgeCount++;
        int n1 = beginVertex.getDegree();
        beginVertex.setDegree(n1++);
        int n2 = endVertex.getDegree();
        endVertex.setDegree(n2++);

    }


    public void addEdge(String begin, String end) {
        addEdge(begin, end, 0);
    }

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public int getNumberOfVertices() {
        return vertices.size();
    }

    public int getNumberOfEdges() {
        return edgeCount;
    }

    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    public void reset(){
        for (Vertex v: vertices.values()) {
            v.visited = false;
            v.setPreNode(null);
            v.setDist(Integer.MAX_VALUE);
        }
    }

}
