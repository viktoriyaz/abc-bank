package com.abc;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.abc.ABCBankUtil.TransactionType;

public class Transaction implements Identified {
	
	private static final AtomicInteger seq = new AtomicInteger();
    private final int _id;  // currently unique for all transactions, but could be unique for all transactions within an account
	private final double _amount;
    private final Date _transactionDate;
    private final TransactionType _type;
    
    public Transaction(double amount) {
        _amount = amount;
        _transactionDate = DateProvider.getInstance().now();
        _id = seq.incrementAndGet();
        _type = ( amount > 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAWAL);
    }
    
    public double getAmount() {
    	return _amount;
    }

    public Date getDate() {
    	return _transactionDate;
    }
    
    public TransactionType getType() {
    	return _type;
    }

	public int getId() {
		return _id;
	}
	
	@Override
	public boolean equals(final Object t) {
		if (!(t instanceof Transaction)) {
			return false;
		}
		
		final Transaction transaction = (Transaction)t; 
		return _id == transaction.getId();
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		return result;
	}
}
