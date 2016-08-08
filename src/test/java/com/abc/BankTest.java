package com.abc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class BankTest {

	@Test
	public void testCustomerCount() {
		final Bank bank = new Bank();
        final Customer oscarWilde = new Customer("Oscar", "Wilde", 34567);
        final Customer henryChoo = new Customer("Henry", "Choo", 12343);
        
        bank.addCustomer(oscarWilde);
        bank.addCustomer(henryChoo);
        
        assertEquals(2, bank.getAllCustomers().size());
	}
	
	@Test
	public void testFirstCustomerNewBank() {
		final Bank bank = new Bank();
		
		assertNull(bank.getFirstCustomer());
	}
	
	@Test
	public void testFirstCustomerEstablishedBank() {
		final Bank bank = new Bank();
        final Customer oscarWilde = new Customer("Oscar", "Wilde", 34567);
        final Customer henryChoo = new Customer("Henry", "Choo", 12343);
        
        bank.addCustomer(oscarWilde);
        bank.addCustomer(henryChoo);
		
		assertEquals("Oscar Wilde", bank.getFirstCustomer().toString());
	}

	@Test
    public void testTotalInterestPaid() {
        final Bank bank = new Bank();
        final Customer oscarWilde = new Customer("Oscar", "Wilde", 34567);
        final Customer henryChoo = new Customer("Henry", "Choo", 12343);
        final Customer billGates = new Customer("Bill", "Gates", 23456);
        
        bank.addCustomer(oscarWilde);
        bank.addCustomer(henryChoo);
        bank.addCustomer(billGates);
        
        final Account oscarWildeCheckingAccount = new CheckingAccount();
        final Account henryChooSavingsAccount = new SavingsAccount();
        final Account billGatesCheckingAccount = new CheckingAccount();
        final Account billGatesMaxiSavingsAccount = new MaxiSavingsAccount();

        oscarWilde.openAccount(oscarWildeCheckingAccount);
        henryChoo.openAccount(henryChooSavingsAccount);
        billGates.openAccount(billGatesCheckingAccount);
        billGates.openAccount(billGatesMaxiSavingsAccount);
        
        oscarWildeCheckingAccount.deposit(2000);  // 2
        henryChooSavingsAccount.deposit(2000);  // 3
        billGatesCheckingAccount.deposit(2000);  // 2
        billGatesMaxiSavingsAccount.deposit(2000);  // 100
        
        assertEquals(107, bank.getTotalInterestPaid(), 0);
    }

    @Test
    public void testCustomerSummary() {
        final Bank bank = new Bank();
        final Customer john = new Customer("John", "Doe", 12345);
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John Doe (1 account)", bank.printCustomerSummary());
    }

}
