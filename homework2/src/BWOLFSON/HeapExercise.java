package BWOLFSON;

import edu.princeton.cs.algs4.*;

// Q3 on Homework 2
public class HeapExercise {

	static Double[] generateData(int n) {
		Double[] vals = new Double[n];
		for (int i = 0; i < n; i++) {
			vals[i] = StdRandom.uniform();
		}
		return vals;
	}

	// You are responsible for updating these values.
	//
	// [n][0] = fewest number of comparisons for delMax on heap of size n
	// [n][1] = most number of comparisons for delMax on heap of size n
	// [n][2] = fewest number of comparisons for insert on heap of size n
	// [n][3] = most number of comparisons for insert on heap of size n
		
	static int[][] results = new int[12][4];

	public static void generateReport() {
		StdOut.println("Heap Trials");
		StdOut.println("N\tDelMax\tInsert");
		for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
			StdOut.println(n + "\t" + results[idx][0] + "-" + results[idx][1] + "\t" +
					 results[idx][2] + "-" + results[idx][3]);
		}
	}

	// Update results, given information for the given trial, data size N, number of comparisons
	// during the delete operation and number of comparisons during the insert operation.
	private static void updateEntry(int trial, int n, int delComparisons, int insertComparisons) {
		if(trial == 1){
			results[n][0]=delComparisons;
			results[n][1]=delComparisons;
			results[n][2]=insertComparisons;
			results[n][3]=insertComparisons;
		}
		else if(trial > 1){
			if(delComparisons <= results[n][0]){
				results[n][0]=delComparisons;
			}
			else{
				results[n][1]=delComparisons;
			}
			if(insertComparisons <= results[n][2]){
				results[n][2]=insertComparisons;
			}
			else{
				results[n][3]=insertComparisons;
			}
		}
	}

	public static void main(String[] args) {

		int T = 10;
		Double[] data;
		
		for (int t = 0; t < T; t++) {
			for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
				data = generateData(n);
				MaxPQ pq = new MaxPQ(n);
				for(int i = 0; i < n; i++){
					pq.insert(data[i]);
				}
				
				for(int i = 0; i<1000; i++){
					pq.delMax();
					int delComparisons = MaxPQ.delComparisons;
					pq.insert(StdRandom.uniform());
					int insertComparisons = MaxPQ.insertComparisons;
					updateEntry(t,idx,delComparisons,insertComparisons);
					MaxPQ.delComparisons=0;
					MaxPQ.insertComparisons=0;
				}
				

			}
		}

		generateReport();
	}

}
