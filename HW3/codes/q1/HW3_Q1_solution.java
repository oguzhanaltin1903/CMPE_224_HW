package HW3_Q1;

//-----------------------------------------------------
// Title: Homework3 question 1 solution class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 3
// Description: This class creates FileRead and Graph objects. With that FileRead object it getValues from the text file and use createGraph method to create Graph.
// After that it uses printEdges method to print edges in form of v->w and w->v since it uses undirected graph.
// After that it uses Kruskal algorithm to find mst path and mst weight.
// Also this class includes Kruskal Algorithm's class to find mst and Union find class to use it in kruskal algorithm.
//-----------------------------------------------------

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HW3_Q1_solution extends Valuefinder{


	public static void main(String[] args) {

		String filename = "HW3_Q1.txt";   
		
		//Creating FileRead object.
        FileRead reader = new FileRead();

		//LinkedList to store file.
        LinkedList<Integer> StoreList = new LinkedList<>();
		//Getting values from the file and add to list with that method.
        reader.getValues(filename, StoreList);

		//creates Graph with createGraph method.
        Graph Graph = createGraph(StoreList);

        //Printing all edges as v->w and w->v form and their weights.
        Graph.printEdges(Graph);

        //Creating Kruskal object to find Minumum Spanning Tree.
        KruskalMST mst = new KruskalMST(Graph);
        System.out.println("The Minimum Spanning Tree Path");

        //Printing MST edges in creating order which is created by Kruskal algorithm.
        mst.printMST(mst);
        
        //Printing Minumum Spanning Tree weight.
        System.out.println("The Minimum Spanning Tree value= " + mst.weight());
	}

    static class KruskalMST {
     //-----------------------------------------------------
     // Title: Kruskal's Minimum Spanning Tree Algorithm. 
     // Author: Oğuzhan Altın
     // ID: 15052066386
     // Section: 1
     // Assignment: 3
     // Description: This class implements Kruskal's algorithm to find the minimum spanning tree of a graph.
     //-----------------------------------------------------


        private Queue<Edge> mst;        //mst's edges.
        private int mstWeight;         //mst's weight.

        public KruskalMST(Graph graph) {
        //-----------------------------------------------------
         // Summary: Kruskal's Minimum Spanning Tree Algorithm's constructor.
         // Precondition: Graph g the initialize edges.
         // Postcondition: Constructs the Minimum Spanning Tree using Kruskal's algorithm.
         //-----------------------------------------------------

            mst = new LinkedList<>();               //Initialize mst.
            PriorityQueue<Edge> pq = new PriorityQueue<>();         //Priority queue for edges.

            //Add all edges of the graph to the priority queue.
            for (int a = 0; a < graph.V(); a++) {  

                //Iterate over all edges adjacent to vertex v         
                for (Edge e : graph.adj(a)) {

                    //Ensure each edge is added only once.
                    if (e.other(a) != a) {

                        pq.add(e);    //Adds that edge in to pq.
                    }
             
                }
            }

            //Union-find data structure for cycle detection.
            UnionFind uf = new UnionFind(graph.V());

            //Kruskal's algorithm.
            while (!pq.isEmpty() && mst.size() < graph.V() - 1) {

                Edge e = pq.poll();                 //Get the edge which has minumum weight.
                int v = e.either(),w = e.other(v);  //Get the vertices of that edge.

                //Checks whether adding that edge creates cycle.
                if (!uf.connected(v, w)) {

                    mstWeight += e.weight();       //Update the total weight of the Minimum Spanning Tree.
                    uf.union(v, w);             //Union the vertices to mark them as connected.
                    mst.add(e);                 //Add that edge in MST.
                
                }
            }
        }

        public Iterable<Edge> edges() {
         //-----------------------------------------------------
         // Summary: Returns the edges of the mst.
         // Precondition: There is no precondition it happens after mst occurs.
         // Postcondition: Returns the edges of the mst.
         //-----------------------------------------------------
            return mst;
        }

        public void printMST(KruskalMST mst){
         //-----------------------------------------------------
         // Summary: Prints the mst's edges.
         // Precondition: KruskalMST mst to get edges.
         // Postcondition: Prints the edges of the mst.
         //-----------------------------------------------------

            //Iterate all over the edges.
            for (Edge e : mst.edges()) {
                
                //Checks which vertex is smaller and prints small one first.  
                if(e.either()<e.other(e.either())){
                    System.out.println(e.either() + " " + e.other(e.either()) + " " + e.weight());
                }
           
                else if(e.other(e.either())<e.either()){
                    System.out.println(e.other(e.either()) + " " + e.either() + " " + e.weight());
                }
            }
        }

        public int weight() {
         //-----------------------------------------------------
         // Summary: Returns the total weight of the mst which is updated in the constructor of the kruskal algorithm.
         // Precondition: There is no precondition.
         // Postcondition: Returns the total weight of the mst.
         //-----------------------------------------------------

            return mstWeight;
        }


        //Union Find class to check if an edge creates cycle if dont we connect that vertices.
        //I use this class because Kruskal algorithm needs that, we didn't learn how to implement it
        //so i took this class from somewhere to use in my kruskal algorithm.
        static class UnionFind {
         
            private int[] parent;
            private int[] rank;

            public UnionFind(int n) {
                parent = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    rank[i] = 0;
                }
            }

            public int find(int p) {
                if (parent[p] != p) {
                    parent[p] = find(parent[p]); 
                }
                return parent[p];
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);

                if (rootP != rootQ) {
                    if (rank[rootP] < rank[rootQ]) {
                        parent[rootP] = rootQ;
                    } else if (rank[rootP] > rank[rootQ]) {
                        parent[rootQ] = rootP;
                    } else {
                        parent[rootQ] = rootP;
                        rank[rootP]++;
                    }
                }
            }

            public boolean connected(int p, int q) {
                return find(p) == find(q);
            }
        }
    }
   

}