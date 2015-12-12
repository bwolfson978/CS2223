package bwolfson;

public class SquareRoot extends UnaryOperatorNode{

	public SquareRoot(String op) {
		super(op);
	}

	public double value() {
		return Math.sqrt(left.value());
	}

}
