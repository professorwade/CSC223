package week2;

/*
To simulate the Clique Problem, we are looking for a subset of vertices in a graph where every vertex is connected to
every other vertex in that subset (a "complete subgraph").

Since the Clique problem is NP-Complete, the simulation below uses a Backtracking (Brute-Force) approach to find the
"Maximum Clique" (the largest possible clique in a given graph).

How this works
Backtracking: The algorithm starts with an empty set and tries adding vertex 0. It then tries to add vertex 1.
Before adding 1, it checks: "Is 1 connected to everything already in the set?"

Verification: If the answer is yes, it moves to the next node. If it hits a dead end, it "backtracks"
(removes the last added node) and tries the next possible candidate.NP-Complete

Behavior: Notice the nested structure. As the number of nodes (N) grows, the number of combinations to check grows
exponentially (2^N). This is why finding the Maximum Clique is so computationally expensive for large graphs.

Real-World Parallel
Think of this like finding a "friend group" at a party where everyone knows everyone else.
Node: A person.
Edge: Two people know each other.
Clique: A group where no one is a stranger to anyone else in that group.

 */


import java.util.*;

public class CliqueSimulator {

    private int[][] adjMatrix;
    private int nodes;
    private List<Integer> maxClique;

    public CliqueSimulator(int[][] matrix) {
        this.adjMatrix = matrix;
        this.nodes = matrix.length;
        this.maxClique = new ArrayList<>();
    }

    // Main recursive function to find the clique
    public void findMaxClique(List<Integer> currentClique, int startIndex) {
        // Try adding each node from startIndex to the end
        for (int i = startIndex; i < nodes; i++) {
            if (canFormClique(currentClique, i)) {
                currentClique.add(i);

                // If the current clique is the largest we've seen, save it
                if (currentClique.size() > maxClique.size()) {
                    maxClique = new ArrayList<>(currentClique);
                }

                // Recurse to try adding more nodes
                findMaxClique(currentClique, i + 1);

                // Backtrack: remove the node to try other combinations
                currentClique.remove(currentClique.size() - 1);
            }
        }
    }

    // Helper: Check if adding 'newNode' maintains a complete subgraph
    private boolean canFormClique(List<Integer> currentClique, int newNode) {
        for (int member : currentClique) {
            if (adjMatrix[member][newNode] == 0) {
                return false;
            }
        }
        return true;
    }

    public void printResult() {
        System.out.println("Maximum Clique size: " + maxClique.size());
        System.out.println("Vertices in Clique: " + maxClique);
    }

    public static void main(String[] args) {
        // Example Graph (Adjacency Matrix)
        // Nodes: 0, 1, 2, 3, 4
        // 0-1, 0-2, 1-2 form a clique of 3
        int[][] graph = {
                {0, 1, 1, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };

        CliqueSimulator simulator = new CliqueSimulator(graph);
        simulator.findMaxClique(new ArrayList<>(), 0);
        simulator.printResult();
    }
}