package Banking.Account;

import Banking.Interfaces.Transactions;

import java.util.ArrayList;

public class TransactionsAccount implements Transactions {
    private ArrayList<String> transactions = new ArrayList<>();

    public void addTransaction(String detail) {
        transactions.add(detail);
    }

    public void showTransactions() {
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
