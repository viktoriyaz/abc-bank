package com.abc;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class ABCBankExample {

	public static void main(String[] args) {

		final Bank abcBank = new Bank();
		final Customer firstCustomer = abcBank.getFirstCustomer();
		System.out.println( firstCustomer == null ? "This bank is not doing too well" : firstCustomer);
		System.out.println(abcBank.printCustomerSummary());
		System.out.println("___________________________\n");
		
		final Customer customer1 = new Customer("Will", "Smith", 123456789);
		final Account account1_1 = new CheckingAccount();
		customer1.openAccount(account1_1);
		account1_1.deposit(200);
		account1_1.withdraw(100);
		
		final Customer customer2 = new Customer("Bill", "Gates", 123344567);
		final Account account2_1 = new CheckingAccount();
		final Account account2_2 = new SavingsAccount();
		final Account account2_3 = new MaxiSavingsAccount();
		final Account account2_4 = new MaxiSavingsAccount();
		customer2.openAccount(account2_1);
		customer2.openAccount(account2_2);
		customer2.openAccount(account2_3);
		customer2.openAccount(account2_4);
		account2_1.deposit(1000);
		account2_2.deposit(2000);
		account2_3.deposit(4000);
		account2_4.deposit(3000);
		account2_1.withdraw(50);
		account2_3.withdraw(1000);
		
		customer2.transfer(account2_1, account2_2, 25);
		
		abcBank.addCustomer(customer1);
		abcBank.addCustomer(customer2);
		
		System.out.println("___________________________\n");

		System.out.println("First Customer is " + abcBank.getFirstCustomer());
		System.out.println(abcBank.printCustomerSummary());
		System.out.println("___________________________\n");
		
		System.out.println(customer1.getStatement());
		System.out.println("___________________________\n");

		System.out.println(customer2.getStatement());
		System.out.println("___________________________\n");

		System.out.println("With Withdrawal interest on total " + account2_3.getTotal() + " = " + account2_3.getInterestEarned());
		System.out.println("Without Withdrawal interest on total " + account2_3.getTotal() + " = " + account2_4.getInterestEarned());
		
	}

}
