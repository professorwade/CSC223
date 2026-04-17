package week14n15.floydwarshall;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Graph {
    // Maps a vertex to an ArrayList of all edges that start from that vertex
    private HashMap<Vertex, ArrayList<Edge>> fromEdges;

    // Maps a vertex to an ArrayList of all edges that go to that vertex
    private HashMap<Vertex, ArrayList<Edge>> toEdges;

    public Graph() {
        fromEdges = new HashMap<Vertex, ArrayList<Edge>>();
        toEdges = new HashMap<Vertex, ArrayList<Edge>>();
    }

    public Vertex addVertex(String newVertexLabel) {
        // Create the new Vertex object
        Vertex newVertex = new Vertex(newVertexLabel);

        // Every vertex must exist as a key in both maps
        fromEdges.put(newVertex, new ArrayList<Edge>());
        toEdges.put(newVertex, new ArrayList<Edge>());

        return newVertex;
    }

    public Edge addDirectedEdge(Vertex fromVertex, Vertex toVertex) {
        // Use 1.0 as the default edge weight
        return addDirectedEdge(fromVertex, toVertex, 1.0);
    }

    public Edge addDirectedEdge(Vertex fromVertex, Vertex toVertex, double weight) {
        // Don't add the same edge twice
        if (hasEdge(fromVertex, toVertex)) {
            return null;
        }

        // Create the Edge object
        Edge newEdge = new Edge(fromVertex, toVertex, weight);

        // Add the edge to the appropriate list in both maps
        fromEdges.get(fromVertex).add(newEdge);
        toEdges.get(toVertex).add(newEdge);

        return newEdge;
    }

    public Edge[] addUndirectedEdge(Vertex vertexA, Vertex vertexB) {
        // Use 1.0 as the default edge weight
        return addUndirectedEdge(vertexA, vertexB, 1.0);
    }

    public Edge[] addUndirectedEdge(Vertex vertexA, Vertex vertexB, double weight) {
        Edge edge1 = addDirectedEdge(vertexA, vertexB, weight);
        Edge edge2 = addDirectedEdge(vertexB, vertexA, weight);
        Edge[] result = { edge1, edge2 };
        return result;
    }

    // Returns a collection of all edges in the graph
    public Collection<Edge> getEdges() {
        HashSet<Edge> edges = new HashSet<Edge>();
        for (ArrayList<Edge> edgeList : fromEdges.values()) {
            edges.addAll(edgeList);
        }

        return edges;
    }

    // Returns the collection of edges with the specified fromVertex
    public Collection<Edge> getEdgesFrom(Vertex fromVertex) {
        return fromEdges.get(fromVertex);
    }

    // Returns the collection of edges with the specified toVertex
    public Collection<Edge> getEdgesTo(Vertex toVertex) {
        return toEdges.get(toVertex);
    }

    // Returns a vertex with a matching label, or null if no such vertex exists
    public Vertex getVertex(String vertexLabel) {
        // Search the collection of vertices for a vertex with a matching label
        for (Vertex vertex : getVertices()) {
            if (vertex.label.equals(vertexLabel)) {
                return vertex;
            }
        }

        return null;
    }

    // Returns the collection of all of this graph's vertices
    public Collection<Vertex> getVertices() {
        return fromEdges.keySet();
    }

    // Returns true if this graph has an edge from fromVertex to toVertex
    public boolean hasEdge(Vertex fromVertex, Vertex toVertex) {
        if (!fromEdges.containsKey(fromVertex)) {
            // fromVertex is not in this graph
            return false;
        }

        // Search the list of edges for an edge that goes to toVertex
        ArrayList<Edge> edges = fromEdges.get(fromVertex);
        for (Edge edge : edges) {
            if (edge.toVertex == toVertex) {
                return true;
            }
        }

        return false;
    }

    // Builds a ShortestPathMatrix using the Floyd-Warshall algorithm
    public ShortestPathMatrix allPairsShortestPath() {
        Collection<Vertex> allVertices = getVertices();
        Collection<Edge> allEdges = getEdges();

        // Initialize the matrix
        ShortestPathMatrix distMatrix = new ShortestPathMatrix(allVertices);

        // Set each distance for vertex to same vertex to 0
        for (Vertex vertex : allVertices) {
            distMatrix.set(vertex, vertex, 0.0);
        }

        // Finish matrix initialization
        for (Edge edge : allEdges) {
            distMatrix.set(edge.fromVertex, edge.toVertex, edge.weight);
        }

        // Loop through vertices
        for (Vertex kVertex : allVertices) { // main loop
            for (Vertex fromVertex : allVertices) {
                for (Vertex toVertex : allVertices) {
                    double currentLength = distMatrix.get(fromVertex, toVertex);
                    double possibleLength = distMatrix.get(fromVertex, kVertex) +
                            distMatrix.get(kVertex, toVertex);
                    if (possibleLength < currentLength) {
                        distMatrix.set(fromVertex, toVertex, possibleLength);
                    }
                }
            }
        }

        return distMatrix;
    }

    // Path reconstruction method
    public List<Edge> reconstructPath(Vertex startVertex, Vertex endVertex,
                                      ShortestPathMatrix matrix) {

        ArrayList<Edge> path = new ArrayList<Edge>();

        Vertex currentVertex = endVertex;
        while (currentVertex != startVertex) {
            Collection<Edge> incomingEdges = getEdgesTo(currentVertex);

            boolean foundNext = false;
            for (Edge currentEdge : incomingEdges) {
                double expected = matrix.get(startVertex, currentVertex) - currentEdge.weight;
                double actual = matrix.get(startVertex, currentEdge.fromVertex);
                if (expected == actual) {
                    // Update current vertex
                    currentVertex = currentEdge.fromVertex;

                    // Append currentEdge to path. The normal algorithm prepends, but
                    // appending also works, provided the path is reversed before returning.
                    path.add(currentEdge);

                    // The next vertex in the path was found
                    foundNext = true;

                    // The correct incoming edge was found, so break the inner loop
                    break;
                }
            }

            if (!foundNext) {
                // Return an empty list
                path.clear();
                return path;
            }
        }

        // Edges were append in reverse, so reverse path before returning
        Collections.reverse(path);

        return path;
    }
}