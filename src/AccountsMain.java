import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class AccountsMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Array to store all the 3 types of accounts
        BankAccounts[] bankAccounts = new BankAccounts[5];
        int accountCount = 0;

        // Menu options
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

                                    if (type < 1 || type > 3) {
                                        System.out.println("Invalid option. Please enter a number between 1 and 3.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a numeric value between 1 and 3.");
                                    sc.nextLine();
                                }
                            } while (type < 1 || type > 3);

                            int accountNo = generateAccountNo(); // Generate 9-digit account number
                            sc.nextLine();
                            System.out.print("Enter Account Holder Name: ");
                            String accountName = sc.nextLine();

                            switch (type) {
                                case 3:
                                    boolean validInput = false;
                                    while (!validInput) {
                                        try {
                                            System.out.print("Enter Minimum Balance: ");
                                            double minInvestment = sc.nextDouble();
                                            System.out.print("Enter Interest Rate: ");
                                            double interest = sc.nextDouble();
                                            bankAccounts[accountCount] = new InvestmentAccount(accountNo, accountName, minInvestment, interest);
                                            validInput = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid Input! Please enter valid numbers for Minimum Balance and Interest Rate.");
                                            sc.nextLine(); // Clear input buffer
                                        }
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid account type.");
                                    continue;
                            }
                            System.out.println("Account created successfully with Account Number: " + accountNo);
                            accountCount++;
                        } else {
                            System.out.println("Account limit reached.");
                        }
                        break;
                    case 2:
                        // Balance Inquiry
                        System.out.print("Enter Account Number: ");
                        int accountNo = sc.nextInt();

                        BankAccounts acc = findAccount(bankAccounts, accountNo);

                        if (acc instanceof InvestmentAccount) {
                            System.out.println("Investment Value (including interest): " + ((InvestmentAccount) acc).inquireInvestmentValue());
                        } else if (acc != null) {
                            System.out.println("Balance: " + acc.inquireBalance());
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    // Other cases remain unchanged...
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
        sc.close();
    }

    // Method to generate a 9-digit account number
    public static int generateAccountNo() {
        Random random = new Random();
        // Ensure it's a 9-digit number
        return 100000000 + random.nextInt(900000000);
    }

    // Method to find an account by number
    private static BankAccounts findAccount(BankAccounts[] accounts, int accountNo) {
        for (BankAccounts account : accounts) {
            if (account != null && account.getAccountNo() == accountNo) {
                return account;
            }
        }
        return null;
    }
}
