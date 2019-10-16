package com.yalovchuk.transfer.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.yalovchuk.transfer.component.TransferPublisher;
import com.yalovchuk.transfer.component.TransferPublisherImpl;
import com.yalovchuk.transfer.dao.TransferDao;
import com.yalovchuk.transfer.dao.TransferDaoImpl;
import com.yalovchuk.transfer.service.TransferService;
import com.yalovchuk.transfer.service.TransferServiceImpl;

public class TransferModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransferService.class).to(TransferServiceImpl.class);
        bind(TransferDao.class).to(TransferDaoImpl.class);
        bind(TransferPublisher.class).to(TransferPublisherImpl.class);
    }

    @Provides
    @Singleton
    public Gson gson() {
        return new Gson();
    }
}
