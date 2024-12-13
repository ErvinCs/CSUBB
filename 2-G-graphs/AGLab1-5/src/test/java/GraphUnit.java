import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class GraphUnit {

    @Test
    public void testGetVertexById()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        graph.addVertex(v1);
        graph.addVertex(v2);

        assertEquals(graph.getVertexById(1).get(), v1);
        assertEquals(graph.getVertexById(2), Optional.empty());
        assertEquals(graph.getVertexById(3).get(), v2);

    }

    @Test
    public void testGetEdge()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        Vertex v3 = new Vertex(5);
        Edge e1 = new Edge(v1, v2, 10);
        Edge e2 = new Edge(v2, v1, 20);
        Edge e3 = new Edge(v1, v3, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);

        assertEquals(graph.getEdge(1, 3).get(), e1);
        assertEquals(graph.getEdge(3, 1).get(), e2);
        assertEquals(graph.getEdge(5, 3), Optional.empty());
    }

    @Test
    public void testExistsVertex()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        Vertex v3 = new Vertex(5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        assertTrue(graph.existsVertex(1));
        assertFalse(graph.existsVertex(2));

    }

    @Test
    public void testExistsEdge()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        Vertex v3 = new Vertex(5);
        Edge e1 = new Edge(v1, v2, 10);
        Edge e2 = new Edge(v2, v1, 20);
        Edge e3 = new Edge(v1, v3, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);

        assertEquals(graph.existsEdge(v1, v2).get(), e1);
        assertEquals(graph.existsEdge(v2, v1).get(), e2);
        assertEquals(graph.existsEdge(v3, v2), Optional.empty());
    }

    @Test
    public void testAddEdge()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        Vertex v3 = new Vertex(5);
        Edge e1 = new Edge(v1, v2, 10);
        Edge e2 = new Edge(v2, v1, 20);
        Edge e3 = new Edge(v1, v3, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        assertEquals(graph.getNoEdges(), 0);
        graph.addEdge(e1);
        assertEquals(graph.getNoEdges(), 1);
        graph.addEdge(e2);
        assertEquals(graph.getNoEdges(), 2);
        graph.addEdge(e3);
        assertEquals(graph.getNoEdges(), 3);
    }

    @Test
    public void testRemovEdge()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        Vertex v3 = new Vertex(5);
        Edge e1 = new Edge(v1, v2, 10);
        Edge e2 = new Edge(v2, v1, 20);
        Edge e3 = new Edge(v1, v3, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);

        assertEquals(graph.getNoEdges(), 3);
        graph.removeEdge(e1);
        assertEquals(graph.getNoEdges(), 2);
        graph.removeEdge(e2);
        assertEquals(graph.getNoEdges(), 1);
        graph.removeEdge(e3);
        assertEquals(graph.getNoEdges(), 0);
    }

    @Test
    public void testAddVertex()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);

        graph.addVertex(v1);
        assertTrue(graph.existsVertex(1));
        assertEquals(graph.getNoVertices(), 1);

        graph.addVertex(v2);
        assertTrue(graph.existsVertex(3));
        assertEquals(graph.getNoVertices(), 2);
    }

    @Test
    public void testRemoveVertex()
    {
        Graph graph = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(3);
        Vertex v3 = new Vertex(5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        assertTrue(graph.existsVertex(1));
        assertTrue(graph.existsVertex(3));
        assertTrue(graph.existsVertex(5));

        assertEquals(graph.getNoVertices(), 3);
        graph.removeVertex(v1);
        graph.removeVertex(v2);
        assertEquals(graph.getNoVertices(), 1);

        assertFalse(graph.existsVertex(1));
        assertFalse(graph.existsVertex(3));
        assertTrue(graph.existsVertex(5));
    }
}
