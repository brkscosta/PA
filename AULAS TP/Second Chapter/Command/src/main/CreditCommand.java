/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 * CONCRETE COMMAND
 * @author BRKsCosta
 */
public class CreditCommand implements Command {
    private final Bank receiver;
    private final String accountNumber;
    private final double amount;

    public CreditCommand(Bank receiver, String accountNumber, double amount) {
        this.receiver = receiver;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }
    
    @Override
    public boolean execute() {
        return receiver.creditAccount(accountNumber, amount);
    }

    @Override
    public boolean undo() {
        return receiver.debitAccount(accountNumber, amount);
    }
    
    
}
