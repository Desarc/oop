package oving6;

import java.util.ArrayList;

public class AccountOverview {
	
	private ArrayList<Account> accounts;
	
	public AccountOverview() {
		accounts = new ArrayList<Account>();
	}
	
	public int getAccountCount() {
		return accounts.size();
	}
	
	public Account getAccount(int p) {
		if (p < getAccountCount()) {
			return accounts.get(p); 
		}
		return null;
	}
	
	public void addAccount(Account account) {
		if (!accounts.contains(account)) {
			accounts.add(account);
		}
	}
	
	public int getTotalBalance() {
		int total = 0;
		for (Account account : accounts) {
			total += account.getBalance();
		}
		return total;
	}
	
	public int getTotalCredit() {
		int total = 0;
		for (Account account : accounts) {
			total += account.getCredit();
		}
		return total;
	}
	
	public int getTotalFees() {
		int total = 0;
		for (Account account : accounts) {
			if (account instanceof CreditAccount) {
				total += ((CreditAccount)account).getFees();
			}
		}
		return total;
	}
	
	
}
