package week14.dfs;

class PrintVertexVisitor implements VertexVisitor {
    public void visit(Vertex vertex) {
        System.out.print(vertex.label + " ");
    }
}