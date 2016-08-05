package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction transaction = new Transaction(5);      
        assertEquals(5, transaction.getAmount(), 0);
    }
}
