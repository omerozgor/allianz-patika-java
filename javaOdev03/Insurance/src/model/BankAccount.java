package model;

import java.math.BigDecimal;

public class BankAccount {
    private String name;
    private String iban;
    private BigDecimal amount;

    public BankAccount(String name, String iban, BigDecimal amount) {
        this.name = name;
        this.iban = iban;
        this.amount = amount;
    }

    public BankAccount() {
    }
}
