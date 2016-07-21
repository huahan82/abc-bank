package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");}
    if (amount > sumTransactions()) {
        throw new RuntimeException("No enough amount to withdraw");
    } else {
        transactions.add(new Transaction(-amount));
    }
}
    //check whether the count has a withdraw in 10 days
    public boolean withdrawedRecently() {
        Calendar calendar = Calendar.getInstance();
        Date date = DateProvider.getInstance().now();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        Date cut = calendar.getTime();
        for(int i=(transactions.size()-1);i>=0;i--) {
            Transaction transaction=transactions.get(i);
            if(transaction.transactionDate.before(cut)) break;
            if(transaction.amount<0)
            return true;
        }
        return false;
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
//                if (amount <= 1000)
//                    return amount * 0.02;
//                if (amount <= 2000)
//                    return 20 + (amount-1000) * 0.05;
//                return 70 + (amount-2000) * 0.1;
                if (!withdrawedRecently())
                    return amount * 0.05;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions) 
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

}
