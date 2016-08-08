package com.abc.account;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import com.abc.ABCBankUtil;
import com.abc.ABCBankUtil.AccountType;
import com.abc.DateProvider;
import com.abc.Identified;
import com.abc.Transaction;

public abstract class Account implements Identified {
	
	private static final AtomicInteger seq = new AtomicInteger();
	
    private final int _id;  // must be unique for all accounts
	// TODO: more data structures could be added as needed, for example Map<Date, Collection<Transaction>> to group transactions by date for searching
    private final Collection<Transaction> _transactions = new CopyOnWriteArraySet<Transaction>();
    
    private int _runningTotal = 0;  // because who has more that Integer.MAX_VALUE on their accounts anyway? :)    
	private AccountType _accountType;

	protected Date _lastWithdrawalTimestamp = null;
    
    
    public Account(final AccountType accountType) {
        _accountType = accountType;
        _id = seq.incrementAndGet();
    }

    public abstract double getInterestEarned();

    public void deposit(double amount) {
        if (amount <= 0d) {
            throw new IllegalArgumentException("You are trying to deposit " + amount + ". Deposit amount must be greater than zero.");
        } 
        
		synchronized (ABCBankUtil.TRANSACTION_LOCK) {
            _transactions.add(new Transaction(amount)); // TODO: add proper logging
            _runningTotal += amount;
        }
    }

    public void withdraw(double amount) {
    	if (amount <= 0d) {
    		throw new IllegalArgumentException("You are trying to withdraw " + amount + ". Withdrawal amount must be greater than zero.");
    	} 
    	
		synchronized (ABCBankUtil.TRANSACTION_LOCK) {
			if (amount > _runningTotal) {
				throw new IllegalArgumentException("You are trying to withdraw " + amount + ". Withdrawal amount must be greater than your total of " + _runningTotal + " to avoid overdrawing.");    		
			} else {
				_transactions.add(new Transaction(0 - amount));  // TODO:  add proper logging
    			_runningTotal -= amount;
    			_lastWithdrawalTimestamp = DateProvider.getInstance().now();
    		}
    	}
    }

    public AccountType getAccountType() {
        return _accountType;
    }

    public double getTotal() {
    	return _runningTotal;
    }
    
    public Collection<Transaction> getAllTransactions() {
    	return Collections.unmodifiableSet(new HashSet<Transaction>(_transactions));
    }

	public int getId() {
		return _id;
	}
	
	@Override
	public boolean equals(final Object a) {
		if (!(a instanceof Account)) {
			return false;
		}
		
		final Account account = (Account)a; 
		return _id == account.getId();
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		return result;
	}
	
	@Override
	public String toString() {
		return String.valueOf(_id);
	}

}
