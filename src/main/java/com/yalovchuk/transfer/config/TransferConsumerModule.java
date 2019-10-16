package com.yalovchuk.transfer.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.yalovchuk.transfer.component.TransferConsumer;
import com.yalovchuk.transfer.component.TransferHandler;
import com.yalovchuk.transfer.component.TransferHandlerImpl;
import com.yalovchuk.transfer.client.AccountClient;
import com.yalovchuk.transfer.client.AccountClientImpl;

import java.util.concurrent.BlockingQueue;

public class TransferConsumerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TransferHandler.class).to(TransferHandlerImpl.class);
        bind(AccountClient.class).to(AccountClientImpl.class);
    }

    @Provides
    public TransferConsumer transferConsumer(BlockingQueue<String> queue, TransferHandler transferHandler, Gson gson) {
        return new TransferConsumer(queue, transferHandler, gson);
    }
}
