public class CreditCardAccount extends BankAccounts {

    private double creditLimit;  // Credit limit for the card
    private double charges;      // Amount currently charged to the card

    // Default constructor
    public CreditCardAccount() {
        super();
        this.creditLimit = 0;
        this.charges = 0;
    }

    // Constructor to initialize the credit card account with given details
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(accountNo, accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
    }

    // Getter method for the credit limit
    public double getCreditLimit() {
        return creditLimit;
    }

    // Getter method for the current charges on the card
    public double getCharges() {
        return charges;
    }

    // Method to make a payment towards the card, reducing the charges
    public void payCard(double amount) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Payment amount must be positive.");
            }
            if (amount > charges) {
                charges = 0;  // Clear the charges if the payment exceeds the charges
                System.out.println("Payment exceeded the charges. Charges cleared.");
            } else {
                charges -= amount;  // Deduct the payment from the outstanding charges
                System.out.println("Payment successful. Remaining charges: " + charges);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to inquire about the available credit (credit limit - charges)
    public void inquireAvailableCredit() {
        try {
            double availableCredit = creditLimit - charges;  // Available credit is the credit limit minus current charges
            if (availableCredit < 0) {
                throw new IllegalStateException("Error calculating available credit.");
            }
            System.out.println("Available Credit: " + availableCredit);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to charge an amount to the card, if there's enough available credit
    public void chargeToCard(double amount) {
        try {
            double availableCredit = creditLimit - charges;  // Calculate available credit

            if (amount <= 0) {
                throw new IllegalArgumentException("Charge amount must be positive.");
            }
            if (availableCredit >= amount) {
                charges += amount;  // Add the amount to charges if there's enough available credit
                System.out.println("Charge successful. New charges: " + charges);
            } else {
                throw new IllegalStateException("Insufficient available credit to charge this amount.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to get a cash advance, but it must be less than or equal to 50% of the available credit
    public void getCashAdvance(double amount) {
        try {
            double availableCredit = creditLimit - charges;  // Calculate available credit
            if (amount <= 0) {
                throw new IllegalArgumentException("Cash advance amount must be positive.");
            }
            if (amount <= (availableCredit * 0.5)) {  // Check if cash advance is within 50% of available credit
                charges += amount;  // Add the cash advance to charges
                System.out.println("Cash advance successful. New charges: " + charges);
            } else {
                throw new IllegalStateException("Cash advance exceeds 50% of available credit. Transaction declined.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
