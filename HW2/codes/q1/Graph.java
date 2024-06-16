package hw2;
//-----------------------------------------------------
// Title: Directed Graph class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 2
// Description: This class includes needed Directed Graph methods and attributes for question 1.
//-----------------------------------------------------

import java.util.LinkedList;

public class Graph {
	
    private final int V;
    private final LinkedList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
     //--------------------------------------------------------
     // Summary: Constructor of the graph.
     // Precondition: int V is the number of vertices.
     // Postcondition: Creates object of undirected graph.
     //--------------------------------------------------------
        this.V = V;
        adj = new LinkedList[V+1];             //Adjust number of vertices for Graph.
        for (int v = 0; v < V+1; v++) {        //Adjust number of vertices for Graph.
            adj[v] = new LinkedList<>();
        }
    }

    public void addEdge(int v, int w) {
     //--------------------------------------------------------
     // Summary: Edge creater between two vertices.
     // Precondition: v and w are integers.
     // Postcondition: Adds an edge from v to w, not from the w to v because it is Directed Graph.
     //--------------------------------------------------------
        adj[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
     //--------------------------------------------------------
     // Summary: Returns list of adjacent
     // Precondition: int v is the vertice number 
     // Postcondition: Returns all of the adjacents of v in a list.
     //--------------------------------------------------------
        return adj[v];
    }

	public int findPathCount(Graph g, int start) {
     //--------------------------------------------------------
     // Summary: Returns the how many paths we have from start.
     // Precondition: Graph g and start for first vertice.
     // Postcondition: Returns path count for paths which is two step forward from start.
     //--------------------------------------------------------
        int index = 0;            //This value is for tracking to number of paths.

        // Loop through the adjacent vertices of the start vertex.
        for (int adjs1 : g.adj(start)) {
            //Loop through to adjacent vertices of the adjacent vertices of start.
            for (int adjs2 : g.adj(adjs1)) {

				index++;           // Increment the number of paths(index) for each valid path found.
            }
        }

        return index;      //Returns the number of paths to create array to store that paths.
    }


    public String[] findPaths(Graph g, int start, String[] arr) {
     //--------------------------------------------------------
     // Summary: Returns the array which includes the paths.
     // Precondition: Graph g and start for first vertice and String array for store the paths.
     // Postcondition: Returns array with paths.
     //--------------------------------------------------------
       
		int index = 0;   //Index of array which stores a path.

        // Loop through the adjacent vertices of the start vertex.
        for (int adjs1 : g.adj(start)) {
            //Loop through to adjacent vertices of the adjacent vertices of start.
            for (int adjs2 : g.adj(adjs1)) {

                arr[index] = start + " " + adjs1 + " " + adjs2;   //Store the pant in the string array which is for storing paths.
				index++;        //Increment index.
            }
        }
        
        return arr;              //Returns a string array which includes paths.
    }
}
