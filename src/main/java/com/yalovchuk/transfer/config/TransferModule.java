package com.yalovchuk.transfer.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;

public class TransferModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Gson.class).toInstance(new Gson());
    }
}
