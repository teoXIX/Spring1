package it.academy.corso.firstspringboot.exception;

public class EnterException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EnterException(String messaggio) {
        super(messaggio);
    }
}
