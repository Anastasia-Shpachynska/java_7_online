package service.Impl;

import java.util.ArrayList;

public interface Graph {
    void readFile(String fileName);
    int findShortestPath(String start, String end);
    void writeFile(ArrayList<Integer> shortPaths);
}
