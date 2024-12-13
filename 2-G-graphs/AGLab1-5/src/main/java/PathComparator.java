import java.util.Comparator;
import java.util.Map;

public class PathComparator implements Comparator<Vertex> {
    Map<Vertex, Integer> distCopy;

    public PathComparator(Map<Vertex, Integer> distCopy) {
        this.distCopy = distCopy;
    }

    public int compare(Vertex v1, Vertex v2) {
        if (distCopy.get(v1) > distCopy.get(v2)) {
            return 1;
        } else if (distCopy.get(v1) < distCopy.get(v2)) {
            return -1;
        }
        return 0;
    }
}
