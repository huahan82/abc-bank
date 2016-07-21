package com.abc;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Ignore
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test
    public void maxi_savings_account_interest() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        //Transaction transaction = new Transaction(100);
       // transaction.
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);
        assertEquals(150.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    @Test
    public void maxi_savings_account_withdraw_interest() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);
        checkingAccount.withdraw(1000.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
        @Test
    public void maxi_savings_account_withdraw_interest2() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);
        Transaction transaction = new Transaction(-1000.0);
        Calendar calendar = Calendar.getInstance();
        Date date = transaction.transactionDate;
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -11);
        long milliseconds = calendar.getTime().getTime();
        transaction.transactionDate.setTime(milliseconds);
        checkingAccount.transactions.add(transaction);
       // checkingAccount.withdraw(1000.0);

        assertEquals(100.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
  @Test
    public void maxi_savings_account_withdraw_interest3() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);
        Transaction transaction = new Transaction(-1000.0);
        Calendar calendar = Calendar.getInstance();
        Date date = transaction.transactionDate;
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        long milliseconds = calendar.getTime().getTime();
        transaction.transactionDate.setTime(milliseconds);
        checkingAccount.transactions.add(transaction);
       // checkingAccount.withdraw(1000.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
