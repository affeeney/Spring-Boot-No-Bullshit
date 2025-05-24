package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

public class ExternalCatFactsDownException extends CustomBaseException {
    public ExternalCatFactsDownException(HttpStatus status, SimpleResponse simpleResponse) {
        super(status, simpleResponse);
    }
}
