/**
 * Represents a CreditCardAccount, which extends the BankAccounts class.
 * This class is used for handling credit card-specific operations such as making payments,
 * charging purchases, inquiring available credit, and obtaining cash advances.
 */
public class CreditCardAccount extends BankAccounts {

    private double creditLimit = 50000;
    private double charges = 0.5;

    /**
     * Initializes a CreditCardAccount with the specified account details.
     *
     * @param accountNo The account number for the credit card account.
     * @param accountName The name of the account holder.
     * @param creditLimit The credit limit for the credit card.
     * @param charges The amount currently charged to the credit card.
     */

    // Constructor to initialize the credit card account with given details
    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(accountNo,accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
    }

    /**
     * Retrieves the credit limit of the credit card account.
     *
     * @return The credit limit of the card.
     */
    public double getCreditLimit() {
        return creditLimit;
    }

    /**
     * Retrieves the current charges on the credit card account.
     *
     * @return The current charges on the card.
     */
    public double getCharges() {
        return charges;
    }

    /**
     * Makes a payment towards the credit card, reducing the outstanding charges.
     * If the payment exceeds the outstanding charges, the charges will be cleared.
     *
     * @param amount The amount to pay towards the credit card charges.
     * @throws IllegalArgumentException if the payment amount is negative or zero.
     */
    public void payCard(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid payment amount. Payment should be positive.");
        }

        if (amount <= charges) {
            charges -= amount;  // Deduct the payment from the outstanding charges
            System.out.println("Payment successful. Remaining charges: " + charges);
        } else {
            charges = 0;  // If payment exceeds charges, clear the charges
            System.out.println("Payment exceeded the charges. Charges cleared.");
        }
    }

    /**
     * Inquires about the available credit on the credit card (credit limit minus current charges).
     *
     * @return The available credit, which is the credit limit minus the current charges.
     */
    public void inquireAvailableCredit() {
        try {
            double availableCredit = creditLimit - charges;  // Available credit is the credit limit minus current charges
            System.out.println("Available Credit: " + availableCredit);
        } catch (Exception e) {
            System.out.println("An error occurred while inquiring about available credit: " + e.getMessage());
        }
    }

    /**
     * Charges an amount to the credit card, if there is enough available credit.
     * If the available credit is insufficient, the charge will not be processed.
     *
     * @param amount The amount to charge to the credit card.
     * @throws IllegalArgumentException if the charge amount is negative or exceeds available credit.
     */
    public void chargeToCard(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Charge amount must be positive.");
        }

        double availableCredit = creditLimit - charges;  // Calculate available credit

        if (availableCredit >= amount) {
            charges += amount;  // Add the amount to charges if there's enough available credit
            System.out.println("Charge successful. New charges: " + charges);
        } else {
            System.out.println("Insufficient available credit to charge this amount.");
        }
    }

    /**
     * Requests a cash advance on the credit card, but the cash advance must be less than or equal to 50% of the available credit.
     * If the requested cash advance exceeds 50% of the available credit, the transaction is declined.
     *
     * @param amount The amount to request for the cash advance.
     * @throws IllegalArgumentException if the cash advance amount exceeds 50% of available credit.
     */
    public void getCashAdvance(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cash advance amount must be positive.");
        }

        double availableCredit = creditLimit - charges;  // Calculate available credit

        if (amount <= (availableCredit * 0.5)) {  // Check if cash advance is within 50% of available credit
            charges += amount;  // Add the cash advance to charges
            System.out.println("Cash advance successful. New charges: " + charges);
        } else {
            System.out.println("Cash advance exceeds 50% of available credit. Transaction declined.");
        }
    }
}
