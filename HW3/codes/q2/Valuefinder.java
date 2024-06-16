package H3_Q2;

//-----------------------------------------------------
// Title: Valuefinder class for q2.
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 3
// Description: This class is for changing data type of the file to integer. Also has a method to create graph and this method takes LinkedList as a parameter.
// This list comes from FileRead's getValues method. With that List we create first graph, after we create edge and add that edge into our graph.
// Also this class includes Directed Graph's class, data and needed methods for question and Edge class, data and needed edge methods for question.
//-----------------------------------------------------

import java.util.LinkedList;

public class Valuefinder {
	
    public static int returnInt(String value) {
     //--------------------------------------------------------
     // Summary: Change the data type of the parameter.
     // Precondition: String v is the data type which will change.
     // Postcondition: Returns int which changed from string.
     //--------------------------------------------------------
        
        return Integer.parseInt(value);       //Change values data type to integer and returns value.
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
        Graph Graph = new Graph(V);
          
   
        //Adds edge from U to W.
        for (int i = 0; i < E; i++) {
            int U = values[index2++];   //Start vertice of edge.
            int W = values[index2++];   //End vertice of edge.
            int weight = values[index2++];   //Weight of the edge.
   
            Edge Edge = new Edge(U, W, weight);   //Creates edge with U,W and weigth.
            Graph.addEdge(Edge);	                 //Adds that edge to the graph.
                   
        }

        return Graph;                  //Returns the graph.
    }


    static class Graph {
     //-----------------------------------------------------
	 // Title: Directed Graph class with using Edges except vertices.
	 // Author: Oğuzhan Altın
	 // ID: 15052066386
	 // Section: 1
	 // Assignment: 2
	 // Description: This class includes needed Edge weighted Directed Graph methods and attributes for question 2.
	 //-----------------------------------------------------
    
        private final int V;
        private final LinkedList<Edge>[] adj;

        @SuppressWarnings("unchecked")
        public Graph(int V){
         //--------------------------------------------------------
         // Summary: Constructor of the graph.
         // Precondition: int V is the number of vertices.
         // Postcondition: Creates an object of the edge weighted directed graph.
         //--------------------------------------------------------

            this.V = V;
            adj = (LinkedList<Edge>[]) new LinkedList[V];
            for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<Edge>();
        }

        public int V(){
         //--------------------------------------------------------
         // Summary: Number of the vertices.
         // Precondition: Has no precondition.
         // Postcondition: Returns number of vertices of a graph.
         //--------------------------------------------------------
            return V;
        }

        public void addEdge(Edge e){
         //--------------------------------------------------------
         // Summary: Adds a directed edge to the graph.
         // Precondition: Edge e is an object containing a starting vertex.
         // Postcondition: Adds edge e to the adjacency list of the starting vertex.
         //--------------------------------------------------------
        
            int v = e.from();      //Get the starting vertex of the edge.
            adj[v].add(e);         // Add edge to the adjacency list of vertex v.
        }

        public Iterable<Edge> adj(int v){
         //--------------------------------------------------------
         // Summary: Returns list of adjacent edges.
         // Precondition: int v is the vertex number.
         // Postcondition: Returns all edges adjacent to vertex v in a list.
         //--------------------------------------------------------

          return adj[v]; 
        }
    }

    static class Edge implements Comparable<Edge>{
     //-----------------------------------------------------
     // Title: Edge class to use in Graph. 
     // Author: Oğuzhan Altın
     // ID: 15052066386
     // Section: 1
     // Assignment: 3
     // Description: This class includes needed Edge data, methods, and attributes for question 2.
     //-----------------------------------------------------

        private final int v, w;         //Vertices of an edge.
        private final double weight;    // Weight of an edge.

        public Edge(int v, int w, double weight){
         //--------------------------------------------------------
         // Summary: Constructor of the Edge.
         // Precondition: v and w are vertices, and weight is the weight of that edge.
         // Postcondition: Creates edge.
         //--------------------------------------------------------
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int from(){  
         //--------------------------------------------------------
         // Summary: Returns the start vertex of that edge.
         // Precondition: Don't have any preconditions.
         // Postcondition: Returns vertex v.
         //--------------------------------------------------------
            return v; 
        }

        public int to(){ 
         //--------------------------------------------------------
         // Summary: Returns the end vertex of that edge.
         // Precondition: Don't have any preconditions.
         // Postcondition: Returns vertex w.
         //--------------------------------------------------------
         return w;
        }

        public double weight(){ 
            //--------------------------------------------------------
         // Summary: Returns the weight of the edge.
         // Precondition: Don't have any preconditions.
         // Postcondition: Returns weight.
         //--------------------------------------------------------
            return weight; 
        }

        @Override
        public int compareTo(Edge that){
         //--------------------------------------------------------
         // Summary: Compares the weight of this edge with another edge.
         // Precondition: The edges are initialized with weights.
         // Postcondition: Returns -1 if this edge has a smaller weight, +1 if it has a larger weight, or 0 if weights are equal.
         //--------------------------------------------------------


            if (this.weight < that.weight) return -1;
            else if (this.weight > that.weight) return +1;
            else return 0;
        }

    
    }
}
