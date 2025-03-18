public class BankAccounts {
    private int accountNo;          // 9-digit account number
    private String accountName;
    protected double balance;       // protected so it can be accessed in subclasses
    private String status;          // "active" or "closed"

    // Constructors
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

    // Getter and Setter methods
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
        this.accountName = accountName;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Balance inquiry
    public double inquireBalance() {
        return balance;
    }

    // Close account
    public void closeAccount() {
        if (balance == 0) {
            status = "closed";
            System.out.println("Account closed.");
        } else {
            System.out.println("Withdraw all money before closing the account.");
        }
    }

    // Transfer money
    public void transferMoney(BankAccounts targetAccount, double amount) {
        if (targetAccount == null) {
            System.out.println("Target account does not exist.");
            return;
        }
        if (balance >= amount) {
            withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient balance for transfer.");
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNo + ", Account Name: " + accountName + ", Balance: " + balance + ", Status: " + status;
    }
}
