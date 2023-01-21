package org.example.exceptions;

public class WrongPasswordException extends Exception{


    public WrongPasswordException() {
        super("wrong password");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
