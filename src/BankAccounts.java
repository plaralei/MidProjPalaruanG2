/**
 * Represents a bank account with basic operations such as deposit, withdraw,
 * transfer, and account closure.
 */
public class BankAccounts {
    private int accountNo;
    private String accountName;
    protected double balance;
    protected String status;
    protected String password;

    /**
     * Default constructor that initializes the account with a balance of 0
     * and an active status.
     */
    public BankAccounts() {
        this.status = "active";
        this.balance = 0;
    }

    /**
     * Parameterized constructor that initializes the account with an account number and name.
     *
     * @param accountNo   The 9-digit account number.
     * @param accountName The name associated with the account.
     * @throws IllegalArgumentException if the account number is not 9 digits.
     */
    public BankAccounts(int accountNo, String accountName) {
        this();
        if (String.valueOf(accountNo).length() == 9) {
            this.accountNo = accountNo;
        } else {
            throw new IllegalArgumentException("Account number must be 9 digits.");
        }
        this.accountName = accountName;
    }

    /**
     * Authenticates the user by checking the provided password.
     *
     * @param password The password to be verified.
     * @return True if the password matches, otherwise false.
     */
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    /**
     * Displays account details such as account number, name, balance, and status.
     */
    public void displayInfo() {
        System.out.println("\nAccount Info:");
        System.out.println("Account No: " + accountNo);
        System.out.println("Account Name: " + accountName);
        System.out.println("Balance: " + balance);
        System.out.println("Status: Active\n");
    }

    /**
     * Gets the account number.
     *
     * @return The account number.
     */
    public int getAccountNo() {
        return accountNo;
    }

    /**
     * Gets the account name.
     *
     * @return The account name.
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Gets the account status.
     *
     * @return The account status (e.g., active or closed).
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the account number, ensuring it is exactly 9 digits.
     *
     * @param accountNo The 9-digit account number.
     * @throws IllegalArgumentException if the account number is not 9 digits.
     */
    public void setAccountNo(int accountNo) {
        if (String.valueOf(accountNo).length() == 9) {
            this.accountNo = accountNo;
        } else {
            throw new IllegalArgumentException("Account number must be 9 digits.");
        }
    }

    /**
     * Sets the account name.
     *
     * @param accountName The name of the account holder.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public void setAccountName(String accountName) {
        if (accountName != null && !accountName.isEmpty()) {
            this.accountName = accountName;
        } else {
            throw new IllegalArgumentException("Account name cannot be null or empty.");
        }
    }

    /**
     * Deposits an amount into the account.
     *
     * @param amount The amount to be deposited.
     * @throws IllegalArgumentException if the deposit amount is not positive.
     */
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

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount The amount to be withdrawn.
     * @throws IllegalArgumentException if the amount is not positive.
     * @throws IllegalStateException    if the balance is insufficient.
     */
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

    /**
     * Gets the current balance of the account.
     *
     * @return The account balance.
     */
    public double inquireBalance() {
        return balance;
    }

    /**
     * Closes the account if the balance is zero.
     *
     * @throws IllegalStateException if the balance is not zero.
     */
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

    /**
     * Transfers money to another account.
     *
     * @param targetAccount The recipient account.
     * @param amount        The amount to be transferred.
     * @throws IllegalArgumentException if the target account is null or amount is not positive.
     * @throws IllegalStateException    if the balance is insufficient.
     */
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

    /**
     * Returns a string representation of the bank account.
     *
     * @return A formatted string containing account details.
     */
    @Override
    public String toString() {
        return "Account No: " + accountNo + ", Account Name: " + accountName + ", Balance: " + balance + ", Status: " + status;
    }
}
