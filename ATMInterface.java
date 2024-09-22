import java.util.Scanner;
import java.util.ArrayList;

class ATM {
    private double balance;
    private ArrayList<String> transactionHistory;
    private String userId;
    private String userPin;

    public ATM(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to check credentials
    public boolean login(String enteredId, String enteredPin) {
        return enteredId.equals(this.userId) && enteredPin.equals(this.userPin);
    }

    // Deposit method
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("\nDeposited: $" + amount);
        System.out.println("\nSuccessfully deposited: $" + amount);
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("\nWithdrew: $" + amount);
            System.out.println("\nSuccessfully withdrew: $" + amount);
        } else {
            System.out.println("\nInsufficient balance.");
        }
    }

    // Transfer method
    public void transfer(double amount, ATM recipient) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactionHistory.add("\nTransferred: $" + amount + " to " + recipient.getUserId());
            System.out.println("\nSuccessfully transferred: $" + amount);
        } else {
            System.out.println("\nInsufficient balance.");
        }
    }

    // Method to view transaction history
    public void viewTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Method to get user ID
    public String getUserId() {
        return userId;
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM("jasmin786", "1234"); // Predefined userId and pin

        System.out.println("\n\n\t\tWelcome to the ATM!");
        System.out.print("\nEnter User ID: ");
        String enteredId = scanner.nextLine();
        System.out.print("\nEnter PIN: ");
        String enteredPin = scanner.nextLine();

        // Check login
        if (atm.login(enteredId, enteredPin)) {
            boolean quit = false;

            while (!quit) {
                System.out.println("\nATM Menu:");
                System.out.println("\n1. View Transaction History");
                System.out.println("\n2. Withdraw");
                System.out.println("\n3. Deposit");
                System.out.println("\n4. Transfer");
                System.out.println("\n5. Quit");
                System.out.print("\nChoose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.viewTransactionHistory();
                        break;
                    case 2:
                        System.out.print("\nEnter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("\nEnter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("\nEnter amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        ATM recipient = new ATM("recipient123", "5678"); // A recipient for the transfer
                        atm.transfer(transferAmount, recipient);
                        break;
                    case 5:
                        quit = true;
                        System.out.println("\n\t\tThank you for using the ATM.\n\n\t\t Have a nice day!!\n\n");
                        break;
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                }
            }
        } else {
            System.out.println("\nInvalid User ID or PIN.");
        }

        scanner.close();
    }
}
