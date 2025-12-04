package br.ifba.l3q3.payment;
import java.time.LocalDate;

public class CreditCardPayment implements IPayment {
    
    private final String paymentType = "credit card";
    private final double value;
    private final String cardNumber;
    private final String expirationDate;

    // Constructor
    public CreditCardPayment(double value, String cardNumber, String expirationDate) {
        if(value <= 0) {
            throw new IllegalArgumentException("Invalid value: must be greater than zero.");
        }

        // Normalizes data
        String normalizedCard = normalizeDigits(cardNumber);
        String normalizedExp  = normalizeDigits(expirationDate);

        // Validates data
        validateCardNumber(normalizedCard);
        validateExpirationDate(normalizedExp);

        // Assign normalized values
        this.value = value;
        this.cardNumber = normalizedCard;
        this.expirationDate = normalizedExp;
    }

    // Getters
    //

    // Gets value
    @Override
    public double getValue() {
        return value;
    }

    // Gets card number
    public String getCardNumber() {
        return cardNumber;
    }

    // Gets expiration date
    public String getExpirationDate() {
        return expirationDate;
    }

    // Gets payment type
    @Override
    public String getPaymentType() {
        return paymentType;
    }

    // Utilities
    //
    // Normalizes a string, by removing any non-digit characters
    private static String normalizeDigits(String s) {
        if(s == null) {
            return "";
        }

        return s.replaceAll("\\D", "");
    }

    // Private methods
    //
    // Validates card number
    private void validateCardNumber(String normalizedCardNumber) {

        // Check if normalizedCardNumber is null or empty
        if(normalizedCardNumber == null || normalizedCardNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid card number: empty or null after normalization.");
        }

        // Checks if the card number length is less than 13 or greater than 19 digits
        int len = normalizedCardNumber.length();
        if(len < 13 || len > 19) {
            throw new IllegalArgumentException("Invalid card number: must have 13-19 digits.");
        }

        // Checks if all digits are the same
        //
        // Gets the first digit of the normalized card number
        char firstDigit = normalizedCardNumber.charAt(0);

        boolean testAllEquals = true;

        // Searches for digits different from the first digit
        for(int i = 1; i < len; i++) {
            if(firstDigit != normalizedCardNumber.charAt(i)) {
                testAllEquals = false;
                break;
            }
        }

        if(testAllEquals) {
            throw new IllegalArgumentException("Invalid card number: all digits are the same.");
        }
    }

    // Validate expiration date
    private void validateExpirationDate(String normalizedExpirationDate) {

        // Check if normalizedExpirationDate is null or empty
        if(normalizedExpirationDate == null || normalizedExpirationDate.isEmpty()) {
            throw new IllegalArgumentException("Invalid expiration date: empty or null after normalization.");
        }

        // Checks if the expiration date length is different from 4 digits (MMYY pattern)
        if(normalizedExpirationDate.length() != 4) {
            throw new IllegalArgumentException("Invalid expiration date: must contain 4 digits in MMYY format.");
        }

        // Extracts the month and year from the expiration date
        int month = Integer.parseInt(normalizedExpirationDate.substring(0,2));
        int year = Integer.parseInt(normalizedExpirationDate.substring(2));
        int fullYear = 2000 + year; // Converts YY to YYYY

        // Checks if the month is between 1 and 12
        if(month < 1 || month > 12) {
           throw new IllegalArgumentException("Invalid expiration date: month must be 01-12.");
        }

        // Gets the current year and month
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        // Checks if the expiration date is already passed
        if(fullYear < currentYear || (fullYear == currentYear && month < currentMonth)) {
            throw new IllegalArgumentException("Invalid expiration date: card is already expired.");
        }
    }
}
