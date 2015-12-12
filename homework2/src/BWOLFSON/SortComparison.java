package BWOLFSON;

import edu.princeton.cs.algs4.*;

// Template to use for Question 1 on Homework 2
public class SortComparison {

	static Double[] generateData(int n) {
		Double[] vals = new Double[n];
		
		for(int i = 0; i < n; i++){
			vals[i] = Double.valueOf(StdRandom.uniform());
		}
		
		return vals;
	}
	
	// These have been placed here so you can double check that each of the sorting algorithms
	// did indeed sort the data. Note: DO NOT CALL THIS FUNCTION WITHIN YOUR Stopwatch time period
	// when you are checking for the total elapsed time for the respective algorithm to complete
	static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
	static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
	
	// You are responsible for updating these values.
	//
	// [n][s][0] = best time for algorithm #s on a dataset of size n
	// [n][s][1] = fewest number of exch operations for algorithm #s on a dataset of size n
	// [n][s][2] = fewest number of less operations for algorithm #s on a dataset of size n
	static double[][][] results = new double[12][5][3];
	
	// Generates the table with the proper formatting. Use as is.
	public static void generateReport() {
		StdOut.println("Time Trials");
		StdOut.println("N\tInsert\tSelect\tMerge\tQuick\tQuickA\n");
		for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
			StdOut.println(n + "\t" + results[idx][0][0] + 
					"\t" + results[idx][1][0] + 
					"\t" + results[idx][2][0] + 
					"\t" + results[idx][3][0] +
					"\t" + results[idx][4][0]);
		}
		
		StdOut.println();
		StdOut.println("Exch Results");
		StdOut.println("N\tInsert\tSelect\tMerge\tQuick\tQuickA\n");
		for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
			StdOut.println(n + "\t" + (int)results[idx][0][1] + 
					"\t" + (int)results[idx][1][1] + 
					"\t" + (int)results[idx][2][1] + 
					"\t" + (int)results[idx][3][1] +
					"\t" + (int)results[idx][4][1]);
		}
		
		StdOut.println();
		StdOut.println("Less Results");
		StdOut.println("N\tInsert\tSelect\tMerge\tQuick\tQuickA\n");
		for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
			StdOut.println(n + "\t" + (int)results[idx][0][2] + 
					"\t" + (int)results[idx][1][2] + 
					"\t" + (int)results[idx][2][2] + 
					"\t" + (int)results[idx][3][2] +
					"\t" + (int)results[idx][4][2]);
		}
	}

	// I suggest it might be useful to have a helper method such as this, which properly updates
	// the results[n][entry][xxx] values where xxx is 0 for time, 1 for exchange, and 2 for less.
	// 
	// The purpose of this method is to ensure you record the minimum time, the fewest number of
	// exchanges, and the fewest number of comparison operations.
	static void updateEntry(int trial, int n, int entry, int exch, int less, double time) {
		//trial 0 - update all entries
		//trial 1 - check if smaller than current then update
		if(trial == 0){
			results[n][entry][0]=time;
			results[n][entry][1]=exch;
			results[n][entry][2]=less;
		}
		else{
			double current_time = results[n][entry][0];
			double current_exch = results[n][entry][1];
			double current_less = results[n][entry][2];
			
			if(time < current_time){
				results[n][entry][0] = time;
			}
			if(exch < current_exch){
				results[n][entry][1]=exch;
			}
			if(less < current_less){
				results[n][entry][2] = less;
			}
		}
	}
	
	public static void main(String[] args) {
		
		// run for 10 trials
		int T = 10;
		
		
		for (int t = 0; t < T; t++) {
			for (int n = 4, idx = 0; n <= 8192; n*= 2, idx++) {
				
				// note idx is a value from 0 up to and including 12.
				// For each algorithm to be compared (InsertionSort,SelectionSort,MergeSort,QuickSort,QuickSortAlternate)
				// you must generate the data set to use, create a Stopwatch to measure the time, launch the 
				// sort request, record the elapsed time, validate that the array was actually sorted, and 
				// then update the appropriate entry with the recorded new values...
				
				//insertion sort
				Double[] insData = generateData(n);
				Stopwatch insWatch = new Stopwatch();
				Insertion.sort(insData);
				double insTime = insWatch.elapsedTime();
				int insExch = Insertion.exch;
				int insLess = Insertion.less;
				boolean insIsSorted = isSorted(insData);
				if(!insIsSorted){
					break;
				}
				updateEntry(t, idx,0,insExch,insLess,insTime);
				Insertion.exch = 0;
				Insertion.less = 0;
				
				//selection sort
				Double[] selData = generateData(n);
				Stopwatch selWatch = new Stopwatch();
				Selection.sort(selData);
				double selTime = selWatch.elapsedTime();
				int selExch = Selection.exch;
				int selLess = Selection.less;
				boolean selIsSorted = isSorted(selData);
				if(!selIsSorted){
					break;
				}
				updateEntry(t, idx,1,selExch,selLess,selTime);
				Selection.exch = 0;
				Selection.less = 0;
				
				//merge sort
				Double[] merData = generateData(n);
				Stopwatch merWatch = new Stopwatch();
				Merge.sort(merData);
				double merTime = merWatch.elapsedTime();
				int merExch = Merge.exch;
				int merLess = Merge.less;
				boolean merIsSorted = isSorted(selData);
				if(!merIsSorted){
					break;
				}
				updateEntry(t, idx,2,merExch,merLess,merTime);
				Merge.exch = 0;
				Merge.less = 0;
				
				//quick sort
				Double[] quiData = generateData(n);
				Stopwatch quiWatch = new Stopwatch();
				Quick.sort(quiData);
				double quiTime = quiWatch.elapsedTime();
				int quiExch = Quick.exch;
				int quiLess = Quick.less;
				boolean quiIsSorted = isSorted(quiData);
				if(!quiIsSorted){
					break;
				}
				updateEntry(t, idx,3,quiExch,quiLess,quiTime);
				Quick.exch = 0;
				Quick.less = 0;
				
				//quick alternate sort
				Double[] qaData = generateData(n);
				Stopwatch qaWatch = new Stopwatch();
				QuickAlternate.sort(qaData);
				double qaTime = qaWatch.elapsedTime();
				int qaExch = QuickAlternate.exch;
				int qaLess = QuickAlternate.less;
				boolean qaIsSorted = isSorted(qaData);
				if(!qaIsSorted){
					break;
				}
				updateEntry(t, idx,4,qaExch,qaLess,qaTime);
				QuickAlternate.exch = 0;
				QuickAlternate.less = 0;

			}
		}
		
		// Once done, generate the report
		generateReport();
	}

}
