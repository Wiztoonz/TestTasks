package org.implemica.tasks.task2.test;

import org.implemica.tasks.task2.algorithm.Dijkstra;
import org.implemica.tasks.task2.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2Test {

    // For test used DATA from TASK2
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        // the number of vertices is specified in the constructor (number of cities)
        Graph graph = new Graph(4);       // city indexes:
        graph.insertVertex("gdansk");    // 0
        graph.insertVertex("bydgoszcz"); // 1
        graph.insertVertex("torun");     // 2
        graph.insertVertex("warszawa");  // 3

        graph.insertEdge(0, 2, 1); // city index: 0 <- city index: 2, cost 1
        graph.insertEdge(0, 3, 3); // city index: 0 <- city index: 3, cost 3
        graph.insertEdge(1, 1, 1); // city index: 1 <- city index: 1, cost 1
        graph.insertEdge(1, 3, 1); // city index: 1 <- city index: 3, cost 1
        graph.insertEdge(1, 4, 4); // city index: 1 <- city index: 4, cost 4
        graph.insertEdge(2, 1, 3); // city index: 2 <- city index: 1, cost 3
        graph.insertEdge(2, 2, 1); // city index: 2 <- city index: 2, cost 1
        graph.insertEdge(2, 4, 1); // city index: 2 <- city index: 4, cost 1
        graph.insertEdge(3, 2, 4); // city index: 3 <- city index: 2, cost 4
        graph.insertEdge(3, 3, 1); // city index: 3 <- city index: 3, cost 1

        // paths
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("gdansk", "warszawa"));    // [forward] cost: 1+1+1=3 (min)
        paths.add(Arrays.asList("bydgoszcz", "warszawa")); // cost: 2 (min)
//        paths.add(Arrays.asList("warszawa", "gdansk"));    // [back] cost: 1+4=5 (min)
        // Dijkstra algorithm
        Dijkstra dijkstra = new Dijkstra(graph);

        // Start city -> End city (method: dijkstra() is return min cost array)
        int[] minCosts = dijkstra.dijkstra(paths);
        dijkstra.printMinCosts(minCosts);

    }

}