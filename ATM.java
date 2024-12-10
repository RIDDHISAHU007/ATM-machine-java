import java.util.Scanner;

class ATM {
    private double balance;
    private String[] transactions;
    private int transactionCount;
    private String pin;

    public ATM() {
        this.balance = 0;
        this.transactions = new String[100]; // assuming a maximum of 100 transactions
        this.transactionCount = 0;
    }

    // For PIN Creating
    public void createPin(String newPin) {
        this.pin = newPin;
        addTransaction("Your PIN is created");
        System.out.println("Your PIN created successfully.");
    }

    // For PIN check
    public boolean checkPin(String pin) {
        return pin.equals(this.pin);
    }

    // For Change PIN
    public void changePin(String newPin) {
        this.pin = newPin;
        addTransaction("Your PIN is changed");
        System.out.println("Your PIN changed successfully.");
    }

    // For Check Balance
    public void checkBalance() {
        System.out.println("Current balance in Your Account: $" + balance);
        addTransaction("Checked balance: $" + balance);
    }

    // For Deposite Amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited Amount in Your Account: $" + amount);
            addTransaction("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // For Withdrown Amount
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn Amount From Your Account: $" + amount);
            addTransaction("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    // For Showing Transaction
    public void showTransactions() {
        System.out.println("Transaction history:");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactions[i]);
        }
    }

    // For Check How Many Transaction
    private void addTransaction(String transaction) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = transaction;
        } else {
            System.out.println("Transaction history is full.");
        }
    }

    // Main Class
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Create your PIN
        System.out.println("Welcome to the ATM Machine.");
        System.out.print("Create your PIN: ");
        String newPin = scanner.nextLine();
        atm.createPin(newPin);

        // Enter The PIN to verify
        System.out.print("Enter your PIN to proceed: ");
        String enteredPin = scanner.nextLine();

        if (atm.checkPin(enteredPin)) {
            while (!exit) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Balance Inquiry");
                System.out.println("2. Cash Deposit");
                System.out.println("3. Cash Withdrawal");
                System.out.println("4. Change PIN");
                System.out.println("5. Transaction History");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        String newPinChange = scanner.nextLine();
                        atm.changePin(newPinChange);
                        break;
                    case 5:
                        atm.showTransactions();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("\nThank you for using the ATM.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid PIN. Exiting...");
        }

        scanner.close();
    }
}