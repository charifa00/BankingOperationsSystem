package Banking.Account.src.Banking.Account;

import Banking.Account.src.Banking.CostumException.WithdrawalException;

public class BusinessAccount extends BasicAccount {
    private final double transactionFee = 10.0;

    public BusinessAccount(String accountNumber, String nom, String prenom, double solde) {
        super(accountNumber, nom, prenom, solde);
    }

    @Override
    public double withdrawal(int amount) throws WithdrawalException {
        double totalAmount = amount + transactionFee;
        if (totalAmount > getSolde()) {
            throw new WithdrawalException("Solde insuffisant pour effectuer ce retrait avec frais.");
        }
        setSolde(getSolde() - totalAmount);
        addtransaction().add("Retrait (Business) : -" + amount + " (Frais : -" + transactionFee + ")");
        return getSolde();
    }
}
