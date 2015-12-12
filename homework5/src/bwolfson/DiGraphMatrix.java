package bwolfson;

// Complete this implementation which represents a Directed Graph using
// an Adjacency Matrix.

public class DiGraphMatrix {
	final int V;
	int E;
	double[][] weights;
	
	public DiGraphMatrix (int V) {
		this.V = V;
		this.E = 0;
		weights = new double[V][V];
	}
	
	public void addEdge (int source, int target, double weight) {
		E++;
		weights[source][target] = weight;
	}
	
	
	/** Returns information about given directed edge, or null if doesn't exist. */
	public DirectedEdge getEdge (int source, int target) {
		return new DirectedEdge(source,target,weights[source][target]);
	}
	
	public Iterable<DirectedEdge> adj(int v) {
		// Hint: You could create a Queue of DirectedEdges, populating it from the
		// specific row of the matrix 'weights' and then return that. 
		Queue<DirectedEdge> neighbors = new Queue<DirectedEdge>();
		for(int i = 0; i < V; i++){
			neighbors.enqueue(new DirectedEdge(v,i,weights[v][i]));
		}
		return neighbors;
	}
	
	// Don't Bother to implement reverse() as shown on p. 569
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (DirectedEdge e : adj(v)) {
                s.append(e.toString());
            }
            s.append("\n");
        }
        return s.toString();
    }
	
}
