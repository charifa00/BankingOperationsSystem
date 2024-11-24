package Banking.Account;

import Banking.CostumException.DepositException;
import Banking.CostumException.WithdrawalException;
import Banking.Interfaces.Operations;

import java.util.ArrayList;

public class BasicAccount implements Operations {
    private String accountNumber;
    private String nom;
    private String prenom;
    private double solde;
    private ArrayList<String> transactions;

    public BasicAccount(String accountNumber, String nom, String prenom, double solde) {
        this.accountNumber = accountNumber;
        this.nom = nom;
        this.prenom = prenom;
        this.solde = solde;
        this.transactions = new ArrayList<>();
    }

    public BasicAccount() {
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public void deposit(double amount) throws DepositException {
        if (amount < 50) {
            throw new DepositException("Impossible d'effectuer cette transaction : dépôt minimum 50.");
        }
        solde += amount;
        transactions.add("Dépôt : +" + amount);
    }

    @Override
    public double withdrawal(int amount) throws WithdrawalException {
        if (amount > solde) {
            throw new WithdrawalException("Solde insuffisant pour effectuer ce retrait.");
        }
        solde -= amount;
        transactions.add("Retrait : -" + amount);
        return solde;
    }

    @Override
    public double Balanceretrieval(String accountNumber) {
        return solde;
    }

    @Override
    public ArrayList<String> addtransaction() {
        return transactions;
    }
}
