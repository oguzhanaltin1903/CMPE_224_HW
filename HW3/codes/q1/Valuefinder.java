package HW3_Q1;

//-----------------------------------------------------
// Title: Valuefinder class.
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 3
// Description: This class is for changing data type of the file to integer. Also this class has createGraph method to create to undirected edge weighted graph
// which uses LinkedList provided by FileRead class' getValues method. Also this method includes Graph class, data and needed graph methods for graph and 
// Edge class, data and needed edge methods.
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
            int U = values[index2++];   //Start vertice of the edge.
            int W = values[index2++];   //End vertice of the edge.
            int weight = values[index2++]; //Weight of the that edge.
   
            Edge Edge = new Edge(U, W, weight);    //Creating edge with vertices U and W, and with weigth.
            Graph.addEdge(Edge);	                  //Adds that weighted edge to our graph.
                   
        }
        return Graph;                  //Returns the graph.
    }

    static class Graph {
     //-----------------------------------------------------
	 // Title: Undirected Graph class with using Edges except vertices. I took this class from our slides and add methods.
	 // Author: Oğuzhan Altın
	 // ID: 15052066386
	 // Section: 1
	 // Assignment: 3
	 // Description: This class includes needed Edge weighted Undirected Graph methods and attributes for question 1.
	 //-----------------------------------------------------
    
        private final int V;
        private final LinkedList<Edge>[] adj;

        @SuppressWarnings("unchecked")
        public Graph(int V){
         //--------------------------------------------------------
         // Summary: Constructor of the graph.
         // Precondition: int V is the number of vertices.
         // Postcondition: Creates an object of the edge weighted undirected graph.
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
         // Summary: Adds an edge to the graph.
         // Precondition: Edge e is an object containing two vertices.
         // Postcondition: Adds edge e to the adjacency list of both vertices involved.
         //--------------------------------------------------------

            int v = e.either(), w = e.other(v); //Get both vertices of that edge.
            adj[v].add(e);  // Add edge to the adjacency list of vertex v   
            adj[w].add(e);  // Add edge to the adjacency list of vertex w
        }

        public Iterable<Edge> adj(int v){
         //--------------------------------------------------------
         // Summary: Returns list of adjacent edges.
         // Precondition: int v is the vertex number.
         // Postcondition: Returns all edges adjacent to vertex v in a list.
         //--------------------------------------------------------
            return adj[v]; 
        }

        public void printEdges(Graph g){
         //--------------------------------------------------------
         // Summary: Printing Edges in both form v->w and w->v.
         // Precondition: Graph g to take vertice number.
         // Postcondition: Prints all edges in both forms v->w and w->v with that edges weight.
         //-------------------------------------------------------- 

            boolean[][] printedEdges = new boolean[g.V()][g.V()];  //Boolean array to check whether we print that edge form.

            //Loop to visit all vertices in the graph.
            for (int v = 0; v < g.V() ; v++) {

                //Loop to visit every edges in that graph.
                for (Edge e : g.adj(v)) {

                    //Makes w to adjacent vertice in that edge with using edge e and vertice v.
                    int w = e.other(v);

                    //Checks whether we print that edge if we dont print that edge, print that edge and then make that edge true in our boolean array.
                    if (!printedEdges[v][w]) {
                        
                        System.out.println(v + " " + w + " " + e.weight());
                        printedEdges[v][w] = true;
                        
                    }
                }
            }
        }

    }


    static class Edge implements Comparable<Edge>{
     //-----------------------------------------------------
	 // Title: Edge class to use in Graph. I took this class from our slides.
	 // Author: Oğuzhan Altın
	 // ID: 15052066386
	 // Section: 1
	 // Assignment: 3
	 // Description: This class includes needed Edge data, methods and attributes for question 1.
	 //-----------------------------------------------------
    

        private final int v, w;             //Vertices of an edge.
        private final int weight;           //Weight of an edge.

        public Edge(int v, int w, int weight){
         //--------------------------------------------------------
         // Summary: Constructor of the Edge.
         // Precondition: v and w is vertices and weight is the weight of that edge.
         // Postcondition: Creates edge.
         //--------------------------------------------------------

            this.v = v;
            this.w = w;
            this.weight = weight;

        }

        public int either(){ 
         //--------------------------------------------------------
         // Summary: Returns the start vertice of that edge.
         // Precondition: Don't have any preconditions.
         // Postcondition: Returns vertex v.
         //--------------------------------------------------------
            return v; 
        }

        public int other(int vertex){
         //--------------------------------------------------------
         // Summary: Since it is undirected graph it returns the other vertex.
         // Precondition: Don't have any preconditions.
         // Postcondition: Returns vertex v or w.
         //--------------------------------------------------------
            
             // Check if the provided vertex is the starting vertex of the edge.
            if (vertex == v)
            return w; //If it is starting vertex return end vertex.
            else return v;  //If it is end vertex return the starting vertex.
        }
    
        public int weight(){
         //--------------------------------------------------------
         // Summary: Returns the weight of the edge.
         // Precondition: Don't have any preconditions.
         // Postcondition: Returns weight.
         //--------------------------------------------------------
            return weight;
        }

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