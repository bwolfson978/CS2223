package bwolfson;

import java.util.LinkedList;

import edu.princeton.cs.algs4.StdOut;

// Several questions in HW4 assume you start with the following BST.

public class BST<Key extends Comparable<Key>> {

	Node root;    // root of the tree
	static int inspectedElementCount;

	class Node {
		Key    key;        
		Node   left, right;  // left and right subtrees

		public Node(Key key) {
			this.key = key;
		}

		/** Helpful debugging method. */
		public String toString() { return "[" + key + "]"; }
	}

	/** Default constructor still used for empty BST. Leave as is. */
	public BST () { }
	
	/** 
	 * Given an ordered array of keys in ascending order of length 2^k-1, construct a perfectly 
	 * balanced BST. 
	 *  
	 * @param keys    keys are in ascending order
	 */
	public BST(Key[] keys) {
		root = new Node(keys[keys.length/2]); //root node is always middle of array
		int distance = (keys.length + 1) / 2; //difference of first two children index numbers
		
		while(distance != 1){
			LinkedList<Key> level = new LinkedList<Key>();
			for(int i = (distance/2 - 1); i < keys.length; i += distance){
				level.add(keys[i]);
			}
			addLevel(level);
			distance /= 2;
		}
	}
	
	public void addLevel(LinkedList<Key> keys){
		while(!keys.isEmpty()){
			insert(root,keys.pop());
		}
	}
	
	public int height() { 
		return height(root);
	}
	
	public int height(Node n) {
		if (n == null) {return -1;}
		return greater(height(n.left),height(n.right)) + 1;
	}
	
	public int greater(int x, int y){
		if (x >= y) return x;
		return y;
	}
	
	public BST<Key> copy(){
		BST<Key> bst = new BST<Key>();
		bst.root = new Node(root.key);
		bst.root.left = copy(root.left);
		bst.root.right = copy(root.right);
		return bst;
	}
	
	private Node copy(Node n){
		if (n == null) return null;
		Node copy = new Node(n.key);
		copy.left = copy(n.left);
		copy.right = copy(n.right);
		return copy;
	}
	
	public boolean identicalStructure(BST<Key> bst){
		return identicalStructure(bst.root, root);
	}
	
	public boolean identicalStructure(Node one, Node two){
		if(one == null && two != null){
			return false;
		}
		if(one != null && two == null){
			return false;
		}
		if(one == null && two == null){
			return true;
		}
		
		return identicalStructure(one.left,two.left) && identicalStructure(one.right,two.right);
	}
	
	public boolean mirrorImage(BST<Key> bst){
		return mirrorImage(bst.root, root);
	}
	
	public boolean mirrorImage(Node one, Node two){
		if(one == null && two != null){
			return false;
		}
		if(one != null && two == null){
			return false;
		}
		if (one == null && two == null){
			return true;
		}
		
		return mirrorImage(one.left,two.right) && mirrorImage(one.right,two.left);
		
	}
	public boolean isEmpty() { return root == null; }

	// One-line method for containment. 
	public boolean contains(Key key) { return get(root, key); }

	private boolean get(Node parent, Key key) {
		if (parent == null) return false;

		int cmp = key.compareTo(parent.key);

		if      (cmp < 0) return get(parent.left, key);
		else if (cmp > 0) return get(parent.right, key);
		else              return true;
	}

	/** Insert key into BST. */
	public void insert(Key key) {
		root = insert(root, key);
	}

	private Node insert(Node parent, Key key) {
		if (parent == null) return new Node(key);

		int cmp = key.compareTo(parent.key);
		if (cmp <= 0) {
			parent.left  = insert(parent.left,  key);
		} else {
			parent.right = insert(parent.right, key);
		}

		return parent;
	}

	public Key min() { return min(root).key; }

	private Node min (Node parent) {
		if (parent.left == null) { return parent; }
		return min(parent.left);
	}

	public Key nonRecursiveMin() {
		Node n = root;

		while (n.left != null) {
			n = n.left;
		}

		return n.key;
	}

	public Key floor(Key key) {
		Node rc = floor(root, key);
		if (rc == null) return null;
		return rc.key;
	} 

	private Node floor(Node parent, Key key) {
		if (parent == null) return null;

		int cmp = key.compareTo(parent.key);
		if (cmp == 0) return parent;                   // found? Then this is floor
		if (cmp <  0) return floor(parent.left, key);  // smaller? must be in left subtree

		Node t = floor(parent.right, key);             // greater? we might be floor, but
		if (t != null) return t;                       // only if 
		else return parent; 
	} 

	// traversal ideas
	// invoke an inorder traversal of the tree
	public void inorder() { inorder(root); }
	private void inorder(Node n) {
		if (n != null) {
			inorder (n.left);
			StdOut.println (n.key);
			inorder (n.right);
		}
	}

	// traversal ideas
	// invoke a pre-order traversal of the tree
	public void preorder() { preorder(root); }
	private void preorder(Node n) {
		if (n != null) {
			StdOut.println (n.key);

			preorder (n.left);
			preorder (n.right);
		}
	}

	/** Implement method to return Value when removing largest element. */
	public void deleteMin() {
		if (root != null) { root = deleteMin(root);	}
	}

	Node deleteMin(Node parent) {
		if (parent.left == null) {
			return parent.right;
		}
		
		parent.left = deleteMin(parent.left);
		return parent;
	}

	// new methods for discussion
	public Key max() { return max(root).key; }

	private Node max (Node parent) {
		if (parent.right == null) { return parent; }
		return max(parent.right);
	}
	
	public void deleteMax(){
		if (root != null){root = deleteMax(root);}
	}
	
	Node deleteMax(Node parent) {
		inspectedElementCount++;
		if (parent.right == null) {
			return parent.left;
		}

		parent.right = deleteMax(parent.right);
		return parent;
	}
	

	public void delete(Key key) { root = delete(root, key); }

	private Node delete(Node parent, Key key) {
		if (parent == null) return null;

		// recurse until you find parent with this key.
		int cmp = key.compareTo(parent.key);
		if      (cmp < 0) parent.left  = delete(parent.left,  key);
		else if (cmp > 0) parent.right = delete(parent.right, key);
		else { 
			// handle easy cases first:
			if (parent.right == null) return parent.left;
			if (parent.left  == null) return parent.right;

			// has two children: Plan on returning min of our right child
			Node old = parent;
			parent = min(old.right);     // will eventually be "new parent"

			// Note this is a simpler case: Delete min from right subtree
			// and DON'T FORGET to stitch back in the original left child
			parent.right = deleteMin(old.right);   
			parent.left = old.left;
		} 

		// as recursions unwind, pass back potential new parent
		return parent;
	}

	

}
