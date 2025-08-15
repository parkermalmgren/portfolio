import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class DLS {

    private final int size;         //  Size of the grid
    private final int subBlocks;    //  size of the subblocks
    private final int[][] board;
    private final int depthLimit;   // <— you must set this!
    private final List<int[][]> solutions = new ArrayList<>();

    // full solve constructor: depthLimit = size*size
    public DLS(int[][] initialBoard) {
        this(initialBoard, initialBoard.length * initialBoard.length);
    }

    public DLS(int[][] initialBoard, int depthLimit) {
        this.size = initialBoard.length;
        this.subBlocks = (int) Math.sqrt(size);
        if (subBlocks * subBlocks != size)
            throw new IllegalArgumentException("Board size must be a perfect square");
        this.board = deepCopy(initialBoard);
        this.depthLimit = depthLimit;        // <— now set
    }

    public List<int[][]> solveAll() {
        dfs(0);
        return solutions;
    }

    private void dfs(int depth) {
        if (depth == depthLimit) {
            solutions.add(deepCopy(board));
            return;
        }

        int row = depth / size;
        int col = depth % size;

        if (board[row][col] != 0) {
            dfs(depth + 1);
            return;
        }

        for (int val = 1; val <= size; val++) {
            if (isSafe(row, col, val)) {
                board[row][col] = val;
                dfs(depth + 1);
                board[row][col] = 0; // Backtrack
            }
        }
    }

    private boolean isSafe(int r, int c, int val) {
        for (int i = 0; i < size; i++) {
            if (board[r][i] == val || board[i][c] == val) return false;
        }

        int boxRow = (r / subBlocks) * subBlocks;
        int boxCol = (c / subBlocks) * subBlocks;
        for (int i = 0; i < subBlocks; i++) {
            for (int j = 0; j < subBlocks; j++) {
                if (board[boxRow + i][boxCol + j] == val) return false;
            }
        }
        return true;
    }

    private int[][] deepCopy(int[][] original) {
        int[][] copy = new int[size][size];
        for (int i = 0; i < size; i++)
            copy[i] = Arrays.copyOf(original[i], size);
        return copy;
    }
}
