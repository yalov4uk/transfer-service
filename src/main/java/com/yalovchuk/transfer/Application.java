package com.yalovchuk.transfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yalovchuk.transfer.config.TransferModule;
import com.yalovchuk.transfer.config.TransferRoute;

public class Application {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TransferModule());

        TransferRoute transferRoute = injector.getInstance(TransferRoute.class);
        transferRoute.configure();
    }
}
