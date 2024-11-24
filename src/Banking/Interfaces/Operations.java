package Banking.Interfaces;

import Banking.CostumException.DepositException;
import Banking.CostumException.WithdrawalException;

import java.util.ArrayList;

public interface Operations {
    void deposit(double amount) throws DepositException;
    double withdrawal(int amount) throws WithdrawalException;
    double Balanceretrieval(String accountNumber);
    ArrayList<String> addtransaction();
}
