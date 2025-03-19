import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountsMain {
    static HashMap<String, String> userAccounts = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isLoggedIn = false;

        // Menu for user to log in, create an account, or exit
        while (!isLoggedIn) {
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

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

        // If successfully logged in, continue with the bank account system
        BankAccounts[] bankAccounts = new BankAccounts[5];
        int accountCount = 0;

        // Menu options after login
        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Create Bank Account");
            System.out.println("2. Balance Inquiry");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Money");
            System.out.println("6. Account Information");
            System.out.println("7. Close Account");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        if (accountCount < bankAccounts.length) {
                            System.out.println("Select Account Type: ");
                            int type = -1;

                            do {
                                try {
                                    System.out.println("1. Checking");
                                    System.out.println("2. Credit Card");
                                    System.out.println("3. Investment");
                                    System.out.print("Enter a valid option (1-3): ");
                                    type = sc.nextInt();
                                    sc.nextLine(); // Consume newline

                                    if (type < 1 || type > 3) {
                                        System.out.println("Invalid option. Please enter a number between 1 and 3.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a numeric value between 1 and 3.");
                                    sc.nextLine();
                                }
                            } while (type < 1 || type > 3);

                            int accountNo = generateAccountNo(); // Generate a 9-digit account number
                            System.out.print("Enter Account Holder Name: ");
                            String accountName = sc.nextLine();

                            switch (type) {
                                case 1:
                                    System.out.print("Enter Minimum Balance: ");
                                    double minBal = sc.nextDouble();
                                    sc.nextLine(); // Consume newline
                                    bankAccounts[accountCount] = new CheckingAccount(accountNo, accountName, minBal);
                                    break;
                                case 2:
                                    System.out.print("Enter Credit Limit: ");
                                    double creditLimit = sc.nextDouble();
                                    sc.nextLine(); // Consume newline
                                    bankAccounts[accountCount] = new CreditCardAccount(accountNo, accountName, creditLimit, 0);
                                    break;
                                case 3:
                                    boolean validInput = false;
                                    while (!validInput) {
                                        try {
                                            System.out.print("Enter Minimum Balance: ");
                                            double minInvestment = sc.nextDouble();
                                            System.out.print("Enter Interest Rate: ");
                                            double interest = sc.nextDouble();
                                            sc.nextLine(); // Consume newline
                                            bankAccounts[accountCount] = new InvestmentAccount(accountNo, accountName, minInvestment, interest);
                                            validInput = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid Input! Please enter valid numbers for Minimum Balance and Interest Rate.");
                                            sc.nextLine();
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid account type.");
                                    continue;
                            }
                            System.out.println("Account created successfully. Account Number: " + accountNo);
                            accountCount++;
                        } else {
                            System.out.println("Account limit reached.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter Account Number: ");
                        int accountNo = sc.nextInt();
                        sc.nextLine(); // Consume newline

                        BankAccounts acc = findAccount(bankAccounts, accountNo);

                        if (acc instanceof InvestmentAccount) {
                            System.out.println("Investment Value (including interest): " + ((InvestmentAccount) acc).inquireInvestmentValue());
                        } else if (acc != null) {
                            System.out.println("Balance: " + acc.inquireBalance());
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter Account Number: ");
                        accountNo = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        acc = findAccount(bankAccounts, accountNo);
                        if (acc != null) {
                            System.out.print("Enter Deposit Amount: ");
                            double amount = sc.nextDouble();
                            sc.nextLine(); // Consume newline
                            acc.deposit(amount);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Account Number: ");
                        accountNo = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        acc = findAccount(bankAccounts, accountNo);
                        if (acc != null) {
                            System.out.print("Enter Withdrawal Amount: ");
                            double amount = sc.nextDouble();
                            sc.nextLine(); // Consume newline
                            acc.withdraw(amount);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter Your Account Number: ");
                        int fromAccNum = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        BankAccounts fromAcc = findAccount(bankAccounts, fromAccNum);
                        System.out.print("Enter Target Account Number: ");
                        int toAccNum = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        BankAccounts toAcc = findAccount(bankAccounts, toAccNum);
                        if (fromAcc != null && toAcc != null) {
                            System.out.print("Enter Transfer Amount: ");
                            double amount = sc.nextDouble();
                            sc.nextLine(); // Consume newline
                            fromAcc.transferMoney(toAcc, amount);
                        } else {
                            System.out.println("One or both accounts not found.");
                        }
                        break;
                    case 6:
                        System.out.print("Enter Account Number: ");
                        accountNo = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        acc = findAccount(bankAccounts, accountNo);
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
                        break;
                    case 7:
                        System.out.print("Enter Account Number to Close: ");
                        accountNo = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        acc = findAccount(bankAccounts, accountNo);
                        if (acc != null) {
                            acc.closeAccount();
                        } else {
                            System.out.println("Account not found.");
                        }
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
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // Method to create a new user account
    public static void createAccount() {
        System.out.print("Enter Full Name (FirstName MiddleInitial. LastName): ");
        String fullName = sc.nextLine();

        // Ensure password is a 6-digit numeric PIN
        String password = "";
        while (true) {
            System.out.print("Enter a 6-digit PIN for your password: ");
            password = sc.nextLine();
            if (password.matches("\\d{6}")) {
                break;
            } else {
                System.out.println("Invalid PIN! Please enter a 6-digit number.");
            }
        }

        int accountNo = generateAccountNo(); // Generate a 9-digit account number for the user
        userAccounts.put(fullName, password);
        System.out.println("Account created successfully!");
        System.out.println("Account Info:");
        System.out.println("Full Name: " + fullName);
        System.out.println("Password: " + password);
        System.out.println("Account Number: " + accountNo); // Display the generated 9-digit account number
    }

    // Method to log in to the system
    public static boolean login() {
        System.out.print("Enter your Full Name: ");
        String fullName = sc.nextLine();

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print("Enter your 6-digit PIN: ");
            String password = sc.nextLine();

            if (userAccounts.containsKey(fullName) && userAccounts.get(fullName).equals(password)) {
                System.out.println("Successfully logged in!");
                return true;
            } else {
                System.out.println("Incorrect login information! You have " + (attempts - 1) + " attempts left.");
            }
        }

        System.out.println("Login failed.");
        return false;
    }

    // Method to find an account by account number
    public static BankAccounts findAccount(BankAccounts[] accounts, int accountNo) {
        for (BankAccounts acc : accounts) {
            if (acc != null && acc.getAccountNo() == accountNo) {
                return acc;
            }
        }
        return null;
    }

    // Method to generate a 9-digit account number
    public static int generateAccountNo() {
        Random rand = new Random();
        return 100000000 + rand.nextInt(900000000); // Generates a 9-digit random number
    }
}
