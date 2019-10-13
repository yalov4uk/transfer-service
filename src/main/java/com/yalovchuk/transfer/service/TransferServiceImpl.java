package com.yalovchuk.transfer.service;

import com.yalovchuk.transfer.component.TransferProcessor;
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
    private final TransferProcessor transferProcessor;

    @Override
    public Transfer create(Transfer transfer) {
        transfer.setStatus(Status.PENDING);
        Transfer result = transferDao.create(transfer);
        transferProcessor.process(result);
        return result;
    }

    @Override
    public Transfer get(int id) {
        return transferDao.get(id);
    }
}
