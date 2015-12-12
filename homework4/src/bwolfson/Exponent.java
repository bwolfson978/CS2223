package bwolfson;

public class Exponent extends BinaryOperatorNode{


	public Exponent(String op) {
		super(op);
	}

	public double value() {
		return Math.pow(left.value(), right.value());
	}

}
