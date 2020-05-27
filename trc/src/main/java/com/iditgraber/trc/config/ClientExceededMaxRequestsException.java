package com.iditgraber.trc.config;

import javax.persistence.EntityNotFoundException;

public class ClientExceededMaxRequestsException extends EntityNotFoundException {

    public ClientExceededMaxRequestsException(String string) {
        super(string);
    }

}
