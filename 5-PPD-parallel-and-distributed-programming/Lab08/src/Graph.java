import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int size;
    private int verts[][];


    public Graph(int size, int verts[][]) {
        this.size = size;
        this.verts = verts;
    }

    public Graph(int size) {
        this.size = size;
        this.generateVerts();
    }

    public int getSize() {
        return size;
    }

    public int[][] getVertsMatrix() {
        return verts;
    }

    public int getValueAt(int i, int j) {
        return verts[i][j];
    }

    public List<Tuple<Integer, Integer>> getPaths() {
        List<Tuple<Integer, Integer>> tuples = new ArrayList<>();
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                if (verts[i][j] != 0)
                    tuples.add(new Tuple<>(i, j));
            }
        }
        return tuples;
    }

    private void generateVerts() {
        this.verts = Main.initMatrix(this.size);
        int fill = 0;
        for(int i = 0; i < size - 1 || fill < size/2; i++) {
            for(int j = 0; j < size - 1 || fill < size/2; j ++) {
                if (i == j)
                    verts[i][j] = 0;
                else if(Math.random() > 0.5) {
                    verts[i][j] = 1;
                    fill++;
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j ++) {
                output += verts[i][j] + " ";
            }
            output += '\n';
        }
        return output;
    }
}
