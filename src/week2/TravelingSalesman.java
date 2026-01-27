package week2;
import java.util.*;

/*
To implement the Traveling Salesman Problem (TSP), we can use a brute-force approach that explores all permutations
of cities to find the shortest possible route. This method is suitable for a small number of cities because it has a
high computational cost.

Traveling Salesman Problem (TSP) Implementation in Java
This implementation uses a recursive backtracking algorithm to find the minimum distance required to visit every city
exactly once and return to the starting city.

Implementation DetailsAdjacency Matrix: The distances between cities are stored in a 2D array.
Brute-Force Strategy: The code explores every possible permutation of city visits.
Scalability: This approach is only feasible for a small number of cities (typically n < 12); for larger datasets,
you should consider heuristics like the nearest neighbor algorithm or approximation algorithms.
*/

public class TravelingSalesman {

    // Method to calculate the minimum distance of the TSP
    public static int solveTSP(int[][] graph, boolean[] visited, int currPos,
                               int n, int count, int cost, int ans) {

        // If all cities are visited and there is a path back to the origin
        if (count == n && graph[currPos][0] > 0) {
            return Math.min(ans, cost + graph[currPos][0]);
        }

        // BACKTRACKING STEP
        // Loop to traverse the adjacency list of currPos node
        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[currPos][i] > 0) {

                // Mark as visited
                visited[i] = true;
                ans = solveTSP(graph, visited, i, n, count + 1,
                        cost + graph[currPos][i], ans);

                // Mark node as unvisited for other paths
                visited[i] = false;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Example adjacency matrix representing city distances
        int[][] graph = {
                { 0, 10, 15, 20 },
                { 10, 0, 35, 25 },
                { 15, 35, 0, 30 },
                { 20, 25, 30, 0 }
        };

        int n = graph.length;
        boolean[] visited = new boolean[n];

        // Start from the first city
        visited[0] = true;
        int minCost = solveTSP(graph, visited, 0, n, 1, 0, Integer.MAX_VALUE);

        System.out.println("The minimum cost is: " + minCost);
    }
}