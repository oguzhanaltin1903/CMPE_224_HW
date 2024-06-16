package H3_Q2;

//-----------------------------------------------------
// Title: Homework3 question 2 solution class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 3
// Description: This class creates FileRead and Graph objects. With that FileRead object it prints graph and uses getValues method for get values from 
// the text file and use createGraph method to create Graph. After it creates DijkstraSP object to find shortest paths from the start vertice to all other vertice.
// and print that paths with their weights. Also this class includes DijkstraSP class and all needed methods for solution of question 2.
//-----------------------------------------------------


import java.util.LinkedList;
import java.util.PriorityQueue;

public class HW3_Q2_solution extends Valuefinder{


	public static void main(String[] args) {

		String filename = "HW3_Q2.txt";   
		
		//Creating FileRead object.
        FileRead reader = new FileRead();

		//LinkedList to store file.
        LinkedList<Integer> StoreList = new LinkedList<>();

		//Getting values from the file and add to list with that method.
        reader.getValues(filename, StoreList);

		//creates Graph with createGraph method.
        Graph Graph = createGraph(StoreList);

        //Start vertice of DijkstraSP algorithm.
        int start = 0;

        //Create object sp for find every shortestPaths from start vertex.
        DijkstraSP sp = new DijkstraSP(Graph, start);
        System.out.println();
        System.out.println("The result");

        //Print all shortest paths from start with using printShortestPaths method.
        sp.printShortestPaths(Graph, sp);

    }
    

    
    static class DijkstraSP {
     //-----------------------------------------------------
     // Title: Dijkstra's Shortest Path Algorithm Implementation. I took it from the slides and change a little.
     // Author: Oğuzhan Altın
     // ID: 15052066386
     // Section: 1
     // Assignment: 3
     // Description: This class provides the find shortest paths from start vertex to all vertices of the graph.
        
        private double[] distTo;            //Array to store distance from start vertex.
        private Edge[] edgeTo;              //Aray to store last edge on the shortest path.
        private PriorityQueue<Edge> pq;     //Priority queue for dijkstra algorithm.

  
        public DijkstraSP(Graph graph, int s) {
         //-----------------------------------------------------
         // Summary: Constructor for Dijkstra's Shortest Path Algorithm.
         // Precondition: Graph graph is initialized with edges, int s is the start vertex.
         // Postcondition: Computes the shortest paths from the start vertex to all other vertices in the graph.
         //-----------------------------------------------------
        
            distTo = new double[graph.V()];         //distTo array with number of vertices
            edgeTo = new Edge[graph.V()];           //edgeTo array with the number of vertices.
            pq = new PriorityQueue<>();

            //Initialize distances to infinity and source distance to 0.
            for (int v = 0; v < graph.V(); v++) {
                distTo[v] = Double.POSITIVE_INFINITY;
            }

            distTo[s] = 0.0;

            //Add the start vertex to the priority queue.
            pq.add(new Edge(s, s, 0.0 ));

            //Dijkstra's algorithm.
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();      //Delete the vertex which has minumum distance.
                int v = edge.to();           //Get the vertex.

                //Iterate all over the adjacent edges.
                for (Edge e : graph.adj(v)) {
                    relax(e);   //Relax each adjacent edge.
                }
            }
        }

        private void relax(Edge e) {
         //-----------------------------------------------------
         // Summary: Relaxes an edge in Dijkstra's algorithm.
         // Precondition: Edge e is the edge to relax.
         // Postcondition: If a shorter path is discovered, it updates the shortest distance to the target vertex.
         //-----------------------------------------------------

            int v = e.from(), w = e.to();

            //Checks whether a shortest path found.
            if (distTo[w] > distTo[v] + e.weight()) {
        
                distTo[w] = distTo[v] + e.weight();   //Uptade the distance.
                edgeTo[w] = e;                        //Update the edge on the shortest path.

                pq.remove(new Edge(w, w, distTo[w]));   //Delete if present, for update priority.
                pq.add(new Edge(w, w, distTo[w]));      //Add the updated edge to the priority queue.
            }
        }

        public void printShortestPaths(Graph g,DijkstraSP sp){
         //-----------------------------------------------------
         // Summary: Printing paths in correct order.
         // Precondition: Graph g to get number of vertices and DijkstraSp to get edgeTo and disTo.
         // Postcondition: Prints the path.
         //-----------------------------------------------------

            //Iterate all over the vertices from vertex 1.
            for (int v = 1; v < g.V(); v++) {

                //LinkedList to store paths.
                LinkedList<Integer> path = new LinkedList<>();
            
                // Check if there is a path to vertex v from start vertice.
                if (sp.distTo[v] < Double.POSITIVE_INFINITY) {
                
                    //Add the edges which is in that path into the list.
                    for (Edge e = sp.edgeTo[v]; e != null; e = sp.edgeTo[e.from()]) {

                        //Here is implemented for the repated edges such as , if i add e.to and e.from in same place it gives me the e.from 2 times.
                        //If it is not start vertex add the other vertice in that edge.
                        path.add(e.to()); 
                    
                        //Check if it is start vertex add it into the list.
                        if (e.from() == 0) {
                             path.add(e.from());
                        }
                    }

                    //Prints the path in correct order.
                    printPath(path);
    
                    //Print the distance to vertex v.
                    System.out.println((int) sp.distTo[v]);
                }
            }
        
        }

        public void printPath(LinkedList<Integer> list){
         //-----------------------------------------------------
         // Summary: Printing paths in correct order.
         // Precondition: LinkedList list to get the path order.
         // Postcondition: Prints the LinkedList from the end.
         //-----------------------------------------------------

            for (int i =list.size() - 1; i >= 0; i--) {
                System.out.print(list.get(i) + " ");
            }

        }

        public double distTo(int v) {
         //-----------------------------------------------------
         // Summary: Returns the shortest distance from the start vertex to vertex v.
         // Precondition: int v is which is the vertex to find distance from start vertex.
         // Postcondition: Returns the shortest distance from the start vertex to vertex v.
         //-----------------------------------------------------
            return distTo[v];
        }

    }
}
