package week14.bfs;

import java.util.HashMap;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Starting vertex name
        String startName = "Eva";

        Graph peopleGraph = new Graph();
        Vertex vertexA = peopleGraph.addVertex("Joe");
        Vertex vertexB = peopleGraph.addVertex("Eva");
        Vertex vertexC = peopleGraph.addVertex("Taj");
        Vertex vertexD = peopleGraph.addVertex("Chen");
        Vertex vertexE = peopleGraph.addVertex("Lily");
        Vertex vertexF = peopleGraph.addVertex("Jun");
        Vertex vertexG = peopleGraph.addVertex("Ken");

        // Add graph edges
        peopleGraph.addUndirectedEdge(vertexA, vertexB);  // Edge from Joe to Eva
        peopleGraph.addUndirectedEdge(vertexA, vertexC);  // Edge from Joe to Taj
        peopleGraph.addUndirectedEdge(vertexB, vertexE);  // Edge from Eva to Lily
        peopleGraph.addUndirectedEdge(vertexC, vertexD);  // Edge from Taj to Chen
        peopleGraph.addUndirectedEdge(vertexC, vertexE);  // Edge from Taj to Lily
        peopleGraph.addUndirectedEdge(vertexD, vertexF);  // Edge from Chen to Jun
        peopleGraph.addUndirectedEdge(vertexE, vertexF);  // Edge from Lily to Jun
        peopleGraph.addUndirectedEdge(vertexF, vertexG);  // Edge from Jun to Ken

        // Get the start vertex
        Vertex startVertex = peopleGraph.getVertex(startName);

        // Create a vertex visitor that adds visited vertices to a vector
        ArrayListVertexVisitor visitor = new ArrayListVertexVisitor();

        if (startVertex != null) {
            HashMap<Vertex, Double> vertexDistances = new HashMap<>();
            peopleGraph.breadthFirstSearch(startVertex, visitor, vertexDistances);

            // Output the result
            System.out.println("Breadth-first search traversal");
            System.out.println("Start vertex: " + startVertex.label);
            for (Vertex vertex : visitor.visitedVertices) {
                System.out.println(vertex.label + ": " + vertexDistances.get(vertex).intValue());
            }
        }
        else {
            System.out.println("Start vertex \"" + startName + "\" not found");
        }
    }
}