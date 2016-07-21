package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    @Test
    public void testWithDrawer() {
        Transaction t=new Transaction(-10);
        assertTrue(t.amount==-10);
    }
    @Test
    public void testDeposit() {
        Transaction t=new Transaction(10);
        assertTrue(t.amount==10);
    }
}
