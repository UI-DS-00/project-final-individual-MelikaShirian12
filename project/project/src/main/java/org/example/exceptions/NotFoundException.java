package org.example.exceptions;

public class NotFoundException extends Exception{

    public NotFoundException(String errorMassage) {
        super(errorMassage);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
