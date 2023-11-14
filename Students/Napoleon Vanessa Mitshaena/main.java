package lab5poo;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private int customerId;
    private String name;
    private String contactInfo;
    private List<BankAccount> accounts;

    public Customer(int customerId, String name, String contactInfo) {
        this.customerId = customerId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.accounts = new ArrayList<>();
    }

    public BankAccount createAccount(String accountType) {
        BankAccount account = new BankAccount(accountType);
        accounts.add(account);
        return account;
    }

    public void makeTransaction(BankAccount sourceAccount, BankAccount destinationAccount, String transactionType, double amount) {
        Transaction transaction = new Transaction(sourceAccount, destinationAccount, transactionType, amount);
        sourceAccount.addTransaction(transaction);
        if (destinationAccount != null) {
            destinationAccount.addTransaction(transaction);
        }
    }

    public void viewBalance(BankAccount account) {
        System.out.println("Customer: " + name);
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Balance: $" + account.getBalance());
    }
}

class BankAccount {
    private static int accountCounter = 0;

    private int accountNumber;
    private String accountType;
    private double balance;
    private List<Transaction> transactions;

    public BankAccount(String accountType) {
        this.accountNumber = ++accountCounter;
        this.accountType = accountType;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }
    
    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}   

    
    class Transaction {
        private static int transactionCounter = 0;

        private int transactionId;
        private BankAccount sourceAccount;
        private BankAccount destinationAccount;
        private String transactionType;
        private double amount;

        public Transaction(BankAccount sourceAccount, BankAccount destinationAccount, String transactionType, double amount) {
            this.transactionId = ++transactionCounter;
            this.sourceAccount = sourceAccount;
            this.destinationAccount = destinationAccount;
            this.transactionType = transactionType;
            this.amount = amount;
        }
    }

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Customer customer1 = new Customer(1, "John Doe", "john.doe@example.com");
	        Customer customer2 = new Customer(2, "Jane Smith", "jane.smith@example.com");

	        BankAccount account1 = customer1.createAccount("Savings");
	        BankAccount account2 = customer2.createAccount("Checking");

	        customer1.makeTransaction(account1, account2, "Transfer", 100.0);
	        customer2.makeTransaction(account2, account1, "Deposit", 50.0);

	        customer1.viewBalance(account1);
	        customer2.viewBalance(account2);

	}
}

