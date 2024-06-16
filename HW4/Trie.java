package hw4;

//-----------------------------------------------------
// Title: Trie class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 4
// Description: This class includes Tries data structure and TrineNode data structure. Also includes needed methods for solving the homework,
// which are insert, search , fullcomplete, autocomplete, reversefind and findtopk methods.
//-----------------------------------------------------

import java.util.*;


class TrieNode {
    

    Map<Character, TrieNode> children; // Map to store children nodes
    boolean isEndOfWord; // Boolean to mark the end of a word
    int count; // Counter to store the frequency of words

    
    public TrieNode() {
        //--------------------------------------------------------
        // Summary: Constructor of the TrieNode.
        // Precondition: No precondition.
        // Postcondition: Creates Trie Node.
        //--------------------------------------------------------
        children = new HashMap<>(); // Initialize children as an empty HashMap
        isEndOfWord = false; // Initially, it's not the end of a word
        count = 0; // Initialize count to 0
    }
}


class Trie {
    private TrieNode root; // Root of the trie
    private List<String> allWords; // List to store all inserted words

    // Constructor to initialize a Trie
    public Trie() {
        //--------------------------------------------------------
        // Summary: Constructor of the Trie.
        // Precondition: No precondition.
        // Postcondition: Creates Trie object.
        //--------------------------------------------------------

        root = new TrieNode(); // Initialize root with a new TrieNode
        allWords = new ArrayList<>(); // Initialize allWords as an empty ArrayList
    }

  
    public void insert(String word) {
        //--------------------------------------------------------
        // Summary: Adds a word into the Trie.
        // Precondition: word is a non-null string.
        // Postcondition: The word is added to the Trie.
        //--------------------------------------------------------

        TrieNode current = root; // Start from the root

        // Traverse through each character of the word
        for (char l : word.toCharArray()) {
            // If the character is not present in the children map, create a new TrieNode
            current.children.putIfAbsent(l, new TrieNode());
            // Move to the next TrieNode
            current = current.children.get(l);
        }

        current.isEndOfWord = true; // Mark the current TrieNode as the end of the word
        allWords.add(word); // Add the word to allWords list
        current.count++; // Increment the count for the word
    }

   
    public int getCount(String word) {
        //--------------------------------------------------------
        // Summary: Gets the count of a word in the Trie.
        // Precondition: word is a non-null string.
        // Postcondition: Returns the count of the word.
        //--------------------------------------------------------

        TrieNode current = root; // Start from the root

        // Traverse through each character of the word
        for (char l : word.toCharArray()) {
            TrieNode node = current.children.get(l);

            // If the character node is null, the word doesn't exist in the Trie
            if (node == null) {
                return 0; // Return count as 0
            }

            current = node;
        }

        // Check if the word is actually present in the trie
        if (current.isEndOfWord) {
            return current.count; // Return the count
        } else {
            return 0; // Word not found, return count as 0
        }
    }

   
    public boolean search(String word) {
        //--------------------------------------------------------
        // Summary: Searches for a word in the Trie.
        // Precondition: word is a non-null string.
        // Postcondition: Returns true if the word is found, false otherwise.
        //--------------------------------------------------------


        // Use getNode method to get the TrieNode if the word is in the Trie
        TrieNode current = getNode(word);
        // Check if the TrieNode for the word exists and marks the end of a word
        boolean result = current != null && current.isEndOfWord;
        System.out.println(result ? "True" : "False"); // Print whether the word is found or not
        return result;
    }

    
    private TrieNode getNode(String word) {
        //--------------------------------------------------------
        // Summary: Helper method to get the node for the last character of a word.
        // Precondition: word is a non-null string.
        // Postcondition: Returns the TrieNode representing the last character of the word, or null if not found.
        //--------------------------------------------------------

        TrieNode current = root; // Start from the root

        // Traverse through each character of the word
        for (char l : word.toCharArray()) {
            // Check if the character is present in lowercase or uppercase
            TrieNode node = current.children.get(Character.toLowerCase(l));

            // If the character node is still null, check for uppercase
            if (node == null) {
                node = current.children.get(Character.toUpperCase(l));

                // If both lowercase and uppercase nodes are null, the word doesn't exist
                if (node == null) {
                    return null;
                }
            }
            current = node;
        }
        return current; // Return the TrieNode for the last character of the word
    }

    
    public void reverseFind(String suffix) {
        //--------------------------------------------------------
        // Summary: Finds and prints all words ending with the given suffix.
        // Precondition: suffix is a non-null string.
        // Postcondition: Prints words ending with the suffix.
        //--------------------------------------------------------

        // Create a new list to store all words from 'allWords' in lowercase for case-insensitive comparison
        List<String> sortedWords = new ArrayList<>();
        for (String word : allWords) {
            sortedWords.add(word.toLowerCase());
        }

        
        // Sort the list of words alphabetically
        Collections.sort(sortedWords);
        boolean found = false;
        LinkedList<String> list = new LinkedList<>();

        // Iterate through sorted words and check for the suffix
        for (String word : sortedWords) {
             // Check if the current word ends with the suffix in lowercase and if it's not already in the list
            if (word.endsWith(suffix.toLowerCase()) && !list.contains(word)) {
                found = true;       // Set found to true as at least one word with the suffix is found
                list.add(word);     // Add the word to the list
            }
        }

        // Print the results
        if (!found) {
            System.out.print("No words");   // If no words with the suffix are found, print a message
        } else {
            for (String word : list) {
                System.out.print(word + ", ");      // Print each word found with the suffix    
            }
        }
        System.out.println();
    }

   
    public void fullComplete(String prefix, String suffix) {
        //--------------------------------------------------------
        // Summary: Finds and prints all words starting with the given prefix and ending with the given suffix.
        // Precondition: prefix and suffix are non-null strings.
        // Postcondition: Prints words matching the prefix and suffix.
        //--------------------------------------------------------

        List<String> sortedWords = new ArrayList<>();
        for (String word : allWords) {
            sortedWords.add(word.toLowerCase());
        }

        Collections.sort(sortedWords);
        boolean found = false;
        LinkedList<String> list = new LinkedList<>();

        // Iterate through sorted words and check for the prefix and suffix
        for (String word : sortedWords) {
            if (word.startsWith(prefix.toLowerCase()) && word.endsWith(suffix.toLowerCase()) && !list.contains(word)) {
                found = true;
                list.add(word);
            }
        }

        // Print the results
        if (!found) {
            System.out.print("No words");
        } else {
            for (String word : list) {
                System.out.print(word + ", ");
            }
        }
        System.out.println();
    }

    
    public void autoComplete(String prefix) {
        //--------------------------------------------------------
        // Summary: Finds and prints all words starting with the given prefix.
        // Precondition: prefix is a non-null string.
        // Postcondition: Prints words matching the prefix.
        //--------------------------------------------------------

        List<String> sortedWords = new ArrayList<>();
        for (String word : allWords) {
            sortedWords.add(word.toLowerCase());
        }

        Collections.sort(sortedWords);
        boolean found = false;
        LinkedList<String> list = new LinkedList<>();

        // Iterate through sorted words and check for the prefix
        for (String word : sortedWords) {
            if (word.startsWith(prefix.toLowerCase()) && !list.contains(word)) {
                found = true;
                list.add(word);
            }
        }

        // Print the results
        if (!found) {
            System.out.print("No words");
        } else {
            for (String word : list) {
                System.out.print(word + ", ");
            }
        }
        System.out.println();
    }

    
    public void findTopK(int k) {
        //--------------------------------------------------------
        // Summary: Finds and prints the top K most frequent words in the Trie.
        // Precondition: k is a non-negative integer.
        // Postcondition: Prints the top K most frequent words.
        //--------------------------------------------------------

        // Create a list to store all words without duplicates
        List<String> sortedWords = new ArrayList<>(allWords);
        Collections.sort(sortedWords);
        List<String> checkList = new ArrayList<>();

        // Remove duplicates from sortedWords
        for (String word : sortedWords) {
            if (!checkList.contains(word)) {
                checkList.add(word);
            }
        }

        // Create a priority queue to store words based on their frequency and alphabetical order
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int countA = getCount(a);
            int countB = getCount(b);

            // If their count is equal, compare the words lexicographically in lowercase
            if (countA == countB) {
                return a.toLowerCase().compareTo(b.toLowerCase());
            }

            // If their counts are different, prioritize the word with higher count
            return countB - countA;
        });

        // Add all words from the checkList to the priority queue
        for (String word : checkList) {
            pq.offer(word);
        }

        // Retrieve the top K most frequent words from the priority queue
        List<String> topKWords = new ArrayList<>();
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            topKWords.add(pq.poll()); // Poll the word with the highest priority (highest count)
        }

        // Print the top K most frequent words
        for (String word : topKWords) {
            System.out.print(word + ", ");
        }

        System.out.println();
    }
}
