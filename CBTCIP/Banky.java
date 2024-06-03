import java.util.HashMap;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ". New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void transfer(Account toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred " + amount + " to account " + toAccount.getAccountNumber());
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }
}

class Bank {
    private HashMap<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            Account newAccount = new Account(accountNumber);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account with this number already exists.");
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

public class Banky {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    bank.createAccount(accountNumber);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    Account depositAccount = bank.getAccount(accountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    Account withdrawAccount = bank.getAccount(accountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter your account number: ");
                    String fromAccountNumber = scanner.nextLine();
                    System.out.print("Enter recipient's account number: ");
                    String toAccountNumber = scanner.nextLine();
                    Account fromAccount = bank.getAccount(fromAccountNumber);
                    Account toAccount = bank.getAccount(toAccountNumber);
                    if (fromAccount != null && toAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        fromAccount.transfer(toAccount, transferAmount);
                    } else {
                        System.out.println("One or both accounts not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
