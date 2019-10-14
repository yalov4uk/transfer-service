package com.yalovchuk.transfer.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.yalovchuk.transfer.component.*;
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
        bind(TransferService.class).to(TransferServiceImpl.class);
        bind(TransferDao.class).to(TransferDaoImpl.class);
        bind(TransferPublisher.class).to(TransferPublisherImpl.class);
        bind(TransferHandler.class).to(TransferHandlerImpl.class);
    }

    @Provides
    @Singleton
    public Gson gson() {
        return new Gson();
    }

    @Provides
    @Singleton
    public BlockingQueue<Transfer> queue() {
        return new ArrayBlockingQueue<>(100);
    }

    @Provides
    public TransferConsumer transferWorker(BlockingQueue<Transfer> queue, TransferHandler transferHandler) {
        return new TransferConsumer(queue, transferHandler);
    }
}
