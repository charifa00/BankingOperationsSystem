package Banking.Account.src.Banking.Interfaces;

import Banking.Account.src.Banking.CostumException.DepositException;
import Banking.Account.src.Banking.CostumException.WithdrawalException;

import java.util.ArrayList;

public interface Operations {
    void deposit(double amount) throws DepositException;
    double withdrawal(int amount) throws WithdrawalException;
    double Balanceretrieval(String accountNumber);
    ArrayList<String> addtransaction();
}
