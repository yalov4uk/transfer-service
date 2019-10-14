package com.yalovchuk.transfer.service;

import com.yalovchuk.transfer.component.TransferPublisher;
import com.yalovchuk.transfer.dao.TransferDao;
import com.yalovchuk.transfer.model.Status;
import com.yalovchuk.transfer.model.Transfer;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class TransferServiceImpl implements TransferService {

    private final TransferDao transferDao;
    private final TransferPublisher transferPublisher;

    @Override
    public Transfer create(Transfer transfer) {
        transfer.setStatus(Status.CREATED);
        Transfer result = transferDao.create(transfer);
        transferPublisher.publish(result);
        return result;
    }

    @Override
    public Transfer get(int id) {
        return transferDao.get(id);
    }
}
