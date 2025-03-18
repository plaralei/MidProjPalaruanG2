public class InvestmentAccount extends BankAccounts {
    private double minimumBalance;
    private double interest; // Example: 0.10 for 10%

    // Constructors
    public InvestmentAccount() {
        super();
        this.minimumBalance = 0;
        this.interest = 0;
    }

    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
        setInterest(interest); // Use setter to validate interest
    }

    // Getters
    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterest() {
        return interest;
    }

    // Setter for interest to validate it
    public void setInterest(double interest) {
        if (interest >= 0 && interest <= 1) {
            this.interest = interest;
        } else {
            throw new IllegalArgumentException("Interest must be between 0 and 1.");
        }
    }

    // Add investment (acts like a deposit)
    public void addInvestment(double amount) {
        if (amount > 0) {
            deposit(amount);
        } else {
            System.out.println("Invalid investment amount.");
        }
    }

    // Inquire investment value (balance + interest)
    public double inquireInvestmentValue() {
        return balance * (1 + interest);
    }

    // Override close account (withdraw full amount with interest)
    @Override
    public void closeAccount() {
        if (balance > 0) {
            double totalBalance = inquireInvestmentValue();
            balance = 0; // Set balance to 0 after withdrawal
            status = "closed"; // Set status to closed
            System.out.println("Account closed. Final balance (with interest): " + totalBalance);
        } else {
            System.out.println("Account already empty.");
        }
    }
}
