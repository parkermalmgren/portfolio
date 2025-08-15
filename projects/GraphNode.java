import java.util.ArrayList;
import java.util.List;

class GraphNode {
    int row, col;
    int value;
    List<GraphNode> neighbors;

    GraphNode(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.neighbors = new ArrayList<>();
    }

    void addNeighbor(GraphNode node) {
        this.neighbors.add(node);
    }

    boolean isSafe(int val) {
        for (GraphNode neighbor : neighbors) {
            if (neighbor.value == val) return false;
        }
        return true;
    }
}