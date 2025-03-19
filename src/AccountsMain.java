import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountsMain {
    static HashMap<String, String> userAccounts = new HashMap<>();
    static HashMap<Integer, BankAccounts> bankAccountsMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isLoggedIn = false;

        System.out.println("Welcome to SLU's Computer Scienc Banking System!!");
        System.out.println("Please select an option\n");

        while (!isLoggedIn) {
            try {
                System.out.println("1. Create Account");
                System.out.println("2. Log In");
                System.out.println("3. Exit\n");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        isLoggedIn = login();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }

        int choice;
        do {
            try {
                System.out.println("\n1. Open Investment Account");
                System.out.println("2. Open Credit Card Account");
                System.out.println("3. Open Checking Account");
                System.out.println("4. Balance Inquiry");
                System.out.println("5. Deposit");
                System.out.println("6. Withdraw");
                System.out.println("7. Transfer Money");
                System.out.println("8. Account Information");
                System.out.println("9. Close Account");
                System.out.println("0. Exit\n");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        createInvestmentAccount();
                        break;
                    case 2:
                        createCreditCardAccount();
                        break;
                    case 3:
                        createCheckingAccount();
                        break;
                    case 4:
                        performBalanceInquiry();
                        break;
                    case 5:
                        performTransfer("deposit");
                        break;
                    case 6:
                        performTransfer("withdraw");
                        break;
                    case 7:
                        performTransfer();
                        break;
                    case 8:
                        displayAccountInfo();
                        break;
                    case 9:
                        closeAccount();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 0-9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    public static void performTransfer() {
        try {
            System.out.print("Enter Account Number: ");
            int accountNo = sc.nextInt();
            sc.nextLine();
            BankAccounts acc = findAccount(accountNo);

            if (acc == null) {
                System.out.println("Account not found. Please check the account number.");
                return;
            }
            System.out.print("Enter Amount:\n");
            double amount = validatePositiveDouble(); // Ensure positive input

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            sc.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred during the transaction: " + e.getMessage());
        }
    }


    public static void createAccount() {
        try {
            String fullName = "";
            while (fullName.isEmpty()) {
                System.out.print("\nEnter Full Name: ");
                fullName = sc.nextLine().trim();
                if (!fullName.matches("[a-zA-Z ]+")) {
                    System.out.println("Invalid name! Please enter only letters and spaces.");
                    fullName = ""; // Reset input
                }
            }

            String password = "";
            while (password.isEmpty()) {
                System.out.print("Enter a 6-digit PIN: ");
                password = sc.nextLine().trim();
                if (!password.matches("\\d{6}")) {
                    System.out.println("Invalid PIN! Enter a 6-digit number.");
                    password = ""; // Reset input
                }
            }
            // Display success message
            System.out.println("\nAccount created successfully!");
            System.out.println("Account Info:");
            System.out.println("Full Name: " + fullName);
            int accountNo = generateUniqueAccountNumber();
            System.out.println("Generated Account Number: " + accountNo + "\n");

            userAccounts.put(fullName.toLowerCase(), password); // Store name in lowercase for case-insensitive login
            bankAccountsMap.put(accountNo, new BankAccounts(accountNo, fullName));

        } catch (Exception e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }


    public static boolean login() {
        System.out.print("Enter Full Name: ");
        String fullName = sc.nextLine().toLowerCase();
        System.out.print("Enter PIN (6 digits): ");
        String password = sc.nextLine();

        if (userAccounts.containsKey(fullName) && userAccounts.get(fullName).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid name/PIN or account doesn't exist.");
            return false;
        }
    }

    public static void createInvestmentAccount() {
        int accountNo = generateUniqueAccountNumber();
        System.out.println("Generated Account Number: " + accountNo);
        System.out.print("Enter Account Name: ");
        String accountName = sc.nextLine();
        bankAccountsMap.put(accountNo, new InvestmentAccount(accountNo, accountName));
        System.out.println("Investment Account Created Successfully!");
    }

    public static void createCreditCardAccount() {
        int accountNo = generateUniqueAccountNumber();
        System.out.println("Generated Account Number: " + accountNo);
        System.out.print("Enter Account Name: ");
        String accountName = sc.nextLine();
        double creditLimit = 50000;
        System.out.print("Credit Limit: " + creditLimit);
        creditLimit = validatePositiveDouble();
        bankAccountsMap.put(accountNo, new CreditCardAccount(accountNo, accountName, creditLimit, 0));
        System.out.println("Credit Card Account Created Successfully!");
    }

    public static void createCheckingAccount() {
        int accountNo = generateUniqueAccountNumber();
        System.out.println("Generated Account Number: " + accountNo);
        System.out.print("Enter Account Name: ");
        String accountName = sc.nextLine();
        Double minimumBalance = null;
        System.out.println("Minimum balance: " + minimumBalance);
        minimumBalance = sc.nextDouble();
        bankAccountsMap.put(accountNo, new CheckingAccount(accountNo, accountName, minimumBalance));
        System.out.println("Checking Account Created Successfully!");
    }

    public static int generateUniqueAccountNumber() {
        Random rand = new Random();
        int accountNo;
        do {
            accountNo = 100000000 + rand.nextInt(900000000);
        } while (bankAccountsMap.containsKey(accountNo)); // Ensure unique account number
        return accountNo;
    }

    public static double validatePositiveDouble() {
        double value;
        while (true) {
            try {
                value = sc.nextDouble();
                if (value > 0) {
                    sc.nextLine();
                    return value;
                } else {
                    System.out.println("Amount must be greater than zero. Try again: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number: ");
                sc.nextLine();
            }
        }
    }

    public static void performBalanceInquiry() {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        BankAccounts acc = findAccount(accountNo);
        if (acc != null) {
            System.out.println("Balance: " + acc.inquireBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void performTransfer(String type) {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        BankAccounts acc = findAccount(accountNo);
        if (acc != null) {
            System.out.print("Enter Amount: ");
            double amount = validatePositiveDouble();
            if (type.equals("deposit")) {
                acc.deposit(amount);
            } else {
                acc.withdraw(amount);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public static BankAccounts findAccount(int accountNo) {
        return bankAccountsMap.get(accountNo);
    }

    public static void displayAccountInfo() {
        try {
            System.out.print("Enter Account Number: ");
            int accountNo = sc.nextInt();
            sc.nextLine();
            BankAccounts acc = findAccount(accountNo);

            if (acc == null) {
                System.out.println("Account not found. Please check the account number.");
                return;
            }

            // Display common account details
            System.out.println("\nAccount Information:");
            System.out.println("Account Number: " + acc.getAccountNo());
            System.out.println("Account Name: " + acc.getAccountName());
            System.out.println("Balance: " + acc.inquireBalance());
            System.out.println("Status: Active");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid account number.");
            sc.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving account information: " + e.getMessage());
        }
    }

    public static void closeAccount() {
        System.out.print("Enter Account Number to Close: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        if (bankAccountsMap.containsKey(accountNo)) {
            bankAccountsMap.remove(accountNo);
            System.out.println("Account closed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }
}
