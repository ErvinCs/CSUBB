import java.util.*;

public class GraphMatrix implements IGraph {
    private Integer[][] matrix;
    private Integer noVertices;
    private Integer noEdges;

    // -------------------- Empty --------------------
    /**
     * Empty Stub
     */
    public Integer addVertex() {
        return 0;
    }
    /**
     * Empty Stub
     */
    public void addEdge(Integer from, Integer to) {

    }
    // -----------------------------------------------

    public GraphMatrix(Integer noVertices, Integer noEdges) {
        matrix = new Integer[noVertices][noVertices];
        this.noVertices = noVertices;
        this.noEdges = noEdges;
        generateGraph(noVertices, noEdges);
    }

    public Integer getNoEdges() {
        return noEdges;
    }

    public Integer getNoVertices() {
        return noVertices;
    }

    public boolean existsEdge(Integer v1, Integer v2)  {
        return matrix[v1][v2] != 0;
    }

    public Set<Integer> parseNOut(Integer v) {
        Set<Integer> outbound = new HashSet<>();
        for(int i = 0; i < noVertices; i++) {
            if(matrix[v][i] != 0) {
                outbound.add(i);
            }
        }
        return outbound;
    }

    public Set<Integer> parseNIn(Integer v) {
        Set<Integer> inbound = new HashSet<>();
        for(int i = 0; i < noVertices; i++) {
            if(matrix[i][v] != 0) {
                inbound.add(i);
            }
        }
        return inbound;
    }

    private void generateGraph(Integer noVertices, Integer noEdges) {
        Random rand = new Random();

        for(int i = 0; i < noVertices; i++) {
            for(int j = 0; j < noVertices; j++) {
                this.matrix[i][j] = 0;
            }
        }

        int i = 0;
        while (i < noEdges) {
            int v1 = rand.nextInt(noVertices);
            int v2 = rand.nextInt(noVertices);

            if (this.existsEdge(v1, v2)) {
                continue;
            }
            matrix[v1][v2] = 1;
            i++;
        }
    }

    @Override
    public String toString() {
        String str = "Graph{\n";
        for(int i = 0; i < noVertices; i++) {
            for(int j = 0; j < noVertices; j++) {
                str += this.matrix[i][j] + " ";
            }
            str += "\n";
        }
        str += "}";
        return str;
    }
}
