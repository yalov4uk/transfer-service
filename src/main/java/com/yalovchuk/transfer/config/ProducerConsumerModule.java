package com.yalovchuk.transfer.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerModule extends AbstractModule {

    @Provides
    @Singleton
    public BlockingQueue<String> queue() {
        return new ArrayBlockingQueue<>(100);
    }
}
