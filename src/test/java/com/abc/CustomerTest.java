package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.SavingsAccount;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        final Account checkingAccount = new CheckingAccount();
        final Account savingsAccount = new SavingsAccount();

        final Customer henry = new Customer("Henry", "Choo", 12343);
        henry.openAccount(checkingAccount);
        henry.openAccount(savingsAccount);

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
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        final Customer oscar = new Customer("Oscar", "Wilde", 34567);
        oscar.openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        final Customer oscar = new Customer("Oscar", "Wilde", 34567);
        oscar.openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Ignore  // Ignoring this test
    public void testThreeAcounts() {
        final Customer oscar = new Customer("Oscar", "Wilde", 34567);
        oscar.openAccount(new SavingsAccount());
        oscar.openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testTransaction() {
        final Customer oscar = new Customer("Oscar", "Wilde", 34567);
        final Account savingsAccount = new SavingsAccount();
        final Account checkingAccount = new CheckingAccount();
        oscar.openAccount(savingsAccount);
        oscar.openAccount(checkingAccount);
        savingsAccount.deposit(1000);
        oscar.transfer(savingsAccount, checkingAccount, 100);
        assertEquals(900, savingsAccount.getTotal(), 0);
        assertEquals(100, checkingAccount.getTotal(), 0);
    }
}
