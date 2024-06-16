package HW3_Q1;

//-----------------------------------------------------
// Title: FileRead class.
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 3
// Description: This class is for printing number of edges and vertices and getting values from that file.
// With that values, we change their data types to the integer and store them in a LinkedList to create graph with method in Valuefinder class.
// and this class has getValues method which creates LinkedList to store values and returns that LinkedList.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileRead extends Valuefinder{
    

    public LinkedList<Integer> getValues(String filename, LinkedList<Integer> List){
		//--------------------------------------------------------
        // Summary: Get values from Filename and print number of vertices and edges from them.
     	// Precondition: String filename for file and LinkedList for store values from filename.
     	// Postcondition: Returns a LinkedList which includes file's values which turned integer to create our graph.
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
					//Converts them to integer for defining U, W and weight.
					int U = returnInt(numbers[0]);
					int W =	returnInt(numbers[1]);
                    int weight = returnInt(numbers[2]);
					//Adds U, W and weigth to the LinkedList.
					List.add(U);
					List.add(W);
                    List.add(weight);
					 
				}	
			}	

			scanner.close();

			return List;		//Returns a LinkedList which stores the values from the file for creating a Edge Weighted Graph.
		}

		catch (FileNotFoundException e) {
			//If file cannot found  throw a runtime exception.
			throw new RuntimeException(e);
		}

			
	}
}
