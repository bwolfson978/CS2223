package BWOLFSON;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Proper Merge Sort from Sedgewick, 4ed
public class MergeSortThreeWay {
    
	static int exch = 0; //number of exchanges
	static int less = 0; //number of less comparisons
	
	static Comparable aux[];
	
    public static void sort(Comparable[] a) {
    	aux = new Comparable[a.length];
    	sort (a, 0, a.length-1);
    }
    
    // recursive helper function
    static void sort (Comparable[] a, int lo, int hi) {
    	if (hi <= lo) return;
    	
    	int left = lo + (hi-lo)/3;
    	int right = lo + (hi-lo)*2/3;
    	
    	sort(a, lo, left);
    	sort(a, left+1, right);
    	sort(a, right + 1, hi);
    	merge(a, lo, left, right, hi);
    }
    
    // merge sorted results a[lo..left] with a[left+1..right] with a[right+1..hi] back into a
    static void merge (Comparable[] a, int lo, int left, int right, int hi) {
    	int i = lo;     // starting index into 1st sorted sub-array
    	int j = left+1;  // starting index into 2nd sorted sub-array
    	int k = right+1; // starting index into 3rd sorted sub-array
    	
    	// copy a[lo..hi] into aux[lo..hi]
    	for (int m = lo; m <= hi; m++) {
    		aux[k] = a[k];
    	}
    	
    	// now comes the merge. Something you might simulate with flashcards
    	// drawn from two stack piles. This is the heart of mergesort. 
    	for(int n = lo; n <= hi; n++){
    		if(i > left && j > right){ a[n] = aux[k++];}
    		else if(i > left && k > hi){ a[n] = aux[j++];}
    		else if(j > right && k > hi){ a[n] = aux[i++];}
    		else if(less(aux[i],aux[j]) && less(aux[i],aux[k])){ a[n] = aux[i++];}
    		else if(less(aux[j],aux[i]) && less(aux[j],aux[k])){ a[n] = aux[j++];}
    		else if(less(aux[k],aux[i]) && less(aux[k],aux[j])){ a[n] = aux[k++];}
    		else if(less(aux[i],aux[j]) || less(aux[i],aux[k])){ a[n] = aux[i++];}
    		else if(less(aux[j],aux[k]))                       { a[n] = aux[j++];}
    	}
    }
    

   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
    	less++;
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
    	exch++;
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    // print array to standard output a[lo..hi]
    private static void show(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            StdOut.println (a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; selection sorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        //String[] a = StdIn.readAllStrings();
        Comparable[] d = {1,5,6,3,2,8,9,7};
        MergeSortThreeWay.sort(d);
        show(d, 0, d.length-1);
    }
}
