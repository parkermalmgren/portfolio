import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class main_tree {
    public static void main(String[] args) {
        // Load books (both unsorted and sorted)
        List<String> booksUnsorted = loadBooks("SciFiLiBooks.txt");
        List<String> booksSorted = loadBooks("SciFiLiSorted.txt");

        // Process both unsorted and sorted books
        List<List<String>> bookLists = new ArrayList<>();
        bookLists.add(booksUnsorted);
        bookLists.add(booksSorted);

        // Loop through both book lists
        for (List<String> books : bookLists) {
            // Print which list we're processing
            System.out.println(books == booksUnsorted ? "Processing Unsorted Books:" : "Processing Sorted Books:");

            // Time analysis for BinaryTree
            System.out.println("Time Analysis for BinaryTree:");

            long startTime = System.nanoTime();
            BinaryTree binaryTree = new BinaryTree();
            for (String book : books) {
                binaryTree.insert(book);
            }
            long endTime = System.nanoTime();
            System.out.println("BinaryTree insert time: " + (endTime - startTime) + " ns");

            // search for the boook that is not found in the tree
            startTime = System.nanoTime();
            boolean found = binaryTree.search("NonExistingBook");
            endTime = System.nanoTime();
            System.out.println("BinaryTree search time (for non-existing book): " + (endTime - startTime) + " ns");
            System.out.println("Found 'NonExistingBook': " + found);

            // removal of a book from the tree
            startTime = System.nanoTime();
            binaryTree.setRoot(binaryTree.remove(binaryTree.getRoot(), "Xanth Series"));
            endTime = System.nanoTime();
            System.out.println("BinaryTree remove time: " + (endTime - startTime) + " ns");

            // Timing for AVLTree
            System.out.println("\nTime Analysis for AVLTree:");


            startTime = System.nanoTime();
            AVLTree avlTree = new AVLTree();
            for (String book : books) {
                avlTree.insert(book);
            }


            endTime = System.nanoTime();
            System.out.println("AVLTree insert time: " + (endTime - startTime) + " ns");

            // search for a book that is not in the tree
            startTime = System.nanoTime();
            found = avlTree.search("NonExistingBook");
            endTime = System.nanoTime();
            System.out.println("AVLTree search time (for non-existing book): " + (endTime - startTime) + " ns");
            System.out.println("Found 'NonExistingBook': " + found);



            // removal a book from the tree
            startTime = System.nanoTime();
            avlTree.setRoot(avlTree.remove(avlTree.getRoot(), "Xanth Series"));
            endTime = System.nanoTime();
            System.out.println("AVLTree remove time: " + (endTime - startTime) + " ns");

            // Time analysis for RedBlackTree
            System.out.println("\nTime Analysis for RedBlackTree:");

            startTime = System.nanoTime();
            RedBlackTree rbTree = new RedBlackTree();
            for (String book : books) {
                rbTree.insert(book);
            }
            endTime = System.nanoTime();
            System.out.println("RedBlackTree insert time: " + (endTime - startTime) + " ns");

            // search for a book that is not in the tree
            startTime = System.nanoTime();
            found = rbTree.search("NonExistingBook");
            endTime = System.nanoTime();
            System.out.println("RedBlackTree search time (for non-existing book): " + (endTime - startTime) + " ns");
            System.out.println("Found 'NonExistingBook': " + found);


            // removal of a book from the tree
            startTime = System.nanoTime();
            rbTree.setRoot(rbTree.remove(rbTree.getRoot(), "Xanth Series"));
            endTime = System.nanoTime();
            System.out.println("RedBlackTree remove time: " + (endTime - startTime) + " ns");

            // conclusion based on sorted/unsorted for different trees
            System.out.println("\nConclusion: Based on the time analysis, observe the differences between each tree's performance for insert, search, and remove operations.\n "
                    + "\nThe AVL and RB tree have slower insertion times, especially for unsorted data can become unbalanced. It could become linked list on a Binary Tree " +
                    "" +
                    "\nInsertion into an AVL requires more logic and rules to follow due to the balancing logic, but when sorted, it becomes insanely" +
                    "\nquicker to search due to the heavy logic that goes into balancing. removal time is relatively slower again due to the balancing logic" +
                    "\nin case the tree becomes unbalanced in the height. THe RB tree performs well for for searches and removals, but is very slow when it comes" +
                    "\nto insertion but has a simpler balancing algo compared to the AVL, but still slower for search and remove than AVL due to moderate" +
                    "\nbalancing/faster maintenence");
        }
    }

    // Placeholder method for loading book data - Chatgpt guidance for using lists
    private static List<String> loadBooks(String fileName) {
        List<String> books = new ArrayList<>();
        // Simulate loading books from file (this could be done with file I/O)
        for (int i = 0; i < 1000; i++) {
            books.add("Book" + i);
        }
        return books;
    }
}
