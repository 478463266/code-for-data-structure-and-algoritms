package graph2;

import graph1.VertexInterface;

import java.util.*;

public class DirectedGraph {

    protected class Vertex{
        private String vertexLabel;// 顶点标识
        private List<Edge> adjEdges; //与该顶点邻接的边
        private int inDegree;// 该顶点的入度-在有向图有作用，无向图为一直0
        private Vertex preNode;
        private boolean visited;//标识顶点是否已访问
        private double cost;//顶点的权值,与边的权值要区别开来
        private int dist; //顶点距离（该顶点到起始顶点的距离），用于最短路径寻找


        public Vertex(String vertexLabel) {
            this.vertexLabel = vertexLabel;
            this.inDegree = 0;
            this.adjEdges = new LinkedList<>();
            this.preNode = null;
            this.visited = false;
            this.cost = 0;
            this.dist = Integer.MAX_VALUE;
        }

        public int getInDegree() {
            return inDegree;
        }

        public double getCost() {
            return cost;
        }

        public String getVertexLabel() {
            return vertexLabel;
        }

        public List<Edge> getAdjEdges() {
            return adjEdges;
        }

        public boolean getVisited(){
            return visited;
        }

        public Vertex getPreNode() {
            return preNode;
        }

        public int getDist() {
            return dist;
        }


        public void Visited() {
            this.visited = true;
        }

        public void setPreNode(Vertex preNode) {
            this.preNode = preNode;
        }

        public void setInDegree(int inDegree) {
            this.inDegree = inDegree;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
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

        public int getWeight() {
            return weight;
        }

        public Vertex getEndVertex() {
            return endVertex;
        }
    }

    //保存了图中所有的顶点，边的关系以List形式保存在Vertex类中
    public Map<String, Vertex> vertices;
    private int edgeCount;

    //按顶点的插入顺序保存顶点,这很重要,因为这会影响到后面图的遍历算法的正确性
    public DirectedGraph(){
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
        beginVertex.adjEdges.add(e);
        endVertex.inDegree++;
        edgeCount++;
        //endVertex.setPreNode(beginVertex);
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

    //易错点：第一次入队就输出，而不是出队才输出
    public void getBreadthFirstTraversal(String origin) {

        reset();
        Vertex beginVertex = vertices.get(origin);
        Queue<Vertex> result = new LinkedList<>();

        result.offer(beginVertex);
        System.out.print(beginVertex.getVertexLabel());
        beginVertex.Visited();

        while (!result.isEmpty()){
            Vertex temp = result.poll();
            for (Edge  e: temp.getAdjEdges()) {
                Vertex newVertex = e.getEndVertex();
                if (newVertex.getVisited() == false) {
                    result.offer(newVertex);
                    System.out.print(newVertex.getVertexLabel());
                    newVertex.Visited();
                }
            }
        }
    }


    //易错点：第一次入栈时输出而不是出栈时输出
    public void getDepthFirstTraversal(String origin) {

        reset();
        Vertex beginVertex = vertices.get(origin);
        Stack<Vertex> result = new Stack<>();

        result.push(beginVertex);
        beginVertex.Visited();
        System.out.print(beginVertex.getVertexLabel());

        while (!result.isEmpty()){
            Vertex temp = result.peek();
            boolean flag = false; //表明temp是否有未被访问的邻居节点
            for (Edge e: temp.getAdjEdges()) {
                Vertex newVertex = e.getEndVertex();
                if (newVertex.getVisited() == false){
                    System.out.print(newVertex.getVertexLabel());
                    result.push(newVertex);
                    newVertex.Visited();
                    flag = true;
                    break;
                }
            }// end for
            if (!flag)
                result.pop();
        }//end while
    }

    public void getDepthFirstTraversal2(String origin) {

        reset();
        Vertex beginVertex = vertices.get(origin);
        beginVertex.Visited();
        System.out.print(beginVertex.getVertexLabel());

        for (Edge e :
                beginVertex.getAdjEdges()) {
            Vertex v = e.getEndVertex();
            if (v.getVisited() == false)
                getDepthFirstTraversal(v.getVertexLabel());
        }

    }

    public void reset(){
        for (Vertex v: vertices.values()) {
            v.visited = false;
            v.setPreNode(null);
        }
    }
}
