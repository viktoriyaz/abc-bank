package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.abc.account.Account;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        final Bank bank = new Bank();
        final Customer john = new Customer("John", "Doe", 12345);
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John Doe (1 account)", bank.printCustomerSummary());
    }

    @Test
    public void checkingAccount() {
        final Bank bank = new Bank();
        final Account checkingAccount = new CheckingAccount();
        final Customer bill = new Customer("Bill", "Gates", 23456);
        bill.openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savingsAccount() {
        final Bank bank = new Bank();
        final Account savingsAccount = new SavingsAccount();
        final Customer bill = new Customer("Bill", "Gates", 23456);
        bill.openAccount(savingsAccount);
        bank.addCustomer(bill);

        savingsAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountNoWithdrawal() {
        final Bank bank = new Bank();
        final Account maxiSavingsAccount = new MaxiSavingsAccount();
        final Customer bill = new Customer("Bill", "Gates", 23456);
        bill.openAccount(maxiSavingsAccount);
        bank.addCustomer(bill);

        maxiSavingsAccount.deposit(3000.0);

        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxiSavingsAccountWithWithdrawal() {
        final Bank bank = new Bank();
        final Account maxiSavingsAccount = new MaxiSavingsAccount();
        final Customer bill = new Customer("Bill", "Gates", 23456);
        bill.openAccount(maxiSavingsAccount);
        bank.addCustomer(bill);

        maxiSavingsAccount.deposit(4000.0);
        maxiSavingsAccount.withdraw(1000.0);

        assertEquals(3.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
