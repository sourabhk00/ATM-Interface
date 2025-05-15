import java.util.*;

public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static double balance = 1000.00; // Default balance
    private static List<String> transactionHistory = new ArrayList<>();
    private static int pin;
    private static String accountType;
    private static boolean isRegistered = false;

    public static void main(String[] args) {
        System.out.println("🏦 Welcome to the ATM Interface");

        registerUser(); // First-time registration
        if (authenticateUser()) {
            showMenu();
        } else {
            System.out.println("❌ Too many incorrect attempts. Exiting...");
        }
    }

    private static void registerUser() {
        System.out.println("\n🔐 Set up your ATM account:");

        // Set PIN
        System.out.print("Enter a 4-digit PIN: ");
        while (true) {
            pin = scanner.nextInt();
            if (String.valueOf(pin).length() == 4) {
                break;
            } else {
                System.out.print("PIN must be 4 digits. Try again: ");
            }
        }

        // Set account type
        scanner.nextLine(); // Consume newline
        System.out.print("Enter account type (Savings / Current): ");
        while (true) {
            accountType = scanner.nextLine().trim().toLowerCase();
            if (accountType.equals("savings") || accountType.equals("current")) {
                accountType = accountType.substring(0, 1).toUpperCase() + accountType.substring(1);
                break;
            } else {
                System.out.print("Invalid type. Enter 'Savings' or 'Current': ");
            }
        }

        isRegistered = true;
        System.out.println("✅ Registration complete!\n");
    }

    private static boolean authenticateUser() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN to login: ");
            int enteredPin = scanner.nextInt();
            if (enteredPin == pin) {
                return true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Try again.");
            }
        }
        return false;
    }

    private static void showMenu() {
        int choice;
        do {
            System.out.println("\n----- 🧾 ATM Menu -----");
            System.out.println("Account Type: " + accountType);
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Change ATM PIN");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    viewTransactionHistory();
                    break;
                case 5:
                    changePin();
                    break;
                case 6:
                    System.out.println("👋 Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 6);
    }

    private static void viewBalance() {
        System.out.printf("💰 Current Balance: ₹%.2f%n", balance);
    }

    private static void depositMoney() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("✅ Deposit successful.");
        } else {
            System.out.println("❌ Invalid amount.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("✅ Withdrawal successful.");
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    private static void viewTransactionHistory() {
        System.out.println("\n📜 Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println("- " + transaction);
            }
        }
    }

    private static void changePin() {
        System.out.print("Enter your current PIN: ");
        int currentPin = scanner.nextInt();
        if (currentPin == pin) {
            System.out.print("Enter new 4-digit PIN: ");
            int newPin = scanner.nextInt();
            if (String.valueOf(newPin).length() == 4) {
                pin = newPin;
                System.out.println("✅ PIN changed successfully!");
            } else {
                System.out.println("❌ New PIN must be 4 digits.");
            }
        } else {
            System.out.println("❌ Incorrect current PIN.");
        }
    }
}
