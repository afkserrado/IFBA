package br.ifba.l3q3.payment;

public class CryptoPayment implements IPayment {
    
    private final String paymentType = "crypto";
    private final double value;
    private final String walletAddress;
    private final String cryptoType;

    // Constructor
    public CryptoPayment(double value, String walletAddress, String cryptoType) {
        if(value <= 0) {
            throw new IllegalArgumentException("Invalid value: must be greater than zero.");
        }

        this.value = value;
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;        
    }

    // Getters
    //
    // Gets value
    @Override
    public double getValue() {
        return value;
    }

    // Gets the wallet address
    public String getWalletAddress() {
        return walletAddress;
    }

    // Gets the crypto type
    public String getCryptoType() {
        return cryptoType;
    }

    // Gets payment type
    @Override
    public String getPaymentType() {
        return paymentType;
    }
}
