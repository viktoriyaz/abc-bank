package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class AccountTest {

	@Test
	public void testCheckingAccount() {
		final Account account = new CheckingAccount();
		
		assertEquals("Checking Account", account.getAccountType().getDisplayName());
	}
	
	@Test
	public void testSavingsAccount() {
		final Account account = new SavingsAccount();
		
		assertEquals("Savings Account", account.getAccountType().getDisplayName());
		
	}
	
	@Test
	public void testMaxiSavingsAccount() {
		final Account account = new MaxiSavingsAccount();
		
		assertEquals("Maxi Savings Account", account.getAccountType().getDisplayName());
		
	}
	
	@Test
	public void testDeposit() {
		final Account account = new CheckingAccount();
		account.deposit(100);
		
		assertEquals(100, account.getTotal(), 0);
	}
	
	@Test
	public void testWithdraw() {
		final Account account = new SavingsAccount();
		account.deposit(300);
		account.withdraw(100);
		
		assertEquals(200, account.getTotal(), 0);
		
	}

	@Test
	public void testTransactionCount() {
		final Account account = new MaxiSavingsAccount();
		account.deposit(1000);
		account.deposit(25);
		account.withdraw(50);
		
		assertEquals(3, account.getAllTransactions().size());
	}

	@Test
	public void testAccountTotal() {
		final Account account = new MaxiSavingsAccount();
		account.deposit(1000);
		account.deposit(25);
		account.withdraw(50);
		
		assertEquals(975, account.getTotal(), 0);		
	}
	
	@Test
	public void testCheckingAccountInterestEarned() {
		final Account account = new CheckingAccount();
		account.deposit(2000);
		
		assertEquals(2, account.getInterestEarned(), 0);
	}

	@Test
	public void testSavingsAccountInterestEarned() {
		final Account account = new SavingsAccount();
		account.deposit(2000);
		
		assertEquals(3, account.getInterestEarned(), 0);
	}

	@Test
	public void testMaxiSavingsAccountInterestEarnedWithoutWithdrawal() {
		final Account account = new MaxiSavingsAccount();
		account.deposit(2000);
		
		assertEquals(100, account.getInterestEarned(), 0);
	}

	@Test
	public void testMaxiSavingsAccountInterestEarnedWithWithdrawal() {
		final Account account = new MaxiSavingsAccount();
		account.deposit(3000);
		account.withdraw(1000);
		
		assertEquals(2, account.getInterestEarned(), 0);
	}

}
