package org.implemica.tasks.task2.graph;

public class Graph {

    // vertex points
    private final Vertex[] vertexArray;
    // adjacency matrix
    private final int[][] matrix;
    // number of matrix vertices
    private int count;

    // initialization (n = Vertex[].length/cities count)
    public Graph(int n) {
        vertexArray = new Vertex[n];
        matrix = new int[n][n];
        count = 0;
    }

    // add vertex
    public void insertVertex(String key) {
        vertexArray[count++] = new Vertex(key);
    }

    // add value for matrix intersections
    public void insertEdge(int begin, int end, int weight) {
        matrix[begin][end - 1] = weight;
    }

    // getters
    public int[][] getMatrix() {
        return matrix;
    }

    public Vertex[] getVertexArray() {
        return vertexArray;
    }


}
