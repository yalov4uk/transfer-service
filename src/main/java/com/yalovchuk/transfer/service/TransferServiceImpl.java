package com.yalovchuk.transfer.service;

import com.yalovchuk.transfer.model.Transfer;

import javax.inject.Singleton;

@Singleton
public class TransferServiceImpl implements TransferService {

    @Override
    public Transfer create(Transfer transfer) {
        return new Transfer(1, 10, 1, 2);
    }

    @Override
    public Transfer get(int id) {
        return new Transfer(1, 10, 1, 2);
    }
}
