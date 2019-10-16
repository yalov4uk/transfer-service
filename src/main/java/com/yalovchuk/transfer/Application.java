package com.yalovchuk.transfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yalovchuk.transfer.config.ProducerConsumerModule;
import com.yalovchuk.transfer.config.TransferModule;
import com.yalovchuk.transfer.config.TransferRoute;
import com.yalovchuk.transfer.component.TransferConsumer;
import com.yalovchuk.transfer.config.TransferConsumerModule;

public class Application {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TransferModule(), new TransferConsumerModule(),
                new ProducerConsumerModule());

        TransferRoute transferRoute = injector.getInstance(TransferRoute.class);
        transferRoute.configure();

        startConsumers(injector);
    }

    private static void startConsumers(Injector injector) {
        for (int i = 0; i < 2; i++) {
            new Thread(injector.getInstance(TransferConsumer.class)).start();
        }
    }
}
