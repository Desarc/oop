package oving2;

import java.util.Stack;


import acm.program.ConsoleProgram;

public class ConsoleRPN extends ConsoleProgram {
	
	Stack<Double> numbers;
	String[] validOperators = {"+", "-", "*", "/", ".", ",", "||", "swap", "%", "sqrt", "²", "flr", "pow", "ceil", "rnd", "sgn", "!", "e", "pi" };
	
	public void init() {
		numbers = new Stack<Double>();
	}
	
	public void run() {
		getInput();
		
	}
	
	public void getInput() {
		String input;
		String validOp = "\n";
		for (int i=0;i<validOperators.length;i++) {
			validOp += validOperators[i]+" ";
		}
		do {
			println(validOp);
			input = readLine();
			handleInput(input);
		}while(!input.equals("exit"));
	}
	private void handleInput(String input) {
		
		try {
			double number = Double.valueOf(input);
			numbers.add(number);
		} catch (NumberFormatException e) {
			boolean valid = false;
			for (int i=0;i<validOperators.length;i++) {
				if (input.equals(validOperators[i])) {
					valid = true;
					break;
				}
			}
			if (!valid) {
				println("Invalid input.");
				return;
			}
			if (input.equals("e")) {
				numbers.add(Math.E);
			}
			else if (input.equals("pi")) {
				numbers.add(Math.PI);
			}
			else if (numbers.size() == 0) {
				println("No numbers on stack.");
				return;
			}
			
			//the following are allowed with 1 operator
			double op = numbers.pop();
			if (input.equals("!")) {
				if (!isInteger(op)) {
					println("Can't faculty a non-integer.");
					numbers.add(op);
					printStack();
					return;
				}
				int n = (int)op;
				int res = 1;
				for (int i=1;i<=n;i++) {
					res *= i;
				}
				numbers.add((double)res);
			}
			else if (input.equals(",")) {
				numbers.add(op);
				numbers.add(op);
			}
			else if (input.equals("||")) {
				numbers.add(Math.abs(op));
			}
			else if (input.equals("sqrt")) {
				numbers.add(Math.sqrt(op));
			}
			else if (input.equals("²")) {
				numbers.add(op*op);
			}
			else if (input.equals("flr")) {
				numbers.add(Math.floor(op));
			}
			else if (input.equals("ceil")) {
				numbers.add(Math.ceil(op));
			}
			else if (input.equals("rnd")) {
				numbers.add((double)Math.round(op));
			}
			else if (input.equals("sgn")) {
				if (op < 0) {
					numbers.add(-1.0);
				}
				else if (op > 0) {
					numbers.add(1.0);
				}
				else {
					numbers.add(0.0);
				}
			}
			else if (input.equals(".")) {
				//do nothing more
			}
			else {
				numbers.add(op);
			}
			
			//the following needs two operators
			if (numbers.size() >= 1) {
				double op2 = numbers.pop();
				double op1 = numbers.pop();
				if (input.equals("+")) {
					numbers.add(op1+op2);
				}
				else if (input.equals("-")) {
					numbers.add(op1-op2);
				}
				else if (input.equals("*")) {
					numbers.add(op1*op2);
				}
				else if (input.equals("/")) {
					numbers.add(op1/op2);
				}
				else if (input.equals("swap")) {
					numbers.add(op2);
					numbers.add(op1);
				}
				else if (input.equals("%")) {
					if (!isInteger(op1) || !isInteger(op2)) {
						println("Can't modulo with non-integers.");
						numbers.add(op1);
						numbers.add(op2);
						printStack();
						return;
					}
					numbers.add(op1%op2);
				}
				else if (input.equals("pow")) {
					numbers.add(Math.pow(op1, op2));
				}
				else {
					numbers.add(op1);
					numbers.add(op2);
				}
			}
			else {
				println("Not enough numbers to complete operation.");
			}
		}
		printStack();
	}
	
	private boolean isInteger(double op) {
		return (op == Math.floor(op));
	}
	
	private void printStack() {
		println(numbers.toString());
	}
	
	
}
