public class InvestmentAccount extends BankAccounts {
    private double minimumBalance;
    private double interest;

    // Constructors
    public InvestmentAccount() {
        super();
    }

    public InvestmentAccount(int accountNo, String accountName, double minimumBalance, double interest) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
        this.interest = interest;
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
        deposit(amount);
    }

    // Inquire investment value (balance + interest)
    public double inquireInvestmentValue() {
        return balance * (1 + interest);
    }

    // Override close account (withdraw full amount with interest)
    @Override
    public void closeAccount() {
        double totalBalance = inquireInvestmentValue();
        balance = 0;
        System.out.println("Account closed. Final balance (with interest): " + totalBalance);
        super.closeAccount();
    }
}
