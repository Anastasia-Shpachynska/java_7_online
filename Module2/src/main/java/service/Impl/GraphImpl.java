package service.Impl;

import java.awt.*;
import java.io.*;
import java.util.*;

public class GraphImpl implements Graph {
    private int n; // number of cities
    private String[] name; // city names
    private int p; //the number of neighbors in the city
    private ArrayList<ArrayList<Integer>> nr; // neighbor list (neighbor indexes)
    private ArrayList<ArrayList<Integer>> cost; // list of path values to neighbors
    private int r; //the number of distances to be found
    private String[] name1; //start path
    private String[] name2; //finish path
    File file = new File("src/main/java/filesDB/output.txt");

    public void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            n = scanner.nextInt();
            if(n > 10000) {
                System.out.println("The maximum number of cities is 10,000!");
                System.exit(0);
            }
            name = new String[n];
            nr = new ArrayList<>();
            cost = new ArrayList<>();
            name1 = new String[n];
            name2 = new String[n];

                for (int i = 0; i < n; i++) {
                    name[i] = scanner.next();
                    p = scanner.nextInt();

                    ArrayList<Integer> currentNeighbors = new ArrayList<>();
                    ArrayList<Integer> currentCosts = new ArrayList<>();

                    for (int j = 0; j < p; j++) {
                        int currentNr = scanner.nextInt();
                        int currentCost = scanner.nextInt();
                        if(currentCost > 200000) {
                            System.out.println("The maximum permissible value of the distance is 200,000!");
                            System.exit(0);
                        }
                            currentNeighbors.add(currentNr);
                            currentCosts.add(currentCost);
                    }

                    nr.add(currentNeighbors);
                    cost.add(currentCosts);
                }

                r = scanner.nextInt();
                if(r > 100) {
                    System.out.println("The maximum number of paths to search is 100!");
                    System.exit(0);
                }
                ArrayList<Integer> shortPaths = new ArrayList<>();

                for (int i = 0; i < r; i++) {
                    name1[i] = scanner.next();
                    name2[i] = scanner.next();
                    if (!checkPathLengthEquality(name1[i], name2[i])) {
                        System.out.println("The lengths of the paths from " + name1[i] + " to " + name2[i] + " and from " + name2[i] + " to " + name1[i] + " are not equal!");
                        System.exit(0);
                    }
                    int currentPath = findShortestPath(name1[i], name2[i]);
                    shortPaths.add(currentPath);
                }
                writeFile(shortPaths);

            scanner.close();

        }catch (InputMismatchException ex) {
            System.out.println("The input format is not followed. Verify that the number of items entered matches the number you specified. " + ex.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found. Try again. " + e.getMessage());
        }catch (NoSuchElementException ex) {
            System.out.println("Fewer elements entered (city -> city) than specified. " + ex.getMessage());
        }
    }

    public int findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String city : name) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(end)) {
                return distances.get(current);
            }

            if (!visited.contains(current)) {
                visited.add(current);
                int currentIndex = Arrays.asList(name).indexOf(current);

                // Обробка сусідніх міст
                for (int i = 0; i < nr.get(currentIndex).size(); i++) {
                    String neighbor = name[nr.get(currentIndex).get(i) - 1];
                    int costToNeighbor = cost.get(currentIndex).get(i);

                    int totalDistance = distances.get(current) + costToNeighbor;
                    if (totalDistance < distances.get(neighbor)) {
                        distances.put(neighbor, totalDistance);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return -1;
    }

    public void writeFile(ArrayList<Integer> shortPaths) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            if(shortPaths == null) {
                System.out.println("Specify the route in the 'input.txt' file.");
            }
            if(!file.exists()) {
                file.createNewFile();
            }
            System.out.println("File -> " + file.getAbsolutePath());

            for (int shortPath : shortPaths) {
                if(shortPath == -1) {
                    System.out.println("Unable to find path. The path to the city was not found.");
                    System.exit(0);
                }
                writer.write(Integer.toString(shortPath));
                writer.newLine();
            }

            writer.close();
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkPathLengthEquality(String start, String end) {
        int startPathLength = findShortestPath(start, end);
        int endPathLength = findShortestPath(end, start);

        return startPathLength == endPathLength;
    }
}
