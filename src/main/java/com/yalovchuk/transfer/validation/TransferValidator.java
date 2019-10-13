package com.yalovchuk.transfer.validation;

import com.yalovchuk.transfer.model.Transfer;

import javax.inject.Singleton;

@Singleton
public class TransferValidator {

    public boolean isValid(Transfer transfer) {
        if (transfer == null) {
            return false;
        }
        if (transfer.getId() != null) {
            return false;
        }
        if (transfer.getValue() < 0) {
            return false;
        }
        return transfer.getStatus() == null;
    }
}
