package com.globalit.contacorrente.services.exceptions;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}