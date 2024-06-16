package hw2q2;
//-----------------------------------------------------
// Title: Homework2 question 2 solution class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 2
// Description: This class creates Directed Graph and finds the paths which exits the graph except 30.
// Also this class includes Directed Graph methods and attributes to use to find paths.
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HW2_Q2_solution {

	public static void main(String[] args) {

		String filename = "HW2_Q2_text.txt";
		FileRead reader = new FileRead();

		//LinkedList which stores V,E and edges inside with getValues method.
		LinkedList <Integer> List = new LinkedList<>();
		reader.getValues(filename, List);

		//Converts list into a array.
		int[] values = new int[List.size()];
		int index = 0;
		//With for loop we look all of the values inside the List and add them into our array.
		for( int node : List){
			values[index] = node;
			index++;
		}

		//Code creates Directed Graph in here with array's values.
		int index2 = 0;

		//Gets value V and E.
		int V = values[index2++];
		int E = values[index2++];

		//Creats Graph with V.
		Graph DGraph = new Graph(V);

		//Creates array to store vertices' values with storeEdgesValues.
		int[] vertices = new int[V];
		reader.storeEdgesValues(filename, vertices);

		//Loop for create edge from U to V until E, i used array to get indexes of that values in the Graph.
		for(int i = 0 ; i < E ; i++){
			//Getting values from our arr array.
			int U = values[index2++];    
			int W = values[index2++];
			DGraph.addEdge(U, W, vertices);
		}

		System.out.println();
		System.out.println("Result is " );
		System.out.println();

		//Printing exit paths with printAllPaths method.
		DGraph.printExitPaths( vertices , DGraph);

	}

	//-----------------------------------------------------
	// Title: Directed Graph class
	// Author: Oğuzhan Altın
	// ID: 15052066386
	// Section: 1
	// Assignment: 2
	// Description: This class includes needed Directed Graph methods and attributes for question 1.
	//-----------------------------------------------------
	public static class Graph {

        private final int V;
        private final ArrayList<Integer>[] adjL;

        @SuppressWarnings("unchecked")
        public Graph(int V) {
            //--------------------------------------------------------
            // Summary: Constructor of the graph.
            // Precondition: int V is the number of vertices.
            // Postcondition: Creates an object of the directed graph.
            //--------------------------------------------------------
            this.V = V;
            adjL = new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adjL[v] = new ArrayList<>();
            }
        }

        public int getVerticeCount() {
            //--------------------------------------------------------
            // Summary: Returns vertex count.
            // Precondition: There is no precondition.
            // Postcondition: Returns the number of vertices.
            //--------------------------------------------------------
            return V;
        }

        public void addEdge(int v, int w, int arr[]) {
		 //--------------------------------------------------------
     	 // Summary: Adding edge between index of v to index of w.
     	 // Precondition: int v for start, int w for end and arr is for the get index of that v and w.
     	 // Postcondition: Edge will be created through v's index to w's index.
     	 //--------------------------------------------------------

            adjL[findValue(arr, v)].add(findValue(arr, w));   //Uses findValue method to get index of v and w and creates edge beetwen them.
        }

        public Iterable<Integer> adj(int v) {
            //--------------------------------------------------------
            // Summary: Returns list of adjacent vertices.
            // Precondition: int v is the vertex number.
            // Postcondition: Returns all adjacent vertices of v in a list.
            //--------------------------------------------------------
            return adjL[v];
        }

        public int findValue(int arr[], int V) {
		 //--------------------------------------------------------
     	 // Summary: Find the index of V.
     	 // Precondition: int array for getting index of V and int V for which value's index will be find.
     	 // Postcondition: Edge will be created directed to start to dest vertice
     	 //--------------------------------------------------------

		  	//Checks array until find V and gets V's index.
            for (int i = 0; i < adjL.length; i++) {
                if (arr[i] == V) {
                    return i;
                }
            }
            return -1;   //If not found return -1.
        }

		

        public void printExitPaths(int[] arr, Graph g) {
		 //--------------------------------------------------------
     	 // Summary: Find the all exit paths which exits the graph.
     	 // Precondition: int arr contains values of vertices, Graph g represents the directed graph.
     	 // Postcondition: Prints all exit paths from the graph.
     	 //--------------------------------------------------------

			//A boolean array to track visited vertices.
            boolean[] visited = new boolean[g.getVerticeCount()];
			//A LinkedList to store current path which is explored.
            ArrayList<Integer> pathList = new ArrayList<>();
			//Add start vertex to list.
            pathList.add(0);
			//Call the utility method to start exploring exit paths from the starting vertex.
			printExitPathsUtil(0, visited, pathList, arr, g);
        }

        private void printExitPathsUtil(int u, boolean[] visited, ArrayList<Integer> pathList, int[] arr, Graph g) {
		 //--------------------------------------------------------
     	 // Summary: A recursive function for searching paths and adds them to LinkedList.
     	 // Precondition: int u is start vertex, visited[] is for checking visited before,
		 // LinkedList pathList is for store paths, arr[] for get index values, Graph g for getting adjacent lists.
     	 // Postcondition: Prints all exit paths from the graph.
     	 //--------------------------------------------------------
            
		 	//Makes the current node true.
            visited[u] = true;

			//If current vertex has no adjacent and if current vertex not equals end vertex(because end vertex has not have adjacents so we exit the graph).
			if (u != g.getVerticeCount()-1 && g.adjL[u].isEmpty()) {
               
				printPath(pathList, arr);  //Prints paths.
			
			}
        
			//Loop for the adjacents of current vertice
            for (Integer i : g.adj(u)) {
				//Checks if we visit that vertice before.
                if (!visited[i]) {
                    pathList.add(i);
                    printExitPathsUtil(i, visited, pathList, arr, g);
                    pathList.remove(i);        //For exploring other paths, it creates a backtrack.
                }
            }

            //Makes the current node false to find other paths.
            visited[u] = false;
        }

        private void printPath(List<Integer> pathList, int[] arr) {
		 //--------------------------------------------------------
		 // Summary: Prints the path represented by the provided list of indices in the given array.
		 // Precondition: The input list pathList contains valid indices referring to elements in the arr array.
		 // Postcondition: The path represented by the indices in pathList is printed to the console.
		 //--------------------------------------------------------
			
			//Create a StringBuilder to construct the string representation of the path
            StringBuilder pathString = new StringBuilder();
			//Iterate through each index in the pathList
            for (int index : pathList) {
				//Append the element at the index in the arr array to the pathString, followed by a space
                pathString.append(arr[index]).append(" ");
            }
			//Print the constructed pathString to the console
            System.out.println(pathString);
        }
    }
}