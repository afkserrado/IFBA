package br.ifba.l3q3.payment;

import br.ifba.l3q3.paymentProcessor.CryptoProcessor;

public class CryptoPayment extends IPayment {

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
        this.processor = new CryptoProcessor();     
    }

    @Override
    public boolean processPayment() {
        System.out.println("CryptoPayment...");
        processor.process();

        return true;
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
}
