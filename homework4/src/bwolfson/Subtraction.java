package bwolfson;

public class Subtraction extends BinaryOperatorNode{

	public Subtraction(String op) {
		super(op);
	}

	public double value() {
		return left.value() - right.value();
	}

}
