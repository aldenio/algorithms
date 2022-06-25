package hackerrank.bfs_shortest_reach_in_a_graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Graph {
  public static final int DISTANCE = 6;

  private int numNodes;
  private int startingNode;
  private Map<Integer, Set<Integer>> edges;

  public Graph(int numNodes) {
    this.numNodes = numNodes;
    this.edges = new HashMap<>();
  }

  public void addEdge(int n1, int n2) {
    if (n1 == n2)
      return;
    if (!edges.containsKey(n1)) {
      edges.put(n1, new HashSet<>());
    }
    if (!edges.containsKey(n2)) {
      edges.put(n2, new HashSet<>());
    }
    edges.get(n1).add(n2);
    edges.get(n2).add(n1);
  }

  public void setStartingNode(int node) {
    this.startingNode = node;
  }

  public void getDistances() {
    Queue<Pair> nodesToVisit = new LinkedList<>();
    nodesToVisit.add(new Pair(startingNode));
    boolean[] visited = new boolean[numNodes];

    Integer[] distances = new Integer[numNodes];
    Arrays.fill(distances, 0);

    while (!nodesToVisit.isEmpty() && !allTrue(visited)) {
      Pair current = nodesToVisit.remove();
      if (visited[current.node])
        continue;
      visited[current.node] = true;
      distances[current.node] = current.distance;
      Set<Integer> currentNodeEdges = edges.get(current.node);
      if (currentNodeEdges != null){
        for (int neighbor : currentNodeEdges) {
          if (!visited[neighbor] && !nodesToVisit.contains(new Pair(neighbor))) {
            nodesToVisit.add(new Pair(neighbor, current.distance + DISTANCE));
          }
        }
      }
    }
    // set non visited nodes distance to -1
    for (int i = 0; i < distances.length; i++) {
      if (distances[i] == 0) {
        distances[i] = -1;
      }
    }
    distances[startingNode] = 0;
    // print the result
    System.out.println(Stream.of(distances).filter(it -> it != 0).map(String::valueOf).collect(Collectors.joining(" ")));
  }

  private boolean allTrue(boolean[] array) {
    for (int i = 0; i < array.length; i++) {
      if (!array[i])
        return false;
    }
    return true;
  }

  class Pair {
    public final int node;
    public final int distance;

    public Pair(int node) {
      this.node = node;
      this.distance = 0;
    }

    public Pair(int node, int distance) {
      this.node = node;
      this.distance = distance;
    }

    @Override
    public boolean equals(Object other){
      return (other != null) && (other instanceof Pair) && (((Pair)other).node == this.node);
    }
    @Override
    public int hashCode(){
      return this.node;
    }
  }
}

public class Solution {
  public static void main(String[] args) {
    /*
     * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
     * class should be named Solution.
     */
    try (Scanner in = new Scanner(System.in)) {
      int numQueries = in.nextInt();

      for (int i = 0; i < numQueries; i++) {
        int numNodes = in.nextInt();
        int numEdges = in.nextInt();

        Graph graph = new Graph(numNodes);
        for (int j = 0; j < numEdges; j++) {
          int n1 = in.nextInt();
          int n2 = in.nextInt();
          graph.addEdge(n1 - 1, n2 - 1);
        }
        graph.setStartingNode(in.nextInt() - 1);
        graph.getDistances();
      }
    }
  }
}
