package org.implemica.tasks.task2.uril;

import org.implemica.tasks.task2.exception.Task2Exception;
import org.implemica.tasks.task2.graph.Graph;
import org.implemica.tasks.task2.model.CityInfo;

import java.util.*;
import java.util.stream.Collectors;

public class Console {

    // method for input data from console
    public static CityInfo input() {
        CityInfo cityInfo = new CityInfo();
        Map<Integer, Map<String, List<List<Integer>>>> inputMap = new HashMap<>();
        List<List<String>> cites = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int counterCitesIndex = 1;
        try {
            System.out.println("Input:");
            // s [the number of tests <= 10]
            System.out.println("Enter number of tests not more 10");
            int s = getDigitValue(scanner, 10, "s [the number of tests <= 10]");
            for (int i = 0; i < s; i++) {
                // n [the number of cities <= 10000]
                System.out.println("Enter number of cities not more 10000");
                int n = getDigitValue(scanner, 10000, "n [the number of cities <= 10000]");
                for (int k = 1; k <= n; k++) {
                    Map<String, List<List<Integer>>> citiesMap = new HashMap<>();
                    List<List<Integer>> costs = new ArrayList<>();
                    // NAME [city name]
                    System.out.println("Enter city name");
                    String name = scanner.next();
                    if (name.length() > 10 || !name.matches("[a-z]+")) {
                        throw new Task2Exception("The name of a city is a string containing characters a,...,z, and is at most 10 characters long");
                    }
                    // p [the number of neighbors of city NAME]
                    System.out.println("Enter number of city - neighbors");
                    int p = getDigitValue(scanner, Integer.MAX_VALUE, "s [the number of tests <= " + Integer.MAX_VALUE + "]");
                    scanner.nextLine();
                    for (int j = 0; j < p; j++) {
                        // nr cost [nr - index of a city connected to NAME (the index of the first city is 1)]
                        // [cost - the transportation cost]
                        System.out.printf("%s %s %s\n", "Enter id city", "(space)", "Enter cost");
                        String nr = scanner.nextLine();
                        String[] nrCosts = nr.split("\\s+");
                        List<Integer> nrCostsList = getNrCosts(nrCosts);
                        costs.add(nrCostsList);
                    }
                    citiesMap.put(name, costs);
                    inputMap.put(counterCitesIndex, citiesMap);
                    counterCitesIndex++;
                }
                // r [the number of paths to find <= 100]
                System.out.println("Enter number of paths to find not more 100");
                int r = getDigitValue(scanner, 100, "r [the number of paths to find <= 100]");
                scanner.nextLine();
                for (int l = 0; l < r; l++) {
                    // NAME1 NAME2 [NAME1 - source, NAME2 - destination]
                    System.out.printf("%s %s %s\n", "Enter source city", "(space)", "Enter destination city");
                    String name = scanner.nextLine();
                    String[] names = name.split("\\s+");
                    List<String> citiesName = getCitiesName(names);
                    cites.add(citiesName);
                }
            }
            // [empty line separating the tests]
            System.out.println();
        } catch (Task2Exception e) {
            System.err.println(e.getMessage());
            cityInfo = new CityInfo();
            inputMap = new HashMap<>();
        } catch (InputMismatchException e) {
            System.err.println("Not valid data!");
            cityInfo = new CityInfo();
            inputMap = new HashMap<>();
        }
        cityInfo.setCities(cites);
        cityInfo.setInputs(inputMap);
        return cityInfo;
    }

    // convert string to start city and end city
    private static List<String> getCitiesName(String[] names) {
        boolean isDo = false;
        for (String name : names) {
            isDo = !name.matches("[a-z]+");
            if (isDo) break;
        }
        if (!isDo) {
            List<String> paths = Arrays.stream(names)
                    .filter(e -> e.length() > 0)
                    .collect(Collectors.toList());
            if (paths.size() != 2) {
                throw new Task2Exception("Incorrect data (Enter source city or Enter destination city)");
            }
            return paths;
        }
        throw new Task2Exception("Incorrect data (Enter source city or Enter destination city)");
    }

    // converting string to numbers
    private static List<Integer> getNrCosts(String[] nrCosts) {
        boolean isDo = Arrays.stream(nrCosts).anyMatch(e -> e.matches("[^0-9]"));
        if (!isDo) {
            List<Integer> cityAndCost = Arrays.stream(nrCosts)
                    .filter(e -> e.matches("[1-9]+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            if (cityAndCost.size() != 2) {
                throw new Task2Exception("Incorrect data (Enter id city or Enter cost)");
            }
            return cityAndCost;
        }
        throw new Task2Exception("Incorrect data (Enter id city or Enter cost)");
    }

    // creating graph using data from console
    public static Graph getGraph(CityInfo cityInfo) {
        Map<Integer, Map<String, List<List<Integer>>>> inputs = cityInfo.getInputs();
        int vertexN = inputs.size();
        Graph graph = new Graph(vertexN);
        for (Map.Entry<Integer, Map<String, List<List<Integer>>>> entry : inputs.entrySet()) {
            int cityIndex = entry.getKey() - 1;
            for (Map.Entry<String, List<List<Integer>>> cityEntry : entry.getValue().entrySet()) {
                graph.insertVertex(cityEntry.getKey());
                List<List<Integer>> citiesList = cityEntry.getValue();
                for (List<Integer> cities : citiesList) {
                    graph.insertEdge(cityIndex, cities.get(0), cities.get(1));
                }
            }
        }
        return graph;
    }

    // getting numbers and handling exceptions
    private static int getDigitValue(Scanner scanner, int max, String ex) {
        int digitValue = scanner.nextInt();
        if (digitValue > max) {
            throw new Task2Exception(ex);
        } else if (digitValue < 1) {
            throw new Task2Exception("Result cannot be find");
        }
        return digitValue;
    }

}
