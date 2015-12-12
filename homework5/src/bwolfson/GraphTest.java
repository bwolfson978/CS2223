package bwolfson;

public class GraphTest {

	public static void main(String[] args) {
		//// test complement ////
		Graph g = new Graph(5);
		Graph comp = g.complement();
		System.out.println("Original: " + g);
		System.out.println("Complement: " + comp);
		
		//// test connected /////
		Graph g1 = new Graph(5);
		g1.addEdge(0, 3);
		g1.addEdge(0, 2);
		g1.addEdge(1, 3);
		g1.addEdge(1, 4);
		Graph g1comp = g1.complement();
		System.out.println("Connectivity Testing: ");
		System.out.println("Original: " + g1);
		System.out.println("Complement: " + g1comp);
		System.out.println("Is the original connected? " + g1.connected());
		System.out.println("Is the complement connected? " + g1comp.connected());
		
		//status test////
		System.out.println("status of previous complement graph is wrt vertex 0: " + g1comp.status(0));
		System.out.println("is that graph status injective? " + g1comp.statusInjective());
		
		//diameter test
		System.out.println("diameter of graph is: " + g1.diameter());
		System.out.println("diameter of graph complement is: " + g1comp.diameter());
	}

}
