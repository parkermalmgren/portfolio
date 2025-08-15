import java.util.*;
import java.util.List;

public class BFS {

    private final int size;      //  Size of the grid
    private final int subBlocks; //  size of the subblocks
    private final int[][] initialBoard; // store the board passed in

    public BFS(int[][] initialBoard) {
        this.initialBoard = initialBoard;
        this.size = initialBoard.length;
        this.subBlocks = (int) Math.sqrt(size);
        if (subBlocks * subBlocks != size)
            throw new IllegalArgumentException("Board size must be a perfect square");
    }

    public List<int[][]> bfsSolve() {
        return bfsSolve(size * size);  //  Calls overload constructor with pre-set size if no size
    }

    // OVERLOAD METHOD for HYBRID MODEL
    public List<int[][]> bfsSolve(int maxDepth) {
        List<int[][]> solutions = new ArrayList<>(); // Creates a list of possible solutions
        Queue<State> queue = new LinkedList<>();     //  Queue for BFS on value-boards
        Set<String> seen = new HashSet<>();          //  Avoid revisiting same layouts
        queue.add(new State(clone(initialBoard), 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            String key = Arrays.deepToString(cur.board);
            if (!seen.add(key)) continue;

            if (cur.depth == maxDepth) {
                solutions.add(cur.board);
                continue;
            }
            int[] empty = findEmpty(cur.board);
            if (empty == null) {
                solutions.add(cur.board);
                continue;
            }
            int r = empty[0], c = empty[1];
            for (int val = 1; val <= size; val++) {
                if (isSafe(cur.board, r, c, val)) {
                    int[][] nb = clone(cur.board);
                    nb[r][c] = val;
                    queue.add(new State(nb, cur.depth + 1));
                }
            }
        }
        return solutions;
    }

    // STATE CLASS — captures board values and depth
    static class State {
        int[][] board;
        int depth;
        State(int[][] b, int d) { board = b; depth = d; }
    }

    // Find next empty cell or null if none
    private int[] findEmpty(int[][] b) {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (b[i][j] == 0) return new int[]{i, j};
        return null;
    }

    // Check row, col, and sub-block
    private boolean isSafe(int[][] b, int r, int c, int v) {
        for (int i = 0; i < size; i++) {
            if (b[r][i] == v || b[i][c] == v) return false;
        }
        int sr = (r/subBlocks)*subBlocks, sc = (c/subBlocks)*subBlocks;
        for (int i = sr; i < sr+subBlocks; i++)
            for (int j = sc; j < sc+subBlocks; j++)
                if (b[i][j] == v) return false;
        return true;
    }

    // Deep copy a value board
    private int[][] clone(int[][] b) {
        int[][] c = new int[size][size];
        for (int i = 0; i < size; i++)
            c[i] = Arrays.copyOf(b[i], size);
        return c;
    }
}
