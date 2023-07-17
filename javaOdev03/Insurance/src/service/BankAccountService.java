package service;

import model.BankAccount;

import java.math.BigDecimal;

public class BankAccountService {

    public BankAccount createBankAccount(String name, String iban, BigDecimal amount){

        BankAccount bankAccount = new BankAccount(name,iban,amount);

        return bankAccount;
    }
}
