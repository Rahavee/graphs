import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Graph {

    private int vertices;
    private LinkedList<Integer> adj[];
    private ArrayList<Integer> dis = new ArrayList<Integer>();
    private ArrayList<Integer> explored = new ArrayList<Integer>();

    // constructor creates an empty adjacency list
    Graph(int V)
    {
        vertices = V;
        adj = new LinkedList[vertices];

        for(int i = 0; i < vertices ; i++){
            adj[i] = new LinkedList<Integer>();
        }
    }
    //adding the edges to the adjacency list
    void addEdge(int a, int b){
        adj[a-1].add(b);

    }
    //Discovered list for the FIFO queue
    void discovered(int a){

        dis.add(a);
    }
    //Explored list for the LIFO stack
    void explored(int a){

        explored.add(a);
    }

    //This method tests if the node was discovered
    boolean ifDiscovered(int a){
        if (dis.contains(a)){
            return true;
        }
        else
            return false;
    }
    //This method tests if the node was explored
    boolean ifExplored(int a){
        if (explored.contains(a)){
            return true;
        }
        else
            return false;
    }
    //Breadth first search algorithm
    void BFS_FIFO(int a){
        //clears out the discovered list
        dis.clear();
        ArrayList<Integer> Q= new ArrayList<Integer>();
        //Add the starting node to the Q list
        Q.add(a);
        System.out.println("Starting node: "+a);
        while (Q.size()!=0){
            // get the node from the beggining of the list
           int node=Q.get(0);
           System.out.println("The next node taken from Q is "+ node);
           Q.remove(0);
           //check if the node is discovered, if no mark discovered
           if (!(ifDiscovered(node))){
               discovered(node);
                //check all the edges of the node chosen from Q
               for( int i=0; i<adj[node-1].size(); i++){
                   if (!(ifDiscovered(adj[node-1].get(i)))){
                       Q.add(adj[node-1].get(i));
                   }
               }
           }
        }
        System.out.println("Order of discovered nodes" +dis);

    }
    // Breadth first search algorithm
    void BFS_LIFO(int a){
        //clears out the explored list
        explored.clear();
        ArrayList<Integer> Q= new ArrayList<Integer>();
        //Add the starting node to Q
        Q.add(a);
        System.out.println("Starting node: "+a);
        //While Q is not empty, take the last node from Q. the last node to be added on, is taken out first
        while (Q.size()!=0){
            int last=Q.size();
            int node=Q.get(last-1);
            System.out.println("The next node taken from Q is "+ node);
            Q.remove(last-1);
            //check if the node is explored, if no then mark the node as explored
            if (!(ifExplored(node))){
                explored(node);
                //check for each edge of the chosen node, if the vertex on the other end is explored
                for( int i=0; i<adj[node-1].size(); i++){
                    if (!(ifExplored(adj[node-1].get(i)))){
                        //if not explored then add to Q
                        Q.add(adj[node-1].get(i));
                    }
                }
            }
        }
        System.out.println("Order of explored nodes "+explored);

    }


    //main function
    public static void main(String[] args) {
        Graph g = new Graph(8);
        //creating the edges and the adjacency list
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(2,4);
        g.addEdge(2,5);
        g.addEdge(3,1);
        g.addEdge(3,2);
        g.addEdge(3,5);
        g.addEdge(3,7);
        g.addEdge(3,8);
        g.addEdge(4,2);
        g.addEdge(4,5);
        g.addEdge(5,2);
        g.addEdge(5,3);
        g.addEdge(5,4);
        g.addEdge(5,6);
        g.addEdge(6,5);
        g.addEdge(7,3);
        g.addEdge(7,8);
        g.addEdge(8,3);
        g.addEdge(8,7);

        //breadth first search algorithms
        System.out.println("BFS search from 1 using a LIFO stack");
        g.BFS_LIFO(1);
        System.out.println("\n");
        System.out.println("BFS search from 8 using a LIFO stack");
        g.BFS_LIFO(8);
        System.out.println("\n");
        System.out.println("BFS search from 1 using a FIFO queue");
        g.BFS_FIFO(1);
        System.out.println("\n");
        System.out.println("BFS search from 8 using a FIFO queue");
        g.BFS_FIFO(8);
    }
}
