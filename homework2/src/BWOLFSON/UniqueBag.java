package BWOLFSON;

/**
 * This data type offers Bag-like behavior with the added constraint that all elements
 * stored are guaranteed to be unique within the bag, based on the equals() method.
 *
 * In all of the performance specifications, N refers to the number of items in the 
 * UniqueBag.
 * 
 * Once you complete this implementation, you will need to provide empirical evidence 
 * to support the performance specifications of each method.
 * 
 * CHANGE LOG:
 * 1. Added logic that each Item must extend Comparable<Item>. This means that you can
 *    fully compare items with each other
 *    
 * 2. Added default constructor so you can create an empty bag more easily.
 * 
 * 3. Cleaned up the description of the Node inner class, which doesn't need generics
 *    because the outer class provides this.
 *    
 * 4. Added remove method, which I somehow had forgotten to include in the template. You need
 *    to implement this (as a complement to add).
 * 
 * 
 * Final Comments:
 * 
 * 1. To receive maximum points you are to implement all methods without using the existing
 *    java.util.* classes that would otherwise be useful. The point of this programming exercise
 *    is to gain experience in working with linked-list structures where the focus is on achieving
 *    the highest performance of the code.
 * 
 * @param <Item>
 */
public class UniqueBag<Item extends Comparable<Item>> {

	Node first; //beginnning of the bag
	private int N;
	
	/** You must use this Node class as part of a LinkedList to store the UniqueBag items. */
	class Node {
		private Item   item;
		private Node   next;
		public Item getItem(){
			return this.item;
		}
	}

	/** Default constructor to create an empty initial bag. */
	public UniqueBag() {
		this.N = 0;
		this.first = null;
	}
	
	/**
	 * Initialize the bag to contain the unique elements found in the initial list.
	 * 
	 * Performance must be dependent of the number of items in initial, or ~ N.
	 */
	public UniqueBag(Item[] initial) {
		for(int i = 0; i < initial.length; i++){
			this.add(initial[i]);
		}
	}
	
	/** 
	 * Return the number of items in the UniqueBag.
	 * 
	 * Performance must be independent of the number of items in the UniqueBag, or ~ 1.
	 */
	public int size() {
		return N;
	}

	/** 
	 * Determines equality with another UniqueBag objects.
	 * 
	 * Performance must be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean identical (UniqueBag other) {
		Comparable[] a = this.toArray();
		Comparable[] b = other.toArray();
		if(a.length != b.length){
			return false;
		}
		for(int i = 0; i < a.length; i++){
			if(a[i]!=b[i]){
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * Return an array that contains the items from the UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public Item[] toArray() {
		Item[] a = (Item[]) new Comparable[this.size()];
		System.out.println("this is the size: " + this.size());
		int counter = 0;
		while(first!=null){
			Node n = first;
			a[counter] = n.item;
			this.remove(first.item);
			counter++;
		}
		return a;
	}

	/** 
	 * Add an item to the UniqueBag; return false if already contained.
	 * 
	 * Performance can be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean add (Item it){
		if(this.contains(it)){
			return false;
		}
		this.N++;
		Node oldfirst = first;
		first = new Node();
		first.item = it;
		first.next = oldfirst;
		return true;
	}
	
	/** 
	 * Remove an item to the UniqueBag; return false if not contained within, true on success.
	 * 
	 * Performance can be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean remove (Item it){
		if(first!=null){
			first = first.next;
			return true;
		}
		return false;
	}

	/** 
	 * Determine whether the item is contained by the UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in the UniqueBag, or ~ N.
	 */
	public boolean contains(Item it) {
		boolean contains = false;
		UniqueBag<Item> u = new UniqueBag<Item>();
		while(this.first!=null){
			if(first.item == it){
				contains = true;
			}
			u.add(first.item);
			this.remove(first.item);
		}
		this.first = u.first;
		return contains;
	}

	/** 
	 * Return a UniqueBag which represents intersection with existing UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in both UniqueBag 
	 * objects, or in otherwords ~ M + N where M is the number of items in other and N
	 * is the number of items in this UniqueBag.
	 */
	public UniqueBag<Item> intersects(UniqueBag<Item> other) {
		UniqueBag<Item> intersect = new UniqueBag<Item>();
		UniqueBag<Item> u = new UniqueBag<Item>();
		while(this.first!=null){
			if(this.contains(first.item)&&other.contains(first.item)){
				intersect.add(first.item);
			}
			u.add(first.item);
			remove(first.item);
		}
		this.first = u.first;
		return intersect;
	}

	/** 
	 * Return a UniqueBag which represents union with existing UniqueBag.
	 * 
	 * Performance must be linearly dependent on the number of items in both UniqueBag 
	 * objects, or in otherwords ~ M + N where M is the number of items in other and N
	 * is the number of items in this UniqueBag.
	 */
	public UniqueBag<Item> union(UniqueBag<Item> other) {
		UniqueBag<Item> union = new UniqueBag<Item>();
		UniqueBag<Item> u = new UniqueBag<Item>();
		UniqueBag<Item> newOther = new UniqueBag<Item>();
		while(this.first!=null){
			if(this.contains(first.item)||other.contains(first.item)){
				union.add(first.item);
			}
			u.add(first.item);
			remove(first.item);
		}
		this.first = u.first;
		//check for items in other that weren't in this UniqueBag
		while(other.first!=null){
			if(!union.contains(other.first.item)){
				union.add(other.first.item);
			}
			newOther.add(other.first.item);
			other.remove(other.first.item);
		}
		other.first = newOther.first;
		return union;
	}


}
