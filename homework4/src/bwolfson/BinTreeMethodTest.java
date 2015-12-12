package bwolfson;

public class BinTreeMethodTest {

	public static void main(String[] args) {
		
		
		//     2
		//   1   3
		//         4 
		//
		BST tree1 = new BST();
		tree1.insert(2);
		tree1.insert(1);
		tree1.insert(3);
		tree1.insert(4);
		
		//    3
		//   2  4
		//        5
		//
		BST tree2 = new BST();
		tree2.insert(3);
		tree2.insert(2);
		tree2.insert(4);
		tree2.insert(5);

		
		//   3
		//  2  6
		//      7
		//
		BST tree3 = new BST();
		tree3.insert(3);
		tree3.insert(2);
		tree3.insert(6);
		tree3.insert(7);
		
		//    4
		//   1  8
		// 0
		//
		BST tree4 = new BST();
		tree4.insert(4);
		tree4.insert(1);
		tree4.insert(0);
		tree4.insert(8);
		
		
		//first copy tree1 and print both to confirm they are the same
		BST copy = tree1.copy();
		tree1.inorder();
		copy.inorder();
		
		//tree one and tree two should be indentical structures
		System.out.println("Are tree one and tree two identical structures? " + tree1.identicalStructure(tree2));
		
		//tree three and tree four should be identical structures
		System.out.println("Are tree three and tree four mirror images? " + tree3.mirrorImage(tree4));

	}

}
