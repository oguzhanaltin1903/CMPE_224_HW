package hw4;

//-----------------------------------------------------
// Title: Homework4 main class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 4
// Description: This class takes user input and with that input it applies the method which user calls. Also there is a extra method which is createTrie
// which creates trie with the user's input file and creates trie.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
 


public class Main {
     
    public static void main(String[] args) {
    
        String input;  
        Trie trie =  new Trie();             // Create a new Trie instance                                                                  
                                                                            
        try(Scanner scan = new Scanner(System.in)){     // Initialize Scanner to read from standard input                                            
    
            while(scan.hasNext()){          // Loop to process input continuously                                                            
    
                input = scan.next();                // Read the next input                                                                                                                      
    
                if(input.contains("Input1.txt")){      // If the input contains the name of the file
                                                                 
                        
                    createTrie(input, trie);        // Call method to insert words from file into Trie
                    System.out.println();
    
                }
    
    
                else if(input.contains("search")){      // If the input is a search command
                                                                   
                    System.out.println();
                    input = scan.next();            // Read the word to search for
                    trie.search(input);             // Apply search method
                    System.out.println();
                          
                                                      
                }
    
    
                else if(input.contains("reverse")){    // If the input is a reverse search command                                            
    
                    System.out.println();
                    input = scan.next();                // Read the suffix to search for
                    trie.reverseFind(input);            // Call method to find words ending with the suffix
                    System.out.println();
    
                                                                                         
                }
    
                else if(input.contains("autocomplete")){          // If the input is an autocomplete command
                        
                    System.out.println();
                    input = scan.next();                    // Read the prefix to search for
                    trie.autoComplete(input);               // Call method to find words starting with the prefix
                    System.out.println();                
    
                                                                
                }
    
                else if(input.contains("full")){        // If the input is a full autocomplete command
                        
                    System.out.println();
                    input = scan.next();            // Read the prefix
                    String input2 = scan.next();    // Read the suffix
                    trie.fullComplete(input, input2);       // Call method to find words starting with the prefix and ending with the suffix
                    System.out.println();
                                                          
                }

                else if(input.contains("topk")){            // If the input is a topk command
                    
                    System.out.println();
                    input = scan.next();                    // Read the number of top frequent words to find
                    int number = Integer.parseInt(input);   // Convert the number from string to integer
                    trie.findTopK(number);                  // Call method to find top k frequent words
                    System.out.println();
                                                               
                }
    
                else{               // If the input is not find                                                              
                    System.exit(0);   //Exit
                }
    
            }
    
            scan.close();      // Close the Scanner                                                                     
            System.exit(0);         //Exit
    
        } 
        
        catch (Exception e){
            e.printStackTrace();
        }
            
    }

    static Trie createTrie(String filename,Trie trie) {
     //-----------------------------------------------------
     // Summary: It creates trie with the file.
     // Precondition: String filename for file, trie for creating trie.
     // Postcondition: Returns the trie with the file information.
     //-----------------------------------------------------

        try{

        // Open the file specified by the filename
		File file = new File(filename);
	    Scanner scanner = new Scanner(file);

        // Read each line
        while(scanner.hasNextLine()){

            
            String s = scanner.nextLine();            // Read the next line

            s = s.replaceAll("[^a-zA-Z0-9 ]", "");      // Remove all characters except alphabets, digits, and spaces
					
			String[] data = s.split(" ");            // Split the line into words based on spaces

            // Insert each word into the trie
            for(int i = 0 ; i< data.length ; i++){

                trie.insert(data[i]);
            }

            
        }
            
            // Close the scanner after reading all lines from the file
            scanner.close();

            return trie;  // Return the trie which contains our file

        }

        // If the file cannot be found, throw a runtime exception
        catch (FileNotFoundException e) {
         //If file cannot found  throw a runtime exception.
            throw new RuntimeException(e);
        }


    
    }

}