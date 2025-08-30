package com.globalit.contacorrente.services.exceptions;

@SuppressWarnings("serial")
public class RateNotApplicableException extends RuntimeException {

	public RateNotApplicableException(String msg) {
        super(msg);
    }
}