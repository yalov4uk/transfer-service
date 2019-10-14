package com.yalovchuk.transfer.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.yalovchuk.transfer.component.TransferProcessor;
import com.yalovchuk.transfer.component.AsyncTransferProcessorImpl;
import com.yalovchuk.transfer.dao.TransferDao;
import com.yalovchuk.transfer.dao.TransferDaoImpl;
import com.yalovchuk.transfer.model.Transfer;
import com.yalovchuk.transfer.service.TransferService;
import com.yalovchuk.transfer.service.TransferServiceImpl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TransferModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Gson.class).toInstance(new Gson());

        bind(new TypeLiteral<BlockingQueue<Transfer>>() {
        }).toInstance(new ArrayBlockingQueue<>(100));

        bind(TransferService.class).to(TransferServiceImpl.class);
        bind(TransferDao.class).to(TransferDaoImpl.class);
        bind(TransferProcessor.class).to(AsyncTransferProcessorImpl.class);
    }
}
