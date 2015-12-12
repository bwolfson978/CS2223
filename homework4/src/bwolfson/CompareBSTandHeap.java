package bwolfson;

import edu.princeton.cs.algs4.*;

// For HW4 you are asked to compare the number of comparisons made during 
// successive calls to 'deleteMax'. Note that in a MaxPQ there are also
// exchange operations, which do not occur in a BST; however, these are
// always fewer than the number of comparisons (as you can see from p. 316). 
public class CompareBSTandHeap {
	static Double[] generateData(int n) {
		Double[] vals = new Double[n];
		for (int i = 0; i < n; i++) {
			vals[i] = StdRandom.uniform();
		}
		return vals;
	}

	public static void main(String[] args) {
		
		float T = 512;   // run 512 trials

		StdOut.println("N\tRatio");
		for (int n = 8; n <= 1048576; n*= 2) {
			
			// maximum ratio for the #of comparisons used by MaxPQ / #comparisons used by BST
			float maxRatio = 0;
			
			for (int t = 0; t <= T; t++) {
				MaxPQ<Double> mpq = new MaxPQ<Double>(n);
				BST<Double> bst = new BST<Double>();

				Double[] data = generateData(n);
				for (double d : data) {
					//System.out.println(d);
					mpq.insert(d);
					bst.insert(d);
				}

				// ready for the trial to start. You need to modify this code to repeatedly remove
				// the maximum value from each structure (one from the heap and one from the BST).
				// You must check that the same value is being retrieved from both for correctness.
				// note that you can only do this by first requesting the max() value from the BST.
				// Only count the number of inspections within your delMax function.
				
				//reset both inspectedElementCount variables
				BST.inspectedElementCount = 0;
				MaxPQ.inspectedElementCount = 0;
				
				while(!bst.isEmpty()&&!mpq.isEmpty()){
					Double maxTree  = bst.max(); //max value in the tree
					//System.out.println("max value in tree " + maxTree);
					bst.deleteMax(); //delete the max value from the bst
					Double maxPQ = mpq.delMax();
					//System.out.println("max value in pq " + maxPQ);
					if(!maxPQ.equals(maxTree)){ //check that the value deleted from the bst is the same value deleted from mpq
						System.out.println("max values were unequal on trial " + t + "for N value " + n);
						break;
					}
				}
				float mpq_inspections = MaxPQ.inspectedElementCount;
				//System.out.println("mpq inspections on trial " + t + "for N " + n + ": " + mpq_inspections);
				float tree_inspections = BST.inspectedElementCount;
				//System.out.println("tree inspections on trial " + t + "for N " + n + ": " + tree_inspections);
				float ratio = mpq_inspections / tree_inspections;
				if (ratio > maxRatio){
					maxRatio = ratio;
				}
			}
			System.out.println(n + "\t" + maxRatio);
		}
	}
}
