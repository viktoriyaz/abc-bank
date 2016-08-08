package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionTest {
    
	@Test
    public void testTransactionAmount() {
        final Transaction transaction = new Transaction(5);      
        
        assertEquals(5, transaction.getAmount(), 0);
    }
    
	@Test
	public void testTransactionTypeDeposit() {
    	final Transaction transaction = new Transaction(100);
    	
    	assertEquals("deposit", transaction.getType().getDisplayName());
    }

	@Test
    public void testTransactionTypeWithdrawal() {
    	final Transaction transaction = new Transaction(-100);
    	
    	assertEquals("withdrawal", transaction.getType().getDisplayName());
    }

}
