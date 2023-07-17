package service;

import model.BankAccount;
import model.MovementType;
import model.PaymentMovement;

import java.math.BigDecimal;

public class PaymentMovementService {


    public PaymentMovement createPaymentMovement(BankAccount bankAccount,
                                                 String description, MovementType movementType, BigDecimal amount){

        PaymentMovement paymentMovement = new PaymentMovement();
        paymentMovement.setBankAccount(bankAccount);
        paymentMovement.setMovementType(movementType);
        paymentMovement.setDescription(description);
        paymentMovement.setAmount(amount);
        return paymentMovement;

    }
}
