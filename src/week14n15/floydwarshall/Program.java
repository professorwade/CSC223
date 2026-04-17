package week14n15.floydwarshall;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        String[] graphVertices = {
                "A,B,C,D",   // Graph 1
                "A,B,C,D",   // Graph 2
                "A,B,C",     // Graph 3
                "A,B,C,D,E"  // Graph 4
        };

        String[] graphEdges = {
                "AB2,BC-3,BD7,CA5,DA-4",
                "AB4,BC3,CD6,DA-1,DB7",
                "AB1,AC1,BC-8",
                "AB1,AE8,BC2,CD3,DA-5,ED9"
        };

        String[] graphPaths = {
                "CD", // Show path from C to D in graph 1
                "DB", // Show path from D to B in graph 2
                "CA", // Show path from C to A in graph 3
                "AD"  // Show path from A to D in graph 4
        };

        // Build each graph and the all pairs shortest path matrix for each
        for (int graphNum = 1; graphNum <= graphVertices.length; graphNum++) {
            // Create a new graph
            Graph graph = new Graph();

            // Create and add vertices to the graph and an ArrayList
            ArrayList<Vertex> vertices = new ArrayList<Vertex>();
            for (String vertexName : graphVertices[graphNum - 1].split(",")) {
                vertices.add(graph.addVertex(vertexName));
            }

            // Parse and add edges
            String edgesString = graphEdges[graphNum - 1];
            for (String edgeString : edgesString.split(",")) {
                Vertex fromVertex = graph.getVertex(edgeString.substring(0, 1));
                Vertex toVertex = graph.getVertex(edgeString.substring(1, 2));
                double weight = Double.parseDouble(edgeString.substring(2));
                graph.addDirectedEdge(fromVertex, toVertex, weight);
            }

            // Get the all pairs shortest path matrix
            ArrayList<Edge> allEdges = new ArrayList<Edge>(graph.getEdges());
            ShortestPathMatrix matrix = graph.allPairsShortestPath();

            // Display the matrix
            System.out.printf("All pairs shortest path matrix (graph %d):%n", graphNum);
            matrix.print();

            // Show an actual path sequence
            String startVertexLabel = graphPaths[graphNum - 1].substring(0, 1);
            String endVertexLabel = graphPaths[graphNum - 1].substring(1, 2);
            System.out.printf("Shortest path from %s to %s:%n",
                    startVertexLabel, endVertexLabel);
            Vertex startVertex = graph.getVertex(startVertexLabel);
            Vertex endVertex = graph.getVertex(endVertexLabel);
            List<Edge> path = graph.reconstructPath(startVertex, endVertex, matrix);

            if (path == null || path.size() == 0) {
                System.out.println("No path");
            }
            else {
                System.out.print(path.get(0).fromVertex.label);
                for (Edge edge : path) {
                    System.out.print(" to " + edge.toVertex.label);
                }

                System.out.println();
            }

            System.out.println();
        }
    }
}