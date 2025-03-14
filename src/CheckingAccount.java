import org.w3c.dom.ls.LSOutput;

public class CheckingAccount extends BankAccounts {
    private double minimumBalance;

    // Constructors
    public CheckingAccount() {
        super();
    }

    public CheckingAccount(int accountNo, String accountName, double minimumBalance) {
        super(accountNo, accountName);
        this.minimumBalance = minimumBalance;
    }

    // Get minimum balance
    public double getMinimumBalance() {
        return minimumBalance;
    }

    // Encash check method (withdraw and encash are not synonymous)
    // machines are not used to validate encashed checks and the amount is not withdrawn from the account
    public void encashCheck(double amount) {
        //withdraw(amount);
        System.out.println("Please see a teller to encash check of " +amount+ ". You may also deposit the check to an account but cash withdrawal after deposit is not allowed.");
    }

}
