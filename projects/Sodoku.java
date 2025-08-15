/***********************************************
 * Authors: Parker Malmgren and Endi Torqe
 * Course: CSC 301 - Advanced Data Structures
 * Instructor: Dr. Lori Jaques
 *
 * Description:
 * This program is based on the theory outlined in the paper:
 * “Comparison Analysis of Breadth First Search and Depth Limited Search Algorithms in Sudoku Game”
 * Source: https://www.researchgate.net/publication/326749335
 *
 * We implement a BFS (Breadth-First Search) and DLS (Depth-Limited Search) based Sudoku solver.
 * BFS explores the board level-by-level, expanding all valid states simultaneously,
 * whereas DLS performs a depth-first traversal with a maximum allowed depth.
 *
 * - BFS is memory-intensive and can quickly exhaust heap space for harder puzzles
 *   due to the high number of stored states/nodes.
 * - DLS is generally more memory-efficient and faster for puzzles with many empty cells,
 *   as it explores fewer paths and uses recursion with pruning.
 *
 * Limitations:
 * - The current implementation struggles with scalability in BFS and has limited support
 *   for generalizing grid sizes (e.g., 16x16 or 3D Sudoku).
 * - Finding multiple solutions with BFS is possible but impractical due to resource usage.
 *
 * All source code adheres to the principles and ideas presented in the cited paper,
 * and no uncredited code was copied from third-party sources.
 ***********************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sodoku {

    public static void main(String[] args) throws IOException {
        int[][] board = Board("sudoku.txt");  //  Reads board from file into a 2D array

        BFS bfs = new BFS(board);  //  bfs instanec
        DLS dls = new DLS(board);  //  dls instance

        List<int[][]> initial = new ArrayList<>();
        initial.add(board);
        System.out.println("Unsolved board:");
        printBoard(initial);  //  unsolved board printed

        //****************************************************************************

        System.out.println("\nBFS method:");
        long bfsStart = System.nanoTime(); // Timer 1 -- Start
        List<int[][]> resultsBFS = bfs.bfsSolve();  //  calling the solve of the BFS
        if (resultsBFS != null && !resultsBFS.isEmpty()) {
            printBoard(resultsBFS);  //  print the board
        } else {
            System.out.println("BFS found no solution");
        }
        long bfsEnd = System.nanoTime();  //  Timer 2 -- End
        System.out.println("BFS Time: %.2f ms\n" + (bfsEnd-bfsStart)/1e6);  //  Time for BFS


        //****************************************************************************

        System.out.println("\nDLS method:");
        long dlsStart = System.nanoTime();  //  Timer 2 -- Start
        List<int[][]> resultsDLS = dls.solveAll();  //  dls instance solved
        if (resultsDLS != null && !resultsDLS.isEmpty()) {
            printBoard(resultsDLS);
        } else {
            System.out.println("DLS found no solution");
        }
        long dlsEnd = System.nanoTime();  //  Timer 2 -- End
        System.out.println("DLS Time: %.2f ms\n" + (dlsEnd-dlsStart)/1e6);  //  Time for DLS


        //*****************************************************************************
        // Improvement section *HYBRID*

        System.out.println("\nHybrid method:");
        int BFS_DEPTH = 2;  //  Initial depth for the BFS
        // depth-limited BFS
        long hybridStart = System.nanoTime();  //  Timer 3 -- start
        List<int[][]> partials = bfs.bfsSolve(BFS_DEPTH);  //  Partial solutions from BFS
        List<int[][]> hybrids = new ArrayList<>();  //  Result array

        for (int[][] partial : partials) {
            hybrids.addAll(new DLS(partial).solveAll());  //  Partial solutions iterated through DLS
        }

        if (!hybrids.isEmpty()) {
            printBoard(hybrids);
        } else {
            System.out.println("Hybrid found no complete solutions");
        }
        long hybridEnd = System.nanoTime();  //  Timer 3 -- End
        System.out.println("Hybrid Time: %.2f ms\n" + (hybridEnd-hybridStart)/1e6);  //  Time for Hybrid
    }

    //  Establish the board
    private static int[][] Board(String filename) throws IOException {
        Scanner sc = new Scanner(new File(filename));
        int N = sc.nextInt();                   // Size of the board (N×N) to accept different sizes instead of size 9
        int[][] board = new int[N][N];          // allocate N×N
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i][j] = sc.nextInt();
        sc.close();
        return board;
    }

    //  Print the board
    public static void printBoard(List<int[][]> solutions) {
        for (int[][] b : solutions) {
            for (int[] row : b) {
                for (int val : row)
                    System.out.print(val + " ");
                System.out.println();
            }
            System.out.println();
        }
    }
}

