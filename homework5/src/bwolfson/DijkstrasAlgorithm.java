package bwolfson;

// modify this for your solution.

public class DijkstrasAlgorithm {
	// TODO: The result of your computation is stored in these arrays.
	static int dist[];
	static int prev[];     // this is equivalent to edgeTo as discussed for DFS/BFS
	
	// conduct from this designated source vertex.
	public static void singleSourceShortestPath(DiGraphMatrix graph, int s) {
		dist = new int[graph.V];
		prev = new int[graph.V];
		boolean visited[] = new boolean[graph.V];
		for(int v = 0; v < graph.V; v++){
			dist[v] = (int)Double.POSITIVE_INFINITY;
			prev[v] = -1;
			visited[v] = false;
		}
		dist[s] = 0;
		int u = s; //start u at source
		//see if all vertices have been visited already
		boolean allVisited = true;
		for(int i = 0; i < graph.V; i++){
			if(visited[i] == false){
				allVisited = true;
			}
		}
		
		while(!allVisited){
			if(dist[u] == Double.POSITIVE_INFINITY) return;
			visited[u] = true;
			for(DirectedEdge v : graph.adj(u)){
				int w = (int) graph.weights[u][v.w];
				int newLen = dist[u] + w;
				if(newLen < dist[v.w]){
					dist[v.w] = newLen;
					prev[v.w] = u;
				}
			}
			//determine u whose dist[u] is smallest of unvisited vertices
			int smallest = (int)Double.POSITIVE_INFINITY;
			int next = 0;
			for(int j = 0; j < graph.V; j++){
				if(!visited[j] && dist[j] < smallest){
					smallest = dist[j];
					next = j;
				}
			}
			u = next;
		}
	
	}
	
	public static void main(String[] args) {
		// TODO: Replace
		// Manually construct the DiGraphMatrix sample graph from Question 4 and 
		// demonstrate that it produces the results as shown.
		
		DiGraphMatrix d = new DiGraphMatrix(5);
		d.addEdge(0, 1, 2);
		d.addEdge(0, 4, 4);
		d.addEdge(1, 2, 3);
		d.addEdge(2, 4, 1);
		d.addEdge(2, 3, 5);
		d.addEdge(4, 3, 7);
		d.addEdge(3, 0, 8);
		singleSourceShortestPath(d, 0);
		System.out.println(d);
	}
}
