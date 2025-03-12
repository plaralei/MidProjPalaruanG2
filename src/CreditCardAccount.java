public class CreditCardAccount extends BankAccounts {
    private double creditLimit;
    private double charges;

    // Constructors
    public CreditCardAccount() {
        super();
    }

    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
    }

    // Getters
    public double getCreditLimit() {
        return creditLimit;
    }

    public double getCharges() {
        return charges;
    }

    // Pay card method
    public void payCard(double amount) {
        if (charges >= amount) {
            charges -= amount;
            System.out.println("Payment successful. Remaining charges: " + charges);
        } else {
            System.out.println("Payment exceeds the outstanding charges.");
        }
    }

    // Inquire available credit
    public double inquireAvailableCredit() {
        return creditLimit - charges;
    }

    // Charge to card
    public void chargeToCard(double amount) {
        if (inquireAvailableCredit() >= amount) {
            charges += amount;
            System.out.println("Charge successful. New charges: " + charges);
        } else {
            System.out.println("Insufficient available credit.");
        }
    }

    // Get cash advance
    public void getCashAdvance(double amount) {
        double availableCredit = inquireAvailableCredit();
        if (amount <= 0.5 * availableCredit) {
            charges += amount;
            System.out.println("Cash advance successful. New charges: " + charges);
        } else {
            System.out.println("Cash advance amount exceeds 50% of available credit.");
        }
    }
}
