package org.implemica.tasks.task2;

import org.implemica.tasks.task2.algorithm.Dijkstra;
import org.implemica.tasks.task2.graph.Graph;
import org.implemica.tasks.task2.model.CityInfo;
import org.implemica.tasks.task2.uril.Console;

import java.util.List;

// For test use (package org.implemica.tasks.task2.test)
// Task2 uses manual input
public class Task2 {

    public static void main(String[] args) {
        // using console for input data
        CityInfo cityInfo = Console.input();
        // creating graph with cities info data
        Graph graph = Console.getGraph(cityInfo);
        // Dijkstra algorithm
        Dijkstra dijkstra = new Dijkstra(graph);
        // getting paths
        List<List<String>> paths = cityInfo.getCities();
        // Dijkstra algorithm for find min costs
        int[] minCosts = dijkstra.dijkstra(paths);
        // print results
        dijkstra.printMinCosts(minCosts);
    }


}
