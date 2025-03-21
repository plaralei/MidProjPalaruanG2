import java.util.InputMismatchException;
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


                            int accountNo = readAccountNo();
                            sc.nextLine();
                            String accountName = readAccountName();

                            switch (type) {
                                case 1:
                                    boolean validCaseOne = false;
                                    while (!validCaseOne) {
                                        try {
                                            System.out.print("Enter Minimum Balance: ");
                                            double minBal = sc.nextDouble();
                                            bankAccounts[accountCount] = new CheckingAccount(accountNo, accountName, minBal);
                                            validCaseOne = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid Input! Please enter valid numbers.");
                                            sc.nextLine();
                                        }
                                    }
                                    break;

                                case 2:
                                    System.out.print("Enter Credit Limit: ");
                                    double creditLimit = sc.nextDouble();
                                    bankAccounts[accountCount] = new CreditCardAccount(accountNo, accountName, creditLimit, 0);
                                    break;

                                case 3:
                                    boolean validCaseThree = false;
                                    while (!validCaseThree) {
                                        try {
                                            System.out.print("Enter Minimum Balance: ");
                                            double minInvestment = sc.nextDouble();
                                            System.out.print("Enter Interest Rate: ");
                                            double interest = sc.nextDouble();

                                            bankAccounts[accountCount] = new InvestmentAccount(accountNo, accountName, minInvestment, interest);
                                            validCaseThree = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid Input! Please enter valid numbers.");
                                            sc.nextLine();
                                        }
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid account type.");
                                    continue;
                            }
                            System.out.println("Account created successfully.");
                            accountCount++;
                        } else {
                            System.out.println("Account limit reached.");
                        }
                        break;
                    case 2:
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
                    case 3:
                        System.out.print("Enter Account Number: ");
                        accountNo = sc.nextInt();
                        acc = findAccount(bankAccounts, accountNo);
                        if (acc != null) {
                            System.out.print("Enter Deposit Amount: ");
                            double amount = sc.nextDouble();
                            acc.deposit(amount);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter Account Number: ");
                        accountNo = sc.nextInt();
                        acc = findAccount(bankAccounts, accountNo);
                        if (acc != null) {
                            System.out.print("Enter Withdrawal Amount: ");
                            double amount = sc.nextDouble();
                            acc.withdraw(amount);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter Your Account Number: ");
                        int fromAccNum = sc.nextInt();
                        BankAccounts fromAcc = findAccount(bankAccounts, fromAccNum);
                        System.out.print("Enter Target Account Number: ");
                        int toAccNum = sc.nextInt();
                        BankAccounts toAcc = findAccount(bankAccounts, toAccNum);
                        if (fromAcc != null && toAcc != null) {
                            System.out.print("Enter Transfer Amount: ");
                            double amount = sc.nextDouble();
                            fromAcc.transferMoney(toAcc, amount);
                        } else {
                            System.out.println("One or both accounts not found.");
                        }
                        break;
                    case 6:
                        System.out.print("Enter Account Number: ");
                        accountNo = sc.nextInt();
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
                        acc = findAccount(bankAccounts, accountNo);
                        if (acc != null) {
                            if (acc instanceof InvestmentAccount) {
                                ((InvestmentAccount) acc).closeAccount();
                            } else {
                                acc.closeAccount();
                            }
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
                // I worked
                // Gumagana ba
            }
        }
        sc.close();
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

    public static int readAccountNo() {
        Scanner sc = new Scanner(System.in);
        boolean validNo = false;
        int accountNo = 0;

        while (!validNo) {
            try {
                System.out.print("Enter Account Number: ");
                String input = sc.nextLine();
                if (input.matches("\\d{9}")) {
                    accountNo = Integer.parseInt(input);
                    validNo = true;
                } else {
                    System.out.println("Invalid input! Please enter exactly 9 digits.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred.");
                sc.nextLine();
            }
        }
        return accountNo;
    }

    public static String readAccountName() {
        Scanner sc = new Scanner(System.in);
        boolean validName = false;
        String accountName = "";

        while (!validName) {
            try {
                System.out.print("Enter Account Holder Name: ");
                accountName = sc.nextLine();
                Integer.parseInt(accountName);
                System.out.println("Invalid input! Please enter string only.");
            } catch (NumberFormatException e) {
                validName = true;
            } catch (Exception e) {
                System.out.println("An unexpected error occurred.");
            }
        }
        return accountName;
    }
}
