package bwolfson;

public class BalancedTreeTest {

	public static void main(String[] args){
		Comparable[] a1 = new Comparable[3];
		Comparable[] a2 = new Comparable[7];
		Comparable[] a3 = new Comparable[15];
		Comparable[] a4 = new Comparable[31];
		Comparable[] a5 = new Comparable[63];
		Comparable[] a6 = new Comparable[127];
		Comparable[] a7 = new Comparable[255];
		Comparable[] a8 = new Comparable[511];
		Comparable[] a9 = new Comparable[1023];
		Comparable[] a10 = new Comparable[2047];

		//tree 1
		for(int i = 0; i < a1.length; i++){
			a1[i] = i + 1;
		}
		BST tree1 = new BST(a1);
		System.out.println("Height of tree 1: " + tree1.height());
		tree1.inorder();
		
		//tree 2
		for(int i = 0; i < a2.length; i++){
			a2[i] = i + 1;
		}
		BST tree2 = new BST(a2);
		System.out.println("Height of tree 2: " + tree2.height());
		tree2.inorder();
		
		//tree 3
		for(int i = 0; i < a3.length; i++){
			a3[i] = i + 1;
		}
		BST tree3 = new BST(a3);
		System.out.println("Height of tree 3: " + tree3.height());
		tree3.inorder();
		
		//tree 4
		for(int i = 0; i < a4.length; i++){
			a4[i] = i + 1;
		}
		BST tree4 = new BST(a4);
		System.out.println("Height of tree 4: " + tree4.height());
		tree4.inorder();
		
		//tree 5
		for(int i = 0; i < a5.length; i++){
			a5[i] = i + 1;
		}
		BST tree5 = new BST(a5);
		System.out.println("Height of tree 5: " + tree5.height());
		tree5.inorder();
		
		//tree 6
		for(int i = 0; i < a6.length; i++){
			a6[i] = i + 1;
		}
		BST tree6 = new BST(a6);
		System.out.println("Height of tree 6: " + tree6.height());
		tree6.inorder();
		
		//tree 7
		for(int i = 0; i < a7.length; i++){
			a7[i] = i + 1;
		}
		BST tree7 = new BST(a7);
		System.out.println("Height of tree 7: " + tree7.height());
		tree7.inorder();
		
		//tree 8
		for(int i = 0; i < a8.length; i++){
			a8[i] = i + 1;
		}
		BST tree8 = new BST(a8);
		System.out.println("Height of tree 8: " + tree8.height());
		tree8.inorder();
		
		//tree 9
		for(int i = 0; i < a9.length; i++){
			a9[i] = i + 1;
		}
		BST tree9 = new BST(a9);
		System.out.println("Height of tree 9: " + tree9.height());
		tree9.inorder();
		
		//tree 10
		for(int i = 0; i < a10.length; i++){
			a10[i] = i + 1;
		}
		BST tree10 = new BST(a10);
		System.out.println("Height of tree 10: " + tree10.height());
		tree10.inorder();
	}
}
