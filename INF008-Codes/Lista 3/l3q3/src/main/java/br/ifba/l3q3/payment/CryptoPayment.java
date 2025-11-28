package br.ifba.l3q3.payment;

public class CryptoPayment extends Payment {
    
     private final String walletAddress;
     private final String cryptoType;

    // Constructor
    public CryptoPayment(double value, String walletAddress, String cryptoType) {
        super(value);
        this.walletAddress = walletAddress;
        this.cryptoType = cryptoType;        
    }

    // Getters
    //
    // Gets the wallet address
    public String getWalletAddress() {
        return walletAddress;
    }

    // Gets the crypto type
    public String getCryptoType() {
        return cryptoType;
    }
}
