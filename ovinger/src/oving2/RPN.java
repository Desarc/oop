package oving2;

import java.util.Stack;

public class RPN {
	
	private Stack<Double> numbers;
	
	public RPN() {
		numbers = new Stack<Double>();
	}
	
	public int getOperandCount() {
		return numbers.size();
	}
	
	public double peek(int n) {
		return numbers.elementAt(getOperandCount()-(n+1));
	}
	
	public void push(double value) {
		numbers.add(value);
	}
	
	public double pop(double defaultValue) {
		if (numbers.empty()) {
			return defaultValue;
		}
		return numbers.pop();
	}
	
	public void performOperation(char operator) {
		
		if (numbers.size() == 0) {
			return;
		}
		
		//the following are allowed with 1 operator
		double op = numbers.pop();
		if (operator == ',') {
			numbers.add(op);
			numbers.add(op);
		}
		else if (operator == '.') {
			//do nothing more
		}
		else {
			numbers.add(op);
		}
		
		//the following needs two operators
		if (numbers.size() >= 1) {
			double op2 = numbers.pop();
			double op1 = numbers.pop();
			if (operator == '+') {
				numbers.add(op1+op2);
			}
			else if (operator == '-') {
				numbers.add(op1-op2);
			}
			else if (operator == '*') {
				numbers.add(op1*op2);
			}
			else if (operator == '/') {
				numbers.add(op1/op2);
			}
		}
	}
}
