package hw1;
//-----------------------------------------------------
//Title: Graph Class
//Author: Oğuzhan Altın
//ID: 15052066386
//Section: 1
//Assignment: 1
//Description: This class includes needed Undirected Graph methods and attributes for q1.
//-----------------------------------------------------

import java.util.LinkedList;

public class Graph {
    
    private final int V;
    private Bag<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
    //--------------------------------------------------------
    // Summary: Constructor of the graph.
    // Precondition: int V is the number of vertices.
    // Postcondition: Creates object of undirected graph.
    //--------------------------------------------------------
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }

    public void addEdge(int v, int w) {
    //--------------------------------------------------------
    // Summary: Edge creater between two vertices.
    // Precondition: v and w are integers.
    // Postcondition: Binds this two vertices with an edge.
    //--------------------------------------------------------
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
    //--------------------------------------------------------
    // Summary: Returns list of adjacent
    // Precondition: int v is the vertice number 
    // Postcondition: Returns all of the adjacents of v in a list.
    //--------------------------------------------------------
        return adj[v];
    }

    public int shortestPath(int start, int end) {
    //--------------------------------------------------------
    // Summary: Calculator for shortest path.
    // Precondition: int start and int end, vertices number for find shortest path.
    // Postcondition: Calculates shortest path between start and end vertices.
    //--------------------------------------------------------
    	if (start == end) {		//If start vertice and end vertice is equal,
            return 0;        	//shortest distance between them is 0.
        }
    	
    	//Array for counting visited vertices.
        boolean[] visited = new boolean[V];
        //Array to store distance from start vertex.
        int[] distance = new int[V];

        //LinkedList to add vertices we visited.
        LinkedList<Integer> verticesList = new LinkedList<Integer>();
       
        //First vertex marks as visited and distance from start sets to 0.
        visited[start] = true;
        distance[start] = 0;
        
        //Add first vertice to list.
        verticesList.add(start);
        
        //Perform search for path until list is empty.
        while (!verticesList.isEmpty()) {
        	//Take the current vertex from the list
            int current = verticesList.poll();

            //Look and iterate for all the adjacent vertices of current.
            for (int adjs : adj[current]) {
            	//Check if it is visited or not.
                if (!visited[adjs]) {
                	
                	distance[adjs] = distance[current]+1;	//uptades distance.
                    visited[adjs]=true;						//mark adjacent as visited.
                    verticesList.add(adjs);					//add it to list for search.
                    
                    
                    if (adjs == end) {			//Checks for end vertex,
                    	return distance[adjs]; 	//if it is the end vertex, return distance from start.
                    }
                }
            }
        }

        return -1;		// Return -1 if no path is found.
    }
    
   
}
