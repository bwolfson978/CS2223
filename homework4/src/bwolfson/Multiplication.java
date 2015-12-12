package bwolfson;

public class Multiplication extends BinaryOperatorNode {

	public Multiplication(String op) {
		super(op);
	}

	
	public double value() {
		return left.value() * right.value();
	}

}
