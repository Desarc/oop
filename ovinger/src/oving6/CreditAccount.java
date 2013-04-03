package oving6;

public class CreditAccount implements Account {

	private int balance, credit, fees, fee;
	
	public CreditAccount(int credit) {
		balance = 0;
		fees = 0;
		fee = 50;
		this.credit = credit;
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
		else if (amount >= 0 && amount+fee <= balance+credit) {
			balance -= amount+fee;
			fees += fee;
			return amount;
		}
		return 0;
	}
	
	public int getFees() {
		return fees;
	}

}
