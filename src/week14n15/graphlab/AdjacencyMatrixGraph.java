package week14n15.graphlab;
import java.util.*;

public class AdjacencyMatrixGraph extends DirectedGraph {
   protected ArrayList<Vertex> vertices = new ArrayList<>();

   // If matrixRows[X][Y] is true, then an edge exists from vertices[X] to
   // vertices[Y]
   protected ArrayList<ArrayList<Boolean>> matrixRows = new ArrayList<>();

   // TODO: Type your additional code here, if desired
   protected void addMatrixRowColumn() {
      if (matrixRows.size() > 0) {
         // First add a column by pushing false to the back of each existing row
         for (var row : matrixRows) {
            row.add(false);
         }

         // Make a new row with the same size as other rows and all entries
         // assigned with false
         ArrayList<Boolean> newRow = new ArrayList<Boolean>();
         for (int i = 0; i < matrixRows.get(0).size(); i++) {
            newRow.add(false);
         }

         // Add the new row
         matrixRows.add(newRow);
      }
      else {
         // Special case if matrixRows is empty
         ArrayList<Boolean> row = new ArrayList<Boolean>(Arrays.asList(false));
         matrixRows.add(row);
      }
   }

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

      // Create and add the vertex
      Vertex vertex = new Vertex(newVertexLabel);
      vertices.add(vertex);

      // Adding a vertex adds a row and column to the matrix
      addMatrixRowColumn();

      return vertex;
   }

   // Adds a directed edge from the first to the second vertex. If the edge
   // already exists in the graph, no change is made and false is returned.
   // Otherwise the new edge is added and true is returned.
   @Override
   public boolean addDirectedEdge(Vertex fromVertex, Vertex toVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return false;
      if (hasEdge(fromVertex, toVertex)) {
         return false;
      }

      // Assign the matrix entry
      int fromVertexIndex = vertices.indexOf(fromVertex);
      int toVertexIndex = vertices.indexOf(toVertex);
      matrixRows.get(fromVertexIndex).set(toVertexIndex, true);

      return true;
   }

   // Returns an ArrayList of edges with the specified fromVertex.
   @Override
   public ArrayList<Edge> getEdgesFrom(Vertex fromVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return new ArrayList<Edge>();
      ArrayList<Edge> result = new ArrayList<Edge>();
      int fromVertexIndex = vertices.indexOf(fromVertex);
      if (fromVertexIndex >= 0) {
         var row = matrixRows.get(fromVertexIndex);
         for (int j = 0; j < (int) row.size(); j++) {
            Vertex toVertex = vertices.get(j);
            if (row.get(j)) {
               result.add(new Edge(fromVertex, toVertex));
            }
         }
      }

      return result;
   }

   // Returns an ArrayList of edges with the specified toVertex.
   @Override
   public ArrayList<Edge> getEdgesTo(Vertex toVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return new ArrayList<Edge>();
      ArrayList<Edge> result = new ArrayList<Edge>();
      int toVertexIndex = vertices.indexOf(toVertex);
      if (toVertexIndex >= 0) {
         // toVertexIndex is the column index. Iterate through rows to find
         // column entries assigned with true
         for (int rowIndex = 0; rowIndex < (int) matrixRows.size(); rowIndex++) {
            if (matrixRows.get(rowIndex).get(toVertexIndex)) {
               Vertex fromVertex = vertices.get(rowIndex);
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
      //return null;
      for (Vertex vertex : vertices) {
         if (vertexLabel.equals(vertex.getLabel())) {
            return vertex;
         }
      }

      return null;
   }

   // Returns true if this graph has an edge from fromVertex to toVertex
   @Override
   public boolean hasEdge(Vertex fromVertex, Vertex toVertex) {
      // TODO: Type your code here (remove placeholder line below)
      //return false;
      int fromVertexIndex = vertices.indexOf(fromVertex);
      int toVertexIndex = vertices.indexOf(toVertex);

      if (fromVertexIndex >= 0 && toVertexIndex >= 0) {
         return matrixRows.get(fromVertexIndex).get(toVertexIndex);
      }

      return false;
   }
}
