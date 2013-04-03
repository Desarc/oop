package oving1;

import acm.program.*;

public class SimpleCalculator extends ConsoleProgram {
	
	private double op1, op2, result;
	
	public void init() {
		
	}
	
	public void run() {
		String operator;
		while (result < 1337) {
			op1 = readDouble("First operand: ");
			op2 = readDouble("Second operand: ");
			operator = readLine("Operator: ");
			performOperation(operator);
			println("The result of "+op1+" "+operator+" "+op2+" is "+result);
		}
		println("Result was over 1337!");
	}
	
	private void performOperation(String operator) {
		if (operator.equals("+")) {
			result = op1+op2;
		}
		else if (operator.equals("-")) {
			result = op1-op2;	
		}
		else if (operator.equals("*")) {
			result = op1*op2;	
		}
		else if (operator.equals("/")) {
			result = op1/op2;	
		}
		showErrorMessage("Illegal operator: "+operator);
		result = 0.0;
	}
}
