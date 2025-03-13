public class BankAccount {
    private int accountNo;
    private String accountName;
    private double balance;
    private String status; // Active or Closed

    public BankAccount() {
        this.status = "Active";
        this.balance = 0;
    }

    public BankAccount(int accountNo, String accountName) {
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.status = "Active";
        this.balance = 0;
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

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful!");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public double inquireBalance() {
        return balance;
    }

    public void transferMoney(BankAccount targetAccount, double amount) {
        if (balance >= amount && targetAccount != null) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
            System.out.println("Money transferred successfully.");
        } else {
            System.out.println("Transfer failed. Insufficient balance or invalid account.");
        }
    }

    public void closeAccount() {
        if (balance > 0) {
            System.out.println("Withdraw all balance before closing.");
        } else {
            status = "Closed";
            System.out.println("Account closed.");
        }
    }

    @Override
    public String toString() {
        return "Account No: " + accountNo + ", Name: " + accountName + ", Balance: " + balance + ", Status: " + status;
    }
}
