package hw1;
//-----------------------------------------------------
//Title: Main Class
//Author: Oğuzhan Altın
//ID: 15052066386
//Section: 1
//Assignment: 1
//Description: This class includes input taker, path print and time calculator for the output. 
//-----------------------------------------------------

import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		//Gets inputs from user for needed values.
	    Scanner scanner = new Scanner(System.in);
		    String s = scanner.nextLine();
		    String[] numbers = s.split(" ");
	    int N = Integer.parseInt(numbers[0]);   	//Number of islands.
	    int M = Integer.parseInt(numbers[1]);    	//Number of undirected edges.
	    int T = Integer.parseInt(numbers[2]);		//Required time for the ports to change their state.
	    int C = Integer.parseInt(numbers[3]);		//Travel time between islands.
	    
	    //Creates the graph
	    Graph UGraph = new Graph(N);
	    
	    //Adds edges between input vertices
	    for (int i = 0; i < M; i++) {
	        int U = scanner.nextInt();
	        int V = scanner.nextInt();
	        UGraph.addEdge(U-1, V-1);     // Adjusts indices to 0-based
	    }

	    //Gets input for start vertex and end vertex
	    int X = scanner.nextInt()-1;      	// Adjusts indices to 0-based
	    int Y = scanner.nextInt()-1;		// Adjusts indices to 0-based
	        
	    int shortestPathLength = UGraph.shortestPath(X, Y);  //Calculates the length of the shortest path between X and Y.
	    
	    int numOfislands = shortestPathLength+1;            //As shortestPath finds the number of edges between start and end,
	    System.out.println(numOfislands);					//its +1 gives me the number of islands visited in shortest path.									
	   
	    int current = X;      //start from the vertex X.
	    do {
	        System.out.print((current + 1) + " ");              //print the current vertex in 1-based.
	        
	        for (int adjs : UGraph.adj(current)) {				//Look and iterate for all the adjacent of current.
	            if (UGraph.shortestPath(Y, adjs) == shortestPathLength - 1) {   //Checks is there exist an adjacent vertex is one edge away from Y.   
	            																
	                shortestPathLength--;   //decrement the remaining path.
	                current = adjs;			//change current to the adjacent vertex.
	                	
	            }
	        }
	    } while (current != Y);   		//continue until reaching the vertex Y.
	    System.out.println(Y+1); 		//prints Y in 1-based.

	    
	    int a = T - (C % T);					//calculates the remaining time until next port state change.
	    int b = ( numOfislands - 1 ) * C;    	//calculating boat route except first island.
	    int c = ( numOfislands - 2 ) * a;		//calculate port change time except first and last island 
		System.out.println( b + c );
		scanner.close();   
       
    }
        
}
