package H3_Q2;

//-----------------------------------------------------
// Title: FileRead class for question 2.
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 3
// Description: This class is for printing file and getting integer values from that file.
// Also this class has a method which returns LinkedList to create the graph. This List includes vertice number, edge number and all edges.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileRead extends Valuefinder{
    

    public LinkedList<Integer> getValues(String filename, LinkedList<Integer> List){
		//--------------------------------------------------------
        // Summary: Get values from Filename and print them.
     	// Precondition: String filename for file and LinkedList for store values from filename.
     	// Postcondition: Returns a LinkedList which includes file's values which turned integer, it also prints file.
     	//--------------------------------------------------------

		try{

			File file = new File(filename);
	        Scanner scanner = new Scanner(file);
			
			//Read values from the file line by line
			while (scanner.hasNextLine()) {

				int V = returnInt(scanner.nextLine());		//Read the number of vertices (V) from the file and convert it to an integer.
				int E =	returnInt(scanner.nextLine());		//Read the number of edges (E) from the file and convert it to an integer.

				//Adding them into a List to store.
				List.add(V);
				List.add(E);
				//Print V and E.
				System.out.println("V=" + V);
				System.out.println("E="+ E);
		
				//Read edge pairs from the file line by line.
				for (int i = 0; i < E ; i++) {
					//Gets the current line.
					String s = scanner.nextLine();
					//Splits current line and add pieces into a array.
					String[] numbers = s.split(" ");
					//Converts them to integer for defining U and W.
					int U = returnInt(numbers[0]);
					int W =	returnInt(numbers[1]);
                    int weight = returnInt(numbers[2]);
					//Adds U, W and weigth to the LinkedList
					List.add(U);
					List.add(W);
                    List.add(weight);

					//Since it is directed graph we can print edges in here.
                    System.out.println(U + " " + W + " " + weight); 
					 
				}	
			}	

			scanner.close();

			return List;		//Returns a LinkedList which stores the values from the file for creating a Graph.
		}

		catch (FileNotFoundException e) {
			//If file cannot found  throw a runtime exception.
			throw new RuntimeException(e);
		}

			
	}
}
