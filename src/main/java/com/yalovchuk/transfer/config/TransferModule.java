package com.yalovchuk.transfer.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.yalovchuk.transfer.service.TransferService;
import com.yalovchuk.transfer.service.TransferServiceImpl;

public class TransferModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Gson.class).toInstance(new Gson());

        bind(TransferService.class).to(TransferServiceImpl.class);
    }
}
