package bwolfson;

public class Division extends BinaryOperatorNode{

	public Division(String op) {
		super(op);
	}

	public double value() {
		return left.value() / right.value();
	}

}
