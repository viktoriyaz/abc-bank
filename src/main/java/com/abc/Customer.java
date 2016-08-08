package com.abc;

import static java.lang.Math.abs;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

import com.abc.account.Account;

public class Customer implements Identified {
	
	private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();  // using LOCALE to default to USD 
	
    private final String _firstName, _lastName;
    private final int _ssn;  // unique ID
    private final Set<Account> _accounts = new HashSet<Account>();

    // null and empty string checks for first and last name should be handled as system input verification, prior to this step
    public Customer(final String firstName, final String lastName, final int ssn) {
        _firstName = firstName;
        _lastName = lastName;
        _ssn = ssn;  
    }

    public String getFirstName() {
        return _firstName;
    }
    
    public String getLastName() {
    	return _lastName;
    }
    
    public String getFullName() {
    	return _firstName + ABCBankUtil.SPACE + _lastName;  
    }
    
    public int getSSN() {
    	return _ssn;
    }

    // better to return a boolean to handle openAccount failures in the future
    public boolean openAccount(final Account account) {
    	if (!_accounts.contains(account)) {
    		_accounts.add(account);
    	}
    	
    	// TODO: handle error of trying to add an existing account appropriately
    	
        return true;
    }

    public int getNumberOfAccounts() {
        return _accounts.size();
    }
    
    public void transfer(final Account fromAccount, final Account toAccount, double amount) {
		synchronized(ABCBankUtil.TRANSACTION_LOCK) {
			if (canTransfer(fromAccount, toAccount, amount)) {
    			fromAccount.withdraw(amount);  // TODO: add proper logging here
    			toAccount.deposit(amount);
    		}
    	}
    }

    public double getTotalInterestEarned() {
        double total = 0d;
        for (final Account account : _accounts) {
            total += account.getInterestEarned();
        }
        return total;
    }

    // TODO: this should convert to reporting framework 
    public String getStatement() {
        final StringBuffer totalStatementBuffer = new StringBuffer();
        totalStatementBuffer.append(ABCBankUtil.STATEMENT_FOR).append(ABCBankUtil.SPACE).append(getFullName()).append(ABCBankUtil.BREAK);
        double total = 0d;
        for (final Account account : _accounts) {
            totalStatementBuffer.append(ABCBankUtil.BREAK).append(statementForAccount(account)).append(ABCBankUtil.BREAK);
            total += account.getTotal();
        }
        totalStatementBuffer.append(ABCBankUtil.BREAK).append(ABCBankUtil.TOTAL_ALL_ACCOUNTS).append(ABCBankUtil.SPACE).append(toDollars(total));
        return totalStatementBuffer.toString().trim();
    }

    // TODO: this should convert to reporting framework
    private String statementForAccount(final Account account) {
        final StringBuffer accountStatementBuffer = new StringBuffer(account.getAccountType().getDisplayName());
        accountStatementBuffer.append(ABCBankUtil.BREAK);

        for (final Transaction transaction : account.getAllTransactions()) {
        	accountStatementBuffer.append(ABCBankUtil.SPACE).append(ABCBankUtil.SPACE).append(transaction.getType()).append(ABCBankUtil.SPACE);
        	accountStatementBuffer.append(toDollars(transaction.getAmount())).append(ABCBankUtil.BREAK);
        }
        accountStatementBuffer.append(ABCBankUtil.TOTAL).append(ABCBankUtil.SPACE).append(toDollars(account.getTotal()));
        return accountStatementBuffer.toString().trim();
    }

    private boolean canTransfer(final Account fromAccount, final Account toAccount, double amount) {
    	if (getNumberOfAccounts() < 2) {
    		System.out.println("You do not have enough accounts for this operation"); // TODO: add proper logging or SNMP Trap or exception here
    		return false;
    	}
    	if (!_accounts.contains(fromAccount)) {
    		System.out.println("Account " + fromAccount + " does not belong to you, and you cannot withdraw from it"); // TODO: add proper logging or SNMP Trap or exception here
    		return false;
    	}
    	if (!_accounts.contains(toAccount)) {
    		System.out.println("Account " + toAccount + " does not belong to you, and you cannot deposit to it"); // TODO: add proper logging or SNMP Trap or exception here
    		return false;
    	}
    	
    	if (amount > fromAccount.getTotal()) {
    		System.out.println("Amount " + amount + " is larger than your total on the account.  Your account total is " + fromAccount.getTotal() + ". Please adjust your transfer amount to proceed");
    		return false;
    	}
    	
    	return true;
    }
    
    private String toDollars(double value){
    	return CURRENCY_FORMAT.format(abs(value));
    }
    
	public int getId() {
		return _ssn;
	}
	
	@Override
	public String toString() {
		return getFullName();
	}
	
	@Override
	public boolean equals(final Object c) {
		if (!(c instanceof Customer)) {
			return false;
		}
		
		final Customer customer = (Customer)c; 
		return _ssn == customer.getId();
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _ssn;
		return result;
	}
  
}
