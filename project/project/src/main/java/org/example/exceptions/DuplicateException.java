package org.example.exceptions;

import com.sun.jdi.request.DuplicateRequestException;

public class DuplicateException extends DuplicateRequestException {

    public DuplicateException(String errorMassage) {
        super(errorMassage);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
