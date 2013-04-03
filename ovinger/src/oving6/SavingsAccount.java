package oving6;

public class SavingsAccount implements Account {

	private int balance;
	private int credit;
	
	
	public SavingsAccount() {
		balance = 0;
		credit = 0;
	}
	
	@Override
	public int getBalance() {
		return balance;
	}

	@Override
	public int getCredit() {
		return credit;
	}

	@Override
	public int deposit(int amount) {
		if (amount >= 0) {
			balance += amount;
		}
		return balance;
	}

	@Override
	public int withdraw(int amount) {
		if (amount >= 0 && amount <= balance) {
			balance -= amount;
			return amount;
		}
		return 0;
	}

}
