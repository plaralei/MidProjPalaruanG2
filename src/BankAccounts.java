import java.util.Scanner;

public class BankAccounts {
    private int accountNo;
    private String accountName;
    protected double balance;
    protected String status;
    protected String password;


    public BankAccounts() {
        this.status = "active";
        this.balance = 0;
    }

    public BankAccounts(int accountNo, String accountName) {
        this();
        if (String.valueOf(accountNo).length() == 9) {
            this.accountNo = accountNo;
        } else {
            throw new IllegalArgumentException("Account number must be 9 digits.");
        }
        this.accountName = accountName;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void displayInfo() {
        System.out.println("\nAccount Info:");
        System.out.println("Account No: " + accountNo);
        System.out.println("Account Name: " + accountName);
        System.out.println("Balance: " + balance);
        System.out.println("Status: Active\n");
    }


    public int getAccountNo() {
        return accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getStatus() {
        return status;
    }

    public void setAccountNo(int accountNo) {
        if (String.valueOf(accountNo).length() == 9) {
            this.accountNo = accountNo;
        } else {
            throw new IllegalArgumentException("Account number must be 9 digits.");
        }
    }

    public void setAccountName(String accountName) {
        if (accountName != null && !accountName.isEmpty()) {
            this.accountName = accountName;
        } else {
            throw new IllegalArgumentException("Account name cannot be null or empty.");
        }
    }


    public void deposit(double amount) {
        try {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. New balance: " + balance);
            } else {
                throw new IllegalArgumentException("Invalid deposit amount. Amount must be positive.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    public void withdraw(double amount) {
        try {
            if (balance >= amount) {
                if (amount > 0) {
                    balance -= amount;
                    System.out.println("Withdrawal successful. New balance: " + balance);
                } else {
                    throw new IllegalArgumentException("Amount must be positive.");
                }
            } else {
                throw new IllegalStateException("Insufficient balance.");
            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    public double inquireBalance() {
        return balance;
    }


    public void closeAccount() {
        try {
            if (balance == 0) {
                status = "closed";
                System.out.println("Account closed.");
            } else {
                throw new IllegalStateException("Withdraw all money before closing the account.");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }


    public void transferMoney(BankAccounts targetAccount, double amount) {
        try {
            if (targetAccount == null) {
                throw new IllegalArgumentException("Target account does not exist.");
            }
            if (balance >= amount) {
                if (amount > 0) {
                    withdraw(amount);
                    targetAccount.deposit(amount);
                    System.out.println("Transfer successful.");
                } else {
                    throw new IllegalArgumentException("Amount must be positive.");
                }
            } else {
                throw new IllegalStateException("Insufficient balance for transfer.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNo + ", Account Name: " + accountName + ", Balance: " + balance + ", Status: " + status;
    }
}
