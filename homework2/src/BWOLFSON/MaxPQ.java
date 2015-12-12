package BWOLFSON;

public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	static int delComparisons = 0;
	static int insertComparisons = 0;
	
	public MaxPQ(int maxN){
		pq = (Key[])new Comparable[maxN +1];
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	public void insert(Key v){
		if(N == pq.length){
			resize(2*pq.length);
		}
		pq[++N] = v;
		swim(N);
	}

	public Key delMax(){
		Key max = pq[1];
		exch(1, N--);
		pq[N+1]=null;
		sink(1);
		return max;
	}
	
	
	
	//////HELPER FUNCTIONS/////////
	private boolean less(int i, int j){
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j){
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	
	private void swim(int k){
		while(k > 1 && less(k/2, k)){
			insertComparisons++;
			exch(k/2,k);
			k =k/2;
		}
		if(k>1 && !less(k/2,k)){
			insertComparisons++;
		}
	}
	
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j < N){ //less will be called
				delComparisons++;
			}
			if(j < N && less(j,j+1)) j++;
			delComparisons++;
			if(!less(k,j))break;
			exch(k,j);
			k=j;
		}
	}
	
	private void resize(int max){
		Key[] temp = (Key[]) new Comparable[max];
		for(int i = 0; i < N; i++){
			temp[i] = pq[i];
		}
		pq = temp;
	}
}
