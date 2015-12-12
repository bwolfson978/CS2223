package USERID.hw1;

import USERID.hw1.CrossingPoint;

public class ConfirmCrossingPoint {

	// change this to be a 5x7 array that confirms the maximum number
	// of array locations to be investigated. 
	public static void main(String[] args) {
		
		// CHANGE THIS
		boolean [][] ar =  {//33
				{ false, false, false, false, true, false, false, false },
				{ false, false, false, false, true, false, false, false },
				{ true,  true,  true,  true,  true, true,  true,  true },
				{ false, false, false, false, true, false, false, false },
		};
		boolean [][] ar2 =  {//35
				{ false, false, false, true, false, false, false},
				{ false, false, false, true, false, false, false},
				{ true,  true,  true,  true,  true, true,  true},
				{ false, false, false, true, false, false, false},
				{ false, false, false, true, false, false, false},
		};
		boolean [][] ar3 =  {//35
				{ true, true, true, true, true, true, true},
				{ false, false, false, false, false, false, true},
				{ false, false, false, false, false, false, true},
				{ false, false, false, false, false, false, true},
				{ false, false, false, false, false, false, true},
		};
		boolean [][] ar4 =  {//33
				{ true, true, true, true, true, true, true},
				{ false, false, false, false, true, false, false},
				{ false, false, false, false, true, false, false},
				{ false, false, false, false, true, false, false},
				{ false, false, false, false, true, false, false},
		};
		boolean [][] ar5 =  {//
				{ true, true, true, true, true, true, true},
				{ true, false, false, false, false, false, false},
				{ true, false, false, false, false, false, false},
				{ true, false, false, false, false, false, false},
				{ true, false, false, false, false, false, false},
		};
		
		// confirm
		new CrossingPoint().locate(ar4);
	}
}
