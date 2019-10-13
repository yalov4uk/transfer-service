package com.yalovchuk.transfer.dao;

import com.yalovchuk.transfer.model.Status;
import com.yalovchuk.transfer.model.Transfer;

import javax.inject.Singleton;

@Singleton
public class TransferDaoImpl implements TransferDao {

    @Override
    public Transfer create(Transfer transfer) {
        return new Transfer(1, 10, 1, 2, Status.PENDING);
    }

    @Override
    public Transfer update(Transfer transfer) {
        return null;
    }

    @Override
    public Transfer get(int id) {
        return new Transfer(1, 10, 1, 2, Status.PENDING);
    }
}
