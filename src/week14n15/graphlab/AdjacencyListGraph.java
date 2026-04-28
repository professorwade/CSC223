package week14n15.graphlab;
import java.util.*;

public class AdjacencyListGraph extends DirectedGraph {
   protected ArrayList<AdjacencyListVertex> vertices = new ArrayList<>();

   // Creates and adds a new vertex to the graph, provided a vertex with the
   // same label doesn't already exist in the graph. Returns the new vertex on
   // success, null on failure.
   @Override
   public Vertex addVertex(String newVertexLabel) {
      // TODO: Type your code here (remove placeholder line below)
      //return null;
      // Return null if a vertex with the label already exists
      if (getVertex(newVertexLabel) != null) {
         return null;
      }
      // Create, add, and return the vertex
      AdjacencyListVertex vertex = new AdjacencyListVertex(newVertexLabel);
      vertices.add(vertex);
      return vertex;
   }

   // Adds a directed edge from the first to the second vertex. If the edge
   // already exists in the graph, no change is made and false is returned.
   // Otherwise the new edge is added and true is returned.
   @Override
   public boolean addDirectedEdge(Vertex fromVertex, Vertex toVertex) {
      // TODO: Type your code here (remove placeholder line below)
      // return false;
      if (hasEdge(fromVertex, toVertex)) {
         return false;
      }
      ((AdjacencyListVertex) fromVertex).adjacent.add(toVertex);
      return true;
   }

   // Returns an ArrayList of edges with the specified fromVertex.
   @Override
   public ArrayList<Edge> getEdgesFrom(Vertex fromVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return new ArrayList<Edge>();
      ArrayList<Edge> result = new ArrayList<Edge>();
      for (Vertex toVertex : ((AdjacencyListVertex) fromVertex).adjacent) {
         result.add(new Edge(fromVertex, toVertex));
      }

      return result;
   }

   // Returns an ArrayList of edges with the specified toVertex.
   @Override
   public ArrayList<Edge> getEdgesTo(Vertex toVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return new ArrayList<Edge>();
      ArrayList<Edge> result = new ArrayList<Edge>();

      // Iterate through all edges in the graph, adding each that has toVertex
      // as the to-vertex
      for (AdjacencyListVertex fromVertex : vertices) {
         for (Vertex candidate : fromVertex.adjacent) {
            if (candidate == toVertex) {
               result.add(new Edge(fromVertex, toVertex));
            }
         }
      }
      return result;
   }

   // Returns a vertex with a matching label, or null if no such vertex
   // exists
   @Override
   public Vertex getVertex(String vertexLabel) {
      // TODO: Type your code here (remove placeholder line below)
      // return null;
      for (AdjacencyListVertex vertex : vertices) {
         if (vertexLabel.equals(vertex.getLabel())) {
            return vertex;
         }
      }
      return null; // return null if no vertex with label exists
   }

   // Returns true if this graph has an edge from fromVertex to toVertex
   @Override
   public boolean hasEdge(Vertex fromVertex, Vertex toVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return false;
      // Iterate through fromVertex's adjacent vertices
      for (Vertex candidate : ((AdjacencyListVertex) fromVertex).adjacent) {
         if (candidate == toVertex) {
            return true;
         }
      }
      return false;
   }
}
