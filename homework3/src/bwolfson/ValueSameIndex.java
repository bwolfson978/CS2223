package bwolfson;

public class ValueSameIndex {
	/** 
	 * Given index values, lo and hi, which are inclusive to the array, a,
	 * return index such that a[idx] = idx.
	 * 
	 * If no such index exists, then return -1.
	 */
	public static int index (int[] a, int lo, int hi) {
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(a[mid] < mid){ lo = mid + 1; }
			else if(a[mid] > mid){ hi = mid - 1; }
			else return mid;
		}
		return -1;
	}
	
	public static void main(String[] args){
		int[] a = {-1,0,1,2,4,9,11,15};
		int[] b = {-2,-1,0,1,2,5,7,8};
		int[] c = {1,2,3,4,5,6,7,8};
		int indexA = index(a,0,a.length-1);
		System.out.println(indexA);
		int indexB = index(b,0,b.length-1);
		System.out.println(indexB);
		int indexC = index(c,0,c.length-1);
		System.out.println(indexC);
		
	}
}
