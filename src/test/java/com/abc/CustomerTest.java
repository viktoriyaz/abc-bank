package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class CustomerTest {

    @Test
    public void testCustomerFirstName() {
        final Customer customer = new Customer("Oscar", "Wilde", 34567);
        
        assertEquals("Oscar", customer.getFirstName());
    }
    
    @Test
    public void testCustomerLastName() {
        final Customer customer = new Customer("Oscar", "Wilde", 34567);
        
        assertEquals("Wilde", customer.getLastName());
    }

    @Test
    public void testCustomerFullName() {
        final Customer customer = new Customer("Oscar", "Wilde", 34567);
        
        assertEquals("Oscar Wilde", customer.getFullName());
    }

    @Test
    public void testCustomerSSN() {
        final Customer customer = new Customer("Oscar", "Wilde", 34567);
        
        assertEquals(34567, customer.getSSN());
    }
    
    @Test
    public void testAccountCount(){
        final Customer customer = new Customer("Oscar", "Wilde", 34567);
        customer.openAccount(new SavingsAccount());
        customer.openAccount(new SavingsAccount());
        customer.openAccount(new CheckingAccount());

        assertEquals(3, customer.getNumberOfAccounts());
    }

    @Test
    public void testTransaction() {
        final Customer customer = new Customer("Oscar", "Wilde", 34567);
        final Account savingsAccount = new SavingsAccount();
        final Account checkingAccount = new CheckingAccount();
        
        customer.openAccount(savingsAccount);
        customer.openAccount(checkingAccount);
        savingsAccount.deposit(1000);
        customer.transfer(savingsAccount, checkingAccount, 100);

        assertEquals(900, savingsAccount.getTotal(), 0);
        assertEquals(100, checkingAccount.getTotal(), 0);
    }

    @Test
    public void testTotalInterestEarned() {
    	final Customer customer = new Customer("Henry", "Choo", 12343);
        final Account checkingAccount = new CheckingAccount();
        final Account savingsAccount = new SavingsAccount();
        final Account maxiSavingsAccount = new MaxiSavingsAccount();
        
        customer.openAccount(checkingAccount);
        customer.openAccount(savingsAccount);
        customer.openAccount(maxiSavingsAccount);
        
        checkingAccount.deposit(1000);  // 1 should be earned
        savingsAccount.deposit(5000);  // 1 + 2 x 4 = 9 should be earned
        maxiSavingsAccount.deposit(10000);  // 500 should be earned
        
        assertEquals(510, customer.getTotalInterestEarned(), 0);
    }
    
    @Test
    public void testCustomerStatement(){
        final Customer customer = new Customer("Henry", "Choo", 12343);
        final Account checkingAccount = new CheckingAccount();
        final Account savingsAccount = new SavingsAccount();

        customer.openAccount(checkingAccount);
        customer.openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry Choo\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", customer.getStatement());
    }
    
}
