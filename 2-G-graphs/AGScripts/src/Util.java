import java.util.*;

public class Util {
    /**
     * Performs a BFS traversal on a graph starting with a given vertex.
     * Prints in the console the vertices for each level of traversal
     * @param graph - Graph to be traversed
     * @param startVertex - The vertex from which the traversal begins
     */
    public static void PrintTree(IGraph graph, Integer startVertex) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<>();
        queue.add(startVertex);
        visited.add(startVertex);

        System.out.println(startVertex);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for(Integer vertex : graph.parseNOut(current)) {
                if (!visited.contains(vertex)) {
                    queue.add(vertex);
                    visited.add(vertex);
                    System.out.print(vertex + "  ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the time taken to parse all the vertices, their inbound neighbours and outbound neighbours
     * @param graph - graph to be parsed
     * @param measureTarget - string that specifies the type of the graph
     */
    public static void EvalGraph(IGraph graph, String measureTarget) {
        Iterator<Integer> iterator;
        double start = System.currentTimeMillis();

        for(int i = 0; i < graph.getNoVertices(); i++) {
            iterator = graph.parseNOut(i).iterator();
            while(iterator.hasNext()) {
                iterator.next();
            }
            iterator = graph.parseNIn(i).iterator();
            while(iterator.hasNext()) {
                iterator.next();
            }
        }

        double finish = System.currentTimeMillis();
        double parseTime = finish - start;
        System.out.println(measureTarget + " Time: " + parseTime + "ms");
    }

    /**
     * Computes the set of vertices accessible from a given vertex
     * @param graph - Oriented Graph
     * @param vertex - Integer - represents a vertex
     * @return Set<Integer> - the set of vertices accessible from vertex
     */
    public static Set<Integer> Accessible(IGraph graph, Integer vertex) {
        Set<Integer> accessible = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        accessible.add(vertex);
        list.add(vertex);

        while(list.size() > 0) {
            Integer current = list.get(0);
            list.remove(0);
            for(Integer v : graph.parseNOut(current)) {
                if (!accessible.contains(v)) {
                    accessible.add(v);
                    list.add(v);
                }
            }
        }

        return accessible;
    }
}
