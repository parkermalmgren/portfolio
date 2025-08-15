/* ***************************************************
 * Parker Malmgren
 * 2/21/2025
 *
 * This program executes a readable text file, with a
 * target input value that searches for in the text file
 * it uses 3 difrernet search methods consisting of
 * Knorris-Moore-Pratt and Boyer-Moore and Brute Force
 * it compares the methods and outputs the fastest algo
 * for each one.
 *************************************************** */

import java.io.File;
import java.util.Scanner;

public class KMP_BM {

    public static void main(String[] args) {

        List text1 = loadFromFile("src/prog1input1.txt");
        List text2 = loadFromFile("src/prog1input2.txt");

        Scanner scan = new Scanner(System.in);
        System.out.println("What is your input?");String target = scan.nextLine(); //Search for a string

        searchScenario(text1, target);
        searchScenario(text1, "Ickle");
        searchScenario(text1, "toot");
        searchScenario(text2, "mmmmm");
        searchScenario(text2, "mmmom");
    }

    public static List loadFromFile(String filename) {
        List<Character> list = new List<>(); // List of characters

        try {
            // Create a File object with the filename provided
            File myObj = new File(filename);

            // Create a Scanner object to read from the file
            Scanner myReader = new Scanner(myObj);

            // Loop through the file line by line
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine(); // Get the next line
                for (char c : line.toCharArray()) { // Convert line to char array
                    list.InsertAfter(c); // Add each character to the list

                }
            }

            myReader.close(); // Don't forget to close the reader!
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        return list; // Return the list filled with characters
    }

    public static void searchScenario(List text, String pattern) {
        System.out.println("\nSearching for: " + pattern);

        // Brute Force Search
        long startTime = System.nanoTime();
        List<Integer> bruteResults = bruteForceSearch(text, pattern);
        long bruteTime = System.nanoTime() - startTime;
        System.out.println("Brute Force: Found at " + bruteResults + " | Time: " + bruteTime + " ns");

        // Boyer-Moore Search
        startTime = System.nanoTime();
        List<Integer> bmResults = boyerMooreSearch(text, pattern);
        long bmTime = System.nanoTime() - startTime;
        System.out.println("Boyer-Moore: Found at " + bmResults + " | Time: " + bmTime + " ns");

        // KMP Search
        startTime = System.nanoTime();
        List<Integer> kmpResults = kmpSearch(text, pattern);
        long kmpTime = System.nanoTime() - startTime;
        System.out.println("KMP: Found at " + kmpResults + " | Time: " + kmpTime + " ns");

        // Conclusion
        System.out.println("Fastest Algorithm: " + (bruteTime < bmTime && bruteTime < kmpTime ? "Brute Force" :
                bmTime < kmpTime ? "Boyer-Moore" : "KMP"));
    }

    private static boolean matchSubstring(List data, int startIdx, String pattern) {
        // Check if the pattern fits within the data starting from startIdx
        if (startIdx + pattern.length() > data.GetSize()) {
            return false; // Pattern doesn't fit in the remaining part of the data
        }

        // Compare each character in the pattern with the corresponding character in the data
        for (int i = 0; i < pattern.length(); i++) {
            // Get the character from the list and compare it to the pattern's character
            char dataChar = (char) data.GetValue(startIdx + i);  // Assuming data.GetValue() returns an Object or Integer, cast it to char
            if (dataChar != pattern.charAt(i)) {
                return false; // Pattern doesn't match at this position
            }
        }

        return true; // Pattern matches
    }

    public static List<Integer> bruteForceSearch(List data, String pattern) {
        List<Integer> result = new List<>();
        for (int i = 0; i < data.GetSize(); i++) {
            if (matchSubstring(data, i, pattern)) {
                result.InsertAfter(i); // Found the pattern at index i
            }
        }
        return result;
    }

    // Boyer-Moore Search Algorithm
    public static List<Integer> boyerMooreSearch(List<Character> data, String pattern) {
        List<Integer> result = new List<>();
        int m = pattern.length();
        int n = data.GetSize();

        // Preprocessing for Boyer-Moore (create the bad character shift table)
        int[] badChar = buildBadCharTable(pattern);
        printBadCharTable(badChar); // Print the bad character table to visualize

        // Searching the text with help from ChatGPT
        int s = 0;
        while (s <= (n - m)) {
            int j = m - 1;
            while (j >= 0 && pattern.charAt(j) == data.GetValue(s + j)) {
                j--;
            }
            if (j < 0) {
                result.InsertAfter(s); // Pattern found at index s
                s += (s + m < n) ? m - badChar[data.GetValue(s + m)] : 1;
            } else {
                s += Math.max(1, j - badChar[data.GetValue(s + j)]);
            }
        }
        return result;
    }

    // Helper function to build the Bad Character Table for Boyer-Moore
    private static int[] buildBadCharTable(String pattern) {
        int[] badChar = new int[256]; // ASCII size for char - GeeksForGeeks
        for (int i = 0; i < 256; i++) {
            badChar[i] = -1; // Initialize all values to -1
        }
        // Not quite positive if it works
        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i; // Store the last occurrence of each character - Possibly
        }
        return badChar;
    }

    // Print the Bad Character Table for Boyer-Moore
    private static void printBadCharTable(int[] badChar) {
        System.out.println("Bad Character Table:");
        for (int i = 0; i < 256; i++) {
            if (badChar[i] != -1) {
                System.out.println((char) i + ": " + badChar[i]);
            }
        }
    }

    // Knuth-Morris-Pratt (KMP) Search Algorithm
    public static List<Integer> kmpSearch(List<Character> data, String pattern) {
        List<Integer> result = new List<>();
        int m = pattern.length();
        int n = data.GetSize();

        // Preprocess the pattern to create the LPS (Longest Prefix Suffix) array
        int[] lps = buildLPSArray(pattern);
        printLPSArray(lps); // Print the LPS array

        // HELP FROM TEXTBOOK Data structures for Algo.
        // Searching the text
        int i = 0; // index for text
        int j = 0; // index for pattern
        while (i < n) {
            if (pattern.charAt(j) == data.GetValue(i)) {
                i++;
                j++;
            }
            if (j == m) {
                result.InsertAfter(i - j); // Pattern found at index i-j
                j = lps[j - 1]; // Get the next best position from the LPS array
            } else if (i < n && pattern.charAt(j) != data.GetValue(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // Move the pattern
                } else {
                    i++;
                }
            }
        }
        return result;
    }

    // Helper function to build the LPS (Longest Prefix Suffix) array for KMP
    private static int[] buildLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int length = 0; // length of the previous longest prefix suffix
        int i = 1;

        // Again help from Textbook
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1]; // Get the length from the LPS array
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // Prints the LPS Array for KMP - although not Necessary, good to see
    private static void printLPSArray(int[] lps) {
        System.out.println("LPS Array:");
        for (int i = 0; i < lps.length; i++) {
            System.out.println(i + ": " + lps[i]);
        }
    }
}

/*
When it comes to determining which is faster, it depends on the time (obviously duh)
Multiple factors come into play, with length of text and length of pattern as well
as what the pattern is.

BruteForce is useful in small datasets, checking for every possibiltiy
as well as simplicity

Boyer-Moore is effecient in larger quantity data sets, as well as when the pattern
is very long, skipping unnesscary comparions

KMP is ideal for when multiple pattern seaeches are needed on the same text
it preprocesses the pattern to avoid uncesscary checks.
 */
