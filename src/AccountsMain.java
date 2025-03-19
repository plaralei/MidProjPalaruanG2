import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class AccountsMain {
    static HashMap<String, String> userAccounts = new HashMap<>();
    static HashMap<Integer, BankAccounts> bankAccountsMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
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
                    System.out.println("Invalid choice.");
            }
        }

        int choice;
        do {
            System.out.println("1. Open New Investment Account");
            System.out.println("2. Open New Credit Card Account");
            System.out.println("3. Open New Checking Account");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Transfer Money");
            System.out.println("8. Account Information");
            System.out.println("9. Close Account");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
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
                        System.out.print("Enter Account Number: ");
                        int accountNo = sc.nextInt();
                        sc.nextLine();
                        BankAccounts acc = findAccount(accountNo);
                        if (acc instanceof InvestmentAccount) {
                            System.out.println("Investment Value (including interest): " + ((InvestmentAccount) acc).inquireInvestmentValue());
                        } else if (acc != null) {
                            System.out.println("Balance: " + acc.inquireBalance());
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 5:
                        performTransaction("deposit");
                        break;
                    case 6:
                        performTransaction("withdraw");
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
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    // Method to generate a 9-digit account number
    public static int generateAccountNo() {
        Random random = new Random();
        // Ensure it's a 9-digit number
        return 100000000 + random.nextInt(900000000);
    }

    public static void createAccount() {
        System.out.print("Enter Full Name: ");
        String fullName = sc.nextLine();

        String password;
        while (true) {
            System.out.print("Enter a 6-digit PIN for your password: ");
            password = sc.nextLine();
            if (password.matches("\\d{6}")) {
                break;
            } else {
                System.out.println("Invalid PIN! Please enter a 6-digit number.");
            }
        }

        userAccounts.put(fullName, password);
        System.out.println("Account created successfully!");
    }

    public static boolean login() {
        System.out.print("Enter Full name: ");
        String Fullname = sc.nextLine();
        System.out.print("Enter PIN (6 digit): ");
        String password = sc.nextLine();

        if (userAccounts.containsKey(Fullname) && userAccounts.get(Fullname).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username/password or account doesn't exist.");
            return false;
        }
    }

    public static void createInvestmentAccount() {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Account Name: ");
        String accountName = sc.nextLine();
        bankAccountsMap.put(accountNo, new InvestmentAccount(accountNo, accountName));
        System.out.println("Investment Account Created Successfully!");
    }

    public static void createCreditCardAccount() {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Account Name: ");
        String accountName = sc.nextLine();
        System.out.print("Enter Credit Limit: ");
        double creditLimit = sc.nextDouble();
        sc.nextLine();
        bankAccountsMap.put(accountNo, new CreditCardAccount(accountNo, accountName, creditLimit, 0));
        System.out.println("Credit Card Account Created Successfully!");
    }

    public static void createCheckingAccount() {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Account Name: ");
        String accountName = sc.nextLine();
        System.out.print("Enter Minimum Balance: ");
        double minBalance = sc.nextDouble();
        sc.nextLine();
        bankAccountsMap.put(accountNo, new CheckingAccount(accountNo, accountName, minBalance));
        System.out.println("Checking Account Created Successfully!");
    }

    public static BankAccounts findAccount(int accountNo) {
        return bankAccountsMap.get(accountNo);
    }

    public static void performTransaction(String type) {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        BankAccounts acc = findAccount(accountNo);
        if (acc != null) {
            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            if (type.equals("deposit")) {
                acc.deposit(amount);
            } else {
                acc.withdraw(amount);
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void performTransfer() {
        System.out.print("Enter Your Account Number: ");
        int fromAccNum = sc.nextInt();
        sc.nextLine();
        BankAccounts fromAcc = findAccount(fromAccNum);
        System.out.print("Enter Target Account Number: ");
        int toAccNum = sc.nextInt();
        sc.nextLine();
        BankAccounts toAcc = findAccount(toAccNum);
        if (fromAcc != null && toAcc != null) {
            System.out.print("Enter Transfer Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            fromAcc.transferMoney(toAcc, amount);
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    public static void displayAccountInfo() {
        System.out.print("Enter Account Number: ");
        int accountNo = sc.nextInt();
        sc.nextLine();
        BankAccounts acc = findAccount(accountNo);
        if (acc != null) {
            System.out.println("Account Number: " + acc.getAccountNo());
            System.out.println("Account Name: " + acc.getAccountName());
            System.out.println("Balance: " + acc.inquireBalance());
            if (acc instanceof InvestmentAccount) {
                System.out.println("Interest Rate: " + ((InvestmentAccount) acc).getInterest());
            }
        } else {
            System.out.println("Account not found.");
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
