package week14n15.mst;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Add vertices A through H to graph1
        Graph graph1 = new Graph();
        String[] vertexNames = { "A", "B", "C", "D", "E", "F", "G", "H" };
        for (String vertexName : vertexNames) {
            graph1.addVertex(vertexName);
        }

        // Add graph1's edges
        graph1.addUndirectedEdge(graph1.getVertex("A"), graph1.getVertex("B"), 15);
        graph1.addUndirectedEdge(graph1.getVertex("A"), graph1.getVertex("D"), 6);
        graph1.addUndirectedEdge(graph1.getVertex("B"), graph1.getVertex("C"), 9);
        graph1.addUndirectedEdge(graph1.getVertex("B"), graph1.getVertex("D"), 12);
        graph1.addUndirectedEdge(graph1.getVertex("B"), graph1.getVertex("G"), 14);
        graph1.addUndirectedEdge(graph1.getVertex("B"), graph1.getVertex("H"), 10);
        graph1.addUndirectedEdge(graph1.getVertex("C"), graph1.getVertex("E"), 16);
        graph1.addUndirectedEdge(graph1.getVertex("D"), graph1.getVertex("E"), 8);
        graph1.addUndirectedEdge(graph1.getVertex("E"), graph1.getVertex("F"), 20);

        // Add vertices A through G, and P, to graph2
        Graph graph2 = new Graph();
        String[] vertexNames2 = { "A", "B", "C", "D", "E", "F", "G", "P" };
        for (String vertexName : vertexNames2) {
            graph2.addVertex(vertexName);
        }

        // Add graph2's edges
        graph2.addUndirectedEdge(graph2.getVertex("A"), graph2.getVertex("B"), 80);
        graph2.addUndirectedEdge(graph2.getVertex("A"), graph2.getVertex("C"), 105);
        graph2.addUndirectedEdge(graph2.getVertex("A"), graph2.getVertex("E"), 182);
        graph2.addUndirectedEdge(graph2.getVertex("B"), graph2.getVertex("C"), 90);
        graph2.addUndirectedEdge(graph2.getVertex("B"), graph2.getVertex("D"), 60);
        graph2.addUndirectedEdge(graph2.getVertex("B"), graph2.getVertex("P"), 100);
        graph2.addUndirectedEdge(graph2.getVertex("C"), graph2.getVertex("P"), 132);
        graph2.addUndirectedEdge(graph2.getVertex("D"), graph2.getVertex("E"), 80);
        graph2.addUndirectedEdge(graph2.getVertex("E"), graph2.getVertex("F"), 70);
        graph2.addUndirectedEdge(graph2.getVertex("F"), graph2.getVertex("G"), 72);
        graph2.addUndirectedEdge(graph2.getVertex("F"), graph2.getVertex("P"), 145);
        graph2.addUndirectedEdge(graph2.getVertex("G"), graph2.getVertex("P"), 180);

        // Get the minimum spanning tree for both graphs
        Graph[] graphs = { graph1, graph2 };
        int graphNum = 1;
        for (Graph graph : graphs) {
            // Get the list of edges for the graph's minimum spanning tree
            List<Edge> treeEdges = graph.minimumSpanningTree();

            // Display the list of edges
            System.out.printf("Edges in minimum spanning tree (graph %d):%n", graphNum);
            for (Edge edge : treeEdges) {
                System.out.print(edge.fromVertex.label + " to " + edge.toVertex.label);
                System.out.printf(", weight = %d%n", (int)edge.weight);
            }

            graphNum++;
        }
    }
}