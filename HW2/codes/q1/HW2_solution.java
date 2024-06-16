package hw2;
//-----------------------------------------------------
// Title: Homework2 solution class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 2
// Description: This class creates Directed Graph and finds the paths of two steps further from the input.
// Also this class includes createGraph method to create a Directed Graph and printArray method to print array.
//-----------------------------------------------------


import java.util.LinkedList;
import java.util.Scanner;


public class HW2_solution {


	public static void main(String[] args) {

		String filename = "HW2_Q1_text.txt";   
		
		//Creating FileRead object.
        FileRead reader = new FileRead();

		//LinkedList to store file.
        LinkedList<Integer> StoreList = new LinkedList<>();
		//Getting values from the file and add to list with that method.
        reader.getValues(filename, StoreList);

		//creates Graph with createGraph method.
        Graph DGraph = createGraph(StoreList);

		//this part is for taking input from user for starting index.
        Scanner scanner = new Scanner(System.in);
		System.out.println("Start point:");
		int Y = scanner.nextInt();
		System.out.println(Y);

		//This part is for finding number of paths and creating string array with that value.
		int pathCount = DGraph.findPathCount(DGraph, Y);
		String[] Paths = new String[pathCount];
	
		//This part is for finding paths and adding them into Paths string array and print Paths array.
		DGraph.findPaths(DGraph, Y, Paths);
		printArray(Paths);

		scanner.close();
	}

	static Graph createGraph(LinkedList<Integer> List) {
	 //--------------------------------------------------------
     // Summary: A method to create Graph.
     // Precondition: LinkedList to take values from.
     // Postcondition: Creates array to store List's value, and with that array generates Graph.
     //--------------------------------------------------------

        int[] values = new int[List.size()];  //Creating an array which has same size with List for storing List(Graph) values from file.
        int index = 0;

		//Gets nodes from list and adds them into array.
        for (int node : List) {
            values[index] = node;
            index++;
        }

		//Gets values from the array.
        int index2 = 0;
        int V = values[index2++];  //Number of vertices from array.
        int E = values[index2++];  //Number of edges from array

		//Creating Graph with V vertices.
        Graph DGraph = new Graph(V);

		//Adds edge from U to W.
        for (int i = 0; i < E; i++) {
            int U = values[index2++];   //Start vertice of edge.
            int W = values[index2++];   //End vertice of edge.
            DGraph.addEdge(U, W);		//Add edge from U to W.
        }
        return DGraph;                  //Returns the graph.
    }
		
	static void printArray(String[] arr){
	 //--------------------------------------------------------
     // Summary: A method to print a String array.
     // Precondition: String array to print.
     // Postcondition: Prints indexes of array in different lines.
     //--------------------------------------------------------
		
		//Looks all indexes of array and print them.
		for(int i = 0 ; i<arr.length ; i++){
		System.out.println(arr[i]);

		}
	}
	
}