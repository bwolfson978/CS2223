package bwolfson;

import edu.princeton.cs.algs4.In;

// skeletal structure for your HW5

public class Graph {
    
    final int V;
    int E;
    Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /** Added this method for day20 to load graph from file. */
    public Graph (In in) {
    	this (in.readInt());
    	int E = in.readInt();
    	for (int i = 0; i < E; i++) {
    		int v = in.readInt();
    		int w = in.readInt();
    		addEdge (v,w);
    	}
    }

    public int V() { return V; }
    public int E() { return E; }


    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }
    
    /** Removes the undirected edge v-w from this graph */
    public void removeEdge(int v, int w){
    	E--;
    	adj[v].remove(w);
    	adj[w].remove(v);
    }

    /** Returns the vertices adjacent to vertex <tt>v</tt>. */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /** Returns the degree of vertex <tt>v</tt>. */
    public int degree(int v) {
        return adj[v].size();
    }

    /** Implement as part of HW5, Question 1. */
    public Graph complement() { 
    	Graph complement = new Graph(V());

    	//iterate over graph adding edges which don't exist, removing ones that do
    	for(int v = 0; v < V; v++){
    		Bag<Integer> neighbors = this.adj[v]; //neighbors of current vertex
    		for(int v1 = 0; v1 < V; v1++){ //look at every other vertex in graph 
    			boolean inNeighbors = false;
    			for(int w : neighbors){ //check if prospective complement neighbor is already a neighbor
    				if(v1 == w) inNeighbors = true;
    			}
    			if(v != v1 && !inNeighbors){ //if the vertex found another that's not itself or a neighbor
    				complement.E++;
    				complement.adj[v].add(v1); //add one edge (half of addEdge's work) v1 - v will get added later
    			}
    		}

    	}
    	return complement;
    }

    /** Implement as part of HW5, Question 1. */
    public boolean connected() { 
    	//use bfs to see if there is a path from every vertex to every other vertex
    	for(int v = 0; v < V; v++){
    		BreadthFirstPaths bfp = new BreadthFirstPaths(this, v);
    		for(int v1 = 0; v1 < V; v1++){
    			if(!bfp.hasPathTo(v)){
    				return false;
    			}
    		}
    	}
    	return true;
    }

    /** Implement as part of HW5, Question 2. */
    public int status(int v) { 
    	//bfs from v to generate all distto values on other vertices
    	BreadthFirstPaths b = new BreadthFirstPaths(this,v);
    	int status = 0;
    	for(int i = 0; i < V; i++){
    		status += b.distTo(i);
    	}
    	return status;
    }
    
    /** Implement as part of HW5, Question 2. */
    public boolean statusInjective() { 
    	int[] statuses = new int[V()];
    	for(int v = 0; v < V; v++){
    		statuses[v]=status(v);
    	}
    	for(int i = 0; i < V; i++){
    		for (int j = 1; j < V; j++){
    			if(statuses[i] == statuses[j]){
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    /** Implement as part of HW5, Question 3. */
    public int findSafeVertex() { 
    	//first check that the graph is connected
    	if(!connected()){
    		return -1;
    	}
    	DepthFirstPaths d = new DepthFirstPaths(this,0);
    	boolean[] marked = d.getMarked();
    	//return a vertex whose neighbors have all been marked
    	for(int v = 0; v < V; v++){
    		boolean allNeighborsVisited = true;
    		for(int w: this.adj(v)){
    			if(marked[w] == false){
    				allNeighborsVisited = false;
    			}
    		}
    		if(allNeighborsVisited == true){
    			return v;
    		}
    	}
    	return -1;
    }

    /** Implement as part of HW5, Question 3. */
    public int diameter() {
    	int diameter = 0;
    	for(int v = 0; v < V; v++){
    		int eccentricity = 0;
        	//bfs from v to generate all distto values on other vertices
        	BreadthFirstPaths b = new BreadthFirstPaths(this,v);
        	for(int i = 0; i < V; i++){
        		if(b.distTo(i)> eccentricity){
        			eccentricity = b.distTo(i);
        		}
        	}
        	if(eccentricity > diameter){
        		diameter = eccentricity;
        	}
    	}
    	return diameter;
    }
    
    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
