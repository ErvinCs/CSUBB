import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Provides a layer over the operations on a Graph.
 */
public class Controller {
    private Graph graph;

    /**
     * Creates a controller over a Graph
     * @param graph - Graph
     */
    public Controller(Graph graph) {
        this.graph = graph;
    }

    /**
     * Creates a controller over a Graph read from the given filename
     * @param filename - String - path to the file with the Graph data
     * @throws FileNotFoundException
     *      Thrown if the file at the specified path could not be found
     */
    public Controller(String filename) throws FileNotFoundException {
        this.graph = new Graph(filename);
    }

    /**
     * Generates a random graph with the given number of vertices and edges
     * @param noVertices - number of vertices
     * @param noEdges - number of edges
     */
    public Controller(int noVertices, int noEdges) {
        this.graph = Graph.generateGraph(noVertices, noEdges);
    }

    /**
     * @return Graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Set the graph to be controlled
     * @param graph - Graph
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * @return Set of vertices of the graph
     */
    public Set<Vertex> getVertices() {
        return graph.getVertices();
    }

    /**
     * @return Set of edges of the graph
     */
    public Set<Edge> getEdges() {
        return graph.getEdges();
    }

    /**
     * @return Number of vertices of the graph
     */
    public int getNoVertices() {
        return graph.getNoVertices();
    }

    /**
     * @return Number of edges of the graph
     */
    public int getNoEdges() {
        return graph.getNoEdges();
    }

    /**
     * Returns the Vertex with the given ID
     * @param id - int - ID of the Vertex
     * @return Vertex
     * @throws IllegalStateException
     *      If no Vertex with the given ID exists
     */
    public Vertex getVertexById(int id) throws IllegalStateException {
        Optional<Vertex> vertex = graph.getVertexById(id);
        if (vertex.isEmpty()) {
            throw new IllegalStateException();
        }
        return vertex.get();
    }

    /**
     * Returns an Edge between 2 Vertices identified by their ids
     * @param v1id - Vertex 1 ID
     * @param v2id - Vertex 2 ID
     * @return Edge - the Edge identified by the 2 vertices
     * @throws IllegalStateException
     *      If no such Edge exists
     */
    public Edge getEdge(int v1id, int v2id) throws IllegalStateException {
        Optional<Edge> edge = graph.getEdge(v1id, v2id);
        if (edge.isEmpty()) {
            throw new IllegalStateException();
        }
        return edge.get();
    }

    /**
     * @return Stream of vertices
     */
    public Stream<Vertex> vertices(){
        return graph.vertices();
    }

    /**
     * @return Stream of edges
     */
    public Stream<Edge> edges(){
        return graph.edges();
    }

    /**
     * Checks the existence of an Edge between 2 Vertices identified by their ids
     * @param v1id - Vertex 1 ID
     * @param v2id - Vertex 2 ID
     * @return Edge - the Edge between the 2 vertices
     * @throws NullPointerException
     *      If the either given Vertex is invalid
     * @throws IllegalStateException
     *      If an Edge does not exist between the 2 vertices
     */
    public Edge existsEdge(int v1id, int v2id) throws NullPointerException, IllegalStateException {
        Optional<Vertex> v1optional = graph.vertices()
                .filter(vertex -> vertex.getvID() == v1id)
                .findFirst();
        Optional<Vertex> v2optional = graph.vertices()
                .filter(vertex -> vertex.getvID() == v2id)
                .findFirst();
        if(v1optional.isEmpty() || v2optional.isEmpty())
            throw new NullPointerException("Invalid Vertex!");

        Vertex v1 = v1optional.get();
        Vertex v2 = v2optional.get();
        Optional<Edge> edge = graph.existsEdge(v1, v2);

        if (edge.isEmpty()) {
            throw new IllegalStateException("No such edge exists!");
        }
        return edge.get();
    }

    /**
     * Adds an edge to the graph
     * @param edge - Edge to be added
     */
    public void addEdge(Edge edge) throws IllegalStateException {
        Optional<Edge> edgeOptional = graph.edges().filter(e -> e.equals(edge)).findAny();
        if (edgeOptional.isEmpty()) {
            graph.addEdge(edge);
        } else {
            throw new IllegalStateException("Edge already exists!");
        }
    }

    /**
     * Removes an edge from the graph
     * @param edge - Edge to be removed
     */
    public void removeEdge(Edge edge) {
        graph.removeEdge(edge);
    }

    /**
     * Adds a vertex to the graph
     * @param vertex - Vertex to be added
     */
    public void addVertex(Vertex vertex) {
        graph.addVertex(vertex);
    }

    /**
     * Removes a vertex from the graph and all its connected edges
     * @param vertex - Vertex to be removed
     */
    public void removeVertex(Vertex vertex) throws IllegalStateException {
        graph.removeVertex(vertex);
    }
}
