package org.implemica.tasks.task2.algorithm;

import org.implemica.tasks.task2.graph.Graph;
import org.implemica.tasks.task2.graph.Vertex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

    private final Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public int[] dijkstra(List<List<String>> paths) {
        int[] minCosts = new int[paths.size()];
        int counter = 0;
        for (List<String> path : paths) {
            String startCity = path.get(0);
            String endCity = path.get(1);
            Vertex[] vertexArray = graph.getVertexArray();
            int[][] adjacencyMatrix = graph.getMatrix();
            int startCityIndex = getCityIndexByCityName(startCity, vertexArray);
            int endCityIndex = getCityIndexByCityName(endCity, vertexArray);
            //  swap for if find path should be from end city
            boolean doSwap = isSwap(vertexArray, adjacencyMatrix, startCityIndex, endCityIndex);
            if (doSwap) {
                startCityIndex = startCityIndex ^ endCityIndex;
                endCityIndex = startCityIndex ^ endCityIndex;
                startCityIndex = startCityIndex ^ endCityIndex;
            }
            int vortex = adjacencyMatrix.length;
            boolean[] visited = new boolean[vortex];
            int[] distance = new int[vortex];
            for (int i = startCityIndex + 1; i < vortex; i++) {
                distance[i] = Integer.MAX_VALUE;
            }
            for (int i = 0; i < vortex; i++) {
                if (endCityIndex == i) {
                    minCosts[counter] = distance[i];
                }
                // Find Vertex with Min distance
                int minVertex = findMinVertex(distance, visited);
                visited[minVertex] = true;
                for (int j = 0; j < vortex; j++) {
                    if (adjacencyMatrix[minVertex][j] != 0 && !visited[j] && distance[minVertex] != Integer.MAX_VALUE) {
                        int newDistance = distance[minVertex] + adjacencyMatrix[minVertex][j];
                        if (newDistance < distance[j]) {
                            distance[j] = newDistance;
                        }
                    }
                }
            }
            counter++;
        }
        return minCosts;
    }

    public void printMinCosts(int[] minCosts) {
        if (minCosts.length > 0) {
            System.out.println("Output:");
            for (int minCost : minCosts) {
                System.out.println(minCost);
            }
        }
    }

    private int findMinVertex(int[] dist, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i] && (minVertex == -1 || dist[i] < dist[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    private int getCityIndexByCityName(String cityName, Vertex[] vertexArray) {
        int index = -1;
        for (int i = 0; i < vertexArray.length; i++) {
            String cityLabel = vertexArray[i].getLabel();
            if (cityName.equals(cityLabel)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    // swap for if find path should be from end city
    private boolean isSwap(Vertex[] vertexArray, int[][] adjacencyMatrix, int startCityIndex, int endCityIndex) {
        if (startCityIndex > endCityIndex) {
            Collections.reverse(Arrays.asList(vertexArray));
            Collections.reverse(Arrays.asList(adjacencyMatrix));
        }
        return startCityIndex > endCityIndex;
    }

}