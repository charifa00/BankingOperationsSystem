package Banking.Account.src;

import Banking.Account.src.Banking.Account.BasicAccount;
import Banking.Account.src.Banking.Account.BusinessAccount;
import Banking.Account.src.Banking.Account.CheckingAccount;
import Banking.Account.src.Banking.Account.SavingsAccount;
import Banking.Account.src.Banking.CostumException.DepositException;
import Banking.Account.src.Banking.CostumException.WithdrawalException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    private HashMap<String, BasicAccount> accounts = new HashMap<>();
    private TextArea displayArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Ajouter des comptes par défaut
        accounts.put("12345", new BusinessAccount("12345", "John", "Doe", 500));
        accounts.put("67890", new SavingsAccount("67890", "Jane", "Smith", 1000));
        accounts.put("11223", new CheckingAccount("11223", "Alice", "Johnson", 300));

        // Interface principale
        primaryStage.setTitle("Gestion des Comptes Bancaires");

        // Champs pour l'utilisateur
        TextField accountNumberField = new TextField();
        accountNumberField.setPromptText("Numéro de compte");

        Button accessAccountButton = new Button("Accéder au compte");
        Button createAccountButton = new Button("Créer un compte");

        displayArea.setEditable(false);
        displayArea.setPrefHeight(200);

        VBox mainLayout = new VBox(10, accountNumberField, accessAccountButton, createAccountButton, displayArea);
        mainLayout.setPadding(new Insets(10));

        // Action : Accéder à un compte existant
        accessAccountButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            if (accounts.containsKey(accountNumber)) {
                openAccountMenu(primaryStage, accounts.get(accountNumber));
            } else {
                displayArea.setText("Compte introuvable !");
            }
        });

        // Action : Créer un nouveau compte
        createAccountButton.setOnAction(e -> openCreateAccountMenu(primaryStage));

        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openAccountMenu(Stage primaryStage, BasicAccount account) {
        primaryStage.setTitle("Compte : " + account.getAccountNumber());

        // Champs pour dépôt et retrait
        TextField amountField = new TextField();
        amountField.setPromptText("Montant");

        Button depositButton = new Button("Dépôt");
        Button withdrawButton = new Button("Retrait");
        Button balanceButton = new Button("Afficher Solde");
        Button backButton = new Button("Retour");

        // Layout
        VBox accountLayout = new VBox(10, amountField, depositButton, withdrawButton, balanceButton, backButton, displayArea);
        accountLayout.setPadding(new Insets(10));

        // Actions des boutons
        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                account.deposit(amount);
                displayArea.setText("Dépôt réussi ! Nouveau solde : " + account.getSolde());
            } catch (DepositException ex) {
                displayArea.setText("Erreur : " + ex.getMessage());
            } catch (NumberFormatException ex) {
                displayArea.setText("Montant invalide !");
            }
        });

        withdrawButton.setOnAction(e -> {
            try {
                int amount = Integer.parseInt(amountField.getText());
                account.withdrawal(amount);
                displayArea.setText("Retrait réussi ! Nouveau solde : " + account.getSolde());
            } catch (WithdrawalException ex) {
                displayArea.setText("Erreur : " + ex.getMessage());
            } catch (NumberFormatException ex) {
                displayArea.setText("Montant invalide !");
            }
        });

        balanceButton.setOnAction(e -> displayArea.setText("Solde actuel : " + account.getSolde()));

        backButton.setOnAction(e -> start(primaryStage));

        Scene accountScene = new Scene(accountLayout, 400, 300);
        primaryStage.setScene(accountScene);
    }

    private void openCreateAccountMenu(Stage primaryStage) {
        primaryStage.setTitle("Créer un nouveau compte");

        // Champs pour créer un compte
        TextField accountNumberField = new TextField();
        accountNumberField.setPromptText("Numéro de compte");

        TextField nameField = new TextField();
        nameField.setPromptText("Nom");

        TextField surnameField = new TextField();
        surnameField.setPromptText("Prénom");

        TextField balanceField = new TextField();
        balanceField.setPromptText("Solde initial");

        Button createButton = new Button("Créer");
        Button backButton = new Button("Retour");

        // Layout
        GridPane createAccountLayout = new GridPane();
        createAccountLayout.setHgap(10);
        createAccountLayout.setVgap(10);
        createAccountLayout.setPadding(new Insets(10));

        createAccountLayout.add(new Label("Numéro de compte :"), 0, 0);
        createAccountLayout.add(accountNumberField, 1, 0);
        createAccountLayout.add(new Label("Nom :"), 0, 1);
        createAccountLayout.add(nameField, 1, 1);
        createAccountLayout.add(new Label("Prénom :"), 0, 2);
        createAccountLayout.add(surnameField, 1, 2);
        createAccountLayout.add(new Label("Solde initial :"), 0, 3);
        createAccountLayout.add(balanceField, 1, 3);
        createAccountLayout.add(createButton, 0, 4);
        createAccountLayout.add(backButton, 1, 4);

        // Actions des boutons
        createButton.setOnAction(e -> {
            String accountNumber = accountNumberField.getText();
            String name = nameField.getText();
            String surname = surnameField.getText();
            try {
                double balance = Double.parseDouble(balanceField.getText());
                if (accounts.containsKey(accountNumber)) {
                    displayArea.setText("Le compte existe déjà !");
                } else {
                    accounts.put(accountNumber, new BasicAccount(accountNumber, name, surname, balance));
                    displayArea.setText("Compte créé avec succès !");
                    start(primaryStage);
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Solde invalide !");
            }
        });

        backButton.setOnAction(e -> start(primaryStage));

        Scene createAccountScene = new Scene(createAccountLayout, 400, 300);
        primaryStage.setScene(createAccountScene);
    }
}
