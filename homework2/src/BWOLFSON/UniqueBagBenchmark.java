package BWOLFSON;

import edu.princeton.cs.algs4.*;

public class UniqueBagBenchmark {
	
	// if you go to larger n, then you will run out of Java Heap space
	public static void main(String[] args) {
		for (int n = 4; n <= 2097152; n *= 2) {
			
			// Create two bags of the integers [1, n]. Bag 'one' has all numbers
			// while bag two has odd numbers only.
			// Add the numbers in REVERSE ORDER otherwise this will take a LOOOOONG time.
			UniqueBag<Integer> one = new UniqueBag<Integer>(); //has all numbers
			UniqueBag<Integer> two = new UniqueBag<Integer>(); //odd numbers only
			for (int k = n; k >=1; k--) {
				one.add(k);
				if (k % 2 == 1) { two.add(k); }
			}
			/*
			while(one.first!=null){
				System.out.println(one.first.getItem());
				one.remove(one.first.getItem());
			}
			System.out.println();
			*/
			// at this point you can benchmark some operations:
			// size, identical, add, toArray, contains, remove, intersects, union
			// use StopWatch as you did before.
			
			//size
			Stopwatch sizeWatch = new Stopwatch();
			one.size();
			double sizeTime = sizeWatch.elapsedTime();
			//identical
			Stopwatch identicalWatch = new Stopwatch();
			one.identical(one);
			double identicalTime = identicalWatch.elapsedTime();
			//toArray
			Stopwatch arrayWatch = new Stopwatch();
			one.toArray();
			double arrayTime = arrayWatch.elapsedTime();
			//contains
			Stopwatch containsWatch = new Stopwatch();
			one.contains(new Integer(4));
			double containsTime = containsWatch.elapsedTime();
			//remove
			Stopwatch removeWatch = new Stopwatch();
			one.remove(new Integer(56));
			double removeTime = removeWatch.elapsedTime();
			//intersects
			Stopwatch intWatch = new Stopwatch();
			one.intersects(two);
			double intTime = intWatch.elapsedTime();
			//union			
			Stopwatch unionWatch = new Stopwatch();
			one.union(two);
			double unionTime = unionWatch.elapsedTime();
			
			System.out.println("size time: " + sizeTime);
			System.out.println("identical time: " + identicalTime);
			System.out.println("toArray time: " + arrayTime);
			System.out.println("contains time: " + containsTime);
			System.out.println("remove time: " + removeTime);
			System.out.println("intersection time: " + intTime);
			System.out.println("union time: " + unionTime);
		}
	}
}
