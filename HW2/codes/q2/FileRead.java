package hw2q2;
//-----------------------------------------------------
// Title: Homework2 solution class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 2
// Description: This class is for printing file and getting integer values from that file.
// Also this class creates a array to store vertices values for using that array to find index of values in the graph.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileRead extends Valuefinder{


	public int[] storeEdgesValues(String filename2, int[] vertices){
	 //--------------------------------------------------------
     // Summary: Reads values from a file and stores unique vertices in an array.
     // Precondition: String filename2 for the file path and int[] vertices for storing unique vertices.
     // Postcondition: Returns an array containing unique vertices from the file.
     //--------------------------------------------------------
		
		try{
			File file2 = new File(filename2);
			Scanner scanner2 = new Scanner(file2);

			//LinkedList to store vertices' values.
			LinkedList<Integer> verticeList = new LinkedList<>();
			
			//Skips the first two lines which is number of vertices and number of edges.
			scanner2.nextLine();
			scanner2.nextLine();

			//Reads values from the file line by line
			while (scanner2.hasNextLine()) {

				//Reads the current line containing two vertices representing an edge.
				String s = scanner2.nextLine();
				//Splits them and add into a string array.
				String [] edges = s.split(" ");

			//Converts the vertices to integers and add them into the vertex value list if not already present.
				if(!verticeList.contains(returnInt(edges[0]))){
					verticeList.add(returnInt(edges[0]));
					
				}

				if(!verticeList.contains(returnInt(edges[1]))){
					verticeList.add(returnInt(edges[1]));
					
					
				}
			}

			//Index of array.
			int index = 0;

			//Copies LinkedList into the vertex's values array.
			for( int node : verticeList){
				vertices[index]=node;
				index++;
			}
			scanner2.close();

			return vertices; //Returns the array which includes values.

		}

		catch (FileNotFoundException e) {
			//If file cannot found throw a runtime exception.
			throw new RuntimeException(e);
		}

    
	}
	
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
				System.out.println(" V=" + V);
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
					//Adds U and W to the LinkedList
					List.add(U);
					List.add(W);
					//Prints them.
					System.out.println(U+ " " + W);  
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
