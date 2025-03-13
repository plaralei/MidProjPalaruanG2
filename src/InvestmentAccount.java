public class InvestmentAccount extends BankAccounts {
    private double minimumBalance;
    private double interest;

    // Constructors
    public InvestmentAccount() {
        super();
    }

    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);

        if (minimumBalance < 0) {
            System.out.println("Minimum balance cannot be negative. Setting to 0.");
            this.minimumBalance = 0;
        } else {
            this.minimumBalance = minimumBalance;
        }

        if (interest < 0) {
            System.out.println("Interest rate cannot be negative. Setting to 0.");
            this.interest = 0;
        } else {
            this.interest = interest;
        }

    }

    // Getters
    public double getMinimumBalance() {
        return minimumBalance;
    }

    public double getInterest() {
        return interest;
    }

    // Add investment (acts like a deposit)
    public void addInvestment(double amount) {
        if (amount > 0) {
            deposit(amount);
        } else {
            System.out.println("Investment amount must be positive.");
        }
    }

    // Inquire investment value (balance + interest)
    public double inquireInvestmentValue() {
        return balance * (1 + interest);
    }

    // Override close account (withdraw full amount with interest)
    @Override
    public void closeAccount() {
        if (balance == 0) {
            System.out.println("Account already empty. Closing...");
            super.closeAccount();
        } else {
            double totalBalance = inquireInvestmentValue();
            System.out.println("Account closed. Final balance (with interest): " + totalBalance);
            balance = 0;
            super.closeAccount();
        }
    }
}
